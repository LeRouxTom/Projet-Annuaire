package Annuaire.api.controller.salaries.model;

import Annuaire.api.controller.sites.model.Sites;

public class Salaries {

    int id_salarie;
    String prenom;
    String nom;
    String fixe;
    String portable;
    String email;
    int site_id;
    int service_id;

    public int getId_salarie() {
        return id_salarie;
    }

    public void setId_salarie(int id_salaries) {
        this.id_salarie = id_salaries;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getFixe() {
        return fixe;
    }

    public void setFixe(String fixe) {
        this.fixe = fixe;
    }

    public String getPortable() {
        return portable;
    }

    public void setPortable(String portable) {
        this.portable = portable;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSite_id() {
        return site_id;
    }

    public void setSite_id(int site_id) {
        this.site_id = site_id;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }
}
