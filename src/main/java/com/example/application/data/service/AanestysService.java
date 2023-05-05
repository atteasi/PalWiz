package com.example.application.data.service;

import com.example.application.data.entity.AanestysAjankohta;
import com.example.application.data.entity.Kurssi;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

/**
 * Service used to communicate between the user and the database
 */
@Service

public class AanestysService {
    private final AanestysRepository repository;

    /**
     * Constructor for the AanestysService
     *
     * @param repository The repository used to contact the database
     */
    public AanestysService(AanestysRepository repository) {
        super();
        this.repository = repository;
    }

    /**
     * Method used to find feedback times using the ID
     *
     * @param id The id of the feedback time we want
     * @return Feedback time
     */
    public Optional<AanestysAjankohta> get(Long id) {
        return repository.findById(id);
    }

    /**
     * Method to update feedback times
     *
     * @param entity The feedback time we want to update
     * @return the updated feedback
     */
    public AanestysAjankohta update(AanestysAjankohta entity) {
        return repository.save(entity);
    }

    /**
     * Method to delete feedback times
     *
     * @param id The id of the feedback we want to delete
     */
    public void delete(Long id) {
        repository.deleteById(id);
    }

    /**
     * Method to find feedback times based on a given course
     *
     * @param k The course which feedback times we want to find
     * @return List of the feedback times
     */
    public List<AanestysAjankohta> findByKurssi(Kurssi k) {
        return repository.findAanestysAjankohtaByKurssi(k);
    }

    public void poistaAanestykset(Kurssi kurssi) {
        repository.deleteAanestykset(kurssi);
    }

}
