package annaire.example.annuaire_client_lourd.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.SimpleStringProperty;

public class NewServices {
    private final SimpleStringProperty type_service;

    public NewServices(@JsonProperty("type_service") String type_service ){

        this.type_service = new SimpleStringProperty(type_service);
    }

    public String getType_service() {
        return type_service.get();
    }

    public SimpleStringProperty type_serviceProperty() {
        return type_service;
    }

    public void setType_service(String type_service) {
        this.type_service.set(type_service);
    }
}
