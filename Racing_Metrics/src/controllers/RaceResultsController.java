package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class RaceResultsController implements Initializable {

    @FXML private ComboBox<String> cbRace;
    @FXML private TableView<ResultRow> tableResults;
    @FXML private TableColumn<ResultRow,Integer> colPos;
    @FXML private TableColumn<ResultRow,String> colDriver;
    @FXML private TableColumn<ResultRow,String> colTeam;
    @FXML private TableColumn<ResultRow,Integer> colPoints;

    private final ObservableList<ResultRow> rows = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Sample races
        cbRace.setItems(FXCollections.observableArrayList("Silverstone","Monaco","Monza"));

        // Bind table columns to ResultRow properties
        colPos.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("pos"));
        colDriver.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("driver"));
        colTeam.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("team"));
        colPoints.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("points"));

        tableResults.setItems(rows);
    }

    @FXML
    void loadResults() {
        String race = cbRace.getValue();
        if (race == null) return;

        // Sample data for demo
        rows.clear();
        rows.addAll(
                new ResultRow(1, "A. Driver", "Team Alpha", 25),
                new ResultRow(2, "B. Racer", "Team Beta", 18),
                new ResultRow(3, "C. Pilot", "Team Gamma", 15)
        );
    }

    @FXML
    void backToDashboard() {
        try {
            main.sceneManager.loadScene("DashboardUI.fxml", "Dashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class ResultRow {
        private final Integer pos;
        private final String driver;
        private final String team;
        private final Integer points;

        public ResultRow(Integer pos, String driver, String team, Integer points){
            this.pos = pos;
            this.driver = driver;
            this.team = team;
            this.points = points;
        }

        public Integer getPos(){ return pos; }
        public String getDriver(){ return driver; }
        public String getTeam(){ return team; }
        public Integer getPoints(){ return points; }
    }
}
