package by.vstu.department.controller;

import by.vstu.department.dto.ParameterDTO;
import by.vstu.department.model.enums.ParameterGroupType;
import by.vstu.department.service.ParameterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parameter")
public class ParameterController {

    private final ParameterService service;

    @GetMapping
    public Page<ParameterDTO> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/group/{id}")
    public List<ParameterDTO> findByGroup(@PathVariable Long id) {
        return service.findByGroup(id);
    }

    @GetMapping("/type/{id}")
    public List<ParameterDTO> findByGroupType(@PathVariable int id) {
        return service.findByGroupType(ParameterGroupType.getByIndex(id));
    }
}
