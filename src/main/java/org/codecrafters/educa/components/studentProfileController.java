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
import org.codecrafters.educa.db.NotesDAO;
import org.codecrafters.educa.db.sqliteNotesDAO;
import org.codecrafters.educa.profiles.Note;
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
    private Label conditionsInfo;
    @FXML
    private TextArea noteLog;

    private NotesDAO notesDAO;
    public Student selectedStudent;
    public SceneManager sceneManager;

    public studentProfileController(){
        notesDAO = new sqliteNotesDAO();
        selectedStudent = App.selectedStudent;
        sceneManager = App.sceneManager;
    }

    public void initialize(){
        studentName.setText(App.selectedStudent.getFirstName() + " " + App.selectedStudent.getLastName());
        conditionsInfo.setText(App.selectedStudent.getConditions());
        StringBuilder log = new StringBuilder();
        for (Note n : notesDAO.getNotesByStudentId(selectedStudent.getId())){
            log.append(n.getDateCreated()).append(" - ").append(n.getContents()).append("\n\n");
        }
        noteLog.setText(log.toString());
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
