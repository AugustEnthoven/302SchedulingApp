package org.codecrafters.educa;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

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

    private String[] preferences = {"1","2","3","4","5"};

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

    }

    @FXML
    void onSubmitClick()
    {

    }
}
