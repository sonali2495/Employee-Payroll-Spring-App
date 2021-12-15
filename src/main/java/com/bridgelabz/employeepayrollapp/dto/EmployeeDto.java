package com.bridgelabz.employeepayrollapp.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class EmployeeDto {
    @NotNull
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}([\\s][A-Z]{1}[a-z]{2,})?$", message = "at least 3 Characters and First Letter Should be Capital")
    private String name;

    private String imagePath;

    private String gender;

    private String salary;

    private String department;

    private String notes;
}
