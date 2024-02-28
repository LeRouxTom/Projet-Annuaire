package annaire.example.annuaire_client_lourd;


import annaire.example.annuaire_client_lourd.dao.SalariesDAO;
import annaire.example.annuaire_client_lourd.models.Salaries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class FicheSalariesController {

    @FXML
    private TextField fieldId;
    @FXML
    private TextField fieldPrenom;
    @FXML
    private TextField fieldNom;
    @FXML
    private TextField fieldFixe;
    @FXML
    private TextField fieldPortable;
    @FXML
    private TextField fieldEmail;
    @FXML
    private TextField fieldTypeService;
    @FXML
    private TextField fieldTypeSite;
    @FXML
    private TextField fieldVille;

    @FXML
    public void initialize(Salaries selectedSalarie) {
        try {
            // Appelle salariesDAO pour récupérer le salarié par son ID
            fieldId.setText(String.valueOf(selectedSalarie.getId_salarie()));
            fieldPrenom.setText(selectedSalarie.getPrenom());
            fieldNom.setText(String.valueOf(selectedSalarie.getNom()));
            fieldFixe.setText(selectedSalarie.getFixe());
            fieldPortable.setText(selectedSalarie.getPortable());
            fieldEmail.setText(selectedSalarie.getEmail());
            fieldTypeService.setText(selectedSalarie.getType_service());
            fieldTypeSite.setText(selectedSalarie.getType_service());
            fieldVille.setText(selectedSalarie.getVille());

        } catch (NumberFormatException e) {
            // Gére l'exception si l'utilisateur n'a pas entré un ID valide
            //System.out.println("Veuillez entrer un ID de salarié valide.");
        }
    }

    @FXML
    private Button returnPageSalaries;

    @FXML
    void cancelAndReturnToSitesPage(ActionEvent event) {

        // Récupérer le bouton actuel pour obtenir la scène
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        // Ferme la scène actuelle
        stage.close();

    }
}
