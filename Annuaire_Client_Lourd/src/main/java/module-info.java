module annaire.example.annuaire_client_lourd {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;


    opens annaire.example.annuaire_client_lourd to javafx.fxml;
    exports annaire.example.annuaire_client_lourd;
    exports annaire.example.annuaire_client_lourd.models;
}