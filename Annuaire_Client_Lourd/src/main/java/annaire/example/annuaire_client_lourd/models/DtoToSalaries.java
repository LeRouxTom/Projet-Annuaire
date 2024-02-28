package annaire.example.annuaire_client_lourd.models;

import annaire.example.annuaire_client_lourd.dao.ServicesDAO;
import annaire.example.annuaire_client_lourd.dao.SitesDAO;

import java.util.ArrayList;
import java.util.List;

public class DtoToSalaries {
    public static Salaries DtoToSalaries (SalariesDTO salariesDTO){
        //On va chercher les valeurs du service et du site du Salarié pour les afficher dans le même tableau
        ServicesDTO service = ServicesDAO.getServiceById(salariesDTO.getService_id());
        SitesDTO site = SitesDAO.getSitesById(salariesDTO.getSite_id());

        //Création d'un nouveau salaries qu'on met à NULL
        Salaries salaries = new Salaries(0,"","","","","","","","");

        //Ajout de toutes les données dans le salarié
        salaries.setId_salarie(salariesDTO.getId_salarie());
        salaries.setPrenom(salariesDTO.getPrenom());
        salaries.setNom(salariesDTO.getNom());
        salaries.setFixe(salariesDTO.getFixe());
        salaries.setPortable(salariesDTO.getPortable());
        salaries.setEmail(salariesDTO.getEmail());
        salaries.setType_service(service.getType_service());
        salaries.setType_site(site.getType_site());
        salaries.setVille(site.getVille());

        return salaries;
    }

    //Permet de passer d'une liste de DTO à une liste de salaries pour l'afficher dans le tableau
    public static List<Salaries> ListDtoSalaries (List<SalariesDTO> listDTO){
        List<Salaries> listSalaries = new ArrayList<>();
        for (SalariesDTO salariesDTO : listDTO){
            listSalaries.add(DtoToSalaries(salariesDTO));
        }
        return listSalaries;
    }
}
