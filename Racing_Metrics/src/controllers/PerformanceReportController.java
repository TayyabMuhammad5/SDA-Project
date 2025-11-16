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

public class PerformanceReportController implements Initializable {

    @FXML private DatePicker dpFrom;
    @FXML private DatePicker dpTo;
    @FXML private ComboBox<String> cbDriver;
    @FXML private Button btnGenerate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbDriver.setItems(FXCollections.observableArrayList("A. Driver","B. Racer","C. Pilot"));
    }

    @FXML
    void generateReport() {
        // produce a small CSV in-memory and allow save
        FileChooser chooser = new FileChooser(); chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV","*.csv"));
        File f = chooser.showSaveDialog(btnGenerate.getScene().getWindow()); if (f==null) return;
        try (BufferedWriter w = new BufferedWriter(new FileWriter(f))) {
            w.write("Driver,From,To,Metric1,Metric2\n");
            w.write((cbDriver.getValue()==null?"ALL":cbDriver.getValue())+","+(dpFrom.getValue()==null?"":dpFrom.getValue())+","+(dpTo.getValue()==null?"":dpTo.getValue())+",123,456\n");
            new Alert(Alert.AlertType.INFORMATION, "Report saved to " + f.getAbsolutePath(), ButtonType.OK).showAndWait();
        } catch (IOException e) { new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).showAndWait(); }
    }
    public void backToDashboard() { try { main.sceneManager.loadScene("DashboardUI.fxml", "Dashboard"); } catch (Exception e) { e.printStackTrace(); } }
}
