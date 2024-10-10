package org.codecrafters.educa;

import com.password4j.Password;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.codecrafters.educa.db.DatabaseConnection;
import org.codecrafters.educa.db.sqliteNotesDAO;
import org.codecrafters.educa.models.authentication.Authentication;
import org.codecrafters.educa.models.user.UserDAO;
import org.codecrafters.educa.profiles.Student;

import java.io.IOException;
import java.sql.Connection;

public class App extends Application {
    public static final int WIDTH = 640;
    public static final int HEIGHT = 400;
    public static boolean viewingProfile;

    private static Student selectedStudent;
    private static SceneManager sceneManager;
    private static UserDAO userDAO;
    private static Authentication authenticatedSession;

    public static UserDAO getUserDAO() { return userDAO; }
    public static Stage getStage() { return sceneManager.getStage(); }
    public static SceneManager getSceneManager() { return sceneManager; }
    public static Authentication getAuthenticatedSession() { return authenticatedSession; }
    public static Student getSelectedStudent() { return selectedStudent; }
    public static void setSelectedStudent(Student student) {selectedStudent = student;}
    @Override
    public void start(Stage stage) throws IOException {
        sceneManager = new SceneManager(stage);

        Stage thisStage = sceneManager.getStage();

        // Authenticate user -  if user is not logged in, show login scene
        if (authenticatedSession.isAuthenticated()) {
            sceneManager.switchScene("home", "Home");
            thisStage.setTitle("EduCalendar - Home");
        } else {
            sceneManager.switchScene("login", "Login");
            thisStage.setTitle("EduCalendar - Login");
        }
        thisStage.show();
    }
    public static void main(String[] args) {
        // Initialisations
        authenticatedSession = new Authentication();
        // Create new user database
        userDAO = new UserDAO();
        userDAO.createUsersTable();
        userDAO.addUser("Null", "Null", "N/A", "teacher", "N/A", Password.hash("N/A").withArgon2().getResult());
        // Database connection
        Connection connection = DatabaseConnection.getInstance();

        launch();
    }
}