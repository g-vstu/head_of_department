package com.vstu.department.controller;

import java.io.IOException;

import com.vstu.department.service.ExcelDepartmentServiceImpl;
import com.vstu.department.service.ExcelServiceImpl;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vstu.department.service.ReportService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReportController {

    ReportService reportService;

    ExcelServiceImpl excelService;

    ExcelDepartmentServiceImpl excelDepartmentService;

    @GetMapping("/forViceRector")
    @PreAuthorize("hasRole('VICE-RECTOR')")
    public @ResponseBody
    ResponseEntity<Resource> generateViceRectorReport(@RequestParam String halfYear)
            throws IOException {
        return reportService.generateViceRectorReport(halfYear);
    }

//    @GetMapping("/forDepHead")
//    @PreAuthorize("hasRole('DEP_HEAD')")
//    public @ResponseBody ResponseEntity<Resource> generateDepHeadReport(@RequestParam String halfYear)
//            throws IOException {
//        return reportService.generateDepHeadReport(halfYear);
//    }

    //Измененный
    @GetMapping("/forDepHead")
    @PreAuthorize("hasRole('DEP_HEAD')")
    public @ResponseBody
    ResponseEntity<Resource> generateDepHeadReport(@RequestParam String halfYear)
            throws IOException {
        return excelDepartmentService.departmentReport(halfYear);
    }

    @GetMapping("/forViceRector/byParam")
    @PreAuthorize("hasRole('VICE-RECTOR')")
    public @ResponseBody
    ResponseEntity<Resource> generateViceRectorReport(@RequestParam String halfYear, @RequestParam Long parameterId)
            throws IOException {
        return excelService.generateViceRectorReport(halfYear, parameterId);
    }
}
