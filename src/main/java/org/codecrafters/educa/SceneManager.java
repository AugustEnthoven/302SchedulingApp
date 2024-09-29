package org.codecrafters.educa;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

public class SceneManager {
    private final ObjectProperty<Stage> stage = new SimpleObjectProperty<>(null);
    // Scenes collection
    private final Map<String, Scene> scenes;
    // Scenes stack
    private final Stack<Scene> stack;
    private final ObjectProperty<Scene> current = new SimpleObjectProperty<>(null);

    public Stage getStage() { return stage.get(); }

    public SceneManager(Stage thisStage){
        thisStage.setResizable(false);
        stage.set(thisStage);
        this.scenes = new HashMap<>();
        this.stack = new Stack<>();
    }

    public void switchScene(String sceneId, String title){
        // Get scene from sceneId
        Scene scene = scenes.get(sceneId);
        // Set scene title
        stage.get().setTitle("EduCalendar" + title);

        if (scene != null){
            // Push old scene to the stack
            stack.push(stage.get().getScene());
            // Set new scene with current scene
            stage.get().setScene(scene);
            current.set(scene);

        } else {
            // Create new scene
            try {
                // Get file path of the FXML file from sceneId
                String fxml = sceneId + ".fxml";
//                // Load CSS stylesheet for the scene
//                String css = Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm();
                // Create FXMLLoader to run FXML file
                FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
                // Load the FXML file and create new scene
                Scene newScene = new Scene(loader.load(), 600, 400);
//                // Add stylesheet for new scene
//                newScene.getStylesheets().add(css);
                // Push the old scene to the stack
                stack.push(stage.get().getScene());
                // Store the new scene
                scenes.put(sceneId, newScene);
                // Set new scene as current scene
                stage.get().setScene(newScene);
                current.set(newScene);
            } catch (IOException exception) {
                System.out.println("Error loading scene: " + exception.getMessage());
            }
        }
    }
    public void back() {
        if (!stack.isEmpty()) {
            Scene prevScene = stack.pop();
            stage.get().setScene(prevScene);
        }
    }
    public Scene getCurrentScene() { return stage.get().getScene(); }
}
