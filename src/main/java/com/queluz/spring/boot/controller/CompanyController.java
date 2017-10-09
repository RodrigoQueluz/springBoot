package com.queluz.spring.boot.controller;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.queluz.spring.boot.model.Company;
import com.queluz.spring.boot.service.CompanyService;

/**
 * Handles and retrieves person request
 */
@Controller
@RequestMapping("/company")
public class CompanyController {

	protected static Logger logger = Logger.getLogger("controller");

	@Autowired
	private CompanyService companyService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<Company> getCompanies(Model model) {

		List<Company> companies = companyService.getAll();
		model.addAttribute("Companies", companies);

		return companies;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Company getEmployeeByID(@PathVariable Long id) {

		logger.debug("Received request to show all Companies");
		Company company = companyService.findByID(id);

		return company;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody Company add(@RequestBody Company company) throws IOException {

		companyService.add(company);
		
		return company;
	}
	
	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public @ResponseBody List<Company> getCompaniesByName(@PathVariable String name) {

		logger.debug("Received request to show all Employees");

		List<Company> companies = companyService.findByName(name);

		return companies;
	}
	
	@RequestMapping(value = "/industry/{industry}", method = RequestMethod.GET)
	public @ResponseBody List<Company> getEmployeeByIndustry(@PathVariable String industry) {

		logger.debug("Received request to show all Employees");

		List<Company> companies = companyService.findByIndustry(industry);

		return companies;
	}
}