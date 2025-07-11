package com.example.prog2tp1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

/**
 * Controlleur de Horaire-view, utilisée pour afficher l’horaire des séances de cours
 * dans une TableView JavaFX. Cette classe configure les colonnes de la TableView, et
 * affiche l'horaire généré selon le nombre de crédits demandés, sous forme d’une liste
 * de séances individuelles.</p>
 */
public abstract class HoraireController  implements Initializable {

    /**
     * ComboBox permettant de filtrer les cours selon le nombre de crédits.
     */
    public HoraireController() {
        // no-arg constructor
    }

    /**  ComboBox permettant de filtrer les cours selon le nombre de crédits.*/
    @FXML public ComboBox<Integer> nbCreditsSession;
    /** TableView affichant toutes les séances des cours chronologiquement.*/
    @FXML private TableView<SeanceViewModel> listeCoursH;

    /** Les colonnes qui affichent chaque proprieté d'un cours et d'une seance */
    @FXML private TableColumn<SeanceViewModel, String> nameCoursH;
    @FXML private TableColumn<SeanceViewModel, Integer> nbCreditsH;
    @FXML private TableColumn<SeanceViewModel, LocalDate> dateDebutH;
    @FXML private TableColumn<SeanceViewModel, LocalDate> dateFinH;
    @FXML private TableColumn<SeanceViewModel, DayOfWeek> jourH;
    @FXML private TableColumn<SeanceViewModel, LocalTime> tempsDebutH;
    @FXML private TableColumn<SeanceViewModel, LocalTime> tempsFinH;


    /**
     * Initialise la liste des crédits disponibles et configure les liaisons entre
     * les colonnes de la TableView et les propriétés des objets {@code SeanceViewModel}.
     *
     * @param location L’emplacement utilisé pour résoudre les chemins relatifs aux ressources.
     * @param resources Les ressources utilisées pour internationaliser le contenu.
     */
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

    /**
     * Méthode appelée lorsqu’un utilisateur sélectionne un nombre de crédits dans {@code nbCreditsSession}.
     * Cette méthode peut être surchargée pour filtrer l’horaire selon le nombre de crédits.
     *
     * @param actionEvent L’événement de sélection dans la ComboBox.
     */
    public void onSelectNumCredits(ActionEvent actionEvent){

        Integer sessionCredits = nbCreditsSession.getValue();

        if(sessionCredits != null) {
            ArrayList<CoursClasse> horaire = HoraireSession.getInstance().gererMonHoraire(sessionCredits);

            if(!horaire.isEmpty()) {
                ObservableList<SeanceViewModel> flatList = FXCollections.observableArrayList();

                for (CoursClasse cours : horaire) {
                    for (SeanceClass seance : cours.getHoraireCours()) {
                        if (seance.getJour() != null) {
                            flatList.add(new SeanceViewModel(cours, seance));
                        }
                    }
                }
                flatList.sort(Comparator.comparing((SeanceViewModel s) -> s.jourProperty().get()).thenComparing(s -> s.debutProperty().get()));
                listeCoursH.setItems(flatList);
            } else{
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setTitle("Aucun horaire trouvé");
                warning.setHeaderText("Aucun horaire respectant les conditions n'a pu être trouvé.");
                warning.setContentText("Veuillez réessayer en changeant le nombre de crédits à prendre ou en modifiant la liste des cours.");
                warning.showAndWait();
            }
        }
    }
}
