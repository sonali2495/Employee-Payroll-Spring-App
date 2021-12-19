package com.bridgelabz.employeepayrollapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Purpose: To Get The Basic Structure of Data
 *
 * @author : Sonali G
 * @since : 13-12-2021
 */
@Entity
@Table(name = "employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    private String name;
    private String imagePath;
    private String gender;
    private String salary;

    @ElementCollection
    @CollectionTable(name = "employee_department", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "department")
    private List<String> departments;

    private String notes;

    public List<String> getDepartments() {
        return departments;
    }

    public void setDepartments(List<String> departments) {
        this.departments = departments;
    }
}
