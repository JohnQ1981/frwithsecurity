package com.udemy.fr.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.udemy.fr.entities.Flight;


public interface FlightService {
	
	List<Flight> getAllFlights();
	Flight getFlightById(Long id);
	
	@Query("from Flight where departureCity=:departureCity and arrivalCity=:arrivalCity and dateOfDeparture=:dateOfDeparture")
	List<Flight> findFlights(@Param("departureCity") String from, @Param("arrivalCity")String to, @Param("dateOfDeparture")Date departureDate);

}
