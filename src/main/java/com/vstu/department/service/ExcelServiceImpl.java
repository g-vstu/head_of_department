package com.vstu.department.service;

import com.vstu.department.dto.statistics.EmployeeStatisticsDTO;
import com.vstu.department.exception.BusinessEntityNotFoundException;
import com.vstu.department.model.enums.ParameterGroupType;
import com.vstu.department.repository.DepartmentRepository;
import com.vstu.department.repository.EmployeeRepository;
import com.vstu.department.util.UtilService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.PropertyTemplate;
import org.apache.poi.util.TempFile;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExcelServiceImpl {

    private static final String XLSX_HEADER = "application/vnd.ms-excel";

    private static final String PARAM = "КРИТЕРИЙ", FIO = "ФИО",
            SCORE = "БАЛЛ", FONT_NAME = "Times New Roman", POSITION = "Должность", CATHEDRA = "КАФЕДРА";

    private static final int COLUMN_FIO_WIDTH = 6000, FONT_HEIGHT = 12, COLUMN_PARAM_WIDTH = 6000, COLUMN_CATHEDRA_WIDTH = 3200;

    final StatisticsService statisticsService;

    final EmployeeRepository employeeRepository;

    final DepartmentRepository departmentRepository;

    final ParameterService parameterService;

    public ResponseEntity<Resource> generateViceRectorReport(String halfYear, Long parameterId) throws IOException {
        File tempFile = TempFile.createTempFile("poi-sxssf-template", ".xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        createAndFillSheets(workbook, halfYear, parameterId);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            workbook.write(fos);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + String.format("Report (%s)", halfYear) + ".xlsx" + "\"")
                    .header("content-type", XLSX_HEADER).body(new UrlResource(tempFile.toURI()));
        }
    }

    private void addHeader(XSSFWorkbook workbook, XSSFSheet sheet) {
        XSSFRow columnNameRow = sheet.createRow(0);
        XSSFCell fioCell = columnNameRow.createCell(0);
        fioCell.setCellValue(FIO);
        fioCell.setCellStyle(createStyle(workbook));
        sheet.setColumnWidth(0, COLUMN_FIO_WIDTH);
        XSSFCell scoreCell = columnNameRow.createCell(1);
        scoreCell.setCellValue(SCORE);
        scoreCell.setCellStyle(createStyle(workbook));
        XSSFCell parameterCell = columnNameRow.createCell(2);
        parameterCell.setCellValue(PARAM);
        parameterCell.setCellStyle(createStyle(workbook));
        sheet.setColumnWidth(2, COLUMN_PARAM_WIDTH);
    }

    private void createAndFillSheets(XSSFWorkbook workbook, String halfYear, Long parameterId) {

        String tabelHead = (String) UtilService.getFieldFromAuthentificationDetails("tabel");

        Map<String, XSSFSheet> departments = new HashMap();

        List<EmployeeStatisticsDTO> statisticsDTOByParameter = statisticsService.getEmployeeParameterStats(tabelHead, parameterId, halfYear);

        statisticsDTOByParameter.sort((a, b) -> b.getFullSum().compareTo(a.getFullSum()));

        departmentRepository.findAll().forEach(currDepartment -> {
            XSSFSheet sheet = workbook.createSheet(currDepartment.getDisplayName());
            addHeader(workbook, sheet);
            departments.put(currDepartment.getName(), sheet);
        });

        CellStyle cellStyle = createStyle(workbook);
        cellStyle.setRotation((short) 90);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);

        String parameterName = parameterService.findByIdNotNull(parameterId).getName();

        departments.forEach((k, v) -> {
            statisticsDTOByParameter.forEach(currDTO -> {
                if (currDTO.getTabel().replaceAll("[^A-Z,a-z]", "").equals(k)) {
                    addStringsToTable(workbook, v, currDTO);
                }
            });
        });

        departments.values().forEach(rows -> {
            XSSFCell cell = rows.getRow(1).createCell(2);
            PropertyTemplate propertyTemplate = new PropertyTemplate();
            propertyTemplate.drawBorders(new CellRangeAddress(1, rows.getLastRowNum(), 2, 2),
                    BorderStyle.THIN, BorderExtent.ALL);
            propertyTemplate.applyBorders(rows);
            rows.addMergedRegion(new CellRangeAddress(1, rows.getLastRowNum(), 2, 2));
            cell.setCellValue(parameterName);
            cell.setCellStyle(cellStyle);

        });

        XSSFSheet sheet = workbook.createSheet("Итоговая");
        addHeaderForStrangeInfo(workbook, sheet);
        //departments.put("strangeInfo", sheet);
        //departments.forEach((k, v) -> {
        statisticsDTOByParameter.forEach(currDTO -> {
            addStringsToStrangeTable(workbook, sheet, currDTO);
        });

        XSSFCell cell = sheet.getRow(1).createCell(4);
        PropertyTemplate propertyTemplate = new PropertyTemplate();
        propertyTemplate.drawBorders(new CellRangeAddress(1, sheet.getLastRowNum(), 4, 4),
                BorderStyle.THIN, BorderExtent.ALL);
        propertyTemplate.applyBorders(sheet);
        sheet.addMergedRegion(new CellRangeAddress(1, sheet.getLastRowNum(), 4, 4));
        cell.setCellValue(parameterName);
        cell.setCellStyle(cellStyle);

    }

    private void addStringsToStrangeTable(XSSFWorkbook workbook, XSSFSheet sheet, EmployeeStatisticsDTO dto) {
        int columnNumber = 0;
        XSSFRow row = findFreeRow(sheet, columnNumber);
        XSSFCell fioCell = row.createCell(columnNumber);
        fioCell.setCellValue(employeeRepository.findByTabel(dto.getTabel())
                .orElseThrow(() -> new BusinessEntityNotFoundException("Tabel not found")).getFio());
        fioCell.setCellStyle(createStyle(workbook));

        XSSFCell positionCell = row.createCell(columnNumber + 1);
        positionCell.setCellValue(String.valueOf(employeeRepository.findByTabel(dto.getTabel())
                .orElseThrow(() -> new BusinessEntityNotFoundException("Tabel not found")).getPosition().getName()));
        positionCell.setCellStyle(createStyle(workbook));

        XSSFCell cathedraCell = row.createCell(columnNumber + 2);
        cathedraCell.setCellValue(String.valueOf(employeeRepository.findByTabel(dto.getTabel())
                .orElseThrow(() -> new BusinessEntityNotFoundException("Tabel not found")).getDepartment().getDisplayName()));
        cathedraCell.setCellStyle(createStyle(workbook));

        XSSFCell scoreCell = row.createCell(columnNumber + 3);
        scoreCell.setCellValue(dto.getFullSum());
        scoreCell.setCellStyle(createStyle(workbook));
    }

    private void addHeaderForStrangeInfo(XSSFWorkbook workbook, XSSFSheet sheet) {
        XSSFRow columnNameRow = sheet.createRow(0);

        XSSFCell fioCell = columnNameRow.createCell(0);
        fioCell.setCellValue(FIO);
        fioCell.setCellStyle(createStyle(workbook));
        sheet.setColumnWidth(0, COLUMN_FIO_WIDTH);

        XSSFCell positionCell = columnNameRow.createCell(1);
        positionCell.setCellValue(POSITION);
        positionCell.setCellStyle(createStyle(workbook));
        sheet.setColumnWidth(1, COLUMN_FIO_WIDTH);

        XSSFCell cathedraCell = columnNameRow.createCell(2);
        cathedraCell.setCellValue(CATHEDRA);
        cathedraCell.setCellStyle(createStyle(workbook));
        sheet.setColumnWidth(2, COLUMN_CATHEDRA_WIDTH);

        XSSFCell scoreCell = columnNameRow.createCell(3);
        scoreCell.setCellValue(SCORE);
        scoreCell.setCellStyle(createStyle(workbook));

        XSSFCell paramCell = columnNameRow.createCell(4);
        paramCell.setCellValue(PARAM);
        paramCell.setCellStyle(createStyle(workbook));
        sheet.setColumnWidth(4, COLUMN_PARAM_WIDTH);
    }

    private void addStringsToTable(XSSFWorkbook workbook, XSSFSheet sheet, EmployeeStatisticsDTO dto) {
        int columnNumber = 0;
        XSSFRow row = findFreeRow(sheet, columnNumber);
        XSSFCell fioCell = row.createCell(columnNumber);
        fioCell.setCellValue(employeeRepository.findByTabel(dto.getTabel())
                .orElseThrow(() -> new BusinessEntityNotFoundException("Tabel not found")).getFio());
        fioCell.setCellStyle(createStyle(workbook));
        XSSFCell scoreCell = row.createCell(columnNumber + 1);
        scoreCell.setCellValue(dto.getFullSum());
        scoreCell.setCellStyle(createStyle(workbook));
    }

    private XSSFRow findFreeRow(XSSFSheet sheet, int columnNumber) {
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            if (sheet.getRow(i).getCell(columnNumber) == null)
                return sheet.getRow(i);
        }
        return sheet.createRow(sheet.getPhysicalNumberOfRows());
    }

    private XSSFCellStyle createStyle(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        XSSFCellStyle style = workbook.createCellStyle();
        font.setFontHeightInPoints((short) FONT_HEIGHT);
        font.setFamily(FontFamily.ROMAN);
        font.setFontName(FONT_NAME);
        style.setFont(font);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        return style;
    }

}
