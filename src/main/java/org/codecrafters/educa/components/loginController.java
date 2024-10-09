package org.codecrafters.educa.components;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import org.codecrafters.educa.App;
import org.codecrafters.educa.SceneManager;
import org.codecrafters.educa.models.authentication.Authentication;
import org.codecrafters.educa.models.authentication.UnauthorisedUserException;

public class loginController {
    public VBox login;

    private void handleLoginSuccess(){
        Platform.runLater(() -> {
            Stage stage = App.getStage();
            SceneManager sceneManager = App.getSceneManager();

            sceneManager.switchScene("home", "Home");
            stage.setTitle("EduCalendar - Home");
        });
    }
    private void handleLoginFailure(Exception exception){
        Platform.runLater(() -> {
            errorMessage.setVisible(true);
            errorMessage.setText(exception.getMessage());

            usernameField.setDisable(false);
            passwordField.setDisable(false);
            loginBtn.setDisable(false);

            usernameField.requestFocus();
        });
    }
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label errorMessage;
    @FXML
    private Button loginBtn;
    @FXML
    private Button registerBtn;
    @FXML
    private Authentication authenticatedSession;
    @FXML
    private SceneManager sceneManager;
    @FXML
    public void initialize(){
        SceneManager sceneManager = App.getSceneManager();
        Scene scene = sceneManager.getCurrentScene();
        if (scene == null) {
            return;
        }

        // Handle event of pressing "ENTER" on the keyboard to login without pressing the actual login button
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                login();
            }
            usernameField.requestFocus(); // Focus on login button instead of register button
        });
    }

    @FXML
    private void login(){
        Authentication authenticatedSession = App.getAuthenticatedSession();

        String username = usernameField.getText();
        String password = passwordField.getText();

        Task<Boolean> loginTask = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                // Create a delay
                Thread.sleep(500);
                // Login
                return authenticatedSession.login(username, password);
            }
            @Override
            protected void succeeded() {
                super.succeeded();
                if (getValue()) {
                    handleLoginSuccess();
                } else {
                    handleLoginFailure(new UnauthorisedUserException("Incorrect username or password."));
                }
            }
            @Override
            protected void failed(){
                super.failed();
                handleLoginFailure(new Exception("An error occurred during login"));
            }
        };

        // Update the UI to indicate login is in progress
        errorMessage.setVisible(false);
        usernameField.setDisable(true);
        passwordField.setDisable(true);
        loginBtn.setDisable(true);

        // Start Task
        new Thread(loginTask).start();
    }
    @FXML
    private void register() {
        Stage stage = App.getStage();
        SceneManager sceneManager = App.getSceneManager();
        stage.setHeight(600);

        sceneManager.switchScene("register", "Register");
        stage.setTitle("EduCalendar - Register");
    }
}