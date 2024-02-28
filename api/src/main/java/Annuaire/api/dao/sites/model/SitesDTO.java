package Annuaire.api.dao.sites.model;

public class SitesDTO {
    int id_site;
    String type_site;
    String ville;

    public int getId_site() {
        return id_site;
    }

    public void setId_site(int id_site) {
        this.id_site = id_site;
    }

    public String getType_site() {
        return type_site;
    }

    public void setType_site(String type_site) {
        this.type_site = type_site;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
