package org.codecrafters.educa;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import org.codecrafters.educa.db.ScheduleDAO;
import org.codecrafters.educa.profiles.Student;

import java.net.URL;
import java.util.ResourceBundle;

public class EditPreferenceController implements Initializable {

    @FXML
    private ChoiceBox<String> EnglishChoicebox;

    @FXML
    private ChoiceBox<String> HistoryChoicebox;

    @FXML
    private ChoiceBox<String> SpanishChoicebox;

    @FXML
    private ChoiceBox<String> ScienceChoicebox;

    @FXML
    private ChoiceBox<String> PEChoicebox;

    @FXML
    private ChoiceBox<String> MathChoicebox;

    public Student selectedStudent;
    public SceneManager sceneManager;
    public ScheduleDAO scheduleDAO;

    private String[] preferences = {"1","2","3","4","5"};

    public EditPreferenceController(){
        selectedStudent = App.getSelectedStudent();
        sceneManager = App.getSceneManager();
        scheduleDAO = new ScheduleDAO();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        EnglishChoicebox.getItems().addAll(preferences);
        HistoryChoicebox.getItems().addAll(preferences);
        SpanishChoicebox.getItems().addAll(preferences);
        ScienceChoicebox.getItems().addAll(preferences);
        PEChoicebox.getItems().addAll(preferences);
        MathChoicebox.getItems().addAll(preferences);
    }

    @FXML
    void onBackButtonClick()
    {
        Stage thisStage = sceneManager.getStage();
        sceneManager.switchScene("studentProfile", selectedStudent.getFirstName() + " " + selectedStudent.getLastName() + "'s timetable");
        thisStage.show();
    }

    @FXML
    void onSubmitClick()
    {

    }
}
