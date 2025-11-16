package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * DashboardController - handles navigation from dashboard to feature screens
 */
public class DashboardController {

    @FXML private Button dummy;

    @FXML
    private void openDriverProfile() {
        open("DriverProfileUI.fxml", "Driver Profiles");
    }

    @FXML
    private void openRaceEntry() {
        open("RaceEntryUI.fxml", "Race Entry");
    }

    @FXML
    private void openLapTime() {
        open("LapTimeUI.fxml", "Record Lap Times");
    }

    @FXML
    private void openPitStop() {
        open("PitStopUI.fxml", "Record Pit Stops");
    }

    @FXML
    private void openTireUsage() {
        open("TireUsageUI.fxml", "Tire Usage");
    }

    @FXML
    private void openDriverStats() {
        open("DriverStatsUI.fxml", "Driver Stats");
    }

    @FXML
    private void openRaceResults() {
        open("RaceResultsUI.fxml", "Race Results");
    }

    @FXML
    private void openSeasonSummary() {
        open("SeasonSummaryUI.fxml", "Season Summary");
    }

    @FXML
    private void openPerformanceReport() {
        open("PerformanceReportUI.fxml", "Performance Report");
    }

    @FXML
    private void openSeasonRanking() {
        open("SeasonRankingUI.fxml", "Season Ranking");
    }

    @FXML
    private void openTireRecommendation() {
        open("TireRecommendationUI.fxml", "Tire Recommendation");
    }

    @FXML
    private void logout() {
        open("loginUI.fxml", "Login");
    }

    private void open(String fxml, String title) {
        try {
            main.sceneManager.loadScene(fxml, title);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
