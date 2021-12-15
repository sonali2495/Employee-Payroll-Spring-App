package com.bridgelabz.employeepayrollapp.builder;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.model.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Purpose : This is builder class which converts payroll dto into entity.
 *
 * @author Sonali G
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-14
 */
@Component
public class EmployeeBuilder {
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Purpose : To convert employeeDto into employeeEntity.
     *
     * @param employeeDto    : employeeDto which is to be converted.
     * @param employeeEntity : employeeEntity which will be overwritten.
     * @return employeePayroll : converted employee payroll
     */
    public Employee buildEmployeeEntity(EmployeeDto employeeDto, Employee employeeEntity) {
        modelMapper.map(employeeDto, employeeEntity);
        return employeeEntity;
    }
}
