package annaire.example.annuaire_client_lourd;

import annaire.example.annuaire_client_lourd.dao.SalariesDAO;
import annaire.example.annuaire_client_lourd.models.NewSalaries;
import annaire.example.annuaire_client_lourd.models.SalariesDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Optional;

public class UpdateSalariesController {

    @FXML
    private TextField fieldSearchSalariesById;
    @FXML
    private TextField fieldNom;
    @FXML
    private TextField fieldPrenom;
    @FXML
    private TextField fieldFixe;
    @FXML
    private TextField fieldPortable;
    @FXML
    private TextField fieldEmail;
    @FXML
    private TextField fieldSiteId;
    @FXML
    private TextField fieldServiceId;

    private final SalariesDAO salariesDAO = new SalariesDAO();

    @FXML
    private Button buttonsearchById;
    @FXML
    void searchByIdSalaries(ActionEvent event) {

        try {
            // Récupére l'ID du salarié à rechercher
            int salariesId = Integer.parseInt(fieldSearchSalariesById.getText());

            // Appelle salariesDAO pour récupérer le salarié par son ID
            SalariesDTO salaries = salariesDAO.getSalariesById(salariesId);

            // Vérifie si le salarié existe
            if (salaries != null) {
                fieldPrenom.setText(String.valueOf(salaries.getPrenom()));
                fieldNom.setText(String.valueOf(salaries.getNom()));
                fieldFixe.setText(String.valueOf(salaries.getFixe()));
                fieldPortable.setText(String.valueOf(salaries.getPortable()));
                fieldEmail.setText(String.valueOf(salaries.getEmail()));
                fieldSiteId.setText(String.valueOf(salaries.getSite_id()));
                fieldServiceId.setText(String.valueOf(salaries.getService_id()));


            } else {
                // Le salarié n'existe pas
                AlerteId();
            }
        } catch (NumberFormatException e) {
            // Gére l'exception si l'utilisateur n'a pas entré un ID valide
            System.out.println("Veuillez entrer un ID de salarié valide.");
        }

    }

    private boolean AlerteId() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Modification du salarié");
        alert.setContentText("Vous devez mettre un ID de salarié existant");

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    @FXML
    private Button buttonUpdateSalaries;
    @FXML
    void updateAndReturnToSalariesPage(ActionEvent event) {
        NewSalaries salaries = new NewSalaries("","","","","","","");
        salaries.setPrenom(fieldPrenom.getText());
        salaries.setNom(fieldNom.getText());
        salaries.setPortable(fieldPortable.getText());
        salaries.setFixe(fieldFixe.getText());
        salaries.setEmail(fieldEmail.getText());
        salaries.setService_id(fieldServiceId.getText());
        salaries.setSite_id(fieldSiteId.getText());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String body = objectMapper.writeValueAsString(salaries);
            SalariesDAO.updateSalaries(Integer.parseInt(fieldSearchSalariesById.getText()), body);
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
    private Button buttonCancelUpdateSalaries;
    @FXML
    void cancelAndReturnToSalariesPage(ActionEvent event) {

        // Récupérer le bouton actuel pour obtenir la scène
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        // Ferme la scène actuelle
        stage.close();

    }
}
