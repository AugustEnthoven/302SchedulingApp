package org.codecrafters.educa.components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.codecrafters.educa.App;
import org.codecrafters.educa.SceneManager;
import org.codecrafters.educa.profiles.Student;

public class studentProfileController {
    public VBox studentProfile;
    public Label studentName;
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
        selectedStudent = App.selectedStudent;
        sceneManager = App.sceneManager;
    }

    public void initialize(){
        studentName.setText(App.selectedStudent.getFirstName() + " " + App.selectedStudent.getLastName());
    }

    public void setLabel(){
        studentName.setText(App.selectedStudent.getFirstName() + " " + App.selectedStudent.getLastName());
    }

    @FXML
    private void toHome(){
        Stage thisStage = sceneManager.getStage();
        sceneManager.switchScene("studentView", "Student View");
        thisStage.show();
        App.selectedStudent = null;
        App.viewingProfile = false;
    }

    @FXML
    public void onEditBtn() {
        Stage thisStage = sceneManager.getStage();
        sceneManager.switchScene("EditStudent", "Edit " + selectedStudent.getFirstName() + " " + selectedStudent.getLastName() + "'s Profile");
        thisStage.show();
    }

}
