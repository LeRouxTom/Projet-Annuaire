package Annuaire.api.dao.services;

import Annuaire.api.controller.services.model.NewServices;
import Annuaire.api.controller.services.model.Services;
import Annuaire.api.controller.sites.model.Sites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ServicesDAO {

    private final JdbcTemplate jdbcTemplate;
    private static final String ID_SERVICE = "id_service";
    private static final String TYPE_SERVICE = "type_service";

    @Autowired
    public ServicesDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Services> rowMapper = (rs, rowNum) -> {
        final Services services = new Services();
        services.setId_service(rs.getInt(ID_SERVICE));
        services.setType_service(rs.getString(TYPE_SERVICE));
        return services;
    };


    public Services create(NewServices services) {
        //INSERT DANS BDD
        Services services1 = null;
        final String query = "INSERT INTO services(type_service) " +
                "VALUES(?)";
        int result = this.jdbcTemplate.update(query, services.getType_service());
        if(result ==1){
            List<Services> listServices = readAll();
            int ServiceId = listServices.get(listServices.size() - 1).getId_service();
            services1= new Services();
            services1.setId_service(ServiceId);
            services1.setType_service(services.getType_service());
        }
        return services1;
    }

    public boolean delete(int servicesId) {
        //DELETE dans la BDD
        int result = this.jdbcTemplate.update(
                "DELETE FROM services WHERE id_service=" + servicesId);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    public Services update(int servicesId, NewServices services) {
        //UPDATE DANS BDD
        Services services1= null;
        final String query = "UPDATE services set type_service=?" +
                "where id_service=?";
        int result = this.jdbcTemplate.update(query, services.getType_service(), servicesId);
        if(result ==1){
            services1= new Services();
            services1.setId_service(servicesId);
            services1.setType_service(services.getType_service());
        }
        return services1;
    }

    public Services read(int servicesId) {
        // READ UN SERVICE DANS BDD
        List<Services> dtos = this.jdbcTemplate.query("select * from services where id_service =" + servicesId, this.rowMapper);
        Services services = null;
        if(dtos != null && dtos.size() == 1){
            services = new Services();
            services.setId_service(dtos.get(0).getId_service());
            services.setType_service(dtos.get(0).getType_service());

        }
        return services;
    }

    public List<Services> readAll() {
        // READ ALL SERVICES DANS BDD
        List<Services> dtos = this.jdbcTemplate.query("select * from services", this.rowMapper);
        return dtos;
    }
}
