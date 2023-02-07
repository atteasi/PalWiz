package com.example.application.views.oppitunnit;

public class Oppitunti {

    private int id;
    private String nimi;
    private String status;
    private String date;
    private Palautetyyppi pt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNimi(){
        return nimi;
    }

    public void setNimi(String n){
        nimi = n;
    }

    public void setPt(Palautetyyppi pt) {
        this.pt = pt;
    }
    public Palautetyyppi getPt() {
        return pt;
    }
}
