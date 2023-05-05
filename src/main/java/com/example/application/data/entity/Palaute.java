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

/**
 * Represents feedback given for a course.
 */
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

    /**
     * Constructs a new Palaute object with the given values.
     *
     * @param annettuVastaus the feedback score given
     * @param paivamaara     the date when the feedback was given
     * @param kurssi         the course for which the feedback was given
     */
    public Palaute(int annettuVastaus, LocalDate paivamaara, Kurssi kurssi) {
        this.vastaus = annettuVastaus;
        this.paivamaara = paivamaara;
        this.kurssi = kurssi;
    }

    /**
     * Constructs a new Palaute object.
     */
    public Palaute() {
    }

    /**
     * Sets the feedback score for this object.
     *
     * @param vastaus the feedback score to set
     */
    public void setAnnettuVastaus(int vastaus) {
        this.vastaus = vastaus;
    }

    /**
     * Returns the feedback score for this object.
     *
     * @return the feedback score for this object
     */
    public int getAnnettuVastaus() {
        return vastaus;
    }

    /**
     * Returns the date when the feedback was given for this object.
     *
     * @return the date when the feedback was given for this object
     */
    public LocalDate getPaivamaara() {
        return paivamaara;
    }

    /**
     * Returns the ID of this object.
     *
     * @return the ID of this object
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of this object.
     *
     * @param id the ID to set for this object
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the feedback score for this object.
     *
     * @return the feedback score for this object
     */
    public int getVastaus() {
        return vastaus;
    }

    /**
     * Sets the feedback score for this object.
     *
     * @param vastaus the feedback score to set for this object
     */
    public void setVastaus(int vastaus) {
        this.vastaus = vastaus;
    }

    /**
     * Sets the date when the feedback was given for this object.
     *
     * @param paivamaara the date when the feedback was given to set for this object
     */
    public void setPaivamaara(LocalDate paivamaara) {
        this.paivamaara = paivamaara;
    }

    /**
     * Returns the course for which the feedback was given for this object.
     *
     * @return the course for which the feedback was given for this object
     */
    public Kurssi getKurssi() {
        return kurssi;
    }

    /**
     * Sets the course for which the feedback was given for this object.
     *
     * @param kurssi the course for which the feedback was given to set for this object
     */
    public void setKurssi(Kurssi kurssi) {
        this.kurssi = kurssi;
    }

    /**
     * Returns the total number of responses for this feedback.
     *
     * @return the total number of responses for this feedback
     */
    public int getKokonaismaara() {
        return kokonaismaara;
    }

    /**
     * Sets the total number of responses for this feedback.
     *
     * @param maara the total number of responses for this feedback
     */
    public void setKokonaismaara(int maara) {
        kokonaismaara = maara;
    }

    /**
     * Returns a string representation of this feedback object, including its ID, course name, and response.
     *
     * @return a string representation of this feedback object, including its ID, course name, and response
     */
    public String toString() {
        return "palaute id: " + id + ", kurssi: " + kurssi.getNimi() + ", vastaus: "
                + vastaus;
    }

}
