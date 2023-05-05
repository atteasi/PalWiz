package com.example.application.data.entity;

import java.sql.Date;
import java.sql.Time;
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

    public Kurssi() {

    }

    public Kurssi(String n, String k, Date ap, Date lp, User user) {
        nimi = n;
        koodi = k;
        aloitusPvm = ap;
        lopetusPvm = lp;
        this.user = user;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

}
