package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.model.Employee;
import com.bridgelabz.employeepayrollapp.repository.EmployeePayrollRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployePayrollServiceTest {
    @Mock
    private EmployeePayrollRepository payrollRepository;

    @InjectMocks
    private EmployeePayrollService payrollService;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void whenAddEmployee_shouldReturnEmployeeDto() {
        Employee employee = new Employee();
        employee.setName("Sonali");
        employee.setSalary(100000);
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(employee, employeeDto);
        when(payrollService.addEmployee(employeeDto)).thenReturn(employee);
        Employee employeeCreated = payrollService.addEmployee(employeeDto);
        assertEquals(employeeDto.getName(), employeeCreated.getName());
        assertEquals(employeeDto.getSalary(), employeeCreated.getSalary());
    }

    @Test
    void whenGetAllEmployeeCalled_ShouldReturnListOfEmployees() {
        List<Employee> employee = new ArrayList<>();

        Employee employee1 = new Employee();
        employee1.setId(1);
        employee1.setName("Sonali");
        employee1.setSalary(100000);
        employee.add(employee1);

        Employee employee2 = new Employee();
        employee2.setId(2);
        employee2.setName("Chris");
        employee2.setSalary(100000);
        employee.add(employee2);

        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Sonali");
        employeeDto.setSalary(100000);
        employeeDtoList.add(employeeDto);

        EmployeeDto employeeDto1 = new EmployeeDto();
        employeeDto1.setName("Chris");
        employeeDto1.setSalary(100000);
        employeeDtoList.add(employeeDto1);

        when(payrollRepository.findAll()).thenReturn(employee);
        when(modelMapper.map(employee.get(0), EmployeeDto.class)).thenReturn(employeeDto);
        when(modelMapper.map(employee.get(1), EmployeeDto.class)).thenReturn(employeeDto1);
        List<EmployeeDto> actualList = payrollService.getAllEmployee();
        assertEquals(2, actualList.size());
        assertEquals(employeeDtoList, actualList);
    }

    @Test
    void whenUpdateEmployeeCalled_shouldUpdate() {
        int id = 1;
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName("Sonali");
        employee.setSalary(10000);

        EmployeeDto newEmployee = new EmployeeDto();
        newEmployee.setName("Chrish");
        newEmployee.setSalary(99999);

        when(payrollRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        when(payrollService.updateEmployee(id, newEmployee))
                .thenReturn(employee);
        Employee employee1 = payrollService.updateEmployee(id, newEmployee);
        employee.setId(id);
        employee.setName("Chris");
        employee.setSalary(99999);
        assertEquals(employee1, employee);
    }

    @Test
    void whenDeleteEmployeeCalled_ShouldDelete() {
        String EMPLOYEE_DELETED_SUCCESSFULLY = "Employee Deleted Successfully";
        int id = 1;
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName("Sonali");
        employee.setSalary(100000);
        when(payrollRepository.findById(id)).thenReturn(Optional.of(employee));
        String actualMessage = payrollService.deleteEmployee(id);
        assertEquals(EMPLOYEE_DELETED_SUCCESSFULLY, actualMessage);
    }

    @Test
    void WhenGivenIdIsNotFound_ShouldThrowException() {
        assertThrows(EntityNotFoundException.class, () -> payrollService.checkIdPresentOrNot(1));
    }
}
