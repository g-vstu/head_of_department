package by.vstu.department.controller;

import by.vstu.department.dto.EmployeeDTO;
import by.vstu.department.exception.BusinessException;
import by.vstu.department.model.Anketa;
import by.vstu.department.service.AnketaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

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
    public Anketa getAnketa(@PathVariable UUID id) {
        return anketaService.get(id);
    }

    @GetMapping("/employee/{tabel}")
    public Anketa getAnketaByTabel(@PathVariable String tabel) {
        return anketaService.getByTabel(tabel);
    }
}
