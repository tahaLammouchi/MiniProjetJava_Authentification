package com.example.miniprojet_java;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {
    public static Connection getConnexion() {
        Connection connection = null ;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/marathon", "root", "");
            System.out.println("connexion effectuee");
        } catch (Exception e) {
            System.out.println("impossible de se connecter ! ");
        }
        return connection ;
    }

}
