package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.configuration.EmployeeConfiguration;
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

/**
 * Purpose: To Define Service Class for CRUD Operations
 *
 * @author : Sonali G
 * @since : 13-12-2021
 */
@Service
public class EmployeePayrollService implements IEmployeePayrollService {
    private static final String EMPLOYEE_ADDED_SUCCESSFULLY = "Employee Added Successfully";
    private static final String EMPLOYEE_UPDATED_SUCCESSFULLY = "Employee Updated Successfully";
    private static final String EMPLOYEE_DELETED_SUCCESSFULLY = "Employee Deleted Successfully";

    @Autowired
    private EmployeePayrollRepository employeeRepo;

    @Autowired
    private EmployeeConfiguration employeeConfiguration;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Purpose : To Get All the Employee
     *
     * @return list of Employee
     */
    @Override
    public List<EmployeeDto> getAllEmployee() {
        return employeeRepo.findAll().stream().map(Employee ->
                modelMapper.map(Employee, EmployeeDto.class)).collect((Collectors.toList()));
    }

    /**
     * Purpose : To Get Employee By id
     *
     * @param empId for searching in repo
     * @return Contact Dto
     */
    @Override
    public EmployeeDto getEmployeeById(int empId) {
        checkIdPresentOrNot(empId);
        Employee employee = employeeRepo.findById(empId).get();
        return modelMapper.map(employee, EmployeeDto.class);
    }

    /**
     * Purpose To Add Employee in Repository
     *
     * @param empPayrollDTO given dto for add in repository
     * @return Success Message for Add
     */
    @Override
    public String addEmployee(EmployeeDto empPayrollDTO) {
        Employee employee = modelMapper.map(empPayrollDTO, Employee.class);
        employeeRepo.save(employee);
        return EMPLOYEE_ADDED_SUCCESSFULLY;
    }

    /**
     * Purpose To Update Existing Employee
     *
     * @param id          for search existing Employee
     * @param employeeDto for update
     * @return success message for update
     */
    public String updateEmployee(int id, EmployeeDto employeeDto) {
        Employee employee = checkIdPresentOrNot(id);
        BeanUtils.copyProperties(employeeDto, employee);
        employeeRepo.save(employee);
        return EMPLOYEE_UPDATED_SUCCESSFULLY;
    }

    /**
     * Purpose To delete Existing Employee
     *
     * @param empId to search for existing data in repo
     * @return success message for delete
     */
    public String deleteEmployee(int empId) {
        Employee employee = checkIdPresentOrNot(empId);
        employeeRepo.delete(employee);
        return EMPLOYEE_DELETED_SUCCESSFULLY;
    }

    /**
     * Purpose To Check Given id Present Or Not
     *
     * @param empId for check
     * @return Employee Entity
     */
    public Employee checkIdPresentOrNot(int empId) {
        return employeeRepo.findById(empId).orElseThrow(EntityNotFoundException::new);
    }
}
