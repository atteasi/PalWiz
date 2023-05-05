package com.example.application.data.entity;

import javax.persistence.*;
import java.sql.Time;

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

    public AanestysAjankohta(Kurssi kurssi, int paiva, Time aanestysAlkaa, Time aanestysLoppuu) {
        this.kurssi = kurssi;
        this.paiva = paiva;
        this.aanestysAlkaa = aanestysAlkaa;
        this.aanestysLoppuu = aanestysLoppuu;
    }

    public AanestysAjankohta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaiva() {
        return paiva;
    }

    public void setPaiva(int paiva) {
        this.paiva = paiva;
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

    public Kurssi getKurssiId() {
        return kurssi;
    }

    public void setKurssiId(Kurssi kurssi) {
        this.kurssi = kurssi;
    }

    public void setAanestysLoppuu(Time aanestysLoppuu) {
        this.aanestysLoppuu = aanestysLoppuu;
    }
}
