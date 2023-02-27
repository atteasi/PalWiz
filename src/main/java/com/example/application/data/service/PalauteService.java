package com.example.application.data.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.application.data.entity.Palaute;

@Service

	
public class PalauteService {
	

	private final PalauteRepository palauteRepository;

	public PalauteService(PalauteRepository palauteRepository) {
		super();
		this.palauteRepository = palauteRepository;
	}

	public List<Palaute> findAllPalautteet() {
		return palauteRepository.findAll();
	}

	public List<Palaute> findAllGood(){
		return palauteRepository.findAnyMatchingPalaute(1);
	}

	public List<Palaute> findAllNeutral(){
		return palauteRepository.findAnyMatchingPalaute(2);
	}

	public List<Palaute> findAllBad(){
		return palauteRepository.findAnyMatchingPalaute(3);
	}


	public long countPalautteet() {
		return palauteRepository.count();
	}

	public void savePalaute(Palaute palaute) {
		if (palaute == null) {
			System.err.println("Palaute is null. Joku on pielessä?");
			return;
		}
		palauteRepository.save(palaute);
	}

}
