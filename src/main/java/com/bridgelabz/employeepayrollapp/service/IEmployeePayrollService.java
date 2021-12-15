package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.model.Employee;

import java.util.List;

public interface IEmployeePayrollService {
    List<EmployeeDto> getAllEmployee();

    EmployeeDto getEmployeeById(int empId);

    String addEmployee(EmployeeDto empPayrollDto);

    String updateEmployee(int id, EmployeeDto employeeDto);

    String deleteEmployee(int empId);
}
