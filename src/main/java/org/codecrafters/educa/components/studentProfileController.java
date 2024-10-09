package org.codecrafters.educa.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.codecrafters.educa.App;
import org.codecrafters.educa.SceneManager;
import org.codecrafters.educa.db.NotesDAO;
import org.codecrafters.educa.db.sqliteNotesDAO;
import org.codecrafters.educa.profiles.Note;
import org.codecrafters.educa.profiles.Student;

import java.time.LocalDate;

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
            log.insert(0, n.getDateCreated() + " - " + n.getContents() + "\n\n");
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

    @FXML
    public void onAddNoteBtn(){
        Stage popup = new Stage();
        VBox comp = new VBox();
        TextField note = new TextField();
        HBox bar = new HBox();
        Button back = new Button();
        Button submit = new Button();
        submit.setDisable(true);

        popup.setTitle("Add note");

        note.setMinHeight(150);
        note.setAlignment(Pos.TOP_LEFT);

        note.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (!note.getText().isEmpty()){
                    submit.setDisable(false);
                } else {
                    submit.setDisable(true);
                }
            }
        });

        back.setText("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                popup.close();
            }
        });

        submit.setText("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!note.getText().isEmpty()){
                    notesDAO.addNote(new Note(selectedStudent.getId(), 1, LocalDate.now().toString(), note.getText()));
                    popup.close();
                    StringBuilder log = new StringBuilder();
                    for (Note n : notesDAO.getNotesByStudentId(selectedStudent.getId())){
                        log.insert(0, n.getDateCreated() + " - " + n.getContents() + "\n\n");
                    }
                    noteLog.setText(log.toString());
                }
            }
        });

        comp.getChildren().add(note);
        comp.getChildren().add(bar);

        bar.getChildren().add(back);
        bar.getChildren().add(submit);
        bar.setAlignment(Pos.CENTER_RIGHT);
        bar.setMargin(back, new Insets(12, 10, 0, 10));
        bar.setMargin(submit, new Insets(12, 10, 0, 10));

        Scene stageScene = new Scene(comp, 500, 200);
        popup.setScene(stageScene);
        popup.show();
    }

}
