package com.queluz.spring.boot.dao;

import org.springframework.data.repository.CrudRepository;

import com.queluz.spring.boot.model.Company;

public interface CompanyRepository extends CrudRepository<Company, Long> {

	 public Iterable<Company> findByNameContainingIgnoreCase(String name);
	 public Iterable<Company> findByIndustryIgnoreCase(String industry);
	 
}