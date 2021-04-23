package com.udemy.fr.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.udemy.fr.dto.ReservationRequest;
import com.udemy.fr.entities.Flight;
import com.udemy.fr.entities.Passenger;
import com.udemy.fr.entities.Reservation;
import com.udemy.fr.repos.FlightRepo;
import com.udemy.fr.repos.PassengerRepo;
import com.udemy.fr.repos.ReservationRepo;
import com.udemy.fr.util.EmailUtil;
import com.udemy.fr.util.PDFGenerator;
@Service
public class ReservationServiceImpl implements ReservationService {
	
	
	@Value("${com.udemy.fr.itinerary.dirpath}")
	private  String ITINERARY_DIR;// = "C:/Users/Administrator/Pictures/reservations/";
	@Autowired
	FlightRepo flightRepo;
	@Autowired
	PassengerRepo passengerRepo;

	@Autowired
	ReservationRepo reservationRepo;
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	@Autowired
	EmailUtil emailUtil;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);
	
	@Override
	public Reservation bookFlight(ReservationRequest request) {
		//Make Payment
		LOGGER.info("Inside the Book flight()");
		Long flightId = request.getFlightId();
		Flight flight=flightRepo.findById(flightId).get();
		LOGGER.info("Inside the fetching flight by id"+ flightId);
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		LOGGER.info("Inside the saving the passenger()"+ passenger);
		Passenger savedPassenger = passengerRepo.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		
		LOGGER.info("Inside the saving the reservation()"+ reservation);
		Reservation savedReservation = reservationRepo.save(reservation);
		
		String filePath = ITINERARY_DIR+"reservation"+ savedReservation.getId()+".pdf";
		LOGGER.info("Generating Itinerary");
		pdfGenerator.generateItinerary(savedReservation, filePath);
		LOGGER.info("Sending the email");
		emailUtil.sendItinerary(passenger.getEmail(), filePath);
				
		
		return savedReservation;
	}

}
