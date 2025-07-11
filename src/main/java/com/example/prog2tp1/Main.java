/**prog2tp1
TP1 Programmation 2 IFT1025
 Cette application permet d'inscrire des cours et de générer un horaire en utilisant
 le GUI JavaFX

 * @author Cal Gutiérrez
 * @author Thomas Ring
 * @since 2025-07-10
 **/


package com.example.prog2tp1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe principale de l'application JavaFX.
 * Cette classe initialise et lance l'interface utilisateur.
 */
public class Main extends Application {
    /**
     * Méthode appelée automatiquement au démarrage de l'application JavaFX.
     *
     * @param mainStage la fenêtre principale de l'application
     * @throws IOException si le fichier FXML ne peut pas être chargé
     */
    @Override
    public void start(Stage mainStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Planif-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        mainStage.setTitle("Planificateur d'horaires par Cal Gutiérrez et Thomas Ring!");
        mainStage.setScene(scene);
        mainStage.show();
    }

    /**
     * Point d'entrée de l'application.
     *
     * @param args les arguments de la ligne de commande (non utilisés ici car GUI javaFX)
     */
    public static void main(String[] args) {
        launch();


    }
}