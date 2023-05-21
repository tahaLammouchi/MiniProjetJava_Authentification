package com.example.miniprojet_java;

import com.example.miniprojet_java.MethodesStatiques;
import com.example.miniprojet_java.Statique.RoleUtilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField pseudo;
    @FXML
    private PasswordField password;

    private boolean verifierConnexion(String pseudo, String password) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/marathon", "root", "")) {
            String query = "SELECT COUNT(*) FROM utilisateurs WHERE pseudo = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, pseudo);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count > 0) {
                // L'utilisateur est authentifié, récupérer le champ "role"
                String roleQuery = "SELECT role FROM utilisateurs WHERE pseudo = ?";
                PreparedStatement roleStmt = conn.prepareStatement(roleQuery);
                roleStmt.setString(1, pseudo);

                ResultSet roleRs = roleStmt.executeQuery();
                roleRs.next();
                String role = roleRs.getString("role");
                RoleUtilisateur.role = role;

                // L'utilisateur est authentifié, récupérer le champ "Id"
                String idQuery = "SELECT id_utilisateur FROM utilisateurs WHERE pseudo = ?";
                PreparedStatement idStmt = conn.prepareStatement(idQuery);
                idStmt.setString(1, pseudo);

                ResultSet idRs = idStmt.executeQuery();
                idRs.next();
                Integer id = idRs.getInt("id_utilisateur");

                // Assigner le id à la variable statique RoleUtilisateur.id
                RoleUtilisateur.id = id;

                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    public void seConnecter(ActionEvent event) {
        String pseudoText = pseudo.getText();
        String passwordText = password.getText();

        // Vérifier que les champs sont remplis
        if (pseudoText.isEmpty() || passwordText.isEmpty()) {
            MethodesStatiques.afficherAlerteError("Champs non remplis", "Veuillez remplir tous les champs.");
            return;
        }

        // Vérifier les informations de connexion dans la base de données
        if (verifierConnexion(pseudoText, passwordText)) {
            // Connexion réussie, rediriger vers la page d'accueil
            try {
                System.out.println(RoleUtilisateur.id);
                System.out.println(RoleUtilisateur.role);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard_admin.fxml"));
                root = loader.load();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                MethodesStatiques.afficherAlerteError("Erreur de chargement de la page", "Une erreur s'est produite lors du chargement de la page d'accueil.");
            }
        } else {
            // Informations de connexion incorrectes, afficher une alerte
            MethodesStatiques.afficherAlerteError("Informations de connexion incorrectes", "Le pseudo ou le mot de passe est incorrect.");
        }
    }

    @FXML
    public void switchInscription(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("inscription.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }







}
