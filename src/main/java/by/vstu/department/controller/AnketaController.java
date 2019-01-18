package by.vstu.department.controller;

import by.vstu.department.dto.AnketaDTO;
import by.vstu.department.dto.EmployeeDTO;
import by.vstu.department.dto.EmployeeParameterDTO;
import by.vstu.department.service.AnketaService;
import by.vstu.department.util.UtilService;
import by.vstu.department.util.ValidList;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/anketa")
public class AnketaController {

    private final AnketaService anketaService;

    @GetMapping
    @PreAuthorize("hasRole('DEP_HEAD')")
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
    @PreAuthorize("hasRole('DEP_HEAD')")
    public AnketaDTO getAnketaByTabel(@PathVariable String tabel,
                                      @Size(max = 6, min = 6) @RequestParam String halfYear) {
        return anketaService.getByTabelAndHalfYear(tabel, halfYear);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('DEP_HEAD')")
    public AnketaDTO updateAnketa(@PathVariable Long id, @Valid @RequestBody ValidList<EmployeeParameterDTO> parameters) {
        return anketaService.updateAnketa(id, parameters);
    }
}
