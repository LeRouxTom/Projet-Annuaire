package Annuaire.api.controller.sites;

import Annuaire.api.business.sites.SitesBusiness;
import Annuaire.api.controller.sites.model.NewSites;
import Annuaire.api.controller.sites.model.Sites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class SitesController {
    private final String version ="/api/v1";
    private final SitesBusiness sitesBusiness;

    @Autowired
    public SitesController(SitesBusiness sitesBusiness){
        this.sitesBusiness = sitesBusiness;
    }

    @PostMapping(version + "/sites")
    public Sites createSites(@RequestBody NewSites sites){
        return sitesBusiness.createSitesBusiness(sites);
    }

    @DeleteMapping(version + "/sites/{idSites}")
    public boolean deleteSites(@PathVariable int idSites){
        return sitesBusiness.deleteSitesBusiness(idSites);
    }

    @PutMapping(version + "/sites/{idSites}")
    public Sites updateSites(@PathVariable int idSites, @RequestBody NewSites sites) {
        return sitesBusiness.updateSitesBusiness(idSites, sites);
    }
    @GetMapping(version + "/sites/{idSites}")
    public Sites readSites(@PathVariable int idSites) {
        return sitesBusiness.getSitesBusiness(idSites);
    }
    @GetMapping(version + "/sites")
    public List<Sites> readAllSites() {
        return sitesBusiness.getAllSitesBusiness();
    }

}
