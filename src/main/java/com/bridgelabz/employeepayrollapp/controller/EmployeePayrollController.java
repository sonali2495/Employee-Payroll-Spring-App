package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.service.EmployeePayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {

    @Autowired
    private EmployeePayrollService employeePayrollService;

    @RequestMapping(value = {"", "/", "/employee"})
    public List<EmployeeDto> getEmployeePayrollData() {
        return employeePayrollService.getAllEmployee();
    }

    @GetMapping("/employee/{empId}")
    public EmployeeDto getEmployeePayrollData(@PathVariable("empId") int empId) {
        return employeePayrollService.getEmployeeById(empId);
    }

    @PostMapping("/employee")
    public String addEmployeePayrollData(@Valid @RequestBody EmployeeDto employeeDto) {
        return employeePayrollService.addEmployee(employeeDto);
    }

    @PutMapping("/employee/{id}")
    public String updateEmployeePayrollData(@PathVariable(value = "id") int id, @RequestBody EmployeeDto EmployeeDto) {
        return employeePayrollService.updateEmployee(id, EmployeeDto);
    }

    @DeleteMapping("/employee/{id}")
    public String deleteEmployeePayrollData(@PathVariable("id") int id) {
        return employeePayrollService.deleteEmployee(id);
    }
}
