package com.example.application.data.service;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.application.data.entity.Palaute;


public interface PalauteRepository extends JpaRepository<Palaute, Long> {
	
}
