package org.codecrafters.educa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SetTimetableController implements Initializable {

    @FXML
    private ChoiceBox<String> M1;
    private ChoiceBox<String> M2;
    private ChoiceBox<String> M3;
    private ChoiceBox<String> M4;
    private ChoiceBox<String> M5;
    private ChoiceBox<String> M6;

    private ChoiceBox<String> T1;
    private ChoiceBox<String> T2;
    private ChoiceBox<String> T3;
    private ChoiceBox<String> T4;
    private ChoiceBox<String> T5;
    private ChoiceBox<String> T6;

    private ChoiceBox<String> W1;
    private ChoiceBox<String> W2;
    private ChoiceBox<String> W3;
    private ChoiceBox<String> W4;
    private ChoiceBox<String> W5;
    private ChoiceBox<String> W6;

    private ChoiceBox<String> TH1;
    private ChoiceBox<String> TH2;
    private ChoiceBox<String> TH3;
    private ChoiceBox<String> TH4;
    private ChoiceBox<String> TH5;
    private ChoiceBox<String> TH6;

    private ChoiceBox<String> F1;
    private ChoiceBox<String> F2;
    private ChoiceBox<String> F3;
    private ChoiceBox<String> F4;
    private ChoiceBox<String> F5;
    private ChoiceBox<String> F6;



    private String[] classes = {"English","History","Science","PE","Math","Spanish"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        M1.getItems().addAll(classes);
        M1.setOnAction(this::updateSchedule);
        M2.getItems().addAll(classes);
        M2.setOnAction(this::updateSchedule);
        M3.getItems().addAll(classes);
        M3.setOnAction(this::updateSchedule);
        M4.getItems().addAll(classes);
        M4.setOnAction(this::updateSchedule);
        M5.getItems().addAll(classes);
        M5.setOnAction(this::updateSchedule);
        M6.getItems().addAll(classes);
        M6.setOnAction(this::updateSchedule);

        T1.getItems().addAll(classes);
        T1.setOnAction(this::updateSchedule);
        T2.getItems().addAll(classes);
        T2.setOnAction(this::updateSchedule);
        T3.getItems().addAll(classes);
        T3.setOnAction(this::updateSchedule);
        T4.getItems().addAll(classes);
        T4.setOnAction(this::updateSchedule);
        T5.getItems().addAll(classes);
        T5.setOnAction(this::updateSchedule);
        T6.getItems().addAll(classes);
        T6.setOnAction(this::updateSchedule);

	    W1.getItems().addAll(classes);
        W1.setOnAction(this::updateSchedule);
        W2.getItems().addAll(classes);
        W2.setOnAction(this::updateSchedule);
        W3.getItems().addAll(classes);
        W3.setOnAction(this::updateSchedule);
        W4.getItems().addAll(classes);
        W4.setOnAction(this::updateSchedule);
        W5.getItems().addAll(classes);
        W5.setOnAction(this::updateSchedule);
        W6.getItems().addAll(classes);
        W6.setOnAction(this::updateSchedule);

	    TH1.getItems().addAll(classes);
        TH1.setOnAction(this::updateSchedule);
        TH2.getItems().addAll(classes);
        TH2.setOnAction(this::updateSchedule);
        TH3.getItems().addAll(classes);
        TH3.setOnAction(this::updateSchedule);
        TH4.getItems().addAll(classes);
        TH4.setOnAction(this::updateSchedule);
        TH5.getItems().addAll(classes);
        TH5.setOnAction(this::updateSchedule);
        TH6.getItems().addAll(classes);
        TH6.setOnAction(this::updateSchedule);

	    F1.getItems().addAll(classes);
        F1.setOnAction(this::updateSchedule);
        F2.getItems().addAll(classes);
        F2.setOnAction(this::updateSchedule);
        F3.getItems().addAll(classes);
        F3.setOnAction(this::updateSchedule);
        F4.getItems().addAll(classes);
        F4.setOnAction(this::updateSchedule);
        F5.getItems().addAll(classes);
        F5.setOnAction(this::updateSchedule);
        F6.getItems().addAll(classes);
        F6.setOnAction(this::updateSchedule);
    }

    public void updateSchedule(ActionEvent event)
    {
        String newClass = M1.getValue(); //Change M1 to whichever choicebox was used
        //Maybe we could use a list of choiceboxes? I'm not sure how you wanted to do this
    }
}
