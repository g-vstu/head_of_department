package com.vstu.department.service;

import com.vstu.department.dto.statistics.EmployeeStatisticsDTO;
import com.vstu.department.exception.BusinessEntityNotFoundException;
import com.vstu.department.model.*;
import com.vstu.department.model.enums.ParameterGroupType;
import com.vstu.department.repository.*;
import com.vstu.department.util.UtilService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.PropertyTemplate;
import org.apache.poi.util.TempFile;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExcelDepartmentServiceImpl {

    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private final ParameterRepository parameterRepository;

    @Autowired
    private final StatisticsService statisticsService;

    @Autowired
    private final DepartmentRepository departmentRepository;

    @Autowired
    private final AnketaRepository anketaRepository;

    @Autowired
    private final EmployeeParameterRepository employeeParameterRepository;

    private static final String param = "Критерий", Fio = "ФИО", department = "Кафедра", value = "Значение критерия";
    private static final String XLSX_HEADER = "application/vnd.ms-excel";

    public ResponseEntity<Resource> departmentReport(String halfYear) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        createAndFillSheet(workbook, halfYear);

//        workbook.write(new FileOutputStream("departmentReport.xlsx"));
//        workbook.close();

        File tempFile = TempFile.createTempFile("departmentReport", ".xlsx");

        workbook.write(new FileOutputStream(tempFile));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + String.format("Report (%s)", halfYear) + ".xlsx" + "\"")
                .header("content-type", XLSX_HEADER).body(new UrlResource(tempFile.toURI()));
    }

    private void createAndFillSheet(XSSFWorkbook workbook, String halfYear) {
        XSSFSheet sheet = workbook.createSheet("department");

        int rowNum = 1, columnNum = 0, maxColumn = 0;
        Row row = sheet.createRow(0);

        Cell cell = row.createCell(columnNum++);
        cell.setCellValue(param);

        CellStyle style = workbook.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setWrapText(true);
        cell.setCellStyle(style);

        CellStyle fioStyle = workbook.createCellStyle();
        fioStyle.setRotation((short) 90);
        fioStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        fioStyle.setAlignment(HorizontalAlignment.CENTER);


        sheet.setColumnWidth(columnNum, 1400);
        row.setRowNum(0);
        cell = row.createCell(columnNum);
        cell.setCellValue(department);
        cell.setCellStyle(style);
        row = sheet.createRow(1);
        cell = row.createCell(columnNum);
        cell.setCellValue(Fio);
        cell.setCellStyle(fioStyle);

        List<Employee> employees = employeeRepository.findAll();

        for (int i = 0; i <= 1; i++) {
            row = sheet.getRow(i);
            columnNum = 1;
            for (Employee employee : employees) {
                cell = row.createCell(++columnNum);
                sheet.setColumnWidth(columnNum, 1100);
                if (row.getRowNum() == 0) {
                    cell.setCellStyle(style);
                    cell.setCellValue(employee.getDepartment().getDisplayName());
                } else if (row.getRowNum() == 1) {
                    cell.setCellValue(employee.getFio());
                    cell.setCellStyle(fioStyle);
                }
            }
        }
        maxColumn = columnNum;

        CellStyle styleParam = workbook.createCellStyle();
        styleParam.setWrapText(true);

        List<Parameter> parameters = parameterRepository.findAll().stream().sorted(Comparator.
                comparing(PersistentEntity::getId)).collect(Collectors.toList());

        for (Parameter parameter : parameters) {
            row = sheet.createRow(++rowNum);
            cell = row.createCell(0);
            cell.setCellValue(parameter.getName());
            sheet.setColumnWidth(0, 2200);
            cell.setCellStyle(styleParam);
        }

        row = sheet.getRow(2);
        cell = row.createCell(1);
        CellStyle styleValue = workbook.createCellStyle();
        styleValue.cloneStyleFrom(fioStyle);
        cell.setCellStyle(styleValue);
        cell.setCellValue(value);

        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(2, rowNum, 1, 1));

        PropertyTemplate propertyTemplate = new PropertyTemplate();
        propertyTemplate.drawBorders(new CellRangeAddress(0, rowNum, 0, maxColumn),
                BorderStyle.THIN, BorderExtent.ALL);
        propertyTemplate.applyBorders(sheet);

        rowNum = 2;
        Cell cellParam;
        for (Parameter parameter : parameters) {
            row = sheet.getRow(rowNum++);
            columnNum = 2;
            for (Employee employee : employees) {
                cellParam = row.createCell(columnNum++);
                cellParam.setCellValue(sum(employee, halfYear, parameter));
                cellParam.setCellStyle(fioStyle);
            }
        }
    }

    private double sum(Employee employee, String halfYear, Parameter parameter) {
        Optional<Anketa> anketa = anketaRepository.findByTabelAndHalfYear(employee.getTabel(), halfYear);

        List<EmployeeStatisticsDTO> statisticsDTOAll = new ArrayList();

        List<EmployeeParameter> statisticsDTOStudy = employeeParameterRepository.
                getParametersBySearch(ParameterGroupType.STUDY, Collections.singletonList(halfYear), employee.getTabel());
        List<EmployeeParameter> statisticsDTOScience = employeeParameterRepository.
                getParametersBySearch(ParameterGroupType.SCIENCE, Collections.singletonList(halfYear), employee.getTabel());
        List<EmployeeParameter> statisticsDTOOther = employeeParameterRepository.
                getParametersBySearch(ParameterGroupType.OTHER, Collections.singletonList(halfYear), employee.getTabel());
        //добавить другие типы параметров
        statisticsDTOStudy.forEach(studyDTO -> {
            statisticsDTOAll.add(new EmployeeStatisticsDTO(studyDTO.getParameter().getName(), studyDTO.getCount() * studyDTO.getCoefficient()));
            ;
        });

        statisticsDTOAll.forEach(allDTO -> {
            statisticsDTOScience.forEach(scienceDTO -> {
                if (allDTO.getTabel().equals(scienceDTO.getAnketa().getTabel())) {
                    allDTO.setFullSum(allDTO.getFullSum() + scienceDTO.getCount() * scienceDTO.getCoefficient());
                }
            });
            statisticsDTOOther.forEach(otherDTO -> {
                if (allDTO.getTabel().equals(otherDTO.getAnketa().getTabel())) {
                    allDTO.setFullSum(allDTO.getFullSum() + otherDTO.getCount() * otherDTO.getCoefficient());
                }
            });
        });
        for (EmployeeStatisticsDTO employeeStatisticsDTO : statisticsDTOAll) {
            if (employeeStatisticsDTO.getTabel().equals(parameter.getName())) {
                return employeeStatisticsDTO.getFullSum();
            }
        }
        return 0;
    }
}
