package com.example.prog2tp1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class PlanifController {

    @FXML
    protected void onGestionButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Cours-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stageCours = new Stage();
            stageCours.setTitle("Gestion de cours");
            stageCours.setScene(new Scene(root));
            stageCours.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    protected void onGenHoraireButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Horaire-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stageCours = new Stage();
            stageCours.setTitle("Ajout de cours");
            stageCours.setScene(new Scene(root));
            stageCours.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}