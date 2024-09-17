package org.codecrafters.educa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.codecrafters.educa.models.user.UserDAO;

import java.io.IOException;

public class App extends Application {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    private static SceneManager sceneManager;

    private static UserDAO userDAO;

    public static UserDAO getUserDAO() { return userDAO; }

    public static Stage getStage() { return sceneManager.getStage(); }

    public static SceneManager getSceneManager() { return sceneManager; }

    @Override
    public void start(Stage stage) throws IOException {
        sceneManager = new SceneManager(stage);

        Stage thisStage = sceneManager.getStage();
        thisStage.initStyle(StageStyle.UNDECORATED);
        sceneManager.switchScene("studentProfile", "Student Profile");
        thisStage.setTitle("Student Profile");
        thisStage.show();
    }
    public static void main(String[] args) {
//        Connection connection = DatabaseConnection.getInstance();
        launch();
    }
}