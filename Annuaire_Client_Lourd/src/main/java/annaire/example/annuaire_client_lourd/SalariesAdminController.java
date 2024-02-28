package annaire.example.annuaire_client_lourd;

import annaire.example.annuaire_client_lourd.dao.SalariesDAO;
import annaire.example.annuaire_client_lourd.models.SalariesDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SalariesAdminController {


    @FXML
    private Button buttonGoToAddSalaries;

    @FXML
    private Button buttonGoToUpdateSalaries;

    @FXML
    private Button buttonGoToDeleteSalaries;

    private static Stage deleteSalarieStage;

    @FXML
    private TableView<SalariesDTO> salariesAdminTable;

    @FXML
    private TableColumn<SalariesDTO, Integer> id_salarie;

    @FXML
    private TableColumn<SalariesDTO, String> prenomColumn;

    @FXML
    private TableColumn<SalariesDTO, String> nomColumn;

    @FXML
    private TableColumn<SalariesDTO, String> portableColumn;

    @FXML
    private TableColumn<SalariesDTO, String> fixeColumn;

    @FXML
    private TableColumn<SalariesDTO, String> emailColumn;

    @FXML
    private TableColumn<SalariesDTO, String> site_id;

    @FXML
    private TableColumn<SalariesDTO, String> service_id;

    private final SalariesDAO salariesDAO = new SalariesDAO();

    @FXML
    public void initialize() {
        // Charge les données depuis l'API
        List<SalariesDTO> salariesList = salariesDAO.getAllSalaries();

        // Lier les données aux cellules du tableau
        id_salarie.setCellValueFactory(cellData -> cellData.getValue().id_salarieProperty().asObject());
        prenomColumn.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
        nomColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        portableColumn.setCellValueFactory(cellData -> cellData.getValue().portableProperty());
        fixeColumn.setCellValueFactory(cellData -> cellData.getValue().fixeProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        site_id.setCellValueFactory(cellData -> cellData.getValue().site_idProperty().asString());
        service_id.setCellValueFactory(cellData -> cellData.getValue().service_idProperty().asString());

        refreshSalariesList();
    }

    // Ajoute ou refresh les données dans le tableau selon le contexte
    private void refreshSalariesList() {
        List<SalariesDTO> salariesList = salariesDAO.getAllSalaries();
        salariesAdminTable.getItems().clear();
        salariesAdminTable.getItems().addAll(salariesList);
    }

    @FXML
    void GoToDeleteSalaries(ActionEvent event) {

        // Vérifie si la scène est déjà ouverte
        if (deleteSalarieStage == null || !deleteSalarieStage.isShowing()) {
            try {
                // Charge le fichier FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("deleteSalaries.fxml"));
                Parent root = loader.load();

                // New scène
                Scene scene = new Scene(root);

                // Créé un nouveau stage pour qui se met par dessus la page principale
                deleteSalarieStage = new Stage();

                // Met la new scène dans le stage
                deleteSalarieStage.setScene(scene);

                deleteSalarieStage.setResizable(false);

                // Affiche et refresh le nouveau stage
                deleteSalarieStage.showAndWait();
                refreshSalariesList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static Stage addSalarieStage;

    @FXML
    void GoToAddSalaries(ActionEvent event) {
        // Vérifie si la scène est déjà ouverte
        if (addSalarieStage == null || !addSalarieStage.isShowing()) {
            try {
                // Charge le fichier FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("addSalaries.fxml"));
                Parent root = loader.load();

                // New scène
                Scene scene = new Scene(root);

                // Créé un nouveau stage qui se met au dessus de la page principale
                addSalarieStage = new Stage();

                // Met la new scène dans le stage
                addSalarieStage.setScene(scene);

                addSalarieStage.setResizable(false);

                // Affiche et refresh le nouveau stage
                addSalarieStage.showAndWait();
                refreshSalariesList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Stage updateSalarieStage;

    @FXML
    void GoToUpdateSalaries(ActionEvent event) {
        // Vérifie si la scène est déjà ouverte
        if (updateSalarieStage == null || !updateSalarieStage.isShowing()) {
            try {
                // Charge le fichier FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("updateSalaries.fxml"));
                Parent root = loader.load();

                // Nouvelle scène
                Scene scene = new Scene(root);

                // Créé un nouveau stage qui se met au dessus de la page princpale
                updateSalarieStage = new Stage();

                // Met la new scène dans le stage
                updateSalarieStage.setScene(scene);

                updateSalarieStage.setResizable(false);

                // Affiche et refresh le nouveau stage
                updateSalarieStage.showAndWait();
                refreshSalariesList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private TextField fieldIdSalaries;
    @FXML
    private Button searchIdSalaries;

    @FXML
    void searchSalaries(ActionEvent event) {

        try {
            int id = Integer.parseInt(fieldIdSalaries.getText());

            SalariesDTO salaries = SalariesDAO.getSalariesById(id);

            // vider le tableau
            salariesAdminTable.getItems().clear();

            // ajouter les valeurs dans le tableau
            if (salaries != null) {
                salariesAdminTable.getItems().add(salaries);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
