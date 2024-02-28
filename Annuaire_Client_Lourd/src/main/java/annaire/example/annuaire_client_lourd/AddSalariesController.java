package annaire.example.annuaire_client_lourd;

import annaire.example.annuaire_client_lourd.dao.SalariesDAO;
import annaire.example.annuaire_client_lourd.models.NewSalaries;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddSalariesController {
    @FXML
    private Button buttonCancelAddSalaries;

    @FXML
    private Button buttonValidateAddSalaries;

    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private TextField email;
    @FXML
    private TextField fixe;
    @FXML
    private TextField portable;
    @FXML
    private TextField id_site;
    @FXML
    private TextField id_service;

    @FXML
    void addAndReturnToSalariesPage(ActionEvent event) {
        NewSalaries salaries = new NewSalaries("","","","","","","");
        salaries.setPrenom(prenom.getText());
        salaries.setNom(nom.getText());
        salaries.setPortable(portable.getText());
        salaries.setFixe(fixe.getText());
        salaries.setEmail(email.getText());
        salaries.setService_id(id_service.getText());
        salaries.setSite_id(id_site.getText());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String body = objectMapper.writeValueAsString(salaries);
            SalariesDAO.createSalaries("salaries", body);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Récupérer le bouton actuel pour obtenir la scène
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        // Ferme la scène actuelle
        stage.close();
    }

    @FXML
    void cancelAndReturnToSalariesPage(ActionEvent event) {

        // Récupérer le bouton actuel pour obtenir la scène
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        // Ferme la scène actuelle
        stage.close();

    }

}
