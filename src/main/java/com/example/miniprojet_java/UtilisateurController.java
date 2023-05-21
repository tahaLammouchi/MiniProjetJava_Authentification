package com.example.miniprojet_java;

import com.example.miniprojet_java.Models.Utilisateur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UtilisateurController {
    public boolean inscrireUtilisateur(Utilisateur utilisateur) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/marathon", "root", "")) {
            String query = "INSERT INTO utilisateurs (pseudo, password,role) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, utilisateur.getPseudo());
            stmt.setString(2, utilisateur.getPassword());
            stmt.setString(3, "COURREUR");


            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
