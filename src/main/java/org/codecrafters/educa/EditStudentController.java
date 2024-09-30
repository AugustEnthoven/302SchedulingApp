package org.codecrafters.educa;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.codecrafters.educa.db.StudentDAO;
import org.codecrafters.educa.profiles.Student;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditStudentController {
    @FXML
    private Button SubmitButton;
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

    private Student selectedStudent;
    private StudentDAO studentDAO;
    public SceneManager sceneManager;

    public EditStudentController(){
        studentDAO = new StudentDAO();
        selectedStudent = App.selectedStudent;
        sceneManager = App.sceneManager;
    }

    @FXML
    public void initialize(){
        firstNameTextField.setText(selectedStudent.getFirstName());
        lastNameTextField.setText(selectedStudent.getLastName());
        dobTextField.setText(selectedStudent.getDOB());
        conditionsTextField.setText(selectedStudent.getConditions());
    }

    /**
     * Runs on any changes to any text field. If all fields have valid values and have
     * changed from the initial values, will enable the create button
     */
    @FXML
    protected void onStudentFieldEdit(){
        boolean validDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate.parse(dobTextField.getText(), formatter);
            validDate = true;
        } catch(Exception e){validDate = false;}
        SubmitButton.setDisable(!(!firstNameTextField.getText().isEmpty() & !lastNameTextField.getText().isEmpty() &
                !dobTextField.getText().isEmpty() & !conditionsTextField.getText().isEmpty() & validDate));
    }

    /**
     * Updates the respective student information in the database and
     * returns to the student view screen
     * @throws IOException
     */
    @FXML
    protected void onSubmitClick() throws IOException{
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String dob = dobTextField.getText();
        String conditions = conditionsTextField.getText();
        studentDAO.update(new Student(selectedStudent.getId(), firstName, lastName, dob, conditions));

        Stage thisStage = sceneManager.getStage();
        sceneManager.switchScene("studentView", "Student View");
        thisStage.show();
        App.selectedStudent = null;
    }

    /**
     * returns to the student view screen
     * @throws IOException
     */
    @FXML
    protected void onBackButtonClick() throws IOException {
        if (App.viewingProfile){
            Stage thisStage = sceneManager.getStage();
            sceneManager.switchScene("studentProfile", selectedStudent.getFirstName() + " " + selectedStudent.getLastName() + "'s profile");
            thisStage.show();
        } else {
            Stage thisStage = sceneManager.getStage();
            sceneManager.switchScene("studentView", "Student View");
            thisStage.show();
            App.selectedStudent = null;
        }
    }
}
