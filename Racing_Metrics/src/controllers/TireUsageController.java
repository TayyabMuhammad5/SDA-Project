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
import java.util.ResourceBundle;

public class TireUsageController implements Initializable {

    @FXML private ComboBox<String> cbRace;
    @FXML private ComboBox<String> cbDriver;
    @FXML private ComboBox<String> cbTireType;
    @FXML private TextField tfLapsUsed;
    @FXML private TableView<TireRow> table;
    @FXML private TableColumn<TireRow, Integer> colNo;
    @FXML private TableColumn<TireRow, String> colDriver;
    @FXML private TableColumn<TireRow, String> colTire;
    @FXML private TableColumn<TireRow, Integer> colLaps;

    private final ObservableList<TireRow> rows = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbRace.setItems(FXCollections.observableArrayList("Silverstone","Monaco","Monza"));
        cbDriver.setItems(FXCollections.observableArrayList("A. Driver","B. Racer","C. Pilot"));
        cbTireType.setItems(FXCollections.observableArrayList("Soft","Medium","Hard","Wet"));
        colNo.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("no"));
        colDriver.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("driver"));
        colTire.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("tire"));
        colLaps.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("laps"));
        table.setItems(rows);
    }

    @FXML
    void saveUsage() {
        String r = cbRace.getValue(); String d = cbDriver.getValue(); String t = cbTireType.getValue();
        String laps = tfLapsUsed.getText();
        if (r==null||d==null||t==null||laps==null||laps.isBlank()) { alert(Alert.AlertType.WARNING, "Validation","Select race/driver/tire and enter laps"); return; }
        try {
            int l = Integer.parseInt(laps.trim());
            rows.add(new TireRow(rows.size()+1, d, t, l));
            tfLapsUsed.clear();
        } catch (NumberFormatException ex) { alert(Alert.AlertType.ERROR,"Invalid","Laps must be integer"); }
    }

    @FXML
    void exportCsv() {
        FileChooser chooser = new FileChooser(); chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV","*.csv"));
        File f = chooser.showSaveDialog(table.getScene().getWindow()); if (f==null) return;
        try (BufferedWriter w = new BufferedWriter(new FileWriter(f))) {
            w.write("No,Driver,Tire,Laps\n"); for (TireRow tr: rows) w.write(tr.no+","+tr.driver+","+tr.tire+","+tr.laps+"\n");
            alert(Alert.AlertType.INFORMATION,"Export","Saved to "+f.getAbsolutePath());
        } catch (IOException e) { alert(Alert.AlertType.ERROR,"Export",e.getMessage()); }
    }

    private void alert(Alert.AlertType t, String title, String msg) { new Alert(t, msg, ButtonType.OK).showAndWait(); }

    public static class TireRow { public final Integer no; public final String driver; public final String tire; public final Integer laps; public TireRow(Integer no,String driver,String tire,Integer laps){this.no=no;this.driver=driver;this.tire=tire;this.laps=laps;} public Integer getNo(){return no;} public String getDriver(){return driver;} public String getTire(){return tire;} public Integer getLaps(){return laps;} }
    public void backToDashboard() { try { main.sceneManager.loadScene("DashboardUI.fxml", "Dashboard"); } catch (Exception e) { e.printStackTrace(); } }
}
