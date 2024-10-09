package org.codecrafters.educa.components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.codecrafters.educa.App;
import org.codecrafters.educa.SceneManager;
import org.codecrafters.educa.profiles.Student;

import static org.codecrafters.educa.App.getSelectedStudent;

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

    public Student selectedStudent;
    public SceneManager sceneManager;

    public studentProfileController(){
        selectedStudent = getSelectedStudent();
        sceneManager = App.getSceneManager();
    }

    @FXML
    private void toHome(){
        Stage thisStage = sceneManager.getStage();
        sceneManager.switchScene("studentView", "Student View");
        thisStage.show();
        App.setSelectedStudent(null);
        App.viewingProfile = false;
    }

    @FXML
    public void onEditBtn() {
        Stage thisStage = sceneManager.getStage();
        sceneManager.switchScene("EditStudent", "Edit " + selectedStudent.getFirstName() + " " + selectedStudent.getLastName() + "'s Profile");
        thisStage.show();
    }

}
