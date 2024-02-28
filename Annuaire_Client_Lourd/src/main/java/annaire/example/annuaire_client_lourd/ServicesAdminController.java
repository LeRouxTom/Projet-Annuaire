package annaire.example.annuaire_client_lourd;

import annaire.example.annuaire_client_lourd.dao.ServicesDAO;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ServicesAdminController {


    @FXML
    private Button buttonGoToAddService;

    @FXML
    private Button buttonGoToUpdateService;

    @FXML
    private Button buttonGoToDeleteService;

    private static Stage deleteServiceStage;

    @FXML
    private TableView<ServicesDTO> servicesTable;

    @FXML
    private TableColumn<ServicesDTO, Integer> id_service;

    @FXML
    private TableColumn<ServicesDTO, String> type_service;



    private final ServicesDAO servicesDAO = new ServicesDAO();

    @FXML
    public void initialize() {
        // Charge les données depuis l'API
        List<ServicesDTO> serviceList = servicesDAO.getAllServices();

        // Lie les données aux cellules du tableau
        id_service.setCellValueFactory(cellData -> cellData.getValue().id_serviceProperty().asObject());
        type_service.setCellValueFactory(cellData -> cellData.getValue().type_serviceProperty());

        refreshServicesList();
    }

    // Ajoute ou refresh les données dans le tableau selon le contexte
    private void refreshServicesList() {
        List<ServicesDTO>serviceList= servicesDAO.getAllServices();
        servicesTable.getItems().clear();
        servicesTable.getItems().addAll(serviceList);
    }

    @FXML
    void GoToDeleteService(ActionEvent event) {

        // Vérifie si la scène est déjà ouverte
        if (deleteServiceStage == null || !deleteServiceStage.isShowing()) {
            try {
                // Charge le fichier FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("deleteServices.fxml"));
                Parent root = loader.load();

                // Créé une nouvelle scène
                Scene scene = new Scene(root);

                // Créé un nouveau stage pour ne pas fermer la page actuelle mais s'ajouter en plus
                deleteServiceStage = new Stage();

                // Met la nouvelle scène dans le nouveau stage
                deleteServiceStage.setScene(scene);

                deleteServiceStage.setResizable(false);

                // Affiche le nouveau stage
                deleteServiceStage.showAndWait();
                refreshServicesList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static Stage addServiceStage;

    @FXML
    void GoToAddService(ActionEvent event) {
        // Vérifie si la scène est déjà ouverte
        if (addServiceStage == null || !addServiceStage.isShowing()) {
            try {
                // Charge le fichier FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("addServices.fxml"));
                Parent root = loader.load();

                // Créé une nouvelle scène
                Scene scene = new Scene(root);

                // Créé un nouveau stage pour ne pas fermer la page actuelle mais s'ajouter en plus
                addServiceStage = new Stage();

                // Met la nouvelle scène dans le nouveau stage
                addServiceStage.setScene(scene);

                addServiceStage.setResizable(false);

                // Affiche le nouveau stage
                addServiceStage.showAndWait();
                refreshServicesList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Stage updateServiceStage;

    @FXML
    void GoToUpdateService(ActionEvent event) {
        // Vérifie si la scène est déjà ouverte
        if (updateServiceStage == null || !updateServiceStage.isShowing()) {
            try {
                // Charge le fichier FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("updateServices.fxml"));
                Parent root = loader.load();

                // Créé une nouvelle scène
                Scene scene = new Scene(root);

                // Créé un nouveau stage pour ne pas fermer la page actuelle mais s'ajouter en plus
                updateServiceStage = new Stage();

                // Met la nouvelle scène dans le nouveau stage
                updateServiceStage.setScene(scene);

                updateServiceStage.setResizable(false);

                // Affiche le nouveau stage
                updateServiceStage.showAndWait();
                refreshServicesList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private TextField fieldIdService;

    @FXML
    private Button searchIdService;

    @FXML
    void searchServices(ActionEvent event) {

        try {
            int id = Integer.parseInt(fieldIdService.getText());

            ServicesDTO service = ServicesDAO.getServicesById(id);

            // Supprime les données existantes dans le tableau
            servicesTable.getItems().clear();

            // Ajoute la famille recherchée dans le tableau
            if (service != null) {
                servicesTable.getItems().add(service);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
