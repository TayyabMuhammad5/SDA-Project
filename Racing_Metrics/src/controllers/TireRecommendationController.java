package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class TireRecommendationController implements Initializable {

    @FXML private ComboBox<String> cbTrack;
    @FXML private TextArea taRecommendation;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbTrack.setItems(FXCollections.observableArrayList("Silverstone","Monaco","Monza"));
    }

    @FXML
    void recommend() {
        String t = cbTrack.getValue(); if (t==null) { taRecommendation.setText("Select a track"); return; }
        // very simple heuristic
        String rec;
        switch (t) {
            case "Monaco": rec = "Use Soft or Medium — low degradation, high grip."; break;
            case "Monza": rec = "Use Hard for long stints; Medium for balanced approach."; break;
            default: rec = "Medium recommended by default; consider Soft for qualifying."; break;
        }
        taRecommendation.setText(rec);
    }
    public void backToDashboard() {
        try {
            main.sceneManager.loadScene("DashboardUI.fxml", "Dashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

