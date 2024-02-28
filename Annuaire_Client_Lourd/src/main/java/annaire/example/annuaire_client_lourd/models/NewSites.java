package annaire.example.annuaire_client_lourd.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.SimpleStringProperty;

public class NewSites {
    private final SimpleStringProperty type_site;
    private final SimpleStringProperty ville;

    public NewSites(@JsonProperty("type_site") String type_site,
                    @JsonProperty("ville") String ville ){

        this.type_site = new SimpleStringProperty(type_site);
        this.ville = new SimpleStringProperty(ville);
    }

    public String getType_site() {
        return type_site.get();
    }

    public SimpleStringProperty type_siteProperty() {
        return type_site;
    }

    public void setType_site(String type_site) {
        this.type_site.set(type_site);
    }

    public String getVille() {
        return ville.get();
    }

    public SimpleStringProperty villeProperty() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville.set(ville);
    }
}

