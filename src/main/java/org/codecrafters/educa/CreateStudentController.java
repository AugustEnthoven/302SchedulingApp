package org.codecrafters.educa;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.codecrafters.educa.db.StudentDAO;
import org.codecrafters.educa.profiles.Student;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreateStudentController {
    @FXML
    private Button createStudentButton;
    @FXML
    private Button BackButton;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField dobTextField;
    @FXML
    private TextField conditionsTextField;

    private StudentDAO studentDAO;
    public CreateStudentController(){
        studentDAO = new StudentDAO();
    }

    /**
     * Runs on any changes to any text field. If all fields have valid values, will enable
     * the create button
     */
    @FXML
    protected void onStudentFieldEdit(){
        boolean validDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate.parse(dobTextField.getText(), formatter);
            validDate = true;
        } catch(Exception e){validDate = false;}
        createStudentButton.setDisable(!(!firstNameTextField.getText().isEmpty() & !lastNameTextField.getText().isEmpty() &
                !dobTextField.getText().isEmpty() & !conditionsTextField.getText().isEmpty() & validDate));
    }

    /**
     * Inserts the respective student information into a new entry in the database and
     * returns to the student view screen
     * @throws IOException
     */
    @FXML
    protected void onCreateStudentClick() throws IOException{
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String dob = dobTextField.getText();
        String conditions = conditionsTextField.getText();
        studentDAO.insert(new Student(firstName, lastName, dob, conditions));

        Stage stage = (Stage) BackButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("StudentView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setScene(scene);
    }

    /**
     * returns to the student view screen
     * @throws IOException
     */
    @FXML
    protected void onBackButtonClick() throws IOException {
        Stage stage = (Stage) BackButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("StudentView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setScene(scene);
    }
}
