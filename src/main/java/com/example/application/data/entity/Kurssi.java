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

/**
 * The Kurssi-class is used to depict the instance of the class that we want to give feedbact to
 */
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

    /**
     * Empty constructor for Kurssi
     */
    public Kurssi() {

    }

    /**
     * Constructor for Kurssi
     *
     * @param n    Name of the course
     * @param k    The feedback code of the course
     * @param ap   The start date of hte course
     * @param lp   the end date of the course
     * @param user The user that created the course
     */
    public Kurssi(String n, String k, Date ap, Date lp, User user) {
        nimi = n;
        koodi = k;
        aloitusPvm = ap;
        lopetusPvm = lp;
        this.user = user;

    }

    /**
     * Returns the name of this course
     *
     * @return the name of this course
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * Sets the name of this course.
     *
     * @param nimi the new name of this course
     */
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    /**
     * Returns the code that the course uses to get to the feedback.
     *
     * @return the code of this course
     */
    public String getKoodi() {
        return koodi;
    }

    /**
     * Sets the code that the course uses to get to the feedback.
     *
     * @param koodi the new code of this course
     */
    public void setKoodi(String koodi) {
        this.koodi = koodi;
    }

    /**
     * Returns the starting date of this course.
     *
     * @return the starting date of this course
     */
    public Date getAloitusPvm() {
        return aloitusPvm;
    }

    /**
     * Sets the starting date of this course.
     *
     * @param aloitusPvm the new starting date of this course
     */
    public void setAloitusPvm(Date aloitusPvm) {
        this.aloitusPvm = aloitusPvm;
    }

    /**
     * Returns the ending date of this course.
     *
     * @return the ending date of this course
     */
    public Date getLopetusPvm() {
        return lopetusPvm;
    }

    /**
     * Sets the ending date of this course.
     *
     * @param lopetusPvm the new ending date of this course
     */
    public void setLopetusPvm(Date lopetusPvm) {
        this.lopetusPvm = lopetusPvm;
    }

    /**
     * Returns the ID of this course.
     *
     * @return the ID of this course
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of this course.
     *
     * @param id the new ID of this course
     */
    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
}
