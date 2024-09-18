package org.codecrafters.educa;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.codecrafters.educa.db.StudentDAO;
import org.codecrafters.educa.profiles.Student;

import java.io.IOException;

public class StudentViewController {
    @FXML
    public Button BackButton;
    @FXML
    public Button EditButton;
    @FXML
    public Button DeleteButton;
    @FXML
    public Button NewButton;
    @FXML
    public TableView StudentsTable;

    private StudentDAO studentDAO;
    public StudentViewController(){
        studentDAO = new StudentDAO();
    }



    @FXML
    protected void onNewButtonClick() throws IOException {
        Stage stage = (Stage) BackButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CreateStudent.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setScene(scene);
    }
}
