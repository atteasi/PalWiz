package com.example.application.data.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.application.data.entity.Kurssi;

/**
 * Service used to communicate between the User and the Course Database
 */
@Service
public class KurssiService {
    private final KurssiRepository kurssiRepository;

    int nykyinenKurssiId;

    /**
     * Constructor for the KurssiService
     *
     * @param kr The course repository
     */
    public KurssiService(KurssiRepository kr) {
        super();
        kurssiRepository = kr;
    }

    /**
     * Method used to find all courses in the database
     *
     * @return List of courses
     */
    public List<Kurssi> findKurssit() {
        return kurssiRepository.findAll();
    }

    /**
     * Method used to find all courses in the database made by a certain User
     *
     * @param id The ID of the User that we want to find the courses from
     * @return List of courses
     */
    public List<Kurssi> findUserKurssit(long id) {
        return kurssiRepository.findKurssiByUserId(id);
    }

    /**
     * Method used to find a Course with its ID
     *
     * @param id ID of the course we want to find
     * @return A course
     */
    public Kurssi findKurssi(int id) {
        return kurssiRepository.findKurssiById(id);
    }

    /**
     * Method used to save Courses into the database
     *
     * @param kurssi Course we want to save
     */
    public void saveKurssi(Kurssi kurssi) {
        if (kurssi == null || kurssi.getNimi() == null) {
            System.err.println("Kurssi is null tai nimi null. Joku nyt mättää");
            System.out.println(kurssi.getNimi());
            return;
        }
        kurssiRepository.save(kurssi);
    }

    /**
     * Method used to set the ID of the course we want to save into memory for communication between views.
     *
     * @param kurssi Course we want to put into memory
     */
    public void setNykyinenKurssiId(int kurssi) {
        nykyinenKurssiId = kurssi;
    }

    /**
     * Method used to set the ID of the course we want to save into memory for communication between views.
     *
     * @return The Course in the memory
     */
    public int getNykyinenKurssiId() {
        return nykyinenKurssiId;
    }

    /**
     * Method used to delete Courses form the database
     *
     * @param kurssi Course we want to delete
     */
    public void poistaKurssi(Kurssi kurssi) {
        kurssiRepository.delete(kurssi);
    }

    /**
     * Method used to edit a Course inside the database
     *
     * @param kurssi Course we want to edit
     */
    public void muokkaaKurssia(Kurssi kurssi) {
        kurssiRepository.updateKurssi(kurssi.getNimi(), kurssi.getKoodi(),
                kurssi.getAloitusPvm(), kurssi.getLopetusPvm(), kurssi.getId());
    }

}
