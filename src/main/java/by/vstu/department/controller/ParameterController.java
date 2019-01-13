package by.vstu.department.controller;

import by.vstu.department.dto.ParameterDTO;
import by.vstu.department.service.ParameterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Employee Management System", description = "Operations pertaining to employee in Employee Management System")
@RestController
@RequiredArgsConstructor
@RequestMapping("/parameter")
public class ParameterController {

    private final ParameterService service;

    @ApiOperation(value = "View a list of available employees", response = List.class)
    @GetMapping
    public Page<ParameterDTO> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/group/{id}")
    public List<ParameterDTO> findByGroupType(@PathVariable Long id) {
        return service.findByGroup(id);
    }
}
