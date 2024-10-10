package org.codecrafters.educa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import org.codecrafters.educa.db.NotesDAO;
import org.codecrafters.educa.db.ScheduleDAO;
import org.codecrafters.educa.db.sqliteNotesDAO;
import org.codecrafters.educa.profiles.Student;

public class TimeTableController {

    public Student selectedStudent;
    public SceneManager sceneManager;
    public ScheduleDAO scheduleDAO;

    public TimeTableController(){
        selectedStudent = App.getSelectedStudent();
        sceneManager = App.getSceneManager();
        scheduleDAO = new ScheduleDAO();
    }

    @FXML
    void onEditButtonClick()
    {
        Stage thisStage = sceneManager.getStage();
        sceneManager.switchScene("SetTimetable", selectedStudent.getFirstName() + " " + selectedStudent.getLastName() + "'s timetable");
        thisStage.show();
    }

    @FXML
    public void onBackButtonClick()
    {
        Stage thisStage = sceneManager.getStage();
        sceneManager.switchScene("studentProfile", selectedStudent.getFirstName() + " " + selectedStudent.getLastName() + "'s profile");
        thisStage.show();
    }
}
