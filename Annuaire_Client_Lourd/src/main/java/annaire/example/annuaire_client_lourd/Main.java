package annaire.example.annuaire_client_lourd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    private static Stage primaryStage; // Déclaration de la variable pour stocker la référence de la scène principale

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        Main.primaryStage = primaryStage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("salaries.fxml"));
        Parent root = fxmlLoader.load();

        setPrimaryStage(stage);

        // Obtenir les dimensions de l'écran
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

        // Définir la taille de la fenêtre à 96% de hauteur des dimensions de l'écran
        double sceneHeight = screenHeight * 0.96;
        double sceneWidth = screenWidth * 1;

        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        stage.setTitle("Annuaire");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }

}