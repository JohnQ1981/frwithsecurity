package com.udemy.fr.controllers;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.udemy.fr.dto.ReservationRequest;
import com.udemy.fr.entities.Flight;
import com.udemy.fr.entities.Reservation;
import com.udemy.fr.repos.FlightRepo;
import com.udemy.fr.service.FlightService;
import com.udemy.fr.service.ReservationService;

@Controller
public class ReservationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
	
	@Autowired
	FlightRepo flightRepo;
	
	@Autowired
	FlightService flightService;
	
	@Autowired
	ReservationService reservationService;
	
	@RequestMapping("/showCompleteReservation")
	public  String showCompleteReservation(@RequestParam("flightId")Long flightId, ModelMap modelMap) {
		
		LOGGER.info("Inside the showCompleteReservation Flight Id: " + flightId);
		Flight flight=flightService.getFlightById(flightId);
		modelMap.addAttribute("flight", flight);
		
		return "completeReservation";
	}

	@RequestMapping(value="/completeReservation", method=RequestMethod.POST)
	public String completeReservation(ReservationRequest request, ModelMap modelMap) {
		LOGGER.info("Inside the CompleteReservation Request is: " + request);
		Reservation reservation = reservationService.bookFlight(request);
		modelMap.addAttribute("msg","Reservation created successfully and the id is  "+ reservation.getId());
		LOGGER.info("Inside the showCompleteReservation Reservation Id: " + reservation.getId());
		return "reservationConfirmation";
	}
}
