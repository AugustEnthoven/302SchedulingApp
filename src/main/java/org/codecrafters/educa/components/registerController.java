package org.codecrafters.educa.components;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import org.codecrafters.educa.App;
import org.codecrafters.educa.SceneManager;
import org.codecrafters.educa.models.authentication.Authentication;
import org.codecrafters.educa.models.authentication.ExistingUserException;
import org.w3c.dom.Text;

import java.io.IOException;
import java.security.Key;
import java.util.regex.Pattern;

public class registerController {
    public VBox register;
    private static final String EMAIL_REGEX = "[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String[] roles = {"Teacher", "Parent"};

    private void resetFormFields() {
        emailField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");

        emailField.setDisable(false);
        usernameField.setDisable(false);
        passwordField.setDisable(false);
        confirmPasswordField.setDisable(false);
        registerButton.setDisable(false);
        roleChoiceBox.setDisable(false);
    }
    private void disableFormFields() {
        emailField.setDisable(true);
        usernameField.setDisable(true);
        passwordField.setDisable(true);
        confirmPasswordField.setDisable(true);
        registerButton.setDisable(true);
        roleChoiceBox.setDisable(true);

        errorMessage.setVisible(false);
        successMessage.setVisible(false);
    }
    private void getRole(ActionEvent event) {
        String role = roleChoiceBox.getValue();
    }
    @FXML
    private TextField emailField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField confirmPasswordField;
    @FXML
    private Button registerButton;
    @FXML
    private Button backButton;
    @FXML
    private Label successMessage;
    @FXML
    private Label errorMessage;
    @FXML
    private ChoiceBox<String> roleChoiceBox;
    @FXML
    private TextField firstnameField;
    @FXML
    private TextField lastnameField;
    @FXML
    public void initialize() {
        SceneManager sceneManager = App.getSceneManager();
        Scene scene = sceneManager.getCurrentScene();
        if (scene == null) {
            return;
        }
        // Handle event of pressing "ENTER" key on the keyboard to register without pressing the actual register button
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                register();
            }
        });
        // Initialise the roleChoiceBox with predefined roles, no default role
        roleChoiceBox.getItems().addAll(roles);
        roleChoiceBox.setValue(null); // Ensure no default value is selected
        roleChoiceBox.setOnAction(this::getRole);
    }
    @FXML
    private void back() throws IOException {
        SceneManager sceneManager = App.getSceneManager();
        sceneManager.back();
    }
    @FXML
    private void register() {
        Authentication authenticatedSession = App.getAuthenticatedSession();

        String lastname = lastnameField.getText();
        String firstname = firstnameField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String role = roleChoiceBox.getValue();


        Task<Void> registrationTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                // Create a delay
                Thread.sleep(500);
                // Validate firstname and lastname fields
                if (firstname.isEmpty() || lastname.isEmpty()) {
                    Platform.runLater(() -> {
                        errorMessage.setVisible(true);
                        errorMessage.setText("Please fill in both first name and last name");

                        resetFormFields();
                        firstnameField.requestFocus();
                    });
                }
                // Validate email format
                if (!(Pattern.compile(EMAIL_REGEX).matcher(email).matches())) {
                    Platform.runLater(() -> {
                        errorMessage.setVisible(true);
                        errorMessage.setStyle("-fx-text-alignment: center;");
                        errorMessage.setText("The email is invalid.");
                        errorMessage.setLineSpacing(errorMessage.getFont().getSize() * 0.5);

                        resetFormFields();
                        emailField.requestFocus();
                    });
                    return null;
                }
                // Validate password match
                if (!(confirmPassword.equals(password))) {
                    Platform.runLater(()-> {
                        errorMessage.setVisible(true);
                        errorMessage.setText("Passwords do NOT match");

                        resetFormFields();
                        passwordField.requestFocus();
                    });
                    return null;
                }
                // Validate that a role is selected
                if (roleChoiceBox.getValue() == null) {
                    Platform.runLater(() -> {
                        errorMessage.setVisible(true);
                        errorMessage.setText("Please select a role");

                        resetFormFields();
                        roleChoiceBox.requestFocus();
                    });
                    return null;
                }
                // Register a user
                try {
                    authenticatedSession.register(lastname, firstname, email,role,username,password);

                    Platform.runLater(() -> {
                        successMessage.setVisible(true);
                        successMessage.setText("Successfully registered user.\n Redirecting to login page...");

                        // Redirecting
                        new Thread(() -> {
                            try {
                                Thread.sleep(2000); // 2 seconds delay
                                Platform.runLater(() -> {
                                    SceneManager sceneManager = App.getSceneManager();
                                    Stage stage = App.getStage();

                                    sceneManager.switchScene("login", "Login");
                                    stage.setTitle("EduCalendar - Login");

                                });
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }).start();
                    });
                } catch (ExistingUserException exception) {
                    Platform.runLater(() -> {
                        errorMessage.setVisible(true);
                        errorMessage.setText(exception.getMessage());

                        resetFormFields();
                    });
                }
                return null;
            }
        };
        // Update the UI to indicate registration is in progress
        disableFormFields();

        // Start Task
        new Thread(registrationTask).start();
    }
}