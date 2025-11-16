package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;


public class sceneManager {
    private static Stage stage;
    private static final String FXML_PATH = "/UI/";

    public static void initialize(Stage primaryStage) {
        stage = primaryStage;
    }

  
    public static void showLoginScene() {
        try {
            loadScene("DashboardUI.fxml", "F1 Racing MIS - Login");
        } catch (IOException e) {
            System.err.println("Error loading login scene: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Load and display a scene from FXML file
     * @param fxmlFile Name of the FXML file (without path)
     * @param title Window title
     */
    public static void loadScene(String fxmlFile, String title) throws IOException {
        try {
            // Load the FXML file
            URL resourceUrl = sceneManager.class.getResource(FXML_PATH + fxmlFile);
            if (resourceUrl == null) {
                throw new IOException("FXML file not found: " + fxmlFile);
            }
            
            FXMLLoader loader = new FXMLLoader(resourceUrl);
            Pane root = loader.load();
            
            // Create scene with the loaded root
            Scene scene = new Scene(root);
            
            // Set scene properties
            stage.setTitle(title);
            stage.setScene(scene);
            stage.setResizable(true);
            stage.show();
            
        } catch (IOException e) {
            System.err.println("Failed to load scene: " + fxmlFile);
            throw e;
        }
    }

    /**
     * Get the current stage
     */
    public static Stage getStage() {
        return stage;
    }

    /**
     * Close the application
     */
    public static void closeApplication() {
        if (stage != null) {
            stage.close();
        }
    }
}
