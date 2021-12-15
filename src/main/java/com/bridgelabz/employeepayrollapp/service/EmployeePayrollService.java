package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.model.Employee;
import com.bridgelabz.employeepayrollapp.repository.EmployeePayrollRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeePayrollService<AtmConfiguration> implements IEmployeePayrollService{

    private static final String EMPLOYEE_ADDED_SUCCESSFULLY = "Employee Added Successfully";
    private static final String EMPLOYEE_UPDATED_SUCCESSFULLY = "Employee Updated Successfully";
    private static final String EMPLOYEE_DELETED_SUCCESSFULLY = "Employee Deleted Successfully";

    @Autowired
    private EmployeePayrollRepository employeeRepo;
    @Autowired
    private AtmConfiguration atmConfiguration;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EmployeeDto> getAllEmployee() {
        return employeeRepo.findAll()
                .stream()
                .map(Employee -> modelMapper.map(Employee, EmployeeDto.class))
                .collect((Collectors.toList()));
    }

    @Override
    public Employee getEmployeeById(int empId) {
        checkIdPresentOrNot(empId);
        return employeeRepo.findById(empId).get();
    }

    @Override
    public String addEmployee(EmployeeDto empPayrollDTO) {
        Employee employee = modelMapper.map(empPayrollDTO, Employee.class);
        employeeRepo.save(employee);
        return EMPLOYEE_ADDED_SUCCESSFULLY;
    }

    public String updateEmployee(int id, EmployeeDto employeeDto) {
        Employee employee = checkIdPresentOrNot(id);
        BeanUtils.copyProperties(employeeDto, employee);
        employeeRepo.save(employee);
        return EMPLOYEE_UPDATED_SUCCESSFULLY;
    }

    public String deleteEmployee(int empId) {
        Employee employee = checkIdPresentOrNot(empId);
        employeeRepo.delete(employee);
        return EMPLOYEE_DELETED_SUCCESSFULLY;
    }

    public Employee checkIdPresentOrNot(int empId) {
        return employeeRepo.findById(empId).orElseThrow(EntityNotFoundException::new);
    }
}
