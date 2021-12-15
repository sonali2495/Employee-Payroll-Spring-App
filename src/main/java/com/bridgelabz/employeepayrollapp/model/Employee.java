package com.bridgelabz.employeepayrollapp.model;

import lombok.Data;

import javax.persistence.*;

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
    private String department;
    private String notes;
}
