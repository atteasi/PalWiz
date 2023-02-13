package com.example.application.data.service;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.application.data.entity.Kurssi;

public interface KurssiRepository extends JpaRepository<Kurssi, Long> {

}
