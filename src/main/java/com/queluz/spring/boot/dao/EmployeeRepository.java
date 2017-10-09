package com.queluz.spring.boot.dao;

import org.springframework.data.repository.CrudRepository;

import com.queluz.spring.boot.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	public Iterable<Employee> findByJobTitleContainingIgnoreCase(String jobTitle);

}