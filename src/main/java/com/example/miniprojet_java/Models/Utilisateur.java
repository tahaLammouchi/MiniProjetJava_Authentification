package com.example.miniprojet_java.Models;

public class Utilisateur {
    private Integer id = 0;
    private String pseudo;
    private String password;
    private String role;

    public Utilisateur(String pseudo, String password) {
        this.pseudo = pseudo;
        this.password = password;
        this.role = "COURREUR";
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
