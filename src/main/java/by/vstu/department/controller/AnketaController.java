package by.vstu.department.controller;

import by.vstu.department.dto.EmployeeDTO;
import by.vstu.department.service.AnketaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/anketa")
public class AnketaController {

    private final AnketaService anketaService;

    @GetMapping("/{tabel}")
    public List<EmployeeDTO> getEmployeesByHead(@PathVariable String tabel) {
        return anketaService.getEmployeeByHeadTabel(tabel);
    }
}
