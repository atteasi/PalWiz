package com.example.application.data.service;

import com.example.application.data.entity.AanestysAjankohta;


import java.util.Optional;

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

}
