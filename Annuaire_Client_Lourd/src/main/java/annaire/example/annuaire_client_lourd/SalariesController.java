package annaire.example.annuaire_client_lourd;

import annaire.example.annuaire_client_lourd.dao.SalariesDAO;
import annaire.example.annuaire_client_lourd.models.DtoToSalaries;
import annaire.example.annuaire_client_lourd.models.Salaries;
import annaire.example.annuaire_client_lourd.models.SalariesDTO;
import annaire.example.annuaire_client_lourd.models.ServicesDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SalariesController {

    @FXML
    private TableView<Salaries> salariesTable;

    @FXML
    private TableColumn<Salaries, Integer> id_salarie;

    @FXML
    private TableColumn<Salaries, String> prenomColumn;

    @FXML
    private TableColumn<Salaries, String> nomColumn;

    @FXML
    private TableColumn<Salaries, String> portableColumn;

    @FXML
    private TableColumn<Salaries, String> fixeColumn;

    @FXML
    private TableColumn<Salaries, String> emailColumn;

    @FXML
    private TableColumn<Salaries, String> typeSiteColumn;

    @FXML
    private TableColumn<Salaries, String> villeColumn;

    @FXML
    private TableColumn<Salaries, String> typeServiceColumn;

    @FXML
    private TextField fieldIdSalaries;

    @FXML
    private TextField fieldPrenom;

    @FXML
    private TextField fieldTypeService;

    @FXML
    private TextField fieldTypeSite;

    private final SalariesDAO salariesDAO = new SalariesDAO();

    @FXML
    public void initialize() {
        // Charge les données depuis l'API
        List<SalariesDTO> salariesList = salariesDAO.getAllSalaries();
        //Il est nécessaire de passer de DTO à Salaries pour afficher les valeurs dans le tableau
        List<Salaries> listSalaries = DtoToSalaries.ListDtoSalaries(salariesList);
        // Lie les données aux cellules du tableau
        id_salarie.setCellValueFactory(cellData -> cellData.getValue().id_salarieProperty().asObject());
        prenomColumn.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
        nomColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        portableColumn.setCellValueFactory(cellData -> cellData.getValue().portableProperty());
        fixeColumn.setCellValueFactory(cellData -> cellData.getValue().fixeProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        typeSiteColumn.setCellValueFactory(cellData -> cellData.getValue().type_siteProperty());
        villeColumn.setCellValueFactory(cellData -> cellData.getValue().villeProperty());
        typeServiceColumn.setCellValueFactory(cellData -> cellData.getValue().type_serviceProperty());

        // Ajoute les données à la table
        salariesTable.getItems().addAll(listSalaries);

        salariesTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !salariesTable.getSelectionModel().isEmpty()) {
                // Sélectionne l'id du salarié quand il y a un double clic sur une ligne du tableau
                Salaries selectedSalarie = salariesTable.getSelectionModel().getSelectedItem();
                // Appel de la fonction d'ouverture de la ficheSalarié
                openFicheSalaries(selectedSalarie);
            }
        });
    }

    @FXML
    private void password(KeyEvent event) {
        if (event.isControlDown() && event.isShiftDown() && event.getCode() == KeyCode.W) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("connexion.fxml"));
                Stage stage2 = new Stage();
                stage2.setScene(new Scene(loader.load()));
                stage2.setTitle("Admin");
                stage2.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    private void openFicheSalaries(Salaries selectedSalarie) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ficheSalaries.fxml"));
            Parent root = loader.load();

            //on va récupérer la nouvelle
            FicheSalariesController ficheSalariesController = loader.getController();
            //on apelle les données des textField avec l'id du salaries sélectionné en paramètre
            ficheSalariesController.initialize(selectedSalarie);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button searchButton;
    @FXML
    void search(ActionEvent event){
        salariesTable.getItems().clear();
        List<SalariesDTO> salariesList = salariesDAO.getAllSalaries();
        List<Salaries> listConvert = DtoToSalaries.ListDtoSalaries(salariesList);

        if (!fieldIdSalaries.getText().isEmpty()){
            listConvert.removeIf((e -> e.getId_salarie()!=Integer.parseInt(fieldIdSalaries.getText())));
        }
        if (!fieldPrenom.getText().isEmpty()){
            listConvert.removeIf(salaries -> !salaries.getPrenom().contains(fieldPrenom.getText()));
        }
        if (!fieldTypeService.getText().isEmpty()){
            listConvert.removeIf(salaries -> !salaries.getType_service().contains(fieldTypeService.getText()));
        }
        if (!fieldTypeSite.getText().isEmpty()){
            listConvert.removeIf(salaries -> !salaries.getType_site().contains(fieldTypeSite.getText()));
        }
        for (Salaries salaries: listConvert){
            salariesTable.getItems().add(salaries);
        }

    }
}
