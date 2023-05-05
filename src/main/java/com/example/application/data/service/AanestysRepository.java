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

/**
 * Interface for the feedback time repository
 */
public interface AanestysRepository extends JpaRepository<AanestysAjankohta, Long>, JpaSpecificationExecutor<AanestysAjankohta> {
    /**
     * Lists all the feedback times in the database based on the course id
     *
     * @param kurssi The course id that we want the times from
     * @return List of feedback times
     */
    List<AanestysAjankohta> findAanestysAjankohtaByKurssi(@Param("kurssi_id") Kurssi kurssi);

    @Transactional
    @Modifying
    @Query("delete from AanestysAjankohta p where p.kurssi=:kurssi")
	void deleteAanestykset(@Param("kurssi") Kurssi kurssi);
}
