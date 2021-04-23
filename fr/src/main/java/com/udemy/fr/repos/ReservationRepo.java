package com.udemy.fr.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.fr.entities.Reservation;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {

}
