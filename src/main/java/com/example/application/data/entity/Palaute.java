package com.example.application.data.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
	@ManyToOne
	private Kurssi kurssi;

	public Palaute(int annettuVastaus, LocalDate paivamaara) {
		this.vastaus = annettuVastaus;
		this.paivamaara = paivamaara;
	}

	public Palaute() {
	}
	
	public void setAnnettuVastaus(int vastaus) {
		this.vastaus = vastaus;
	}

	public int getAnnettuVastaus() {
		return vastaus;
	}

	public LocalDate getPaivamaara() {
		return paivamaara;
	}

	public String toString() {
		return "palaute id: " + id + ", kurssi: " + kurssi.getNimi() + ", vastaus: " + vastaus;
	}

}
