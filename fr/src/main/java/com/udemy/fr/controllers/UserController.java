package com.udemy.fr.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.udemy.fr.entities.Flight;
import com.udemy.fr.entities.User;
import com.udemy.fr.repos.FlightRepo;
import com.udemy.fr.repos.UserRepo;
import com.udemy.fr.service.SecurityService;

@Controller
public class UserController {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private FlightRepo flightRepo;
	
	@Autowired
	private SecurityService securityService;
	
@Autowired
	private BCryptPasswordEncoder encoder;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/displayFlights", method = RequestMethod.GET)
	public List<Flight> showFlights() {
		return flightRepo.findAll();
	}

	@RequestMapping("/login")
	public String login() {
		LOGGER.info("Inside login()");
		return "login/login";
	}

	@RequestMapping("/showReg")
	public String showRegistationPage() {
		LOGGER.info("Inside showRegistrationPage()");

		return "login/registerUser";
	}

	@RequestMapping(value = "registerUser", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {
		
		LOGGER.info("Inside register()"+ user);
		
		user.setPassword(encoder.encode(user.getPassword()));
		userRepo.save(user);
		return "login/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelMap modelMap) {
		LOGGER.info("Inside login() and email is: "+ email);
		LOGGER.error("ERROR");
		LOGGER.warn("WARN");
		LOGGER.info("INFO");
		LOGGER.debug("DEBUG");
		LOGGER.trace("TRACE");

		boolean loginResponse = securityService.login(email, password);
		//User user = userRepo.findByEmail(email);

		//if (user.getPassword().equals(password))
			if (loginResponse){

			return "findFlights";

		} else {
			modelMap.addAttribute("msg", "Invalid user name or password, please try again");

		}
		return "login/login";

	}

}
