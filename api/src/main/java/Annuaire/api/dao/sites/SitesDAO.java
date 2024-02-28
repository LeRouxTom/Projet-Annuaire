package Annuaire.api.dao.sites;

import Annuaire.api.controller.sites.model.NewSites;
import Annuaire.api.controller.sites.model.Sites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class SitesDAO {

    private final JdbcTemplate jdbcTemplate;
    private static final String ID_SITE = "id_site";
    private static final String TYPE_SITE = "type_site";
    private static final String VILLE = "ville";


    @Autowired
    public SitesDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Sites> rowMapper = (rs, rowNum) -> {
        final Sites sites = new Sites();
        sites.setId_site(rs.getInt(ID_SITE));
        sites.setType_site(rs.getString(TYPE_SITE));
        sites.setVille(rs.getString(VILLE));
        return sites;
    };
    public Sites create(NewSites sites) {
        //INSERT DANS BDD
        Sites sites1 = null;
        final String query = "INSERT INTO sites(type_site, ville) " +
                "VALUES(?,?)";
        int result = this.jdbcTemplate.update(query, sites.getType_site(), sites.getVille());
        if(result ==1){
            List<Sites> listSites = readAll();
            int SiteId = listSites.get(listSites.size() - 1).getId_site();
            sites1= new Sites();
            sites1.setId_site(SiteId);
            sites1.setType_site(sites.getType_site());
            sites1.setVille(sites.getVille());
        }
        return sites1;
    }

    public boolean delete(int sitesId) {
        //DELETE dans la BDD
        int result = this.jdbcTemplate.update(
                "DELETE FROM sites WHERE id_site=" + sitesId);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    public Sites update(int sitesId, NewSites sites) {
        //UPDATE DANS BDD
        Sites site1= null;
        final String query = "UPDATE sites set type_site=?, ville=?" +
                "where id_site=?";
        int result = this.jdbcTemplate.update(query, sites.getType_site(), sites.getVille(), sitesId);
        if(result ==1){
            site1= new Sites();
            site1.setId_site(sitesId);
            site1.setType_site(sites.getType_site());
            site1.setVille(sites.getVille());
        }
        return site1;
    }

    public Sites read(int sitesId) {
        // READ UN SITE DANS BDD
        List<Sites> dtos = this.jdbcTemplate.query("select * from sites where id_site =" + sitesId, this.rowMapper);
        Sites sites = null;
        if(dtos != null && dtos.size() == 1){
            sites = new Sites();
            sites.setId_site(dtos.get(0).getId_site());
            sites.setType_site(dtos.get(0).getType_site());
            sites.setVille(dtos.get(0).getVille());

        }
        return sites;
    }

    public List<Sites> readAll() {
        // READ ALL SITES DANS BDD
        List<Sites> dtos = this.jdbcTemplate.query("select * from sites", this.rowMapper);
        return dtos;
    }
}
