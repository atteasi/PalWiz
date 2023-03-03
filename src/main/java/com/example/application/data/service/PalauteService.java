package com.example.application.data.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

import com.example.application.data.entity.Kurssi;
import com.example.application.data.entity.Palaute;

@Service

	
public class PalauteService {
	
	LocalDate nykyinenPalaute;

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

	public List<Palaute> findAllGoodByID(Kurssi kurssi){
		return palauteRepository.findPalauteByValueAndKurssi(1,  kurssi);
	}

	public List<Palaute> findAllNeutralByID(Kurssi kurssi){
		return palauteRepository.findPalauteByValueAndKurssi(2,  kurssi);
	}

	public List<Palaute> findAllBadByID(Kurssi kurssi){
		return palauteRepository.findPalauteByValueAndKurssi(3, kurssi);
	}

	public List<Palaute> findByDate(Date date) {
		return palauteRepository.findAnyMatchingDatePalaute(date);
	}

	public List<Palaute> findDistinctDate() {
		return palauteRepository.findDistinctDates();
	}

	public List<Palaute> findPalautteetByKurssi(Kurssi kurssi) {
		return palauteRepository.findPalautteet(kurssi);
	}

	public List<Palaute> findAllGoodByIDAndDate(Kurssi kurssi, LocalDate date){
		return palauteRepository.findPalauteByValueAndKurssiAndDate(1,  kurssi, date);
	}

	public List<Palaute> findAllNeutralByIDAndDate(Kurssi kurssi, LocalDate date){
		return palauteRepository.findPalauteByValueAndKurssiAndDate(2,  kurssi, date);
	}

	public List<Palaute> findAllBadByIDAndDate(Kurssi kurssi, LocalDate date){
		return palauteRepository.findPalauteByValueAndKurssiAndDate(3,  kurssi, date);
	}

	public long countPalautteet() {
		return palauteRepository.count();
	}

	public int countAllPalautteetByIDAndDate(Kurssi kurssi, LocalDate date) {
		List<Palaute> lista = palauteRepository.findAllPalautteetByIDAndDate(kurssi, date);
		return lista.size();
	}

	public void setNykyinenPalautePvm(LocalDate date) {
		nykyinenPalaute = date;
	}

	public LocalDate getNykyinenPalautePvm() {
		return nykyinenPalaute;
	}

	public void savePalaute(Palaute palaute) {
		if (palaute == null) {
			System.err.println("Palaute is null. Joku on pielessä?");
			return;
		}
		palauteRepository.save(palaute);
	}

}
