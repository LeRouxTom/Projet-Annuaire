package Annuaire.api.business.services;

import Annuaire.api.controller.services.model.NewServices;
import Annuaire.api.controller.services.model.Services;
import Annuaire.api.dao.services.ServicesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicesBusiness {

    private final ServicesDAO servicesDAO;

    @Autowired
    public ServicesBusiness(ServicesDAO servicesDAO){
        this.servicesDAO = servicesDAO;
    }
    public Services createServicesBusiness(NewServices services){
        return servicesDAO.create(services);
    }
    public boolean deleteServicesBusiness(int servicesId) {
        return servicesDAO.delete(servicesId);
    }
    public Services updateServicesBusiness(int servicesId, NewServices services){
        return servicesDAO.update(servicesId,services);}
    public Services getServicesBusiness(int servicesId){
        return servicesDAO.read(servicesId);
    }
    public List<Services> getAllServicesBusiness(){
        return servicesDAO.readAll();
    }
}
