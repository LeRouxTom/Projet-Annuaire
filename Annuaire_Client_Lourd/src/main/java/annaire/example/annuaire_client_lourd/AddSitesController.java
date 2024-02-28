package annaire.example.annuaire_client_lourd;


import annaire.example.annuaire_client_lourd.dao.SitesDAO;
import annaire.example.annuaire_client_lourd.models.NewSites;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddSitesController {
    @FXML
    private Button buttonCancelAddSites;

    @FXML
    private Button buttonValidateAddSites;

    @FXML
    private TextField ville;
    @FXML
    private TextField typeSite;

    @FXML
    void addAndReturnToSitesPage(ActionEvent event) {
        NewSites sites = new NewSites("","");
        sites.setType_site(typeSite.getText());
        sites.setVille(ville.getText());


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String body = objectMapper.writeValueAsString(sites);
            SitesDAO.createSites("sites", body);
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
    void cancelAndReturnToSitesPage(ActionEvent event) {

        // Récupérer le bouton actuel pour obtenir la scène
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        // Ferme la scène actuelle
        stage.close();

    }
}
