package annaire.example.annuaire_client_lourd.models;

import javafx.beans.property.SimpleStringProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewSalaries {

    private final SimpleStringProperty prenom;
    private final SimpleStringProperty nom;
    private final SimpleStringProperty fixe;
    private final SimpleStringProperty portable;
    private final SimpleStringProperty email;
    private final SimpleStringProperty site_id;
    private final SimpleStringProperty service_id;


    public NewSalaries(@JsonProperty("prenom") String prenom,
                    @JsonProperty("nom") String nom,
                    @JsonProperty("fixe") String fixe,
                    @JsonProperty("portable") String portable,
                    @JsonProperty("email") String email,
                    @JsonProperty("site_id") String site_id,
                    @JsonProperty("service_id") String service_id ){

        this.prenom = new SimpleStringProperty(prenom);
        this.nom = new SimpleStringProperty(nom);
        this.fixe = new SimpleStringProperty(fixe);
        this.portable = new SimpleStringProperty(portable);
        this.email = new SimpleStringProperty(email);
        this.site_id = new SimpleStringProperty(site_id);
        this.service_id = new SimpleStringProperty(service_id);
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

    public String getFixe() {
        return fixe.get();
    }

    public SimpleStringProperty fixeProperty() {
        return fixe;
    }

    public void setFixe(String fixe) {
        this.fixe.set(fixe);
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

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getSite_id() {
        return site_id.get();
    }

    public SimpleStringProperty site_idProperty() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id.set(site_id);
    }

    public String getService_id() {
        return service_id.get();
    }

    public SimpleStringProperty service_idProperty() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id.set(service_id);
    }
}

