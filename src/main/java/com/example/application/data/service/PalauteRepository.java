package com.example.application.data.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.application.data.entity.Kurssi;
import com.example.application.data.entity.Palaute;

import net.bytebuddy.asm.Advice.Local;

public interface PalauteRepository extends JpaRepository<Palaute, Long>, JpaSpecificationExecutor<Palaute> {
	@Query("SELECT p FROM Palaute p WHERE "
			+ "(:value is null or p.vastaus = :value)")
	List<Palaute> findAnyMatchingPalaute(@Param("value") int value);

	@Query("SELECT p FROM Palaute p WHERE "
			+ "(:value is null or p.vastaus = :value)" + "AND" + "(:kurssi is null or p.kurssi = :kurssi)")
	List<Palaute> findPalauteByValueAndKurssi(@Param("value") int value, @Param("kurssi") Kurssi kurssi);

	@Query("SELECT p FROM Palaute p WHERE "
			+ "(:value is null or p.vastaus = :value)" + "AND" + "(:kurssi is null or p.kurssi = :kurssi)" + "AND"
			+ "(:date is null or p.paivamaara = :date)")
	List<Palaute> findPalauteByValueAndKurssiAndDate(@Param("value") int value, @Param("kurssi") Kurssi kurssi,
			@Param("date") LocalDate date);

	@Query("SELECT p FROM Palaute p WHERE "
			+ "(:date is null or p.paivamaara = :date)")
	List<Palaute> findAnyMatchingDatePalaute(@Param("date") Date date);

	@Query("SELECT DISTINCT p.paivamaara FROM Palaute p")
	List<Palaute> findDistinctDates();

	@Query("SELECT p FROM Palaute p WHERE "
			+ "(:kurssi is null or p.kurssi = :kurssi)")
	List<Palaute> findPalautteet(@Param("kurssi") Kurssi kurssi);

	@Query("SELECT p FROM Palaute p WHERE "
			+ "(:kurssi is null or p.kurssi = :kurssi)" + "AND" + "(:date is null or p.paivamaara = :date)")
	List<Palaute> findAllPalautteetByIDAndDate(@Param("kurssi") Kurssi kurssi, @Param("date") LocalDate date);

	@Transactional
	@Modifying
	@Query("delete from Palaute p where p.kurssi=:kurssi")
	void deleteKurssilla(@Param("kurssi") Kurssi kurssi);
}
