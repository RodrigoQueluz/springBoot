package com.queluz.spring.boot.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.queluz.spring.boot.dao.EmployeeRepository;
import com.queluz.spring.boot.model.Employee;

/**
 * Service for processing Persons
 * 
 */
@Service
@Transactional
public class EmployeeService {

	protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
	private EmployeeRepository repository;
	/**
	 * Retrieves all persons
	 * 
	 * @return a list of persons
	 */
	public List<Employee> getAll() {
		logger.debug("Retrieving all persons");
		return (List<Employee>) repository.findAll();
	}

	public Employee add(Employee employee) {
		return repository.save(employee);
	}
	
	public Employee findByID(Long id){
		return repository.findOne(id);
	}
	
	public List<Employee> findByJobTitle(String jobTitle){
		Iterable<Employee> iterable = repository.findByJobTitleContainingIgnoreCase(jobTitle);
		List<Employee> employeesList = new ArrayList<Employee>();
		
		iterable.forEach(employeesList::add);
		
		return employeesList;
	}
}