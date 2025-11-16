package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class DriverStatsController implements Initializable {

    @FXML private ComboBox<String> cbDriver;
    @FXML private BarChart<String, Number> barChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbDriver.setItems(FXCollections.observableArrayList("A. Driver","B. Racer","C. Pilot"));
        CategoryAxis x = (CategoryAxis) barChart.getXAxis();
        NumberAxis y = (NumberAxis) barChart.getYAxis();
        barChart.getData().clear();
    }

    @FXML
    void showStats() {
        String d = cbDriver.getValue();
        if (d == null) return;
        barChart.getData().clear();
        XYChart.Series<String, Number> s = new XYChart.Series<>();
        s.setName("Lap avg (s)");
        // sample data
        s.getData().add(new XYChart.Data<>("Race1", 78));
        s.getData().add(new XYChart.Data<>("Race2", 76));
        s.getData().add(new XYChart.Data<>("Race3", 77));
        barChart.getData().add(s);
    }
    public void backToDashboard() { try { main.sceneManager.loadScene("DashboardUI.fxml", "Dashboard"); } catch (Exception e) { e.printStackTrace(); } }
}

