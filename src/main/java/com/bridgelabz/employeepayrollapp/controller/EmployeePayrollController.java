package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.service.EmployeePayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Purpose: to demonstrate different http methods
 *
 * @author : Sonali G
 * @since : 13-12-2021
 */
@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {

    @Autowired
    private EmployeePayrollService employeePayrollService;

    /**
     * Purpose : To get the list of all employee from repository
     *
     * @return : list of all employee
     */
    @RequestMapping(value = {"", "/", "/employee"})
    public List<EmployeeDto> getEmployeePayrollData() {
        return employeePayrollService.getAllEmployee();
    }

    /**
     * Purpose : To Get employee From id
     *
     * @param empId to find the id in repository
     * @return dto of given id
     */
    @GetMapping("/employee/{empId}")
    public EmployeeDto getEmployeePayrollData(@PathVariable("empId") int empId) {
        return employeePayrollService.getEmployeeById(empId);
    }

    /**
     * Purpose : To Add employee in Repo
     *
     * @param employeeDto : this metadata gets added to repo
     * @return : success message for add
     */
    @PostMapping("/employee")
    public String addEmployeePayrollData(@Valid @RequestBody EmployeeDto employeeDto) {
        return employeePayrollService.addEmployee(employeeDto);
    }

    /**
     * Purpose : To Update/Edit Existing Employee data
     *
     * @param id          for search data in repo
     * @param employeeDto for changing existing data to new
     * @return success message for update
     */
    @PutMapping("/employee/{id}")
    public String updateEmployeePayrollData(@PathVariable(value = "id") int id, @RequestBody EmployeeDto employeeDto) {
        return employeePayrollService.updateEmployee(id, employeeDto);
    }

    /**
     * Purpose : To Delete Existing Employee
     *
     * @param id For Search in repo
     * @return success message for delete
     */
    @DeleteMapping("/employee/{id}")
    public String deleteEmployeePayrollData(@PathVariable("id") int id) {
        return employeePayrollService.deleteEmployee(id);
    }
}
