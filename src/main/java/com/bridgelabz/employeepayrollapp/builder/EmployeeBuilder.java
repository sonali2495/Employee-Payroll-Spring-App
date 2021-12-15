package com.bridgelabz.employeepayrollapp.builder;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.model.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeBuilder {
    @Autowired
    private ModelMapper modelMapper;

    public Employee buildEmployeeEntity(EmployeeDto employeeDto, Employee employeeEntity) {
        modelMapper.map(employeeDto, employeeEntity);
        return employeeEntity;
    }
}
