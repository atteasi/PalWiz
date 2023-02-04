package com.example.application.views.oppitunnit;

import java.util.GregorianCalendar;
import java.util.List;

public class Kurssi {
    
    private String nimi;
    private List oppitunnit;
    private GregorianCalendar aloituspaiva;
    private GregorianCalendar lopetuspaiva;

    public Kurssi(String nimi, List ot, GregorianCalendar ap, GregorianCalendar lp){
        this.nimi = nimi;
    	oppitunnit = ot;
        aloituspaiva = ap;
        lopetuspaiva = lp;
    }
    
    public void addTunti(Oppitunti ot) {
    	oppitunnit.add(ot);
    }
}
