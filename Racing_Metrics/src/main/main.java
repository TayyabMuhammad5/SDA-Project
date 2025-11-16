package main;

import javafx.application.Application;
import javafx.stage.Stage;


public class main extends Application {

    public void start(Stage primaryStage) {
        try {
            
            sceneManager.initialize(primaryStage);
            
           
            primaryStage.setWidth(1000);
            primaryStage.setHeight(700);
            primaryStage.setTitle("F1 Racing MIS");
            
        
            sceneManager.showLoginScene();
            
        } catch (Exception e) {
            System.err.println("Error starting application: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
