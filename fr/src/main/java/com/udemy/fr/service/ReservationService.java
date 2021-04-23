package com.udemy.fr.service;



import com.udemy.fr.dto.ReservationRequest;
import com.udemy.fr.entities.Reservation;

public interface ReservationService {
	
	public Reservation bookFlight(ReservationRequest request);

}
