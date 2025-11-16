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
import java.util.Comparator;
import java.util.ResourceBundle;

public class SeasonRankingController implements Initializable {

    @FXML private ComboBox<String> cbSeason;
    @FXML private TableView<RankRow> table;
    @FXML private TableColumn<RankRow,Integer> colRank;
    @FXML private TableColumn<RankRow,String> colDriver;
    @FXML private TableColumn<RankRow,Integer> colPoints;

    private final ObservableList<RankRow> rows = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbSeason.setItems(FXCollections.observableArrayList("2024","2025"));
        colRank.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("rank"));
        colDriver.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("driver"));
        colPoints.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("points"));
        table.setItems(rows);
    }

    @FXML
    void computeRanking() {
        rows.clear();
        // sample points
        ObservableList<RankRow> tmp = FXCollections.observableArrayList(new RankRow("A. Driver",100), new RankRow("B. Racer",85), new RankRow("C. Pilot",72));
        tmp.sort(Comparator.comparingInt(RankRow::getPoints).reversed());
        int r=1; for (RankRow rr: tmp) rows.add(new RankRow(r++, rr.driver, rr.points));
    }

    @FXML
    void exportCsv() {
        FileChooser chooser = new FileChooser(); chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV","*.csv"));
        File f = chooser.showSaveDialog(table.getScene().getWindow()); if (f==null) return;
        try (BufferedWriter w = new BufferedWriter(new FileWriter(f))) {
            w.write("Rank,Driver,Points\n"); for (RankRow rr: rows) w.write(rr.rank+","+rr.driver+","+rr.points+"\n");
            new Alert(Alert.AlertType.INFORMATION, "Saved to " + f.getAbsolutePath(), ButtonType.OK).showAndWait();
        } catch (IOException e) { new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).showAndWait(); }
    }
    public void backToDashboard() { try { main.sceneManager.loadScene("DashboardUI.fxml", "Dashboard"); } catch (Exception e) { e.printStackTrace(); } }

    public static class RankRow { public final Integer rank; public final String driver; public final Integer points; public RankRow(String driver, Integer points){this.rank=null;this.driver=driver;this.points=points;} public RankRow(Integer rank,String driver,Integer points){this.rank=rank;this.driver=driver;this.points=points;} public Integer getRank(){return rank;} public String getDriver(){return driver;} public Integer getPoints(){return points;} }
}
