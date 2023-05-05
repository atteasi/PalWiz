package com.example.application.data.entity;

import javax.persistence.*;
import java.sql.Time;

/**
 * AanestysAjankohta is a class that contains information when a certain class opens up for feedback
 *
 * @author Atte Asikainen
 */
@Entity
@Table(name = "aanestys")
public class AanestysAjankohta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne
    private Kurssi kurssi;

    //1=su, 2=ma, 3=ti..... 7=la
    private int paiva;
    private Time aanestysAlkaa;
    private Time aanestysLoppuu;

    /**
     * Constructor for AanestysAjankohta
     *
     * @param kurssi         The id of the Kurssi-class instance that we give feedback to
     * @param paiva          Integer-value to represent whick day the feedback will be open. 0 = Sunday, 1 = Monday and so on until 6 = Saturday
     * @param aanestysAlkaa  Time when the feedback starts
     * @param aanestysLoppuu Time when the feedback ends
     */
    public AanestysAjankohta(Kurssi kurssi, int paiva, Time aanestysAlkaa, Time aanestysLoppuu) {
        this.kurssi = kurssi;
        this.paiva = paiva;
        this.aanestysAlkaa = aanestysAlkaa;
        this.aanestysLoppuu = aanestysLoppuu;
    }

    /**
     * Empty constructor for AanestysAjankohta
     */
    public AanestysAjankohta() {
    }

    /**
     * Getter for AanestysAjankohta id
     *
     * @return id for the AanestysAjankohta
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for AanestysAjankohta id
     *
     * @param id The id we want to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for the day that the feedback will be open
     *
     * @return Integer value that represents the day
     */
    public int getPaiva() {
        return paiva;
    }

    /**
     * Setter for the day that the feedback will be open
     *
     * @param paiva The day we want to set as the feedback day
     */
    public void setPaiva(int paiva) {
        this.paiva = paiva;
    }

    /**
     * Getter that returns the time that the feedback starts
     *
     * @return Time value that depicts when the feedback starts
     */
    public Time getAanestysAlkaa() {
        return aanestysAlkaa;
    }

    /**
     * Setter that returns the time that the feedback starts
     *
     * @param aanestysAlkaa Time when then feedback starts
     */
    public void setAanestysAlkaa(Time aanestysAlkaa) {
        this.aanestysAlkaa = aanestysAlkaa;
    }

    /**
     * Getter that returns the time that the feedback ends
     *
     * @return Time value that depicts when the feedback ends
     */
    public Time getAanestysLoppuu() {
        return aanestysLoppuu;
    }

    /**
     * Getter for the Kurssi-ID that we give feedback to on this time
     *
     * @return The course we give feedback to
     */
    public Kurssi getKurssiId() {
        return kurssi;
    }

    /**
     * Setter for the Kurssi-ID that we give feedback to on this time
     *
     * @param kurssi The course we give feedback to
     */
    public void setKurssiId(Kurssi kurssi) {
        this.kurssi = kurssi;
    }

    /**
     * Setter that returns the time that the feedback ends
     *
     * @param aanestysLoppuu Time when then feedback ends
     */
    public void setAanestysLoppuu(Time aanestysLoppuu) {
        this.aanestysLoppuu = aanestysLoppuu;
    }
}
