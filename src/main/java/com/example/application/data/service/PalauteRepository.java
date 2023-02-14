package com.example.application.data.service;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.application.data.entity.Palaute;



public interface PalauteRepository extends JpaRepository<Palaute, Long>,JpaSpecificationExecutor<Palaute>  {
    
    @Query("SELECT p FROM Palaute p WHERE "
		+"(:value is null or p.vastaus = :value)")
List<Palaute> findAnyMatchingPalaute(@Param("value") int value);
		
    
    


    
}
