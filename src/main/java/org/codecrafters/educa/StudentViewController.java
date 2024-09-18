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

    private StudentDAO studentDAO;
    private Student selectedStudent;
    public StudentViewController(){
        studentDAO = new StudentDAO();
    }

    @FXML
    public void initialize(){
        firstName.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        age.setCellValueFactory(new PropertyValueFactory<Student, String>("age"));
        conditions.setCellValueFactory(new PropertyValueFactory<Student, String>("conditions"));

        StudentsTable.getItems().setAll(studentDAO.getAll());
    }

    @FXML
    protected void onNewButtonClick() throws IOException {
        Stage stage = (Stage) BackButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CreateStudent.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setScene(scene);
    }

    @FXML
    protected void onCellClick(){
        selectedStudent = StudentsTable.getSelectionModel().getSelectedItem();
        System.out.println(selectedStudent.getFirstName());
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
}
