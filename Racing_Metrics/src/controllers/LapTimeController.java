package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;

public class LapTimeController implements Initializable {

    @FXML
    private ComboBox<String> cbRace;

    @FXML
    private ComboBox<String> cbDriver;

    @FXML
    private TextField tfLapTime; // format seconds.millis

    @FXML
    private Button btnSave;

    @FXML
    private TableView<LapRow> tableLapTimes;

    @FXML
    private TableColumn<LapRow, Integer> colLapNo;

    @FXML
    private TableColumn<LapRow, String> colDriver;

    @FXML
    private TableColumn<LapRow, String> colTime;

    private final ObservableList<LapRow> laps = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // sample items
        cbRace.setItems(FXCollections.observableArrayList("Silverstone", "Monaco", "Monza"));
        cbDriver.setItems(FXCollections.observableArrayList("A. Driver", "B. Racer", "C. Pilot"));

        colLapNo.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("lapNo"));
        colDriver.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("driver"));
        colTime.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("timeStr"));

        tableLapTimes.setItems(laps);
    }

    @FXML
    void saveLap() {
        String race = cbRace.getValue();
        String driver = cbDriver.getValue();
        String timeText = tfLapTime.getText();

        if (race == null || driver == null || timeText == null || timeText.isBlank()) {
            showAlert(Alert.AlertType.WARNING, "Validation", "Please select race, driver and enter lap time.");
            return;
        }

        // basic parse, allow seconds.millis
        try {
            double secs = Double.parseDouble(timeText.trim());
            // accept
            int lapNo = laps.size() + 1;
            LapRow row = new LapRow(lapNo, driver, String.format("%.3f", secs));
            laps.add(row);
            tfLapTime.clear();
        } catch (NumberFormatException ex) {
            showAlert(Alert.AlertType.ERROR, "Invalid time", "Enter lap time as seconds.millis (e.g. 78.345)");
        }
    }

    @FXML
    void exportCsv() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Export lap times");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files", "*.csv"));
        File f = chooser.showSaveDialog(tableLapTimes.getScene().getWindow());
        if (f == null) return;
        try (BufferedWriter w = new BufferedWriter(new FileWriter(f))) {
            w.write("Lap,Driver,Time\n");
            for (LapRow r : laps) {
                w.write(r.getLapNo() + "," + r.getDriver() + "," + r.getTimeStr() + "\n");
            }
            showAlert(Alert.AlertType.INFORMATION, "Export", "Saved to " + f.getAbsolutePath());
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Export error", e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType t, String title, String message) {
        Alert a = new Alert(t, message, ButtonType.OK);
        a.setTitle(title);
        a.showAndWait();
    }

    public static class LapRow {
        private final Integer lapNo;
        private final String driver;
        private final String timeStr;

        public LapRow(Integer lapNo, String driver, String timeStr) {
            this.lapNo = lapNo;
            this.driver = driver;
            this.timeStr = timeStr;
        }

        public Integer getLapNo() { return lapNo; }
        public String getDriver() { return driver; }
        public String getTimeStr() { return timeStr; }
    }
    @FXML
    private void saveLapTimes() {
        // Add your logic here. For now, just print to console:
        System.out.println("Save button clicked!");
    }
    public void backToDashboard() { try { main.sceneManager.loadScene("DashboardUI.fxml", "Dashboard"); } catch (Exception e) { e.printStackTrace(); } }
}
