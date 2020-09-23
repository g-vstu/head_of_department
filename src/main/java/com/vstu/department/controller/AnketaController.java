package com.vstu.department.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vstu.department.dto.AnketaDTO;
import com.vstu.department.dto.EmployeeDTO;
import com.vstu.department.dto.EmployeeParameterDTO;
import com.vstu.department.service.AnketaService;
import com.vstu.department.util.UtilService;
import com.vstu.department.util.ValidList;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/anketa")
public class AnketaController {

    private final AnketaService anketaService;

    @GetMapping
    @PreAuthorize("hasAnyRole('DEP_HEAD', 'VICE-RECTOR')")
    public List<EmployeeDTO> getEmployeesByHead() {
        String tabelHead = (String) UtilService.getFieldFromAuthentificationDetails("tabel");
        return anketaService.getEmployeeByHeadTabel(tabelHead);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('DEP_HEAD')")
    public AnketaDTO getAnketa(@PathVariable Long id) {
        return anketaService.get(id);
    }

    @GetMapping("/employee/{tabel}")
    @PreAuthorize("hasAnyRole('DEP_HEAD', 'TEACHER')")
    public AnketaDTO getAnketaByTabel(@PathVariable String tabel,
            @Size(max = 6, min = 6) @RequestParam String halfYear) {
        return anketaService.getByTabelAndHalfYear(tabel, halfYear);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('DEP_HEAD', 'TEACHER')")
    public AnketaDTO updateAnketa(@PathVariable Long id,
            @Valid @RequestBody ValidList<EmployeeParameterDTO> parameters) {
        return anketaService.updateAnketa(id, parameters);
    }
}
