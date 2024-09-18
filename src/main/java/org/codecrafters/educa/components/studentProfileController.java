package org.codecrafters.educa.components;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.codecrafters.educa.App;
import org.codecrafters.educa.SceneManager;

public class studentProfileController {
    public VBox studentProfile;
    @FXML
    private Button addNoteBtn;
    @FXML
    private Button editStudentProfileBtn;
    @FXML
    private Button toStudentScheduleBtn;
    @FXML
    private Button studentMedicalHistoryBtn;
    @FXML
    private TextArea allergiesInfo;
    @FXML
    private TextArea teachingAdviceInfo;
    @FXML
    private void toHome(){
        Stage stage = App.getStage();
        SceneManager sceneManager = App.getSceneManager();

        sceneManager.switchScene("home", "Home");
        stage.setTitle("EduCalendar - Home");
    };

}
