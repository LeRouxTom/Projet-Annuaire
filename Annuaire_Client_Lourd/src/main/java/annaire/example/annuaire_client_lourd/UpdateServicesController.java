package annaire.example.annuaire_client_lourd;


import annaire.example.annuaire_client_lourd.dao.ServicesDAO;
import annaire.example.annuaire_client_lourd.models.NewServices;
import annaire.example.annuaire_client_lourd.models.ServicesDTO;
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

public class UpdateServicesController {

    @FXML
    private TextField fieldSearchServicesById;
    @FXML
    private TextField fieldTypeService;
    @FXML
    private Button buttonCancelUpdateServices;
    @FXML
    private Button buttonsearchById;

    private final ServicesDAO servicesDAO = new ServicesDAO();


    @FXML
    void searchByIdServices(ActionEvent event) {

        try {
            int servicesId = Integer.parseInt(fieldSearchServicesById.getText());

            ServicesDTO services = servicesDAO.getServicesById(servicesId);

            if (services != null) {
                fieldTypeService.setText(String.valueOf(services.getType_service()));
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
        alert.setHeaderText("Modification du service");
        alert.setContentText("Vous devez mettre un ID de service existant");

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }


    @FXML
    void cancelAndReturnToServicesPage(ActionEvent event) {

        // Récupérer le bouton actuel pour obtenir la scène
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        // Ferme la scène actuelle
        stage.close();

    }

    @FXML
    private Button buttonUpdateServices;
    @FXML
    void updateAndReturnToServicePage(ActionEvent event) {
        NewServices services = new NewServices("");
        services.setType_service(fieldTypeService.getText());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String body = objectMapper.writeValueAsString(services);
            ServicesDAO.updateServices(Integer.parseInt(fieldSearchServicesById.getText()), body);
        } catch (Exception e) {
            // Gérer les erreurs de sérialisation JSON ici
            e.printStackTrace();
        }

        // Récupérer le bouton actuel pour obtenir la scène
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        // Ferme la scène actuelle
        stage.close();
    }
}
