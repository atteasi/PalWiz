package com.example.application.data.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.application.data.entity.Kurssi;
import com.example.application.data.entity.Palaute;

@Service
public class KurssiService {
	private final KurssiRepository kurssiRepository;
	
	public KurssiService(KurssiRepository kr) {
		super();
		kurssiRepository = kr;
	}

	public List<Kurssi> findKurssit() {
		return kurssiRepository.findAll();
	}

	public List<Kurssi> findUserKurssit(long id) {
		return kurssiRepository.findKurssiByUserId(id);
	}

	public Kurssi findKurssi(int id) {
		return kurssiRepository.findKurssiById(id);
	}

	


	public void saveKurssi(Kurssi kurssi) {
		if (kurssi == null) {
			System.err.println("Kurssi is null. Joku nyt mättää");
			return;
		}
		kurssiRepository.save(kurssi);
	}

}
