package annaire.example.annuaire_client_lourd.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ServicesDTO {
    private final SimpleIntegerProperty id_service;
    private final SimpleStringProperty type_service;

    public ServicesDTO(@JsonProperty("id_service") Integer id_service,
                       @JsonProperty("type_service") String type_service ){

        this.id_service = new SimpleIntegerProperty(id_service);
        this.type_service = new SimpleStringProperty(type_service);
    }


    public int getId_service() {
        return id_service.get();
    }

    public SimpleIntegerProperty id_serviceProperty() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service.set(id_service);
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
