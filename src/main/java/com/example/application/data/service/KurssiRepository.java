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

/**
 * Interface for the course repository
 */
public interface KurssiRepository extends JpaRepository<Kurssi, Long>, JpaSpecificationExecutor<Kurssi> {

    /**
     * Finds courses from the database based on ID
     *
     * @param id The ID of the course we want to find
     * @return The course we want
     */
    Kurssi findKurssiById(@Param("id") int id);

    /**
     * Finds courses from the database based on User ID
     *
     * @param id The User ID of the courses we want
     * @return A list of the courses that have the wanted User ID
     */
    List<Kurssi> findKurssiByUserId(@Param("user_id") long id);

    @Transactional
    @Modifying
    @Query("UPDATE Kurssi k SET k.nimi = :nimi, k.koodi = :koodi,"
            + "k.aloitusPvm = :aloituspvm, k.lopetusPvm = :lopetuspvm "
            + "WHERE k.id = :id")
    /**
     * Updates courses in the database with new information
     */
    void updateKurssi(@Param("nimi") String nimi, @Param("koodi") String koodi,
                      @Param("aloituspvm") Date aloitusPvm, @Param("lopetuspvm") Date lopetusPvm,
                      @Param("id") int id);
}
