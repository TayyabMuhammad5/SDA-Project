package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class SeasonSummaryController implements Initializable {

    @FXML private LineChart<Number, Number> lineChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NumberAxis x = (NumberAxis) lineChart.getXAxis();
        NumberAxis y = (NumberAxis) lineChart.getYAxis();
        lineChart.getData().clear();
    }

    @FXML
    void showAggregate() {
        XYChart.Series<Number, Number> s = new XYChart.Series<>();
        s.setName("Points");
        s.getData().add(new XYChart.Data<>(1, 25));
        s.getData().add(new XYChart.Data<>(2, 18));
        s.getData().add(new XYChart.Data<>(3, 15));
        lineChart.getData().setAll(s);
    }
    public void backToDashboard() { try { main.sceneManager.loadScene("DashboardUI.fxml", "Dashboard"); } catch (Exception e) { e.printStackTrace(); } }
}

