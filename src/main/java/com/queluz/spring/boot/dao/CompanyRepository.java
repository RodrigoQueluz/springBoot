package com.queluz.spring.boot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.queluz.spring.boot.model.Company;

public interface CompanyRepository extends CrudRepository<Company, Long> {

	public Iterable<Company> findByNameContainingIgnoreCase(String name);

	public Iterable<Company> findByIndustryIgnoreCase(String industry);
	
	@Query(value = "SELECT DISTINCT industry FROM company order by industry", nativeQuery = true)
	public List<Company> findDistinctByIndustry();

}