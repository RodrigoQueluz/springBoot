package com.queluz.spring.boot.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.queluz.spring.boot.domain.EmployeeDomain;
import com.queluz.spring.boot.model.Company;
import com.queluz.spring.boot.model.Employee;
import com.queluz.spring.boot.service.CompanyService;
import com.queluz.spring.boot.service.EmployeeService;

/**
 * Handles and retrieves person request
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	protected static Logger logger = Logger.getLogger("controller");

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CompanyService companyService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<Employee> getEmployees() {

		List<Employee> employees = employeeService.getAll();

		return employees;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Employee getEmployeeByID(@PathVariable Long id) {

		logger.debug("Received request to show all Employees");

		Employee employee = employeeService.findByID(id);

		return employee;
	}

	@RequestMapping(value = "/jobTitle/{jobTitle}", method = RequestMethod.GET)
	public @ResponseBody List<Employee> getEmployeeByJobTitle(@PathVariable String jobTitle) {

		logger.debug("Received request to show all Employees");

		List<Employee> employee = employeeService.findByJobTitle(jobTitle);

		return employee;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody Employee add(@RequestBody EmployeeDomain employeeDomain) throws IOException {

		Employee employee = genereateEmployee(employeeDomain);

		Company company = companyService.findByID(employeeDomain.getEmployer());

		employee.setEmployer(company);
		company.getEmployees().add(employee);

		employeeService.add(employee);
		return employee;
	}

	private Employee genereateEmployee(EmployeeDomain employeeDomain) throws IOException {
		Employee employee = parse(employeeDomain);

		String url = "https://randomuser.me/api/?seed=" + employee.getSeed();

		JsonElement root = enviaRequest(url);

		JsonObject element = root.getAsJsonObject().get("results").getAsJsonArray().get(0).getAsJsonObject();

		JsonObject name = element.get("name").getAsJsonObject();
		employee.setName(name.get("title").getAsString() + " " + name.get("first").getAsString() + " "
				+ name.get("last").getAsString());
		employee.setEmail(element.get("email").getAsString());
		employee.setGender(element.get("gender").getAsString());

		return employee;
	}

	private JsonElement enviaRequest(String url) throws IOException {
		StringBuilder result = new StringBuilder();

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", "");

		int responseCode = con.getResponseCode();

		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) {
			InputStream in = new BufferedInputStream(con.getInputStream());

			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line + "\n");
			}

		}

		System.out.println(result.toString());

		return new JsonParser().parse(result.toString());
	}

	private Employee parse(EmployeeDomain employeeDomain) {
		Employee employee = new Employee();

		employee.setCpf(employeeDomain.getCpf());
		employee.setJobTitle(employeeDomain.getJobTitle());
		employee.setSeed(employeeDomain.getSeed());

		return employee;
	}
}