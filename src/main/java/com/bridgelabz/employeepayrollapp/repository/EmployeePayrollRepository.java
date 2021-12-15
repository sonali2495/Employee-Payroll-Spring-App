package com.bridgelabz.employeepayrollapp.repository;

import com.bridgelabz.employeepayrollapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Purpose : To Define Repository
 *
 * @author Sonali G
 * @Since 13-12-2021
 */
public interface EmployeePayrollRepository extends JpaRepository<Employee, Integer> {
}
