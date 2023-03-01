package com.example.application.data.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.application.data.entity.Kurssi;

public interface KurssiRepository extends JpaRepository<Kurssi, Long> {
  @Query("SELECT k FROM Kurssi k WHERE "
		+"(k.id = :id)")
List<Kurssi> findKurssiById(@Param("id") int id);
}
