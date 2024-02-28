package annaire.example.annuaire_client_lourd.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SalariesDTO {

    private final SimpleIntegerProperty id_salarie;
    private final SimpleStringProperty prenom;
    private final SimpleStringProperty nom;
    private final SimpleStringProperty fixe;
    private final SimpleStringProperty portable;
    private final SimpleStringProperty email;
    private final SimpleIntegerProperty site_id;
    private final SimpleIntegerProperty  service_id;


    public SalariesDTO(@JsonProperty("id_salarie") Integer id_salarie,
                       @JsonProperty("prenom") String prenom,
                       @JsonProperty("nom") String nom,
                       @JsonProperty("fixe") String fixe,
                       @JsonProperty("portable") String portable,
                       @JsonProperty("email") String email,
                       @JsonProperty("site_id") Integer site_id,
                       @JsonProperty("service_id") Integer service_id ){

        this.id_salarie = new SimpleIntegerProperty(id_salarie);
        this.prenom = new SimpleStringProperty(prenom);
        this.nom = new SimpleStringProperty(nom);
        this.fixe = new SimpleStringProperty(fixe);
        this.portable = new SimpleStringProperty(portable);
        this.email = new SimpleStringProperty(email);
        this.site_id = new SimpleIntegerProperty(site_id);
        this.service_id = new SimpleIntegerProperty(service_id);
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


    public int getSite_id() {
        return site_id.get();
    }

    public SimpleIntegerProperty site_idProperty() {
        return site_id;
    }

    public void setSite_id(int site_id) {
        this.site_id.set(site_id);
    }

    public int getService_id() {
        return service_id.get();
    }

    public SimpleIntegerProperty service_idProperty() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id.set(service_id);
    }
}

