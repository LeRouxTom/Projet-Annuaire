package Annuaire.api.business.sites;

import Annuaire.api.controller.sites.model.NewSites;
import Annuaire.api.controller.sites.model.Sites;
import Annuaire.api.dao.sites.SitesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SitesBusiness {

    private final SitesDAO sitesDAO;

    @Autowired
    public SitesBusiness(SitesDAO sitesDAO){
        this.sitesDAO = sitesDAO;
    }
    public Sites createSitesBusiness(NewSites sites){
        return sitesDAO.create(sites);
    }
    public boolean deleteSitesBusiness(int sitesId) {
        return sitesDAO.delete(sitesId);
    }
    public Sites updateSitesBusiness(int sitesId,NewSites sites) { return sitesDAO.update(sitesId,sites);}
    public Sites getSitesBusiness(int sitesId){
        return sitesDAO.read(sitesId);
    }
    public List<Sites> getAllSitesBusiness(){
        return sitesDAO.readAll();
    }

}
