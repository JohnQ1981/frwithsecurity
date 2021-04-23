package com.udemy.fr.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.fr.entities.Passenger;

public interface PassengerRepo extends JpaRepository<Passenger, Long> {

}
