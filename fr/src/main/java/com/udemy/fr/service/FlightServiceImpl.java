package com.udemy.fr.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.fr.entities.Flight;
import com.udemy.fr.repos.FlightRepo;
@Service
public class FlightServiceImpl implements FlightService {
	
	@Autowired
	private FlightRepo flightRepo;
	@Override
	public List<Flight> getAllFlights() {
		return flightRepo.findAll();
		
	}
	@Override
	public List<Flight> findFlights(String from, String to, Date departureDate) {
		
		return null;
	}
	@Override
	public Flight getFlightById(Long id) {
		
		return flightRepo.findById(id).get();
	}

}
