package com.example.application.data.entity;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

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
	private String aanestyspaivakoodi;
	private Time aanestysAlkaa;
	private Time aanestysLoppuu;
	//OpeID viel lisäks foreign keyks
	
	public Kurssi() {
		
	} 
	
	public Kurssi(String n, String k, Date ap, Date lp, String apk, Time aa, Time al) {
		nimi = n;
		koodi = k;
		aloitusPvm = ap;
		lopetusPvm = lp;
		aanestyspaivakoodi = apk;
		aanestysAlkaa = aa;
		aanestysLoppuu = al;
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
	
}
