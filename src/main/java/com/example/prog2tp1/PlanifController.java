package com.example.prog2tp1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Contrôleur {@code PlanifController} pour l’écran principal du planificateur d’horaires.
 * <p>
 * Ce contrôleur agit comme un menu principal avec deux boutons :
 * un pour gérer les cours, et un autre pour générer/visualiser un horaire.
 * Lorsqu’un bouton est cliqué, une nouvelle fenêtre JavaFX (Stage) est ouverte
 * avec le contenu FXML correspondant.
 * </p>
 *Fichiers FXML liés :
 *{@code Cours-view.fxml} – pour la gestion des cours</li>
 * {@code Horaire-view.fxml} – pour l’affichage de l’horaire</li>
 */
public class PlanifController {

    /**
     * Gère le clic sur le bouton « Gestion de cours ».
     *
     * Ouvre une nouvelle fenêtre avec l'interface de gestion des cours
     * définie dans le fichier {@code Cours-view.fxml}.
     *
     * @param event l’événement de clic sur le bouton
     */
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

    /**
     * Gère le clic sur le bouton « Générer l’horaire ».
     *
     * Ouvre une nouvelle fenêtre avec l'interface d'affichage de l'horaire
     * définie dans le fichier {@code Horaire-view.fxml}.
     *
     * @param event l’événement de clic sur le bouton
     */
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