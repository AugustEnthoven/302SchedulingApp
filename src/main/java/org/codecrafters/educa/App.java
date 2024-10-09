package org.codecrafters.educa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.codecrafters.educa.models.user.UserDAO;
import org.codecrafters.educa.profiles.Student;

import java.io.IOException;

public class App extends Application {
    public static final int WIDTH = 640;
    public static final int HEIGHT = 400;
    public static Student selectedStudent;

    public static SceneManager sceneManager;

    public static boolean viewingProfile;

    private static UserDAO userDAO;

    public static UserDAO getUserDAO() { return userDAO; }

    public static Stage getStage() { return sceneManager.getStage(); }

    public static SceneManager getSceneManager() { return sceneManager; }

    @Override
    public void start(Stage stage) throws IOException {
        sceneManager = new SceneManager(stage);
        selectedStudent = null;
        viewingProfile = false;

        Stage thisStage = sceneManager.getStage();
        sceneManager.switchScene("SetTimetable", "Set Timetable");
        thisStage.show();
    }
    public static void main(String[] args) {
//        Connection connection = DatabaseConnection.getInstance();
        launch();
    }
}