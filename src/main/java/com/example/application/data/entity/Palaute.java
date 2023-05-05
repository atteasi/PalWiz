package com.example.application.data.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

	/*public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVastaus() {
		return vastaus;
	}

	public void setVastaus(int vastaus) {
		this.vastaus = vastaus;
	}*/

	public void setPaivamaara(LocalDate paivamaara) {
		this.paivamaara = paivamaara;
	}

	public Kurssi getKurssi() {
		return kurssi;
	}

	public void setKurssi(Kurssi kurssi) {
		this.kurssi = kurssi;
	}

	public int getKokonaismaara() {
		return kokonaismaara;
	}

	public void setKokonaismaara(int maara) {
		kokonaismaara = maara;
	}

	/*public String toString() {
		return "palaute id: " + id + ", kurssi: " + kurssi.getNimi() + ", vastaus: "
				+ vastaus;
	}*/

}
