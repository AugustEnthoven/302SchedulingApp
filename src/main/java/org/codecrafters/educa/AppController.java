package org.codecrafters.educa;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.codecrafters.educa.db.StudentDAO;
import org.codecrafters.educa.profiles.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AppController {
    @FXML
    public Button createStudentButton;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField dobTextField;
    @FXML
    private TextField conditionsTextField;

    private StudentDAO studentDAO;
    public AppController(){
        studentDAO = new StudentDAO();
    }

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
    @FXML
    protected void onCreateStudentClick(){
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String dob = dobTextField.getText();
        String conditions = conditionsTextField.getText();
        studentDAO.insert(new Student(firstName, lastName, dob, conditions));
        System.out.println("Student Profile Created Successfully");
    }
}
