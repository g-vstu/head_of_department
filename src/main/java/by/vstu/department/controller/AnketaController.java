package by.vstu.department.controller;

import by.vstu.department.dto.AnketaDTO;
import by.vstu.department.dto.EmployeeDTO;
import by.vstu.department.dto.EmployeeParameterDTO;
import by.vstu.department.exception.BusinessException;
import by.vstu.department.service.AnketaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/anketa")
public class AnketaController {

    private final AnketaService anketaService;

    @GetMapping
    public List<EmployeeDTO> getEmployeesByHead(Principal principal) {
        try {
            String tabelHead = principal.getName().split(",")[1];
            return anketaService.getEmployeeByHeadTabel(tabelHead);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BusinessException("Teapot, use another endpoint");
        }
    }

    @GetMapping("/{id}")
    public AnketaDTO getAnketa(@PathVariable Long id) {
        return anketaService.get(id);
    }

    @GetMapping("/employee/{tabel}")
    public AnketaDTO getAnketaByTabel(@PathVariable String tabel) {
        return anketaService.getByTabel(tabel);
    }

    @PutMapping("/{id}")
    public AnketaDTO updateAnketa(@PathVariable Long id, @RequestBody @Valid List<EmployeeParameterDTO> parameters) {
        return anketaService.updateAnketa(id, parameters);
    }
}
