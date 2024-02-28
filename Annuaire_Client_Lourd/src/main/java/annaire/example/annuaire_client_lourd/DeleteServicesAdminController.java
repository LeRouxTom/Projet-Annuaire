package annaire.example.annuaire_client_lourd;

import annaire.example.annuaire_client_lourd.dao.ServicesDAO;
import annaire.example.annuaire_client_lourd.models.ServicesDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Optional;

public class DeleteServicesAdminController {
    @FXML
    private Button buttonCancelDeleteServices;
    @FXML
    private TextField fieldSearchServicesById;
    @FXML
    private TextField fieldTypeService;
    @FXML
    private Button buttonSearchById;

    private final ServicesDAO servicesDAO = new ServicesDAO();

    @FXML
    void searchByIdService(ActionEvent event) {

        try {
            // Récupére l'ID du service à rechercher
            int serviceId = Integer.parseInt(fieldSearchServicesById.getText());

            // Appelle usersDAO pour récupérer le users par son ID
            ServicesDTO service = servicesDAO.getServicesById(serviceId);

            // Vérifie si le client existe
            if (service != null) {
                fieldTypeService.setText(String.valueOf(service.getType_service()));
            } else {
                // Le service n'existe pas
                AlerteId();
            }
        } catch (NumberFormatException e) {
            // Gére l'exception si l'utilisateur n'a pas entré un ID valide
            System.out.println("Veuillez entrer un ID de service valide.");
        }

    }

    private boolean AlerteId() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Supprimer le service");
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
    private Button buttonDeleteServices;
    @FXML
    void deleteAndReturnToServicesPage(ActionEvent event) {

        try {
            // Récupére l'ID du client à rechercher
            int servicesId = Integer.parseInt(fieldSearchServicesById.getText());

            // Appelle UsersDAO pour récupérer le client par son ID
            ServicesDTO servicesDTO = ServicesDAO.getServicesById(servicesId);

            // Vérifie si le client existe
            if (servicesDTO != null) {
                // Supprime le client
                ServicesDAO.deleteServices(servicesId);

                // Ferme la fenêtre actuelle
                Node buttonDeleteUsers = (Node) event.getSource();
                Stage stage = (Stage) buttonDeleteUsers.getScene().getWindow();
                stage.close();
            }else {
                // Le client n'existe pas
                AlerteId();
            }
        } catch (NumberFormatException e) {
            // Gérer l'exception si l'utilisateur n'a pas entré un ID valide
            System.out.println("Veuillez entrer un ID de service valide.");
        }
    }
}
