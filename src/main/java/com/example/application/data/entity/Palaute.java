package com.example.application.data.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "palaute")
public class Palaute {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	private int vastaus;
	private LocalDate paivamaara; // oikeesti joku date tyyppi?
	// private int kurssiID; //foreign key

	public Palaute(int annettuVastaus, LocalDate paivamaara) {
		this.vastaus = annettuVastaus;
		this.paivamaara = paivamaara;
	}
	
	public Palaute() {}

	public void setAnnettuVastaus(int vastaus) {
		this.vastaus = vastaus;
	}

	public int getAnnettuVastaus() {
		return vastaus;
	}

	

	public LocalDate getPaivamaara() {
		return paivamaara;
	}

}
