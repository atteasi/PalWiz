package com.example.application.data.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kurssi")
public class Kurssi {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	private String nimi;
	private String koodi;
	private Date aloitusPvm;
	private Date lopetusPvm;
	//OpeID viel lisäks foreign keyks
	
	public Kurssi() {
		
	} 
	
	public Kurssi(String n, String k, Date ap, Date lp) {
		nimi = n;
		koodi = k;
		aloitusPvm = ap;
		lopetusPvm = lp;
	}

	public String getNimi() {
		return nimi;
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

	public int getID() {
		return id;
	}
	
}
