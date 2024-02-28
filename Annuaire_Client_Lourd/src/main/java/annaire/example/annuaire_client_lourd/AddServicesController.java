package annaire.example.annuaire_client_lourd;

import annaire.example.annuaire_client_lourd.dao.ServicesDAO;
import annaire.example.annuaire_client_lourd.models.NewServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddServicesController {
    @FXML
    private Button buttonCancelAddServices;

    @FXML
    private Button buttonValidateAddServices;

    @FXML
    private TextField typeSite;

    @FXML
    void addAndReturnToServicesPage(ActionEvent event) {
        NewServices services = new NewServices("");
        services.setType_service(typeSite.getText());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String body = objectMapper.writeValueAsString(services);
            ServicesDAO.createServices("services", body);
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
    void cancelAndReturnToServicesPage(ActionEvent event) {

        // Récupérer le bouton actuel pour obtenir la scène
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        // Ferme la scène actuelle
        stage.close();

    }
}
