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

/**
 * Interface for the feedback repository
 */
public interface PalauteRepository extends JpaRepository<Palaute, Long>, JpaSpecificationExecutor<Palaute> {
    /**
     * Query to the database to find all feedback that match the given value
     *
     * @param value The value of feedbacks we want to find
     * @return List of feedbacks
     */
    @Query("SELECT p FROM Palaute p WHERE "
            + "(:value is null or p.vastaus = :value)")
    List<Palaute> findAnyMatchingPalaute(@Param("value") int value);

    /**
     * Query to the databade to find all feedback that match the given value from a certain course
     *
     * @param value  The value of feedbacks we want to find
     * @param kurssi The course that we want to find the feedback from
     * @return List of feedbacks
     */
    @Query("SELECT p FROM Palaute p WHERE "
            + "(:value is null or p.vastaus = :value)" + "AND" + "(:kurssi is null or p.kurssi = :kurssi)")
    List<Palaute> findPalauteByValueAndKurssi(@Param("value") int value, @Param("kurssi") Kurssi kurssi);

    /**
     * Query to the databade to find all feedback that match the given value from a certain course on a given date
     *
     * @param value  The value of feedbacks we want to find
     * @param kurssi The course that we want to find the feedback from
     * @param date   The date that we want to find the feedback from
     * @return List of feedbacks
     */
    @Query("SELECT p FROM Palaute p WHERE "
            + "(:value is null or p.vastaus = :value)" + "AND" + "(:kurssi is null or p.kurssi = :kurssi)" + "AND"
            + "(:date is null or p.paivamaara = :date)")
    List<Palaute> findPalauteByValueAndKurssiAndDate(@Param("value") int value, @Param("kurssi") Kurssi kurssi,
                                                     @Param("date") LocalDate date);

    /**
     * Query to the database to find all feedback on a given date
     *
     * @param date The date that we want to find the feedback from
     * @return List of feedbacks
     */
    @Query("SELECT p FROM Palaute p WHERE "
            + "(:date is null or p.paivamaara = :date)")
    List<Palaute> findAnyMatchingDatePalaute(@Param("date") Date date);

    /**
     * Query to the database to find all feedback on a distinct date
     *
     * @return List of feedbacks
     */
    @Query("SELECT DISTINCT p.paivamaara FROM Palaute p")
    List<Palaute> findDistinctDates();

    /**
     * Query to the database to find all the feedbacks from a certain course
     *
     * @param kurssi The course that we want to find the feedback from
     * @return List of feedbacks
     */
    @Query("SELECT p FROM Palaute p WHERE "
            + "(:kurssi is null or p.kurssi = :kurssi)")
    List<Palaute> findPalautteet(@Param("kurssi") Kurssi kurssi);

    /**
     * Query to the database to find all the feedbacks with a certain id and date
     *
     * @param kurssi The course that we want to find the feedback from
     * @param date   The date that we want to find the feedback from
     * @return List of feedbacks
     */
    @Query("SELECT p FROM Palaute p WHERE "
            + "(:kurssi is null or p.kurssi = :kurssi)" + "AND" + "(:date is null or p.paivamaara = :date)")
    List<Palaute> findAllPalautteetByIDAndDate(@Param("kurssi") Kurssi kurssi, @Param("date") LocalDate date);

    /**
     * Query to the database to delete all the feedbacks from a certain course
     *
     * @param kurssi The course that we want to delete the feedbacks from
     */
    @Transactional
    @Modifying
    @Query("delete from Palaute p where p.kurssi=:kurssi")
    void deleteKurssilla(@Param("kurssi") Kurssi kurssi);
}
