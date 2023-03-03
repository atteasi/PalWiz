package com.example.application.data.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.application.data.entity.Kurssi;

@Service
public class KurssiService {
	private final KurssiRepository kurssiRepository;

	int nykyinenKurssiId;

	public KurssiService(KurssiRepository kr) {
		super();
		kurssiRepository = kr;
	}

	public List<Kurssi> findKurssit() {
		return kurssiRepository.findAll();
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

	public void setNykyinenKurssiId(int kurssi) {
		nykyinenKurssiId = kurssi;
	}

	public int getNykyinenKurssiId() {
		return nykyinenKurssiId;
	}

}
