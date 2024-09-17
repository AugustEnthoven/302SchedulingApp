package org.codecrafters.educa;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.codecrafters.educa.db.DatabaseConnection;
import org.codecrafters.educa.models.user.UserDAO;

import java.io.IOException;
import java.sql.Connection;

public class App extends Application {
    private static SceneManager sceneManager;

    private static UserDAO userDAO;

    public static UserDAO getUserDAO() { return userDAO; }

    @Override
    public void start(Stage stage) throws IOException {
        // Create sceneManger to control switching scene
        sceneManager = new SceneManager(stage);
        // Check if the stage is loaded correctly
        Stage stage1 = sceneManager.getStage();
        stage1.initStyle(StageStyle.UNDECORATED);

        sceneManager.switchScene("home", "Home");
        stage1.setTitle("Home");

        stage1.show();
    }
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getInstance();
    }
}