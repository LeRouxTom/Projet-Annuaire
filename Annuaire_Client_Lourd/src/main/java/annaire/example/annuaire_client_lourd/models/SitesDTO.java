package annaire.example.annuaire_client_lourd.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SitesDTO {
    private final SimpleIntegerProperty id_site;
    private final SimpleStringProperty type_site;
    private final SimpleStringProperty ville;

    public SitesDTO(@JsonProperty("id_site") Integer id_site,
                    @JsonProperty("type_site") String type_site,
                    @JsonProperty("ville") String ville ){

        this.id_site = new SimpleIntegerProperty(id_site);
        this.type_site = new SimpleStringProperty(type_site);
        this.ville = new SimpleStringProperty(ville);
    }

    public int getId_site() {
        return id_site.get();
    }

    public SimpleIntegerProperty id_siteProperty() {
        return id_site;
    }

    public void setId_site(int id_site) {
        this.id_site.set(id_site);
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
