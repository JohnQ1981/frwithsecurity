package com.udemy.fr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.fr.entities.Flight;
import com.udemy.fr.repos.FlightRepo;

@RestController
@RequestMapping("/display")
public class FlightRestController {
	
	@Autowired
	private FlightRepo flightRepo;
	
	@GetMapping
	public List<Flight> displayFlights() {
		
				return flightRepo.findAll();
	}

}
