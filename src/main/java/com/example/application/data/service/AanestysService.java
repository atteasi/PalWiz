package com.example.application.data.service;

import com.example.application.data.entity.AanestysAjankohta;
import com.example.application.data.entity.Kurssi;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service

public class AanestysService {
    private final AanestysRepository repository;

    public AanestysService(AanestysRepository repository) {
        super();
        this.repository = repository;
    }

    public Optional<AanestysAjankohta> get(Long id) {
        return repository.findById(id);
    }

    public AanestysAjankohta update(AanestysAjankohta entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<AanestysAjankohta> findByKurssi(Kurssi k) {
        return repository.findAanestysAjankohtaByKurssi(k);
    }

    public void poistaAanestykset(Kurssi kurssi) {
        repository.deleteAanestykset(kurssi);
    }

}
