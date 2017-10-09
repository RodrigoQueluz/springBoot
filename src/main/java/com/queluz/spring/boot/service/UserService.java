package com.queluz.spring.boot.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.queluz.spring.boot.dao.UserRepository;
import com.queluz.spring.boot.model.User;

/**
 * Service for processing Persons
 * 
 */
@Service
@Transactional
public class UserService {

	protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
	private UserRepository repository;
	/**
	 * Retrieves all persons
	 * 
	 * @return a list of persons
	 */
	public List<User> getAll() {
		logger.debug("Retrieving all persons");

		// Retrieve session from Hibernate
		return (List<User>) repository.findAll();
	}

	/**
	 * Adds a new person
	 * 
	 * @param firstName
	 *            the first name of the person
	 * @param lastName
	 *            the last name of the person
	 * @param money
	 *            the money of the person
	 */
	public void add(String firstName, String lastName, String email) {
		logger.debug("Adding new person");

		// Create a new person
		User user = new User();
		// Save
		repository.save(user);
	}

	/**
	 * Deletes an existing person
	 * 
	 * @param id
	 *            the id of the existing person
	 */
	public void delete(Integer id) {
		logger.debug("Deleting existing person");

		// Retrieve existing person first
		repository.delete(Long.valueOf(id));
	}

	/**
	 * Edits an existing person
	 * 
	 * @param id
	 *            the id of the existing person
	 * @param firstName
	 *            the first name of the existing person
	 * @param lastName
	 *            the last name of the existing person
	 * @param money
	 *            the money of the existing person
	 */
	public void edit(Integer id, String firstName, String lastName, String email) {
		logger.debug("Editing existing person");

		User user = repository.findOne(Long.valueOf(id));

		// Assign updated values to this person
		
		// Save updates
		repository.save(user);
	}

	public User add(User user) {
		return repository.save(user);
	}
}