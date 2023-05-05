package com.example.application.data.service;

import com.example.application.data.entity.AanestysAjankohta;
import com.example.application.data.entity.Kurssi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AanestysRepository extends JpaRepository<AanestysAjankohta, Long>, JpaSpecificationExecutor<AanestysAjankohta> {
    List<AanestysAjankohta> findAanestysAjankohtaByKurssi(@Param("kurssi_id") Kurssi kurssi);

    @Transactional
    @Modifying
    @Query("delete from AanestysAjankohta p where p.kurssi=:kurssi")
	void deleteAanestykset(@Param("kurssi") Kurssi kurssi);
}
