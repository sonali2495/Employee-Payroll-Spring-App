package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.model.Employee;

import java.util.List;

public interface IEmployeePayrollService {
    List<EmployeeDto> getAllEmployee();

    Employee getEmployeeById(int empId);

    Employee addEmployee(EmployeeDto empPayrollDto);

    Employee updateEmployee(int id, EmployeeDto employeeDto);

    String deleteEmployee(int empId);
}
