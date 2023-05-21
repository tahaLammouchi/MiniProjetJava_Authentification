package com.example.miniprojet_java;

import com.example.miniprojet_java.Models.Marathon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DashboardAdminController implements Initializable {
    @FXML
    private TextField id;
    @FXML
    private TextField nom;
    @FXML
    private TextField lieu_dep;
    @FXML
    private TextField lieu_arv;
    @FXML
    private TextField distance;

    @FXML
    TableView<Marathon> table = new TableView<>();
    @FXML
    private TableColumn<Marathon, Integer> id_column;
    @FXML
    private TableColumn<Marathon, String> nom_column = new TableColumn<>();
    @FXML
    private TableColumn<Marathon, String> lieu_dep_column = new TableColumn<>();
    @FXML
    private TableColumn<Marathon,String> lieu_arv_column = new TableColumn<>();
    @FXML
    private TableColumn<Marathon,String> distance_column = new TableColumn<>();
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setPromptText("ID");
        nom.setPromptText("Nom");
        lieu_dep.setPromptText("Lieu de départ");
        lieu_arv.setPromptText("Lieu d'arrivée");
        distance.setPromptText("Distance");
        // Configurer les cellules des colonnes avec les propriétés de la classe Marathon
        id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_column.setCellValueFactory(new PropertyValueFactory<>("nom"));
        lieu_dep_column.setCellValueFactory(new PropertyValueFactory<>("lieu_depart"));
        lieu_arv_column.setCellValueFactory(new PropertyValueFactory<>("lieu_arrive"));
        distance_column.setCellValueFactory(new PropertyValueFactory<>("distance"));

        // Récupérer les données des marathons depuis la base de données et les afficher dans la TableView
        Connection con = ConnectionDB.getConnexion();
        String sql = "SELECT * FROM marathon";
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String lieuDep = resultSet.getString("lieu_dep");
                String lieuArv = resultSet.getString("lieu_arv");
                Integer distance = resultSet.getInt("distance");

                Marathon marathon = new Marathon(id,nom, lieuDep, lieuArv, distance);
                table.getItems().add(marathon);
            }
            table.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'exécution de la requête SQL : " + e.getMessage());
        }
    }

    @FXML
    public void ajouter() {
        if (id.getText().isEmpty() || nom.getText().isEmpty() || lieu_dep.getText().isEmpty()
                || lieu_arv.getText().isEmpty() || distance.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Form Error!");
            alert.setHeaderText(null);
            alert.setContentText("Priére de renseigner les champs manquants");
            alert.showAndWait();
        } else {
            String nomText = nom.getText();
            String lieu_depText = lieu_dep.getText(); // Correction : lieu_dep au lieu de lieu_arv
            String lieu_arvText = lieu_arv.getText();
            Integer distanceValue = Integer.parseInt(distance.getText());
            Integer idValue = Integer.parseInt(id.getText());

            PreparedStatement st = null;
            ResultSet rs = null;
            Connection con = ConnectionDB.getConnexion();
            String sql = "INSERT INTO marathon (id, nom, lieu_dep, lieu_arv, distance) VALUES (?, ?, ?, ?, ?)";
            try {
                st = con.prepareStatement(sql);
                st.setInt(1, idValue);
                st.setString(2, nomText);
                st.setString(3, lieu_depText);
                st.setString(4, lieu_arvText);
                st.setInt(5, distanceValue);
                st.executeUpdate();
                nom.clear();
                lieu_dep.clear();
                lieu_arv.clear();
                distance.clear();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ajout réussie");
                alert.setHeaderText(null);
                alert.setContentText("Le marathon a été ajouté avec succès.");
                alert.showAndWait();

                // Actualiser la TableView avec toutes les données de la base de données
                table.getItems().clear(); // Effacer les données précédentes de la TableView

                // Récupérer les données des marathons depuis la base de données et les afficher dans la TableView
                String selectAllSql = "SELECT * FROM marathon";
                try (Statement selectAllStatement = con.createStatement();
                     ResultSet resultSet = selectAllStatement.executeQuery(selectAllSql)) {
                    while (resultSet.next()) {
                        Integer id = resultSet.getInt("id");
                        String nom = resultSet.getString("nom");
                        String lieuDep = resultSet.getString("lieu_dep");
                        String lieuArv = resultSet.getString("lieu_arv");
                        Integer distance = resultSet.getInt("distance");

                        Marathon marathon = new Marathon(id,nom, lieuDep, lieuArv, distance);
                        table.getItems().add(marathon);
                    }
                    table.refresh();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.err.println("Erreur lors de l'exécution de la requête SQL : " + e.getMessage());
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SQL : " + e.getMessage());
            }
        }
    }

    @FXML
    public void modifier() {
        if (id.getText().isEmpty() || nom.getText().isEmpty() || lieu_dep.getText().isEmpty()
                || lieu_arv.getText().isEmpty() || distance.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Form Error!");
            alert.setHeaderText(null);
            alert.setContentText("Priére de renseigner les champs manquants");
            alert.showAndWait();
        } else {
            String nomText = nom.getText();
            String lieu_depText = lieu_dep.getText();
            String lieu_arvText = lieu_arv.getText();
            Integer distanceValue = Integer.parseInt(distance.getText());
            Integer idValue = Integer.parseInt(id.getText());

            PreparedStatement st = null;
            Connection con = ConnectionDB.getConnexion();
            String sql = "UPDATE marathon SET nom = ?, lieu_dep = ?, lieu_arv = ?, distance = ? WHERE id = ?";
            try {
                st = con.prepareStatement(sql);
                st.setString(1, nomText);
                st.setString(2, lieu_depText);
                st.setString(3, lieu_arvText);
                st.setInt(4, distanceValue);
                st.setInt(5, idValue);
                st.executeUpdate();
                nom.clear();
                lieu_dep.clear();
                lieu_arv.clear();
                distance.clear();
                id.clear();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Modification réussite");
                alert.setHeaderText(null);
                alert.setContentText("Le marathon a été modifié avec succès.");
                alert.showAndWait();

                // Actualiser la TableView avec toutes les données de la base de données
                table.getItems().clear(); // Effacer les données précédentes de la TableView

                // Récupérer les données des marathons depuis la base de données et les afficher dans la TableView
                String selectAllSql = "SELECT * FROM marathon";
                try (Statement selectAllStatement = con.createStatement();
                     ResultSet resultSet = selectAllStatement.executeQuery(selectAllSql)) {
                    while (resultSet.next()) {
                        Integer marathonId = resultSet.getInt("id");
                        String marathonNom = resultSet.getString("nom");
                        String marathonLieuDep = resultSet.getString("lieu_dep");
                        String marathonLieuArv = resultSet.getString("lieu_arv");
                        Integer marathonDistance = resultSet.getInt("distance");

                        Marathon marathon = new Marathon(marathonId,marathonNom, marathonLieuDep, marathonLieuArv, marathonDistance);
                        table.getItems().add(marathon);
                    }
                    table.refresh();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.err.println("Erreur lors de l'exécution de la requête SQL : " + e.getMessage());
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SQL : " + e.getMessage());
            }
        }
    }

    @FXML
    public void supprimer() {
        Marathon marathonSelectionne = table.getSelectionModel().getSelectedItem();
        if (marathonSelectionne == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sélection invalide");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un marathon à supprimer.");
            alert.showAndWait();
        } else {
            Integer idMarathon = marathonSelectionne.getId();

            PreparedStatement st = null;
            Connection con = ConnectionDB.getConnexion();
            String sql = "DELETE FROM marathon WHERE id = ?";
            try {
                st = con.prepareStatement(sql);
                st.setInt(1, idMarathon);
                st.executeUpdate();

                // Supprimer le marathon de la TableView
                table.getItems().remove(marathonSelectionne);
                table.refresh();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Suppression réussie");
                alert.setHeaderText(null);
                alert.setContentText("Le marathon a été supprimé avec succès.");
                alert.showAndWait();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SQL : " + e.getMessage());
            }
        }
    }
    @FXML
    public void getData(MouseEvent mouseEvent) {
        Marathon m = table.getSelectionModel().getSelectedItem();
        Integer selectedId = m.getId();
        id.setText(String.valueOf(selectedId));
        nom.setText(m.getNom());
        lieu_dep.setText(m.getLieuDep());
        lieu_arv.setText(m.getLieuArv());
        distance.setText(String.valueOf(m.getDistance()));

    }
}
