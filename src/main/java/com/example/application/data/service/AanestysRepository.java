package com.example.application.data.service;

import com.example.application.data.entity.AanestysAjankohta;
import com.example.application.data.entity.Kurssi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

public interface AanestysRepository extends JpaRepository<AanestysAjankohta, Long>, JpaSpecificationExecutor<AanestysAjankohta> {
    //AanestysAjankohta findAanestysAjankohtaByKurssiId(@Param("kurssi_id") int id);

}
