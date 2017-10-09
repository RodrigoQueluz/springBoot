package com.queluz.spring.boot.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.queluz.spring.boot.dao.CompanyRepository;
import com.queluz.spring.boot.model.Company;

/**
 * Service for processing Persons
 * 
 */
@Service
@Transactional
public class CompanyService {

	protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
	private CompanyRepository repository;
	/**
	 * Retrieves all persons
	 * 
	 * @return a list of persons
	 */
	public List<Company> getAll() {
		logger.debug("Retrieving all persons");
		return (List<Company>) repository.findAll();
	}

	public Company add(Company company) {
		return repository.save(company);
	}
	
	public Company findByID(Long id){
		return repository.findOne(id);
	}

	public List<Company> findByName(String name) {
		Iterable<Company> iterable = repository.findByNameContainingIgnoreCase(name);
		List<Company> companiesList = new ArrayList<Company>();
		
		iterable.forEach(companiesList::add);
		
		return companiesList;
	}

	public List<Company> findByIndustry(String industry) {
		Iterable<Company> iterable = repository.findByIndustryIgnoreCase(industry);
		List<Company> companiesList = new ArrayList<Company>();
		
		iterable.forEach(companiesList::add);
		
		return companiesList;
	}
	
	public List<String> findIndustries(){
		List<String> industries = new ArrayList<>();
		
		List<Company> companiesList = repository.findDistinctByIndustry();
		
		companiesList.forEach(company -> industries.add(company.getIndustry()));
		
		return industries;
	}
}