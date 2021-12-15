package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.service.EmployeePayrollService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeePayrollControllerTest {
    @InjectMocks
    private EmployeePayrollController controller;
    @Mock
    private EmployeePayrollService service;

    @Test
    void whenGetEmployeePayrollDataCalled_shouldReturnListOfEmployee() {
        List<EmployeeDto> employeeDtoList = new ArrayList<>();

        EmployeeDto employeeDto1 = new EmployeeDto();
        employeeDto1.setName("Sonali");
        employeeDto1.setImagePath("/img1");
        employeeDto1.setGender("Female");
        employeeDto1.setSalary("100000");
        employeeDto1.setDepartment("IT");
        employeeDto1.setNotes("Test");
        employeeDtoList.add(employeeDto1);

        EmployeeDto employeeDto2 = new EmployeeDto();
        employeeDto2.setName("Sonali");
        employeeDto2.setImagePath("/img1");
        employeeDto2.setGender("Female");
        employeeDto2.setSalary("100000");
        employeeDto2.setDepartment("IT");
        employeeDto2.setNotes("Test");
        employeeDtoList.add(employeeDto2);

        when(service.getAllEmployee()).thenReturn(employeeDtoList);
        List<EmployeeDto> actualResponse = controller.getEmployeePayrollData();
        for (int i = 0; i < actualResponse.size(); i++) {
            assertEquals(employeeDtoList.get(i).getName(), actualResponse.get(i).getName());
            assertEquals(employeeDtoList.get(i).getImagePath(), actualResponse.get(i).getImagePath());
            assertEquals(employeeDtoList.get(i).getGender(), actualResponse.get(i).getGender());
            assertEquals(employeeDtoList.get(i).getSalary(), actualResponse.get(i).getSalary());
            assertEquals(employeeDtoList.get(i).getDepartment(), actualResponse.get(i).getDepartment());
            assertEquals(employeeDtoList.get(i).getNotes(), actualResponse.get(i).getNotes());
        }
    }

    @Test
    void whenAddEmployeePayrollDataCalled_shouldReturnSuccessMessage() {
        String expectedMessage = "Employee Added Successfully";

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Sonali");
        employeeDto.setImagePath("/img1");
        employeeDto.setGender("Female");
        employeeDto.setSalary("100000");
        employeeDto.setDepartment("IT");
        employeeDto.setNotes("Test");

        when(service.addEmployee(employeeDto)).thenReturn(expectedMessage);
        String actualMessage = controller.addEmployeePayrollData(employeeDto);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void whenUpdateEmployeePayrollDataCalled_shouldReturnSuccessMessage() {
        String expectedMessage = "Employee Updated Successfully";
        int id = 1;
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Sonali");
        employeeDto.setImagePath("/img1");
        employeeDto.setGender("Female");
        employeeDto.setSalary("100000");
        employeeDto.setDepartment("IT");
        employeeDto.setNotes("Test");

        when(service.updateEmployee(id, employeeDto)).thenReturn(expectedMessage);
        String actualMessage = controller.updateEmployeePayrollData(id, employeeDto);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void whenDeleteEmployeePayrollDataCalled_shouldReturnSuccessMessage() {
        String expectedMessage = "Employee Deleted Successfully";
        int id = 1;

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Sonali");
        employeeDto.setImagePath("/img1");
        employeeDto.setGender("Female");
        employeeDto.setSalary("100000");
        employeeDto.setDepartment("IT");
        employeeDto.setNotes("Test");

        when(service.deleteEmployee(id)).thenReturn(expectedMessage);
        String actualMessage = controller.deleteEmployeePayrollData(id);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void givenId_whenGetEmployeeByIdCalled_shouldReturnEmployeeDto() {
        int id = 1;
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Sonali");
        employeeDto.setImagePath("/img1");
        employeeDto.setGender("Female");
        employeeDto.setSalary("100000");
        employeeDto.setDepartment("IT");
        employeeDto.setNotes("Test");

        when(service.getEmployeeById(id)).thenReturn(employeeDto);
        EmployeeDto employeePayrollData = controller.getEmployeePayrollData(1);
        assertEquals(employeeDto, employeePayrollData);
    }
}
