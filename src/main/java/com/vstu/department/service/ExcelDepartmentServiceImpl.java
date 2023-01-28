package com.vstu.department.service;

import com.vstu.department.dto.statistics.EmployeeStatisticsDTO;
import com.vstu.department.dto.statistics.StatisticsDTO;
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
import org.apache.regexp.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

    private static final String param = "Критерий", Fio = "ФИО", department = "Кафедра", value = "Значение критерия", general = "Общее";
    private static final String XLSX_HEADER = "application/vnd.ms-excel";

    private CellStyle style, fioStyle;

    public ResponseEntity<Resource> departmentReport(String halfYear) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        createAndFillSheet(workbook, halfYear);

//        workbook.write(new FileOutputStream("departmentReport.xlsx"));
//        workbook.close();
//        return null;

        File tempFile = TempFile.createTempFile("Рейтинг_заведующих_кафедрой", ".xlsx");

        workbook.write(new FileOutputStream(tempFile));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + String.format("Report (%s)", halfYear) + ".xlsx" + "\"")
                .header("content-type", XLSX_HEADER).body(new UrlResource(tempFile.toURI()));
    }

    private void createAndFillSheet(XSSFWorkbook workbook, String halfYear) {
        XSSFSheet sheet = workbook.createSheet("department");
        createStyle(workbook);

        int rowNum = 1, columnNum = 0, maxColumn = 0;
        Row row = sheet.createRow(0);

        Cell cell = row.createCell(columnNum++);
        cell.setCellValue(param);
        cell.setCellStyle(style);

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
            sheet.setColumnWidth(0, 14200);
            cell.setCellStyle(styleParam);
        }

        row = sheet.getRow(2);
        cell = row.createCell(1);
        cell.setCellStyle(fioStyle);
        cell.setCellValue(value);

        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(2, rowNum, 1, 1));

        Cell cellParam;
        row = sheet.createRow(++rowNum);
        cell = row.createCell(0);
        cell.setCellValue(general);

        double fullSum;
        double[] depHeaderFullSum = new double[employees.size()];
        int i, k = 0;
        columnNum = 2;
        List<EmployeeStatisticsDTO> employeeStatisticsDTOS = new ArrayList<>();
        for (Employee employee : employees) {
            rowNum = 2;
            employeeStatisticsDTOS = sum(employee, halfYear);
            fullSum = 0;
            i = 0;
            for (EmployeeStatisticsDTO empStats : employeeStatisticsDTOS) {
                if (empStats.getTabel().equals(parameters.get(i++).getName())) {
                    row = sheet.getRow(rowNum++);
                    cellParam = row.createCell(columnNum);
                    cellParam.setCellValue(empStats.getFullSum());
                    cellParam.setCellStyle(fioStyle);
                    fullSum += empStats.getFullSum();
                }
            }
            row = sheet.getRow(rowNum++);
            cellParam = row.createCell(columnNum);
            cellParam.setCellValue(fullSum);
            cellParam.setCellStyle(fioStyle);
            depHeaderFullSum[k++] = fullSum;
            columnNum++;
        }

        createBorder(sheet, 0, rowNum, maxColumn);

        columnNum = 0;
        row = sheet.createRow(rowNum++);
        cell = row.createCell(columnNum);
        cell.setCellValue("");

        int rowRating = rowNum;
        row = sheet.createRow(rowNum++);
        cell = row.createCell(columnNum);
        cell.setCellValue("Обучение");
        row = sheet.createRow(rowNum++);
        cell = row.createCell(columnNum);
        cell.setCellValue("Наука");
        row = sheet.createRow(rowNum++);
        cell = row.createCell(columnNum);
        cell.setCellValue("Другое");
        row = sheet.createRow(rowNum++);
        cell = row.createCell(columnNum);
        cell.setCellValue(general);

        RestTemplate restTemplate = new RestTemplate();
        double[] values;
        double[] employeeFullSum = new double[employees.size()];
        columnNum = 2;
        k = 0;
        for (Employee employee : employees) {
            values = ratingDepartment(restTemplate, employee.getTabel(), halfYear);
            rowNum = rowRating;
            for (double v : values) {
                row = sheet.getRow(rowNum++);
                cell = row.createCell(columnNum);
                cell.setCellValue(v);
                cell.setCellStyle(fioStyle);
            }
            row = sheet.getRow(rowNum++);
            cell = row.createCell(columnNum);
            cell.setCellValue(Arrays.stream(values).sum());
            cell.setCellStyle(fioStyle);
            employeeFullSum[k++] = Arrays.stream(values).sum();
            columnNum++;
        }

        createBorder(sheet, rowRating, rowNum, maxColumn);

        columnNum = 0;
        row = sheet.createRow(rowNum++);
        cell = row.createCell(columnNum);
        cell.setCellValue("");

        rowRating = rowNum;
        row = sheet.createRow(rowNum);
        cell = row.createCell(columnNum);
        cell.setCellValue("Итого");

        columnNum = 2;
        for (int j = 0; j < employees.size(); j++) {
            row = sheet.getRow(rowNum);
            cell = row.createCell(columnNum);
            cell.setCellValue(depHeaderFullSum[j] + employeeFullSum[j]);
            cell.setCellStyle(fioStyle);
            columnNum++;
        }

        createBorder(sheet, rowRating, rowRating + 1, maxColumn);

    }

    private void createBorder(XSSFSheet sheet, int minRow, int maxRow, int maxColumn) {
        PropertyTemplate propertyTemplate = new PropertyTemplate();
        propertyTemplate.drawBorders(new CellRangeAddress(minRow, maxRow - 1, 0, maxColumn),
                BorderStyle.THIN, BorderExtent.ALL);
        propertyTemplate.applyBorders(sheet);
    }

    private double[] ratingDepartment(RestTemplate restTemplate, String tableHead, String halfYear) {
        StatisticsDTO study = restTemplate.exchange("http://localhost:8006/statistics?type=" + 1 + "&halfYear=" + halfYear + "&department=" + tableHead,
                HttpMethod.GET, getRequest(), StatisticsDTO.class).getBody();
        StatisticsDTO science = restTemplate.exchange("http://localhost:8006/statistics?type=" + 2 + "&halfYear=" + halfYear + "&department=" + tableHead,
                HttpMethod.GET, getRequest(), StatisticsDTO.class).getBody();
        StatisticsDTO other = restTemplate.exchange("http://localhost:8006/statistics?type=" + 3 + "&halfYear=" + halfYear + "&department=" + tableHead,
                HttpMethod.GET, getRequest(), StatisticsDTO.class).getBody();
        double[] arr = new double[3];
        arr[0] = study.getUserStatistics().stream().filter(a -> a.getTabel().equals(tableHead)).mapToDouble(EmployeeStatisticsDTO::getFullSum).findAny().getAsDouble();
        arr[1] = science.getUserStatistics().stream().filter(a -> a.getTabel().equals(tableHead)).mapToDouble(EmployeeStatisticsDTO::getFullSum).findAny().getAsDouble();
        arr[2] = other.getUserStatistics().stream().filter(a -> a.getTabel().equals(tableHead)).mapToDouble(EmployeeStatisticsDTO::getFullSum).findAny().getAsDouble();
        return arr;
    }

    private List<EmployeeStatisticsDTO> sum(Employee employee, String halfYear) {
        Optional<Anketa> anketa = anketaRepository.findByTabelAndHalfYear(employee.getTabel(), halfYear);

        List<EmployeeStatisticsDTO> statisticsDTOAll = new ArrayList<>();

        List<EmployeeParameter> statisticsDTOStudy = employeeParameterRepository.
                getParametersBySearch(ParameterGroupType.STUDY, Collections.singletonList(halfYear), employee.getTabel());
        statisticsDTOStudy = statisticsDTOStudy.stream().sorted(Comparator.comparing(o -> o.getParameter().getId())).collect(Collectors.toList());
        statisticsDTOStudy.forEach(studyDTO -> {
            statisticsDTOAll.add(new EmployeeStatisticsDTO(studyDTO.getParameter().getName(), studyDTO.getCount() * studyDTO.getCoefficient()));
        });
        return statisticsDTOAll;
    }

    private void createStyle(XSSFWorkbook workbook) {
        style = workbook.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setWrapText(true);

        fioStyle = workbook.createCellStyle();
        fioStyle.setRotation((short) 90);
        fioStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        fioStyle.setAlignment(HorizontalAlignment.CENTER);
    }

    private HttpEntity<Void> getRequest() {
        HttpHeaders headers = new HttpHeaders();
        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().
                getHeaders("Authorization").nextElement().split(" ")[1];
        headers.set("Authorization", "Bearer " + token);
        return new HttpEntity<>(headers);
    }

}
