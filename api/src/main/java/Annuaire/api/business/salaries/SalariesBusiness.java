package Annuaire.api.business.salaries;

import Annuaire.api.controller.salaries.model.NewSalaries;
import Annuaire.api.controller.salaries.model.Salaries;
import Annuaire.api.dao.salaries.SalariesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalariesBusiness {
    private final SalariesDAO salariesDAO;

    @Autowired
    public SalariesBusiness(SalariesDAO salariesDAO){
        this.salariesDAO = salariesDAO;
    }
    public Salaries createSalariesBusiness(NewSalaries salaries){
        return salariesDAO.create(salaries);
    }
    public boolean deleteSalariesBusiness(int salariesId) {
        return salariesDAO.delete(salariesId);
    }
    public Salaries updateSalariesBusiness(int salariesId,NewSalaries salaries){
        return salariesDAO.update(salariesId,salaries);}
    public Salaries getSalariesBusiness(int salariesId){
        return salariesDAO.read(salariesId);
    }
    public List<Salaries> getAllSalariesBusiness(){
        return salariesDAO.readAll();
    }

}
