package com.example.miniprojet_java.Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Marathon {
    private SimpleIntegerProperty id;
    private SimpleStringProperty nom;
    private SimpleStringProperty lieu_depart;
    private SimpleStringProperty lieu_arrive;
    private SimpleIntegerProperty distance;
    private Courreur vainqueur = null;


    public Marathon(Integer id,String nom , String lieu_depart, String lieu_arrive, Integer distance) {
        this.id = new SimpleIntegerProperty(id);
        this.nom = new SimpleStringProperty(nom);
        this.lieu_depart = new SimpleStringProperty(lieu_depart);
        this.lieu_arrive = new SimpleStringProperty(lieu_arrive);
        this.distance = new SimpleIntegerProperty(distance);
    }

    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }
    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getNom() {
        return nom.get();
    }
    public void setNom(String nom) {
        this.nom.set(nom);
    }
    public SimpleStringProperty nomProperty() {
        return nom;
    }
    public String getLieuDep() {
        return lieu_depart.get();
    }
    public void setLieuDep(String lieuDep) {
        this.lieu_depart.set(lieuDep);
    }
    public SimpleStringProperty lieuDepProperty() {
        return lieu_depart;
    }
    public String getLieuArv() {
        return lieu_arrive.get();
    }
    public void setLieuArv(String lieuArv) {
        this.lieu_arrive.set(lieuArv);
    }
    public SimpleStringProperty lieuArvProperty() {
        return lieu_arrive;
    }
    public Integer getDistance() {
        return distance.get();
    }
    public void setDistance(Integer distance) {
        this.distance.set(distance);
    }
    public SimpleIntegerProperty distanceProperty() {
        return distance;
    }
    public Courreur getVainqueur() {
        return vainqueur;
    }

    public void setVainqueur(Courreur vainqueur) {
        this.vainqueur = vainqueur;
    }
}
