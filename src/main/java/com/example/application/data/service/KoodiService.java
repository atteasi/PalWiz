package com.example.application.data.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.application.data.entity.Koodi;

@Service
public class KoodiService {

	private final KoodiRepository koodiRepository;

	public KoodiService(KoodiRepository kr) {
		super();
		koodiRepository = kr;
	}

	public List<Koodi> findKoodit() {
		return koodiRepository.findAll();
	}

	public void pohjustaKoodit() {
		Koodi a = new Koodi("aanesta");
		koodiRepository.save(a);
		koodiRepository.save(new Koodi("kakka"));
		koodiRepository.save(new Koodi("moi"));
	}
	/*
	 * public void savePalaute(Palaute palaute) { if (palaute == null) {
	 * System.err.println("Palaute is null. Joku on pielessä?"); return; }
	 * koodiRepository.save(palaute); }
	 */
}
