package annaire.example.annuaire_client_lourd;

import annaire.example.annuaire_client_lourd.dao.SitesDAO;
import annaire.example.annuaire_client_lourd.models.SitesDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Optional;


public class DeleteSitesAdminController {

    private final SitesDAO sitesDAO = new SitesDAO();
    @FXML
    private TextField fieldSearchSitesById;
    @FXML
    private TextField fieldVille;
    @FXML
    private TextField fieldTypeSite;


    @FXML
    void searchByIdSite(ActionEvent event) {

        try {
            // Récupére l'ID du site à rechercher
            int siteId = Integer.parseInt(fieldSearchSitesById.getText());

            // Appelle sitesDAO pour récupérer le site par son ID
            SitesDTO site = sitesDAO.getSitesById(siteId);

            // Vérifie si le site existe
            if (site != null) {
                fieldTypeSite.setText(String.valueOf(site.getType_site()));
                fieldVille.setText(String.valueOf(site.getVille()));
            } else {
                // Le site n'existe pas
                AlerteId();
            }
        } catch (NumberFormatException e) {
            // Gére l'exception si l'utilisateur n'a pas entré un ID valide
            System.out.println("Veuillez entrer un ID de site valide.");
        }

    }

    private boolean AlerteId() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Supprimer le site");
        alert.setContentText("Vous devez mettre un ID de site existant");

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    @FXML
    private Button buttonCancelDeleteSites;
    @FXML
    void cancelAndReturnToSitesPage(ActionEvent event) {

        // Récupérer le bouton actuel pour obtenir la scène
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        // Ferme la scène actuelle
        stage.close();

    }

    @FXML
    private Button buttonDeleteSites;
    @FXML
    void deleteAndReturnToSitesPage(ActionEvent event) {

        try {
            // Récupére l'ID du site à rechercher
            int sitesId = Integer.parseInt(fieldSearchSitesById.getText());

            // Appelle SitesDAO pour récupérer le sites par son ID
            SitesDTO sitesDTO = SitesDAO.getSitesById(sitesId);

            // Vérifie si le site existe
            if (sitesDTO != null) {
                // Supprime le site
                SitesDAO.deleteSites(sitesId);

                // Ferme la fenêtre actuelle
                Node buttonDeleteSites = (Node) event.getSource();
                Stage stage = (Stage) buttonDeleteSites.getScene().getWindow();
                stage.close();
            }else {
                // Le site n'existe pas
                AlerteId();
            }
        } catch (NumberFormatException e) {
            // Gérer l'exception si l'utilisateur n'a pas entré un ID valide
            System.out.println("Veuillez entrer un ID de site valide.");
        }
    }
}
