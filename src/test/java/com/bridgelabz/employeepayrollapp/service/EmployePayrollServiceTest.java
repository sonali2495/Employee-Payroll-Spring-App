package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.model.Employee;
import com.bridgelabz.employeepayrollapp.repository.EmployeePayrollRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployePayrollServiceTest {
    @InjectMocks
    private EmployeePayrollService service;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private EmployeePayrollRepository repository;

    @Test
    void whenGetAllMethodCalled_shouldReturnListOfEmployee() {
        List<Employee> employeeEntityList = new ArrayList<>();

        Employee employeeEntity1 = new Employee();
        employeeEntity1.setId(1);
        employeeEntity1.setName("Sonali G");
        employeeEntity1.setImagePath("/img1");
        employeeEntity1.setGender("Female");
        employeeEntity1.setSalary("100000");
        employeeEntity1.setDepartment("IT");
        employeeEntity1.setNotes("Test");
        employeeEntityList.add(employeeEntity1);

        Employee employeeEntity2 = new Employee();
        employeeEntity1.setId(2);
        employeeEntity2.setName("Chris E");
        employeeEntity2.setImagePath("/img1");
        employeeEntity2.setGender("Male");
        employeeEntity2.setSalary("100000");
        employeeEntity2.setDepartment("IT");
        employeeEntity2.setNotes("Test");
        employeeEntityList.add(employeeEntity2);

        List<EmployeeDto> employeeDtoList = new ArrayList<>();

        EmployeeDto employee1 = new EmployeeDto();
        employee1.setName(employeeEntity1.getName());
        employee1.setImagePath(employeeEntity1.getImagePath());
        employee1.setGender(employeeEntity1.getGender());
        employee1.setSalary(employeeEntity1.getSalary());
        employee1.setDepartment(employeeEntity1.getDepartment());
        employee1.setNotes(employeeEntity1.getNotes());
        employeeDtoList.add(employee1);

        EmployeeDto employee2 = new EmployeeDto();
        employee2.setName(employeeEntity2.getName());
        employee2.setImagePath(employeeEntity2.getImagePath());
        employee2.setGender(employeeEntity2.getGender());
        employee2.setSalary(employeeEntity2.getSalary());
        employee2.setDepartment(employeeEntity2.getDepartment());
        employee2.setNotes(employeeEntity2.getNotes());
        employeeDtoList.add(employee2);


        when(repository.findAll()).thenReturn(employeeEntityList);
        when(modelMapper.map(employeeEntityList.get(0),
                EmployeeDto.class)).thenReturn(employee1);
        when(modelMapper.map(employeeEntityList.get(1),
                EmployeeDto.class)).thenReturn(employee2);
        List<EmployeeDto> actualResponse = service.getAllEmployee();
        assertEquals(2, actualResponse.size());
        assertEquals(employeeDtoList, actualResponse);
    }

    @Test
    void whenAddMethodCalled_shouldReturnSuccessMessage() {
        String expectedMessage = "Employee Added Successfully";
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Sonali");
        employeeDto.setImagePath("/img1");
        employeeDto.setGender("Female");
        employeeDto.setSalary("100000");
        employeeDto.setDepartment("IT");
        employeeDto.setNotes("Test");

        Employee employeeEntity = new Employee();
        employeeEntity.setId(1);
        employeeEntity.setName(employeeDto.getName());
        employeeEntity.setImagePath(employeeDto.getImagePath());
        employeeEntity.setGender(employeeDto.getGender());
        employeeEntity.setSalary(employeeDto.getSalary());
        employeeEntity.setDepartment(employeeDto.getDepartment());
        employeeEntity.setNotes(employeeDto.getNotes());

        when(modelMapper.map(employeeDto, Employee.class))
                .thenReturn(employeeEntity);
        String actualMessage = service.addEmployee(employeeDto);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void whenUpdateEmployeeCalled_shouldReturnSuccessMessage() {
        ArgumentCaptor<Employee> employeeEntityArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        String expectedMessage = "Employee Updated Successfully";

        int id = 1;
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Sonali");
        employeeDto.setImagePath("/img1");
        employeeDto.setGender("Female");
        employeeDto.setSalary("100000");
        employeeDto.setDepartment("IT");
        employeeDto.setNotes("Test");

        Employee employeeEntity = new Employee();
        employeeEntity.setId(id);
        employeeEntity.setName("Chris");
        employeeEntity.setImagePath("/img2");
        employeeEntity.setGender("Male");
        employeeEntity.setSalary("100000");
        employeeEntity.setDepartment("IT");
        employeeEntity.setNotes("Test");

        when(repository.findById(id)).thenReturn(Optional.of(employeeEntity));
        employeeEntity.setId(id);
        employeeEntity.setName(employeeDto.getName());
        employeeEntity.setImagePath(employeeDto.getImagePath());
        employeeEntity.setGender(employeeDto.getGender());
        employeeEntity.setSalary(employeeDto.getSalary());
        employeeEntity.setDepartment(employeeDto.getDepartment());
        employeeEntity.setNotes(employeeDto.getNotes());
//       when(employeeBuilder.buildEmployeeEntity(employeeDto, employeeEntity)).thenReturn(employeeEntity);
        String actualMessage = service.updateEmployee(id, employeeDto);
        verify(repository, times(1))
                .save(employeeEntityArgumentCaptor.capture());
        assertEquals(expectedMessage, actualMessage);
        assertEquals(employeeDto.getName(), employeeEntityArgumentCaptor.getValue().getName());
        assertEquals(employeeDto.getImagePath(), employeeEntityArgumentCaptor.getValue().getImagePath());
        assertEquals(employeeDto.getGender(), employeeEntityArgumentCaptor.getValue().getGender());
        assertEquals(employeeDto.getSalary(), employeeEntityArgumentCaptor.getValue().getSalary());
        assertEquals(employeeDto.getDepartment(),
                employeeEntityArgumentCaptor.getValue().getDepartment());
        assertEquals(employeeDto.getNotes(), employeeEntityArgumentCaptor.getValue().getNotes());
    }

    @Test
    void givenId_whenNotFound_shouldThrowException() {
        int id = 1;
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> service.checkIdPresentOrNot(id));
    }

    @Test
    void whenDeleteEmployeeCalled_shouldReturnSuccessMessage() {
        int id = 1;
        String expectedMessage = "Employee Deleted Successfully";

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Sonali");
        employeeDto.setImagePath("/img1");
        employeeDto.setGender("Female");
        employeeDto.setSalary("100000");
        employeeDto.setDepartment("IT");
        employeeDto.setNotes("Test");

        Employee employeeEntity = new Employee();
        employeeEntity.setId(id);
        employeeEntity.setName(employeeDto.getName());
        employeeEntity.setImagePath(employeeDto.getImagePath());
        employeeEntity.setGender(employeeDto.getGender());
        employeeEntity.setSalary(employeeDto.getSalary());
        employeeEntity.setDepartment(employeeDto.getDepartment());
        employeeEntity.setNotes(employeeDto.getNotes());

        when(repository.findById(id)).thenReturn(Optional.of(employeeEntity));
        String actualMessage = service.deleteEmployee(id);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void whenGetEmployeeByIDCalled_shouldReturnEmployeeDto() {
        int id = 1;

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Chris");
        employeeDto.setImagePath("/img2");
        employeeDto.setGender("Male");
        employeeDto.setSalary("100000");
        employeeDto.setDepartment("IT");
        employeeDto.setNotes("Test");

        Employee employeeEntity = new Employee();
        employeeEntity.setId(id);
        employeeEntity.setName("Chris");
        employeeEntity.setImagePath("/img2");
        employeeEntity.setGender("Male");
        employeeEntity.setSalary("100000");
        employeeEntity.setDepartment("IT");
        employeeEntity.setNotes("Test");

        when(repository.findById(id)).thenReturn(Optional.of(employeeEntity));
        when(modelMapper.map(employeeEntity, EmployeeDto.class))
                .thenReturn(employeeDto);
        EmployeeDto actualEmployee = service.getEmployeeById(id);
        assertEquals(employeeDto, actualEmployee);
    }
}
