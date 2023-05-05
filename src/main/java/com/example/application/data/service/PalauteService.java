package com.example.application.data.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.application.data.entity.Kurssi;
import com.example.application.data.entity.Palaute;

/**
 * Service used to communicate between the User and the Feedback database
 */
@Service
public class PalauteService {

    LocalDate nykyinenPalaute;

    private final PalauteRepository palauteRepository;

    /**
     * Constructor for the PalauteService
     *
     * @param palauteRepository Repository of Feedbacks
     */
    public PalauteService(PalauteRepository palauteRepository) {
        super();
        this.palauteRepository = palauteRepository;
    }

    /**
     * Method used to find all good feedbacks from a certain course
     *
     * @param kurssi Course we want to find the good feedbacks from
     * @return List of feedbacks
     */
    public List<Palaute> findAllGoodByID(Kurssi kurssi) {
        return palauteRepository.findPalauteByValueAndKurssi(1, kurssi);
    }

    /**
     * Method used to find all neutral feedbacks from a certain course
     *
     * @param kurssi Course we want to find the neutral feedbacks from
     * @return List of feedbacks
     */
    public List<Palaute> findAllNeutralByID(Kurssi kurssi) {
        return palauteRepository.findPalauteByValueAndKurssi(2, kurssi);
    }

    /**
     * Method used to find all bad feedbacks from a certain course
     *
     * @param kurssi Course we want to find the bad feedbacks from
     * @return List of feedbacks
     */
    public List<Palaute> findAllBadByID(Kurssi kurssi) {
        return palauteRepository.findPalauteByValueAndKurssi(3, kurssi);
    }

    /**
     * Method used to find all feedbacks from a certain course
     *
     * @param kurssi The course we want the feedbacks from
     * @return List of feedbacks
     */
    public List<Palaute> findPalautteetByKurssi(Kurssi kurssi) {
        return palauteRepository.findPalautteet(kurssi);
    }

    /**
     * Method used to find all good feedbacks from a certain course and a certain date
     *
     * @param kurssi The course we want the feedbacks from
     * @param date   The date we want the feedbacks from
     * @return List of feedbacks
     */
    public List<Palaute> findAllGoodByIDAndDate(Kurssi kurssi, LocalDate date) {
        return palauteRepository.findPalauteByValueAndKurssiAndDate(1, kurssi, date);
    }

    /**
     * Method used to find all neutral feedbacks from a certain course and a certain date
     *
     * @param kurssi The course we want the feedbacks from
     * @param date   The date we want the feedbacks from
     * @return List of feedbacks
     */
    public List<Palaute> findAllNeutralByIDAndDate(Kurssi kurssi, LocalDate date) {
        return palauteRepository.findPalauteByValueAndKurssiAndDate(2, kurssi, date);
    }

    /**
     * Method used to find all neutral feedbacks from a certain course and a certain date
     *
     * @param kurssi The course we want the feedbacks from
     * @param date   The date we want the feedbacks from
     * @return List of feedbacks
     */
    public List<Palaute> findAllBadByIDAndDate(Kurssi kurssi, LocalDate date) {
        return palauteRepository.findPalauteByValueAndKurssiAndDate(3, kurssi, date);
    }

    /**
     * Method used to find the amount of feedbacks from a certain course by date
     *
     * @param kurssi Course we want the feedback amount from
     * @param date   The date we want the feedback amount from
     * @return
     */
    public int countAllPalautteetByIDAndDate(Kurssi kurssi, LocalDate date) {
        List<Palaute> lista = palauteRepository.findAllPalautteetByIDAndDate(kurssi, date);
        return lista.size();
    }

    /**
     * Method used to set the date that we want to see the feedbacks from
     *
     * @param date the date that we want to see the feedbacks from
     */
    public void setNykyinenPalautePvm(LocalDate date) {
        nykyinenPalaute = date;
    }

    /**
     * Method used to get the date that we want to see the feedbacks from
     *
     * @return The date we are seeing the feedbacks from
     */
    public LocalDate getNykyinenPalautePvm() {
        return nykyinenPalaute;
    }

    /**
     * Method used to save feedback into the database
     *
     * @param palaute The feedback we want to save
     */
    public void savePalaute(Palaute palaute) {
        if (palaute == null) {
            System.err.println("Palaute is null. Joku on pielessä?");
            return;
        }
        palauteRepository.save(palaute);
    }

    /**
     * Method used to delete all feedbacks from a course
     *
     * @param kurssi The course that we want to delete the feedbacks from
     */
    public void poistaPalauteet(Kurssi kurssi) {
        palauteRepository.deleteKurssilla(kurssi);
    }

}
