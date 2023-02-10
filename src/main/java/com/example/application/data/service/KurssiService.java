package com.example.application.data.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.application.data.entity.Kurssi;

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

	public void saveKurssi(Kurssi kurssi) {
		if (kurssi == null) {
			System.err.println("Kurssi is null. Joku nyt mättää");
			return;
		}
		kurssiRepository.save(kurssi);
	}

	public void pohjustaKurssit() {
		saveKurssi(new Kurssi("Suunnittelumallit", "Suunnittelumallit23", Date.valueOf("2023-01-01"), Date.valueOf("2023-08-01")));
	}
}