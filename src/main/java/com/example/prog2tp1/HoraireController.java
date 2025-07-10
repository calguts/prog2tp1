package com.example.prog2tp1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public abstract class HoraireController  implements Initializable {

    public HoraireController() {
        // no-arg constructor
    }

    @FXML public ComboBox<Integer> nbCreditsSession;
    @FXML
    private TableView<SeanceViewModel> listeCoursH;

    @FXML private TableColumn<SeanceViewModel, String> nameCoursH;
    @FXML private TableColumn<SeanceViewModel, Integer> nbCreditsH;
    @FXML private TableColumn<SeanceViewModel, LocalDate> dateDebutH;
    @FXML private TableColumn<SeanceViewModel, LocalDate> dateFinH;
    @FXML private TableColumn<SeanceViewModel, DayOfWeek> jourH;
    @FXML private TableColumn<SeanceViewModel, LocalTime> tempsDebutH;
    @FXML private TableColumn<SeanceViewModel, LocalTime> tempsFinH;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Integer> creditsSessionPoss = FXCollections.observableArrayList();
        for (int i = 1; i <= 6; i++)
            creditsSessionPoss.add(i);

        nbCreditsSession.setItems(creditsSessionPoss);

        nameCoursH.setCellValueFactory(cell -> cell.getValue().nomCoursProperty());
        nbCreditsH.setCellValueFactory(cell -> cell.getValue().nbCreditsProperty().asObject());
        dateDebutH.setCellValueFactory(cell -> cell.getValue().dateDebutProperty());
        dateFinH.setCellValueFactory(cell -> cell.getValue().dateFinProperty());
        jourH.setCellValueFactory(cell -> cell.getValue().jourProperty());
        tempsDebutH.setCellValueFactory(cell -> cell.getValue().debutProperty());
        tempsFinH.setCellValueFactory(cell -> cell.getValue().finProperty());
    }

    public void onSelectNumCredits(ActionEvent actionEvent){

        Integer sessionCredits = nbCreditsSession.getValue();

        /*CoursListe.addCoursListe(newCours);
        maListeCours.add(new CoursViewModel(newCours));
        clearForm(); */


        // Flatten all seances from all courses
        /*ObservableList<SeanceViewModel> flatList = FXCollections.observableArrayList();
        for (CoursClasse cours : ListeCours.getInstance().getCours()) {
            if (cours.getSeance1().isActive()) flatList.add(new SeanceViewModel(cours, cours.getSeance1()));
            if (cours.getSeance2().isActive()) flatList.add(new SeanceViewModel(cours, cours.getSeance2()));
            if (cours.getSeance3().isActive()) flatList.add(new SeanceViewModel(cours, cours.getSeance3()));
        }

        // Sort by day and time
        flatList.sort(Comparator
                .comparing((SeanceViewModel s) -> s.jourProperty().get())
                .thenComparing(s -> s.debutProperty().get()));

        listeCoursH.setItems(flatList);*/

    }
}
