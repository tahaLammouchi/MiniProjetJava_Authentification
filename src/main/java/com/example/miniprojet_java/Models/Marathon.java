package com.example.miniprojet_java.Models;

import java.util.Date;

public class Marathon {
    private Integer id = 0 ;
    private String nom;
    private Date date;
    private String lieu_depart;
    private String lieu_arrive;
    private Integer Distance;
    private Courreur vainqueur = null;


    public Marathon(String nom, Date date, String lieu_depart, String lieu_arrive, Integer distance) {
        this.nom = nom;
        this.date = date;
        this.lieu_depart = lieu_depart;
        this.lieu_arrive = lieu_arrive;
        Distance = distance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu_depart() {
        return lieu_depart;
    }

    public void setLieu_depart(String lieu_depart) {
        this.lieu_depart = lieu_depart;
    }

    public String getLieu_arrive() {
        return lieu_arrive;
    }

    public void setLieu_arrive(String lieu_arrive) {
        this.lieu_arrive = lieu_arrive;
    }

    public Integer getDistance() {
        return Distance;
    }

    public void setDistance(Integer distance) {
        Distance = distance;
    }
    public Courreur getVainqueur() {
        return vainqueur;
    }

    public void setVainqueur(Courreur vainqueur) {
        this.vainqueur = vainqueur;
    }
}
