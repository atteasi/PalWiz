package com.example.application.data.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.application.data.entity.Kurssi;

public interface KurssiRepository extends JpaRepository<Kurssi, Long> {

}
