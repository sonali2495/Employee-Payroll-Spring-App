package com.bridgelabz.employeepayrollapp.integration;

import com.bridgelabz.employeepayrollapp.controller.EmployeePayrollController;
import com.bridgelabz.employeepayrollapp.dto.EmployeeDto;
import com.bridgelabz.employeepayrollapp.repository.EmployeePayrollRepository;
import com.bridgelabz.employeepayrollapp.service.EmployeePayrollService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(EmployeePayrollController.class)
public class EmployeePayrollControllerIT {
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeePayrollService employeePayrollService;

    @Test
    void getAllAddressTest() throws Exception {
        when(employeePayrollService.getAllEmployee()).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/employeepayrollservice/employee"))
                .andExpect(status().isOk());
    }

    @Test
    void addAddressTest() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Sonali");
        employeeDto.setImagePath("/img1");
        employeeDto.setGender("Female");
        employeeDto.setSalary("100000");
        employeeDto.setDepartment("IT");
        employeeDto.setNotes("Test");
        String jsonRequest = objectMapper.writeValueAsString(employeeDto);
        when(employeePayrollService.addEmployee(any())).thenReturn("success");
        mockMvc.perform(MockMvcRequestBuilders.post("/employeepayrollservice/employee")
                        .content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void updateAddressTest() throws Exception {
        int id = 1;
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Sonali");
        employeeDto.setImagePath("/img1");
        employeeDto.setGender("Female");
        employeeDto.setSalary("100000");
        employeeDto.setDepartment("IT");
        employeeDto.setNotes("Test");

        String jsonRequest = objectMapper.writeValueAsString(employeeDto);
        when(employeePayrollService.updateEmployee(id, employeeDto))
                .thenReturn("Success");
        mockMvc.perform(MockMvcRequestBuilders.put("/employeepayrollservice/employee/1")
                        .content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAddressTest() throws Exception {
        int id = 1;
        when(employeePayrollService.deleteEmployee(id)).thenReturn("Success");
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/employeepayrollservice/employee/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getAddressTest() throws Exception {
        int id = 1;

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Sonali");
        employeeDto.setImagePath("/img1");
        employeeDto.setGender("Female");
        employeeDto.setSalary("100000");
        employeeDto.setDepartment("IT");
        employeeDto.setNotes("Test");
        when(employeePayrollService.getEmployeeById(id)).thenReturn(employeeDto);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/employeepayrollservice/employee/1")).andExpect(status().isOk());
    }
}
