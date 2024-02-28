package annaire.example.annuaire_client_lourd.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Salaries {
    private final SimpleIntegerProperty id_salarie;
    private final SimpleStringProperty prenom;
    private final SimpleStringProperty nom;
    private final SimpleStringProperty portable;
    private final SimpleStringProperty fixe;
    private final SimpleStringProperty email;
    private final SimpleStringProperty type_service;
    private final SimpleStringProperty type_site;
    private final SimpleStringProperty ville;

    public Salaries (@JsonProperty("id_salarie") Integer id_salarie,
                     @JsonProperty("prenom") String prenom,
                     @JsonProperty("nom") String nom,
                     @JsonProperty("portable") String portable,
                     @JsonProperty("fixe") String fixe,
                     @JsonProperty("email") String email,
                     @JsonProperty("type_service") String type_service,
                     @JsonProperty("type_site") String type_site,
                     @JsonProperty("ville") String ville){

        this.id_salarie = new SimpleIntegerProperty(id_salarie);
        this.prenom = new SimpleStringProperty(prenom);
        this.nom = new SimpleStringProperty(nom);
        this.portable = new SimpleStringProperty(portable);
        this.fixe = new SimpleStringProperty(fixe);
        this.email = new SimpleStringProperty(email);
        this.type_service = new SimpleStringProperty(type_service);
        this.type_site = new SimpleStringProperty(type_site);
        this.ville = new SimpleStringProperty(ville);

    }


    public int getId_salarie() {
        return id_salarie.get();
    }

    public SimpleIntegerProperty id_salarieProperty() {
        return id_salarie;
    }

    public void setId_salarie(int id_salarie) {
        this.id_salarie.set(id_salarie);
    }

    public String getPrenom() {
        return prenom.get();
    }

    public SimpleStringProperty prenomProperty() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    public String getNom() {
        return nom.get();
    }

    public SimpleStringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getPortable() {
        return portable.get();
    }

    public SimpleStringProperty portableProperty() {
        return portable;
    }

    public void setPortable(String portable) {
        this.portable.set(portable);
    }

    public String getFixe() {
        return fixe.get();
    }

    public SimpleStringProperty fixeProperty() {
        return fixe;
    }

    public void setFixe(String fixe) {
        this.fixe.set(fixe);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
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
