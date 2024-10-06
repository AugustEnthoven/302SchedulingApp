package org.codecrafters.educa;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class EditPreferenceController implements Initializable {

    @FXML
    private ChoiceBox<String> classDropDown;

    @FXML
    private ChoiceBox<String> preferenceDropDown;

    private String[] classes = {"English","History","Science","PE","Math","Spanish"};

    private String[] preferences = {"1","2","3","4","5"};

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        classDropDown.getItems().addAll(classes);
        preferenceDropDown.getItems().addAll(preferences);
    }

    public void onBackButtonClick()
    {

    }

    public void onSubmitClick()
    {

    }
}
