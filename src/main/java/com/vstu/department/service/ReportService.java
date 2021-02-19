package com.vstu.department.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FontFamily;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.TempFile;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vstu.department.dto.statistics.EmployeeStatisticsDTO;
import com.vstu.department.exception.BusinessEntityNotFoundException;
import com.vstu.department.model.enums.ParameterGroupType;
import com.vstu.department.repository.DepartmentRepository;
import com.vstu.department.repository.EmployeeRepository;
import com.vstu.department.util.UtilService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportService {

    private static final String XLSX_HEADER = "application/vnd.ms-excel";

    private static final String STUDY = "Учеба", SCIENCE = "Наука", OTHER = "Другое",
            /* GENERAL = "Общее", */ FIO = "ФИО", SCORE = "БАЛЛ", FONT_NAME = "Times New Roman";

    private static final int COLUMN_FIO_WIDTH = 6000, FONT_HEIGHT = 12;

    private static XSSFWorkbook workbook = new XSSFWorkbook();

    final StatisticsService statisticsService;

    final EmployeeRepository employeeRepository;

    final DepartmentRepository departmentRepository;

    public ResponseEntity<Resource> generateViceRectorReport(String halfYear) throws IOException {
        File tempFile = TempFile.createTempFile("poi-sxssf-template", ".xlsx");
        createAndFillSheets(workbook, halfYear);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            workbook.write(fos);
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + String.format("Report (%s)", halfYear) + ".xlsx" + "\"")
                .header("content-type", XLSX_HEADER).body(new UrlResource(tempFile.toURI()));
    }

    private void addHeader(XSSFSheet sheet) {
        XSSFRow tableNameRow = sheet.createRow(0);
        tableNameRow.createCell(0).setCellValue(STUDY);
        tableNameRow.createCell(3).setCellValue(SCIENCE);
        tableNameRow.createCell(6).setCellValue(OTHER);
//        tableNameRow.createCell(9).setCellValue(GENERAL);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 4));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 6, 7));
//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 9, 10));
        XSSFRow columnNameRow = sheet.createRow(1);
        for (int i = 0, j = 1; i <= 7/* 10 */; j = (i = i + 3) + 1) {
            XSSFCell fioCell = columnNameRow.createCell(i);
            fioCell.setCellValue(FIO);
            fioCell.setCellStyle(createStyle(workbook));
            sheet.setColumnWidth(i, COLUMN_FIO_WIDTH);
            XSSFCell scoreCell = columnNameRow.createCell(j);
            scoreCell.setCellValue(SCORE);
            scoreCell.setCellStyle(createStyle(workbook));
        }
    }

    private void createAndFillSheets(XSSFWorkbook workbook, String halfYear) {

        String tabelHead = (String) UtilService.getFieldFromAuthentificationDetails("tabel");

        Map<String, XSSFSheet> departments = new HashMap();

        List<EmployeeStatisticsDTO> statisticsDTOStudy = statisticsService
                .getEmployeeParameterStats(tabelHead, ParameterGroupType.STUDY, halfYear).getUserStatistics();

        List<EmployeeStatisticsDTO> statisticsDTOScience = statisticsService
                .getEmployeeParameterStats(tabelHead, ParameterGroupType.SCIENCE, halfYear).getUserStatistics();

        List<EmployeeStatisticsDTO> statisticsDTOOther = statisticsService
                .getEmployeeParameterStats(tabelHead, ParameterGroupType.OTHER, halfYear).getUserStatistics();

        statisticsDTOStudy.sort((a, b) -> b.getFullSum().compareTo(a.getFullSum()));
        statisticsDTOScience.sort((a, b) -> b.getFullSum().compareTo(a.getFullSum()));
        statisticsDTOOther.sort((a, b) -> b.getFullSum().compareTo(a.getFullSum()));

        departmentRepository.findAllUniq().forEach(currDepartment -> {
            XSSFSheet sheet = workbook.createSheet(currDepartment.getDisplayName());
            addHeader(sheet);
            departments.put(currDepartment.getName(), sheet);
        });

        departments.forEach((k, v) -> {
            statisticsDTOStudy.forEach(currDTO -> {
                if (currDTO.getTabel().replaceAll("[^A-Z,a-z]", "").equals(k)) {
                    addStringsToTable(v, currDTO, ParameterGroupType.STUDY);
                }
            });
            statisticsDTOScience.forEach(currDTO -> {
                if (currDTO.getTabel().replaceAll("[^A-Z,a-z]", "").equals(k)) {
                    addStringsToTable(v, currDTO, ParameterGroupType.SCIENCE);
                }
            });
            statisticsDTOOther.forEach(currDTO -> {
                if (currDTO.getTabel().replaceAll("[^A-Z,a-z]", "").equals(k)) {
                    addStringsToTable(v, currDTO, ParameterGroupType.OTHER);
                }
            });
        });
    }

    private void addStringsToTable(XSSFSheet sheet, EmployeeStatisticsDTO dto, ParameterGroupType type) {
        int columnNumber = type == ParameterGroupType.STUDY ? 0
                : type == ParameterGroupType.SCIENCE ? 3 : type == ParameterGroupType.OTHER ? 6 : 9;

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
