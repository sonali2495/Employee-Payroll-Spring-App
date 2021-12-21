package com.bridgelabz.employeepayrollapp.repository;

import com.bridgelabz.employeepayrollapp.model.Employee;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Purpose : To Define Repository
 *
 * @author Sonali G
 * @Since 13-12-2021
 */

@Repository
public interface EmployeePayrollRepository extends JpaRepository<Employee, Integer> {
}
