package com.example.application.data.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "kurssi")
public class Kurssi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne
	private User user;
	private String nimi;
	private String koodi;
	private Date aloitusPvm;
	private Date lopetusPvm;
	private String aanestyspaivakoodi;
	private Time aanestysAlkaa;
	private Time aanestysLoppuu;

	public Kurssi() {

	}

	public Kurssi(String nimi, String koodi, Date alkuPvm, Date loppuPvm, String aanestysPaivaKoodi, Time aanestysAlkaa,
			Time aanestysLoppuu, User user) {
		this.nimi = nimi;
		this.koodi = koodi;
		this.aloitusPvm = alkuPvm;
		this.lopetusPvm = loppuPvm;
		this.aanestyspaivakoodi = aanestysPaivaKoodi;
		this.aanestysAlkaa = aanestysAlkaa;
		this.aanestysLoppuu = aanestysLoppuu;
		this.user = user;

	}

	public Time getAanestysAlkaa() {
		return aanestysAlkaa;
	}

	public void setAanestysAlkaa(Time aanestysAlkaa) {
		this.aanestysAlkaa = aanestysAlkaa;
	}

	public Time getAanestysLoppuu() {
		return aanestysLoppuu;
	}

	public void setAanestysLoppuu(Time aanestysLoppuu) {
		this.aanestysLoppuu = aanestysLoppuu;
	}

	public String getNimi() {
		return nimi;
	}

	public String getAanestyspaivakoodi() {
		return aanestyspaivakoodi;
	}

	public void setAanestyspaivakoodi(String aanestyspaivakoodi) {
		this.aanestyspaivakoodi = aanestyspaivakoodi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public String getKoodi() {
		return koodi;
	}

	public void setKoodi(String koodi) {
		this.koodi = koodi;
	}

	public Date getAloitusPvm() {
		return aloitusPvm;
	}

	public void setAloitusPvm(Date aloitusPvm) {
		this.aloitusPvm = aloitusPvm;
	}

	public Date getLopetusPvm() {
		return lopetusPvm;
	}

	public void setLopetusPvm(Date lopetusPvm) {
		this.lopetusPvm = lopetusPvm;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
