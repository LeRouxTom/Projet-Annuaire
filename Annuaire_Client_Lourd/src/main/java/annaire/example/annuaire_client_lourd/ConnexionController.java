package annaire.example.annuaire_client_lourd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class ConnexionController {

    @FXML
    private TextField fieldPassword;

    String password = "AnnuaireAdmin";

    private void centerSceneOnPrimaryScreen(Stage stage) {
        // Obtenir les dimensions de l'écran principal
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // Centrer la scène sur l'écran
        double centerX = bounds.getMinX() + (bounds.getWidth() - stage.getWidth()) / 2;
        double centerY = bounds.getMinY() + (bounds.getHeight() - stage.getHeight()) / 2;

        // Définir la position de la scène
        stage.setX(centerX);
        stage.setY(centerY);
    }
    @FXML
    public Button buttonConnexion;
    @FXML
    public void goToConnexion(ActionEvent event) {
        if (fieldPassword.getText().equals(password)) {
            try {

                Stage currentStage = (Stage) buttonConnexion.getScene().getWindow();
                currentStage.close();


                FXMLLoader loader = new FXMLLoader(getClass().getResource("salariesAdmin.fxml"));
                Parent root = loader.load();


                // Obtenez la hauteur et la longueur de l'écran
                double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
                double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();

                // Définissez la hauteur de la scène à 96% de l'écran
                double sceneHeight = screenHeight * 0.96;
                double sceneWidth = screenWidth * 1;
                Scene scene = new Scene(root, sceneWidth, sceneHeight);


                Stage stage = Main.getPrimaryStage();
                stage.setScene(scene);
                stage.show();

                centerSceneOnPrimaryScreen(stage);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            fieldPassword.setText("");
        }
    }
}
