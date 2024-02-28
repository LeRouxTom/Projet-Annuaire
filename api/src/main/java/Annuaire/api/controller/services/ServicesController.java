package Annuaire.api.controller.services;

import Annuaire.api.business.services.ServicesBusiness;
import Annuaire.api.controller.services.model.NewServices;
import Annuaire.api.controller.services.model.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class ServicesController {
    private final String version ="/api/v1";
    private final ServicesBusiness servicesBusiness;

    @Autowired
    public ServicesController(ServicesBusiness servicesBusiness){
        this.servicesBusiness = servicesBusiness;
    }

    @PostMapping(version + "/services")
    public Services createServices(@RequestBody NewServices services){
        return servicesBusiness.createServicesBusiness(services);
    }

    @DeleteMapping(version + "/services/{idServices}")
    public boolean deleteServices(@PathVariable int idServices){
        return servicesBusiness.deleteServicesBusiness(idServices);
    }

    @PutMapping(version + "/services/{idServices}")
    public Services updateServices(@PathVariable int idServices, @RequestBody NewServices services) {
        return servicesBusiness.updateServicesBusiness(idServices, services);
    }
    @GetMapping(version + "/services/{idServices}")
    public Services readServices(@PathVariable int idServices) {
        return servicesBusiness.getServicesBusiness(idServices);
    }
    @GetMapping(version + "/services")
    public List<Services> readAllServices() {
        return servicesBusiness.getAllServicesBusiness();
    }

}
