module com.example.miniprojet_java {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;



    opens com.example.miniprojet_java to javafx.fxml;
    exports com.example.miniprojet_java;
    exports com.example.miniprojet_java.Controllers;
    opens com.example.miniprojet_java.Controllers to javafx.fxml;
}