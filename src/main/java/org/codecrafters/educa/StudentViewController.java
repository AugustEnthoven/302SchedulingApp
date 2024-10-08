package org.codecrafters.educa;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.codecrafters.educa.db.StudentDAO;
import org.codecrafters.educa.profiles.Student;

import java.io.IOException;

import static org.codecrafters.educa.App.getSelectedStudent;

public class StudentViewController {
    @FXML
    private Button BackButton;
    @FXML
    private Button EditButton;
    @FXML
    private Button DeleteButton;
    @FXML
    private Button NewButton;
    @FXML
    private TableView<Student> StudentsTable;
    @FXML
    private TableColumn<Student, String> firstName;
    @FXML
    private TableColumn<Student, String> lastName;
    @FXML
    private TableColumn<Student, String> age;
    @FXML
    private TableColumn<Student, String> conditions;

    private final StudentDAO studentDAO;
    private Student selectedStudent = getSelectedStudent();
    public StudentViewController(){
        studentDAO = new StudentDAO();
    }
    public SceneManager sceneManager;

    @FXML
    public void initialize(){
        sceneManager = App.getSceneManager();

        firstName.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        age.setCellValueFactory(new PropertyValueFactory<Student, String>("age"));
        conditions.setCellValueFactory(new PropertyValueFactory<Student, String>("conditions"));

        StudentsTable.getItems().setAll(studentDAO.getAll());
    }

    @FXML
    protected void onNewButtonClick() throws IOException {
        Stage thisStage = sceneManager.getStage();
        sceneManager.switchScene("CreateStudent", "CreateStudent");
        thisStage.show();
    }

    @FXML
    protected void onEditButtonClick() throws IOException{
        if (getSelectedStudent() != null) {
            Stage thisStage = sceneManager.getStage();
            sceneManager.switchScene("EditStudent", "Edit " + selectedStudent.getFirstName() + " " + selectedStudent.getLastName() + "'s Profile");
            thisStage.show();
        }
    }

    @FXML
    protected void onCellClick(){
        selectedStudent = StudentsTable.getSelectionModel().getSelectedItem();
    }

    @FXML
    protected void onDeleteButtonClick(){
        if (selectedStudent != null){
            studentDAO.delete(selectedStudent.getId());
            selectedStudent = null;
            StudentsTable.getItems().clear();
            StudentsTable.getItems().setAll(studentDAO.getAll());
        }
    }

    @FXML
    protected void onViewButtonClick(){
        if (getSelectedStudent() != null) {
            Stage thisStage = sceneManager.getStage();
            sceneManager.switchScene("studentProfile", selectedStudent.getFirstName() + " " + selectedStudent.getLastName() + "'s profile");
            thisStage.show();
            App.viewingProfile = true;
        }
    }
}
