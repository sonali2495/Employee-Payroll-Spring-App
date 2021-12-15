package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.service.EmployeePayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {
    private static final String EMPLOYEE_ADDED_SUCCESSFULLY = "Employee Added Successfully";
    private static final String EMPLOYEE_UPDATED_SUCCESSFULLY = "Employee Updated Successfully";

    @Autowired
    private EmployeePayrollService employeePayrollService;

    @RequestMapping(value = {"", "/", "/employee"})
    public ResponseEntity<String> getEmployeePayollData() {
        return new ResponseEntity(employeePayrollService.getAllEmployee(), HttpStatus.OK);
    }

    @GetMapping("/employee/{empId}")
    public ResponseEntity<String> getEmployeePayrollData(@PathVariable("empId") int empId) {
        return new ResponseEntity(employeePayrollService.getEmployeeById(empId), HttpStatus.OK);
    }

    @PostMapping("/employee")
    public ResponseEntity<String> addEmployeePayrollData(@RequestBody EmployeeDto employeeDto) {
        employeePayrollService.addEmployee(employeeDto);
        return new ResponseEntity<>(EMPLOYEE_ADDED_SUCCESSFULLY, HttpStatus.OK);    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<String> updateEmployeePayrollData(@PathVariable(value = "id") int id,
                                                            @RequestBody EmployeeDto EmployeeDto) {
        employeePayrollService.updateEmployee(id, EmployeeDto);
        return new ResponseEntity<>(EMPLOYEE_UPDATED_SUCCESSFULLY, HttpStatus.OK);    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployeePayrollData(@PathVariable("id") int id) {
        return new ResponseEntity<>(employeePayrollService.deleteEmployee(id), HttpStatus.OK);
    }
}
