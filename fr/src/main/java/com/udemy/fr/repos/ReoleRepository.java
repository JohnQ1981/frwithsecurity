package com.udemy.fr.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.fr.entities.Role;

public interface ReoleRepository extends JpaRepository<Role, Long> {

}
