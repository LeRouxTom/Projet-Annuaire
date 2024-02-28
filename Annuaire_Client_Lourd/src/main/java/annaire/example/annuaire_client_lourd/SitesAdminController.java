package annaire.example.annuaire_client_lourd;

import annaire.example.annuaire_client_lourd.dao.SitesDAO;
import annaire.example.annuaire_client_lourd.models.ServicesDTO;
import annaire.example.annuaire_client_lourd.models.SitesDTO;
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

public class SitesAdminController {


    @FXML
    private Button buttonGoToAddSite;

    @FXML
    private Button buttonGoToUpdateSite;

    @FXML
    private Button buttonGoToDeleteSite;

    private static Stage deleteSiteStage;

    @FXML
    private TableView<SitesDTO> sitesTable;

    @FXML
    private TableColumn<SitesDTO, Integer> id_sites;

    @FXML
    private TableColumn<SitesDTO, String> type_siteColumn;

    @FXML
    private TableColumn<SitesDTO, String> villeColumn;

    private final SitesDAO siteDAO = new SitesDAO();

    @FXML
    public void initialize() {
        // Charge les données depuis l'API
        List<SitesDTO> sitesList = siteDAO.getAllSites();

        // Lie les données aux cellules du tableau
        id_sites.setCellValueFactory(cellData -> cellData.getValue().id_siteProperty().asObject());
        type_siteColumn.setCellValueFactory(cellData -> cellData.getValue().type_siteProperty());
        villeColumn.setCellValueFactory(cellData -> cellData.getValue().villeProperty());

        refreshSitesList();
    }

    // Ajoute ou refresh les données dans le tableau selon le contexte
    private void refreshSitesList() {
        List<SitesDTO>sitesList= siteDAO.getAllSites();
        sitesTable.getItems().clear();
        sitesTable.getItems().addAll(sitesList);
    }

    @FXML
    void GoToDeleteSite(ActionEvent event) {

        // Vérifie si la scène est déjà ouverte (pour qu'il ne soit pas possible d'ouvrir une autre fenêtre de suppression en même temps)
        if (deleteSiteStage == null || !deleteSiteStage.isShowing()) {
            try {
                // Charge le fichier FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("deleteSites.fxml"));
                Parent root = loader.load();

                // New scène
                Scene scene = new Scene(root);

                // Créé un nouveau stage pour ne pas fermer la page actuelle
                deleteSiteStage = new Stage();

                // La nouvelle scene est mise dans le stage
                deleteSiteStage.setScene(scene);

                deleteSiteStage.setResizable(false);

                // Affiche et refresh le nouveau stage
                deleteSiteStage.showAndWait();
                refreshSitesList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static Stage addSiteStage;

    @FXML
    void GoToAddSite(ActionEvent event) {
        // Vérifie si la scène est déjà ouverte (pour qu'il ne soit pas possible d'ouvrir une autre fenêtre d'ajout en même temps)
        if (addSiteStage == null || !addSiteStage.isShowing()) {
            try {
                // Charge le fichier FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("addSites.fxml"));
                Parent root = loader.load();

                // New scène
                Scene scene = new Scene(root);

                // Créé un nouveau stage pour ne pas fermer la page actuelle
                addSiteStage = new Stage();

                // La nouvelle scene est mise dans le stage
                addSiteStage.setScene(scene);

                addSiteStage.setResizable(false);


                // Affiche et refresh le nouveau stage
                addSiteStage.showAndWait();
                refreshSitesList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Stage updateSiteStage;

    @FXML
    void GoToUpdateSite(ActionEvent event) {
        // Vérifie si la scène est déjà ouverte (pour qu'il ne soit pas possible d'ouvrir une autre fenêtre d'update en même temps)
        if (updateSiteStage == null || !updateSiteStage.isShowing()) {
            try {
                // Charge le fichier FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("updateSites.fxml"));
                Parent root = loader.load();

                // New scene
                Scene scene = new Scene(root);

                // Créé un nouveau stage pour ne pas fermer la page actuelle mais la mettre au dessus
                updateSiteStage = new Stage();

                // La nouvelle scene est mise dans le stage
                updateSiteStage.setScene(scene);

                updateSiteStage.setResizable(false);

                // Affiche et refresh le nouveau stage
                updateSiteStage.showAndWait();
                refreshSitesList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private TextField fieldIdSite;
    @FXML
    private Button searchIdSite;

    @FXML
    void searchSites(ActionEvent event) {

        try {
            int id = Integer.parseInt(fieldIdSite.getText());

            SitesDTO site = SitesDAO.getSitesById(id);

            // Supprime les données existantes dans le tableau
            sitesTable.getItems().clear();

            // Ajoute la famille recherchée dans le tableau
            if (site != null) {
                sitesTable.getItems().add(site);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
