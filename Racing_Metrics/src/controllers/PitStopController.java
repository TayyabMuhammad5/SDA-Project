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
import java.time.LocalTime;
import java.util.ResourceBundle;

public class PitStopController implements Initializable {

    @FXML private ComboBox<String> cbRace;
    @FXML private ComboBox<String> cbDriver;
    @FXML private TextField tfStopDuration; // seconds
    @FXML private TextArea taNotes;
    @FXML private TableView<PitRow> tablePit;
    @FXML private TableColumn<PitRow, Integer> colPitNo;
    @FXML private TableColumn<PitRow, String> colPitDriver;
    @FXML private TableColumn<PitRow, String> colDuration;

    private final ObservableList<PitRow> rows = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbRace.setItems(FXCollections.observableArrayList("Silverstone","Monaco","Monza"));
        cbDriver.setItems(FXCollections.observableArrayList("A. Driver","B. Racer","C. Pilot"));
        colPitNo.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("no"));
        colPitDriver.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("driver"));
        colDuration.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("duration"));
        tablePit.setItems(rows);
    }

    @FXML
    void savePit() {
        String race = cbRace.getValue();
        String driver = cbDriver.getValue();
        String dur = tfStopDuration.getText();
        if (race==null||driver==null||dur==null||dur.isBlank()) {
            alert(Alert.AlertType.WARNING, "Validation", "Select race/driver and enter duration.");
            return;
        }
        try {
            double s = Double.parseDouble(dur.trim());
            int no = rows.size()+1;
            rows.add(new PitRow(no, driver, String.format("%.2f", s), taNotes.getText()));
            tfStopDuration.clear(); taNotes.clear();
        } catch (NumberFormatException ex) {
            alert(Alert.AlertType.ERROR, "Invalid", "Duration must be numeric seconds.");
        }
    }

    @FXML
    void exportCsv() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV","*.csv"));
        File f = chooser.showSaveDialog(tablePit.getScene().getWindow());
        if (f==null) return;
        try (BufferedWriter w = new BufferedWriter(new FileWriter(f))) {
            w.write("No,Driver,Duration,Notes\n");
            for (PitRow r: rows) w.write(r.no+","+r.driver+","+r.duration+","+r.notes+"\n");
            alert(Alert.AlertType.INFORMATION, "Export", "Saved to "+f.getAbsolutePath());
        } catch (IOException e) { alert(Alert.AlertType.ERROR, "Export", e.getMessage()); }
    }

    private void alert(Alert.AlertType t, String title, String msg) { new Alert(t, msg, ButtonType.OK).showAndWait(); }

    public static class PitRow {
        public final Integer no; public final String driver; public final String duration; public final String notes;
        public PitRow(Integer no, String driver, String duration, String notes) { this.no=no; this.driver=driver; this.duration=duration; this.notes=notes; }
        public Integer getNo(){return no;} public String getDriver(){return driver;} public String getDuration(){return duration;} public String getNotes(){return notes;}
    }
    public void backToDashboard() { try { main.sceneManager.loadScene("DashboardUI.fxml", "Dashboard"); } catch (Exception e) { e.printStackTrace(); } }
}



