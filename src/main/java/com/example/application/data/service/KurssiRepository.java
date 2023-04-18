package com.example.application.data.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.example.application.data.entity.Kurssi;

public interface KurssiRepository extends JpaRepository<Kurssi, Long>, JpaSpecificationExecutor<Kurssi>  {

	Kurssi findKurssiById(@Param("id") int id);

	List<Kurssi> findKurssiByUserId(@Param("user_id") long id);
	
	@Transactional
	@Modifying
	@Query("UPDATE Kurssi k SET k.nimi = :nimi, k.koodi = :koodi,"
			+ "k.aloitusPvm = :aloituspvm, k.lopetusPvm = :lopetuspvm "
			+ "WHERE k.id = :id")
	void updateKurssi(@Param("nimi") String nimi, @Param("koodi") String koodi,  @Param("aloituspvm") Date aloitusPvm, @Param("lopetuspvm") Date lopetusPvm,
			@Param("id") int id);
}
