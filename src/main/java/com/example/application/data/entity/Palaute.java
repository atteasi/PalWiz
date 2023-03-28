package com.example.application.data.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "palaute")
public class Palaute {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	private int vastaus;
	private LocalDate paivamaara;
	@ManyToOne
	private Kurssi kurssi;
	@Transient
	private int kokonaismaara;

	public Palaute(int annettuVastaus, LocalDate paivamaara, Kurssi kurssi) {
		this.vastaus = annettuVastaus;
		this.paivamaara = paivamaara;
		this.kurssi = kurssi;
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

	public int getKokonaismaara() {
		return kokonaismaara;
	}

	public void setKokonaismaara(int maara) {
		kokonaismaara = maara;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return "palaute id: " + id + ", kurssi: " + kurssi.getNimi() + ", vastaus: "
				+ vastaus;
	}

}
