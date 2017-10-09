package com.queluz.spring.boot.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.queluz.spring.boot.model.User;
import com.queluz.spring.boot.service.UserService;

/**
 * Handles and retrieves person request
 */
@Controller
@RequestMapping("/main")
public class UserController {

	protected static Logger logger = Logger.getLogger("controller");

	@Resource(name = "userService")
	private UserService userService;

	/**
	 * Handles and retrieves all persons and show it in a JSP page
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public @ResponseBody List<User> getUsers(Model model) {

		logger.debug("Received request to show all users");

		// Retrieve all persons by delegating the call to PersonService
		List<User> users = userService.getAll();

		// Attach persons to the Model
		model.addAttribute("users", users);

		// This will resolve to /WEB-INF/jsp/personspage.jsp
		return users;
	}

	/**
	 * Adds a new person by delegating the processing to PersonService. Displays
	 * a confirmation JSP page
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public @ResponseBody User add( @RequestBody User user) {

		logger.debug("Received request to add new person");

		// Call PersonService to do the actual adding
		userService.add(user);

		// This will resolve to /WEB-INF/jsp/addedpage.jsp
		return user;
	}

	/**
	 * Deletes an existing person by delegating the processing to PersonService.
	 * Displays a confirmation JSP page
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/users/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(value = "id", required = true) Integer id, Model model) {

		logger.debug("Received request to delete existing person");

		// Call PersonService to do the actual deleting
		userService.delete(id);

		// Add id reference to Model
		model.addAttribute("id", id);

		// This will resolve to /WEB-INF/jsp/deletedpage.jsp
		return "deletedpage";
	}

	/**
	 * Edits an existing person by delegating the processing to PersonService.
	 * Displays a confirmation JSP page
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/persons/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "firstname", required = true) String firstName,
			@RequestParam(value = "lastname", required = true) String lastName,
			@RequestParam(value = "email", required = true) String email, Model model) {

		logger.debug("Received request to edit existing person");

		// Call PersonService to do the actual editing
		userService.edit(id, firstName, lastName, email);

		// Add id reference to Model
		model.addAttribute("id", id);

		// This will resolve to /WEB-INF/jsp/editedpage.jsp
		return "editedpage";
	}
}