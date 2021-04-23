package com.udemy.fr.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.fr.entities.User;

public interface UserRepo extends JpaRepository<User, Long> {

	User findByEmail(String email);

	

}
