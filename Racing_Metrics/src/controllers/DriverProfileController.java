package controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class DriverProfileController implements Initializable {

    @FXML
    private TableView<DriverRow> driverTable;

    @FXML
    private TableColumn<DriverRow, Integer> colId;

    @FXML
    private TableColumn<DriverRow, String> colName;

    @FXML
    private TableColumn<DriverRow, String> colTeam;

    @FXML
    private TableColumn<DriverRow, String> colCountry;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTeam.setCellValueFactory(new PropertyValueFactory<>("team"));
        colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));

        ObservableList<DriverRow> rows = FXCollections.observableArrayList(
                new DriverRow(1, "Lewis Hamilton", "Mercedes", "UK"),
                new DriverRow(7, "Max Verstappen", "Red Bull", "Netherlands"),
                new DriverRow(44, "Alex Johnson", "Ferrari", "USA")
        );

        driverTable.setItems(rows);
    }

    public void backToDashboard() {
        try {
            main.sceneManager.loadScene("DashboardUI.fxml", "Dashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class DriverRow {
        private final SimpleIntegerProperty id;
        private final SimpleStringProperty name;
        private final SimpleStringProperty team;
        private final SimpleStringProperty country;

        public DriverRow(int id, String name, String team, String country) {
            this.id = new SimpleIntegerProperty(id);
            this.name = new SimpleStringProperty(name);
            this.team = new SimpleStringProperty(team);
            this.country = new SimpleStringProperty(country);
        }

        public int getId() { return id.get(); }
        public String getName() { return name.get(); }
        public String getTeam() { return team.get(); }
        public String getCountry() { return country.get(); }
    }
}
