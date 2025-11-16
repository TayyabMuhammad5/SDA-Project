package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 * LoginController - Handles login UI interactions
 * Converted from React component with equivalent state and event handling logic
 */
public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private VBox errorContainer;

    @FXML
    private Label errorMessage;

    @FXML
    private VBox loadingContainer;

    @FXML
    private ProgressIndicator loadingIndicator;

    private String username = "";
    private String password = "";
    private String error = "";
    private boolean isLoading = false;

  
    @FXML
    public void initialize() {
     
        usernameField.textProperty().addListener((obs, oldVal, newVal) -> {
            this.username = newVal;
        });

    
        passwordField.textProperty().addListener((obs, oldVal, newVal) -> {
            this.password = newVal;
        });
    }

    @FXML
    private void handleLogin() {
        
        clearError();

    
        if (username.isEmpty() || password.isEmpty()) {
            showError("Please enter both username and password");
            return;
        }

       
        setLoading(true);

        PauseTransition delay = new PauseTransition(Duration.millis(1000));
        delay.setOnFinished(event -> {
            setLoading(false);
            showError("Invalid credentials. Please try again.");
        });
        delay.play();
    }

    /**
     * Handle Forgot Password link click
     */
    @FXML
    private void handleForgot() {
        showError("Contact system administrator for password reset");
    }

    /**
     * Display error message
     */
    private void showError(String message) {
        this.error = message;
        errorMessage.setText(message);
        errorContainer.setVisible(true);
        errorContainer.setManaged(true);
    }

    /**
     * Clear error message
     */
    private void clearError() {
        this.error = "";
        errorMessage.setText("");
        errorContainer.setVisible(false);
        errorContainer.setManaged(false);
    }

    /**
     * Set loading state - shows/hides loading indicator
     */
    private void setLoading(boolean loading) {
        this.isLoading = loading;
        loginButton.setDisable(loading);
        loginButton.setText(loading ? "AUTHENTICATING..." : "LOGIN");

        loadingContainer.setVisible(loading);
        loadingContainer.setManaged(loading);
    }

    /**
     * Get username - for potential authentication use
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get password - for potential authentication use
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get current error message
     */
    public String getError() {
        return error;
    }

    /**
     * Check if currently loading
     */
    public boolean isLoading() {
        return isLoading;
    }
}
