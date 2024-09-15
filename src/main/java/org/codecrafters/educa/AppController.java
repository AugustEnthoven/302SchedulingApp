package org.codecrafters.educa;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.codecrafters.educa.db.StudentDAO;
import org.codecrafters.educa.profiles.Student;

public class AppController {
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
    protected void onCreateStudentClick(){
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String dob = dobTextField.getText();
        String conditions = conditionsTextField.getText();
        if (!firstName.isEmpty() & !lastName.isEmpty() & !dob.isEmpty() & !conditions.isEmpty()){
            studentDAO.insert(new Student(firstName, lastName, dob, conditions));
        } else {
            System.out.println("can't be null");
        }
    }
}
