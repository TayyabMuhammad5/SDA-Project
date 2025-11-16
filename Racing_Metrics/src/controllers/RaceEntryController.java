package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RaceEntryController {

    @FXML
    private Button backButton;

    @FXML
    private Label titleLabel;

    @FXML
    private TableView<RaceRow> raceTable;

    @FXML
    private TableColumn<RaceRow, Integer> colRaceId;

    @FXML
    private TableColumn<RaceRow, LocalDate> colRaceDate;

    @FXML
    private TableColumn<RaceRow, String> colRaceLocation;

    @FXML
    private TableColumn<RaceRow, String> colRaceWeather;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        colRaceId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colRaceDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colRaceLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colRaceWeather.setCellValueFactory(new PropertyValueFactory<>("weather"));

        ObservableList<RaceRow> rows = FXCollections.observableArrayList(
                new RaceRow(1, LocalDate.of(2025, 3, 15), "Silverstone", "Dry"),
                new RaceRow(2, LocalDate.of(2025, 4, 6), "Monaco", "Wet"),
                new RaceRow(3, LocalDate.of(2025, 5, 20), "Monza", "Cloudy")
        );

        raceTable.setItems(rows);
    }

    @FXML
    public void backToDashboard() {
        try {
            main.sceneManager.loadScene("DashboardUI.fxml", "Dashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class RaceRow {
        private final Integer id;
        private final LocalDate date;
        private final String location;
        private final String weather;

        public RaceRow(Integer id, LocalDate date, String location, String weather) {
            this.id = id;
            this.date = date;
            this.location = location;
            this.weather = weather;
        }

        public Integer getId() { return id; }
        public LocalDate getDate() { return date; }
        public String getLocation() { return location; }
        public String getWeather() { return weather; }
    }
}
