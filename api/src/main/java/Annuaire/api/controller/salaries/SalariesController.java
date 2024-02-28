package Annuaire.api.controller.salaries;

import Annuaire.api.business.salaries.SalariesBusiness;
import Annuaire.api.controller.salaries.model.NewSalaries;
import Annuaire.api.controller.salaries.model.Salaries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Validated
public class SalariesController {

    private final String version ="/api/v1";
    private final SalariesBusiness salariesBusiness;

    @Autowired
    public SalariesController(SalariesBusiness salariesBusiness){
        this.salariesBusiness = salariesBusiness;
    }

    @PostMapping(version + "/salaries")
    public Salaries createSalaries(@RequestBody NewSalaries salaries) {
        return salariesBusiness.createSalariesBusiness(salaries);
    }

    @DeleteMapping(version + "/salaries/{idSalaries}")
    public boolean deleteSalaries(@PathVariable int idSalaries){
        return salariesBusiness.deleteSalariesBusiness(idSalaries);
    }

    @PutMapping(version + "/salaries/{idSalaries}")
    public Salaries updateSalaries(@PathVariable int idSalaries, @RequestBody NewSalaries salaries) {
        return salariesBusiness.updateSalariesBusiness(idSalaries, salaries);
    }
    @GetMapping(version + "/salaries/{idSalaries}")
    public Salaries readSalaries(@PathVariable int idSalaries) {
        return salariesBusiness.getSalariesBusiness(idSalaries);
    }
    @GetMapping(version + "/salaries")
    public List<Salaries> readAllSalaries() {
        return salariesBusiness.getAllSalariesBusiness();
    }
}
