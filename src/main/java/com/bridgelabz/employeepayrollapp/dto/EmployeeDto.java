package com.bridgelabz.employeepayrollapp.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Purpose: To Get Data From End User
 *
 * @author : Sonali G
 * @since : 13-12-2021
 */
@Data
public class EmployeeDto {
    @NotNull
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}([\\s][A-Z]{1}[a-z]{2,})?$", message = "Minimum 3 Characters and First Letter Should be Capital")
    private String name;

    @NotNull(message = "Image Path Cant Not be Empty")
    private String imagePath;
    private String gender;

    @NotNull(message = "Salary Cant Not be Empty")
    private String salary;

    @NotNull(message = "Department Should Not be Empty")
    private List<String> departments;

    @NotNull(message = "Notes Should Not be Empty")
    private String notes;

    public List<String> getDepartments() {
        return departments;
    }

    public void setDepartments(List<String> departments) {
        this.departments = departments;
    }
}
