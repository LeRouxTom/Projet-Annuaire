package annaire.example.annuaire_client_lourd;

import annaire.example.annuaire_client_lourd.dao.SitesDAO;
import annaire.example.annuaire_client_lourd.models.NewSites;
import annaire.example.annuaire_client_lourd.models.SitesDTO;
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

public class UpdateSitesController {

    @FXML
    private Button buttonCancelUpdateSites;
    @FXML
    private TextField fieldSearchSitesById;
    @FXML
    private TextField fieldTypeSite;
    @FXML
    private TextField fieldVille;

    private final SitesDAO sitesDAO = new SitesDAO();

    @FXML
    private Button buttonSearchSite;
    @FXML
    void searchByIdSites(ActionEvent event) {

        try {
            int sitesId = Integer.parseInt(fieldSearchSitesById.getText());

            SitesDTO sites = sitesDAO.getSitesById(sitesId);

            if (sites != null) {
                fieldTypeSite.setText(String.valueOf(sites.getType_site()));
                fieldVille.setText(String.valueOf(sites.getVille()));
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
        alert.setHeaderText("Modification du site");
        alert.setContentText("Vous devez mettre un ID de site existant");

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    @FXML
    void cancelAndReturnToSitesPage(ActionEvent event) {

        // Récupérer le bouton actuel pour obtenir la scène
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        // Ferme la scène actuelle
        stage.close();

    }

    @FXML
    private Button buttonUpdateSites;
    @FXML
    void updateAndReturnToSitePage(ActionEvent event) {
        NewSites sites = new NewSites("", "");
        sites.setType_site(fieldTypeSite.getText());
        sites.setVille(fieldVille.getText());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String body = objectMapper.writeValueAsString(sites);
            SitesDAO.updateSites(Integer.parseInt(fieldSearchSitesById.getText()), body);
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
