package Annuaire.api.dao.salaries;

import Annuaire.api.controller.salaries.model.NewSalaries;
import Annuaire.api.controller.salaries.model.Salaries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class SalariesDAO {

    private final JdbcTemplate jdbcTemplate;
    private static final String ID_SALARIE = "id_salarie";
    private static final String PRENOM = "prenom";
    private static final String NOM = "nom";
    private static final String PORTABLE = "portable";
    private static final String FIXE = "fixe";
    private static final String EMAIL = "email";
    private static final String SITE_ID = "site_id";
    private static final String SERVICE_ID = "service_id";


    @Autowired
    public SalariesDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<Salaries> rowMapper = (rs, rowNum) -> {
        final Salaries salaries = new Salaries();
        salaries.setId_salarie(rs.getInt(ID_SALARIE));
        salaries.setPrenom(rs.getString(PRENOM));
        salaries.setNom(rs.getString(NOM));
        salaries.setPortable(rs.getString(PORTABLE));
        salaries.setFixe(rs.getString(FIXE));
        salaries.setEmail(rs.getString(EMAIL));
        salaries.setSite_id(rs.getInt(SITE_ID));
        salaries.setService_id(rs.getInt(SERVICE_ID));
        return salaries;
    };

    public Salaries create(NewSalaries salaries) {
        //INSERT DANS BDD
        Salaries salaries1 = null;
        final String query = "INSERT INTO salaries(prenom, nom, fixe, portable, email, site_id, service_id) " +
                "VALUES(?,?,?,?,?,?,?)";
        int result = this.jdbcTemplate.update(query, salaries.getPrenom(), salaries.getNom(), salaries.getFixe(),
                salaries.getPortable(), salaries.getEmail(), salaries.getSite_id(), salaries.getService_id());
        if(result ==1){
            List<Salaries> listSalaries = readAll();
            int SalarieId = listSalaries.get(listSalaries.size() - 1).getId_salarie();
            salaries1= new Salaries();
            salaries1.setId_salarie(SalarieId);
            salaries1.setNom(salaries.getNom());
            salaries1.setPrenom(salaries.getPrenom());
            salaries1.setFixe(salaries.getFixe());
            salaries1.setPortable(salaries.getPortable());
            salaries1.setEmail(salaries.getEmail());
            salaries1.setSite_id(salaries.getSite_id());
            salaries1.setService_id(salaries.getService_id());
        }
        return salaries1;
    }

    public boolean delete(int idSalaries) {
        //DELETE dans la BDD
        int result = this.jdbcTemplate.update(
                "DELETE FROM salaries WHERE id_salarie=" + idSalaries);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    public Salaries update(int idSalaries, NewSalaries salaries) {
        //UPDATE DANS BDD
        Salaries salaries1= null;
        final String query = "UPDATE salaries set prenom=?, nom=?, fixe=?, portable=?, email=?, site_id=?, service_id=? " +
                "where id_salarie=?";
        int result = this.jdbcTemplate.update(query, salaries.getPrenom(), salaries.getNom(), salaries.getFixe(),
                salaries.getPortable(), salaries.getEmail(), salaries.getSite_id(), salaries.getService_id(), idSalaries);
        if(result ==1){
            salaries1= new Salaries();
            salaries1.setId_salarie(idSalaries);
            salaries1.setNom(salaries.getNom());
            salaries1.setPrenom(salaries.getPrenom());
            salaries1.setFixe(salaries.getFixe());
            salaries1.setPortable(salaries.getPortable());
            salaries1.setEmail(salaries.getEmail());
            salaries1.setSite_id(salaries.getSite_id());
            salaries1.setService_id(salaries.getService_id());
        }
        return salaries1;
    }

    public Salaries read(int idSalaries){
        // READ UN SALARIES DANS BDD
        List<Salaries> dtos = this.jdbcTemplate.query("select * from salaries where id_salarie =" + idSalaries, this.rowMapper);
        Salaries salaries = null;
        if(dtos != null && dtos.size() == 1){
            salaries = new Salaries();
            salaries.setId_salarie(dtos.get(0).getId_salarie());
            salaries.setPrenom(dtos.get(0).getPrenom());
            salaries.setNom(dtos.get(0).getNom());
            salaries.setFixe(dtos.get(0).getFixe());
            salaries.setPortable(dtos.get(0).getPortable());
            salaries.setEmail(dtos.get(0).getEmail());
            salaries.setSite_id(dtos.get(0).getSite_id());
            salaries.setService_id(dtos.get(0).getService_id());


        }
        return salaries;
    }

    public List<Salaries> readAll(){
        // READ ALL SALARIES DANS BDD
        List<Salaries> dtos = this.jdbcTemplate.query("select * from salaries", this.rowMapper);
        return dtos;
    }
}
