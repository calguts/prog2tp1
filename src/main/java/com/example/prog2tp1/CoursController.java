package com.example.prog2tp1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public abstract class CoursController implements Initializable {

    private final ObservableList<CoursClasse> maListeCours = FXCollections.observableArrayList();



    public void onDeleteSelectedClick(ActionEvent actionEvent) {
    }



    public void onSelectNumSeances(ActionEvent actionEvent) {
    }

    public void onAjoutCoursClick(ActionEvent actionEvent) {
    }
}
