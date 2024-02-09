package com.demo.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.testing.domain.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
