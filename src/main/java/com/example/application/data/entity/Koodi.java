package com.example.application.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "koodi")
public class Koodi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	private String koodi;
	
	public Koodi(String k) {
		koodi = k;
	}
	
	public Koodi() {
		 
	}
	
	public void setKoodi(String k) {
		koodi = k;
	}

	public String getKoodi() {
		return koodi;
	}
	
	
}
