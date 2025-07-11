package com.example.prog2tp1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Contrôleur de Cours-view pour la gestion des cours.
 * Permet l'ajout, la modification et la suppression de cours, ainsi que
 * l'interaction avec la vue via une TableView.
 *
 * Ce contrôleur lie les champs de formulaire (nom, crédits, dates, séances)
 * à des objets ViewModel et Model, et gère également les validations et la logique
 * de présentation côté utilisateur.
 *
 * FXML : Planif-view.fxml
 */
public class CoursController implements Initializable {

    public CoursController() {
        // no-arg constructor
    }
    // === TABLE VIEW & COLONNES ===

    /** TableView contenant les cours affichés à l'utilisateur. */
    @FXML private TableView<CoursViewModel> listeCours;
    // === TABLE VIEW & COLONNES ===

    /** TableView contenant les cours affichés à l'utilisateur. */
    @FXML private TableColumn<CoursViewModel, String> nameCours;
    /** Colonne du nombre de crédits. */
    @FXML private TableColumn<CoursViewModel, Integer> nbCredits;
    /** Colonne du nombre de crédits. */
    @FXML private TableColumn<CoursViewModel, LocalDate> dateDebut;
    /** Colonne de la date de fin du cours. */
    @FXML private TableColumn<CoursViewModel, LocalDate> dateFin;

    // === COLONNES DE SÉANCES (JOUR, HEURE DE DÉBUT ET DE FIN) ===
    @FXML private TableColumn<CoursViewModel, DayOfWeek> jour1;
    @FXML private TableColumn<CoursViewModel, LocalTime> tempsDebut1;
    @FXML private TableColumn<CoursViewModel, LocalTime> tempsFin1;

    @FXML private TableColumn<CoursViewModel, DayOfWeek> jour2;
    @FXML private TableColumn<CoursViewModel, LocalTime> tempsDebut2;
    @FXML private TableColumn<CoursViewModel, LocalTime> tempsFin2;

    @FXML private TableColumn<CoursViewModel, DayOfWeek> jour3;
    @FXML private TableColumn<CoursViewModel, LocalTime> tempsDebut3;
    @FXML private TableColumn<CoursViewModel, LocalTime> tempsFin3;

    // === FORMULAIRES D'AJOUT ===
    @FXML private TextField nomCoursAjout;
    @FXML private ComboBox<Integer> nbCreditsAjout;
    @FXML private DatePicker dateDebutAjout;
    @FXML private DatePicker dateFinAjout;
    @FXML private ComboBox<Integer> nbSeances;

    @FXML private ComboBox<DayOfWeek> jourSeance1Ajout;
    @FXML private ComboBox<DayOfWeek> jourSeance2Ajout;
    @FXML private ComboBox<DayOfWeek> jourSeance3Ajout;

    @FXML private ComboBox<LocalTime> tempsDebutSeance1Ajout;
    @FXML private ComboBox<LocalTime> tempsDebutSeance2Ajout;
    @FXML private ComboBox<LocalTime> tempsDebutSeance3Ajout;

    @FXML private ComboBox<LocalTime> tempsFinSeance1Ajout;
    @FXML private ComboBox<LocalTime> tempsFinSeance2Ajout;
    @FXML private ComboBox<LocalTime> tempsFinSeance3Ajout;

    @FXML private HBox ajouterCours;
    @FXML private HBox ajoutSeance1;
    @FXML private HBox ajoutSeance2;
    @FXML private HBox ajoutSeance3;
    /** Liste observable contenant tous les cours affichés dans la TableView. */
    @FXML
    private final ObservableList<CoursViewModel> maListeCours = FXCollections.observableArrayList();

    /**
     * Initialise le contrôleur après le chargement de son FXML.
     * Configure les éléments de l'interface (ComboBoxes, CellFactories, etc.)
     * et lie les données du modèle aux colonnes de la TableView.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Enable editing
        listeCours.setEditable(true);

        // === Setup Times (every 30 minutes) ===
        ObservableList<LocalTime> horaires = FXCollections.observableArrayList();
        for (int hour = 0; hour < 24; hour++) {
            horaires.add(LocalTime.of(hour, 0));
            horaires.add(LocalTime.of(hour, 30));
        }

        ObservableList<Integer> creditsPoss = FXCollections.observableArrayList();
        for (int i = 1; i <= 6; i++)
            creditsPoss.add(i);

        ObservableList<DayOfWeek> jours = FXCollections.observableArrayList(DayOfWeek.values());

        // === Bindings for Display ===
        nameCours.setCellValueFactory(cell -> cell.getValue().nomCoursProperty());
        nbCredits.setCellValueFactory(cell -> cell.getValue().nbCreditsProperty().asObject());
        dateDebut.setCellValueFactory(cell -> cell.getValue().dateDebutCoursProperty());
        dateFin.setCellValueFactory(cell -> cell.getValue().dateFinCoursProperty());

        jour1.setCellValueFactory(cell -> cell.getValue().jour1Property());
        tempsDebut1.setCellValueFactory(cell -> cell.getValue().debut1Property());
        tempsFin1.setCellValueFactory(cell -> cell.getValue().fin1Property());

        jour2.setCellValueFactory(cell -> cell.getValue().jour2Property());
        tempsDebut2.setCellValueFactory(cell -> cell.getValue().debut2Property());
        tempsFin2.setCellValueFactory(cell -> cell.getValue().fin2Property());

        jour3.setCellValueFactory(cell -> cell.getValue().jour3Property());
        tempsDebut3.setCellValueFactory(cell -> cell.getValue().debut3Property());
        tempsFin3.setCellValueFactory(cell -> cell.getValue().fin3Property());

        // === Cell Factories (Editors) ===

        nameCours.setCellFactory(TextFieldTableCell.forTableColumn());

        nbCredits.setCellFactory(ChoiceBoxTableCell.forTableColumn(creditsPoss));

        dateDebut.setCellFactory(column -> new DatePickerCell<>());
        dateFin.setCellFactory(column -> new DatePickerCell<>());

        jour1.setCellFactory(ChoiceBoxTableCell.forTableColumn(jours));
        jour2.setCellFactory(ChoiceBoxTableCell.forTableColumn(jours));
        jour3.setCellFactory(ChoiceBoxTableCell.forTableColumn(jours));

        tempsDebut1.setCellFactory(column -> new TimePickerCell<CoursViewModel>());
        tempsFin1.setCellFactory(column -> new TimePickerCell<CoursViewModel>());
        tempsDebut2.setCellFactory(column -> new TimePickerCell<CoursViewModel>());
        tempsFin2.setCellFactory(column -> new TimePickerCell<CoursViewModel>());
        tempsDebut3.setCellFactory(column -> new TimePickerCell<CoursViewModel>());
        tempsFin3.setCellFactory(column -> new TimePickerCell<CoursViewModel>());


        nameCours.setOnEditCommit(e -> {
            if (e.getNewValue() == null || e.getNewValue().isBlank()){
                e.getRowValue().nomCoursProperty().set(e.getOldValue());
            } else
                e.getRowValue().nomCoursProperty().set(e.getNewValue());
        });

        nbCredits.setOnEditCommit(e -> e.getRowValue().nbCreditsProperty().set(e.getNewValue()));

        dateDebut.setOnEditCommit(e -> {
            LocalDate newDateDebut = e.getNewValue();
            LocalDate dateFin = e.getRowValue().dateFinCoursProperty().get();

            if (dateFin != null && newDateDebut.isAfter(dateFin)) {
                e.getRowValue().dateDebutCoursProperty().set(e.getOldValue());
            } else {
                e.getRowValue().dateDebutCoursProperty().set(newDateDebut);
            }
        });

        dateFin.setOnEditCommit(e -> {
            LocalDate newDateFin = e.getNewValue();
            LocalDate dateDebut = e.getRowValue().dateFinCoursProperty().get();

            if (dateDebut != null && newDateFin.isBefore(dateDebut)) {
                e.getRowValue().dateDebutCoursProperty().set(e.getOldValue());
            } else {
                e.getRowValue().dateFinCoursProperty().set(newDateFin);
            }
        });

        jour1.setOnEditCommit(e -> e.getRowValue().jour1Property().set(e.getNewValue()));

        tempsDebut1.setOnEditCommit(e -> {
            LocalTime newTempsDebut = e.getNewValue();
            LocalTime tempsFin = e.getRowValue().fin1Property().get();

            if (tempsFin != null && newTempsDebut.isAfter(tempsFin)) {
                e.getRowValue().debut1Property().set(e.getOldValue());
            } else {
                e.getRowValue().debut1Property().set(newTempsDebut);
            }
        });

        tempsFin1.setOnEditCommit(e -> {
            LocalTime newTempsFin = e.getNewValue();
            LocalTime tempsDebut = e.getRowValue().debut1Property().get();

            if (tempsDebut != null && newTempsFin.isBefore(tempsDebut)) {
                e.getRowValue().fin1Property().set(e.getOldValue());
            } else {
                e.getRowValue().fin1Property().set(newTempsFin);
            }
        });

        jour2.setOnEditCommit(e -> {
            e.getRowValue().jour2Property().set(e.getNewValue());
            LocalTime tempsDebut = e.getRowValue().debut2Property().get();
            LocalTime tempsFin = e.getRowValue().fin2Property().get();

            if (tempsDebut == null){
                e.getRowValue().debut2Property().set(LocalTime.of(1, 0));
            }
            if (tempsFin == null){
                e.getRowValue().fin2Property().set(LocalTime.of(23, 30));
            }
        });

        tempsDebut2.setOnEditCommit(e -> {
            LocalTime newTempsDebut = e.getNewValue();
            LocalTime tempsFin = e.getRowValue().fin2Property().get();

            if (tempsFin != null && newTempsDebut.isAfter(tempsFin)) {
                e.getRowValue().debut2Property().set(e.getOldValue());
            } else {
                e.getRowValue().debut2Property().set(newTempsDebut);

                if(tempsFin == null){
                    e.getRowValue().fin2Property().set(LocalTime.of(23, 30));
                }
                if(e.getRowValue().jour2Property().get() == null){
                    e.getRowValue().jour2Property().set(DayOfWeek.MONDAY);
                }
            }
        });

        tempsFin2.setOnEditCommit(e -> {
            LocalTime newTempsFin = e.getNewValue();
            LocalTime tempsDebut = e.getRowValue().debut2Property().get();

            if (tempsDebut != null && newTempsFin.isAfter(tempsDebut)) {
                e.getRowValue().fin2Property().set(e.getOldValue());
            } else {
                e.getRowValue().debut2Property().set(newTempsFin);

                if(tempsDebut == null){
                    e.getRowValue().fin2Property().set(LocalTime.of(23, 30));
                }
                if(e.getRowValue().jour2Property().get() == null){
                    e.getRowValue().jour2Property().set(DayOfWeek.MONDAY);
                }
            }
        });


        jour3.setOnEditCommit(e -> {

            if(e.getRowValue().jour2Property().get() != null) {
                e.getRowValue().jour3Property().set(e.getNewValue());
                LocalTime tempsDebut = e.getRowValue().debut3Property().get();
                LocalTime tempsFin = e.getRowValue().fin3Property().get();

                if (tempsDebut == null) {
                    e.getRowValue().debut3Property().set(LocalTime.of(1, 0));
                }
                if (tempsFin == null) {
                    e.getRowValue().fin3Property().set(LocalTime.of(23, 30));
                }
            }
            else{
                e.getRowValue().jour3Property().set(e.getOldValue());
            }
        });

        tempsDebut3.setOnEditCommit(e -> {
            if(e.getRowValue().jour2Property().get() != null) {
                LocalTime newTempsDebut = e.getNewValue();
                LocalTime tempsFin = e.getRowValue().fin3Property().get();

                if (tempsFin != null && newTempsDebut.isAfter(tempsFin)) {
                    e.getRowValue().debut3Property().set(e.getOldValue());
                } else {
                    e.getRowValue().debut3Property().set(newTempsDebut);

                    if (tempsFin == null) {
                        e.getRowValue().fin3Property().set(LocalTime.of(23, 30));
                    }
                    if (e.getRowValue().jour3Property().get() == null) {
                        e.getRowValue().jour3Property().set(DayOfWeek.MONDAY);
                    }
                }
            } else{
                e.getRowValue().debut3Property().set(e.getOldValue());
            }
        });

        tempsFin3.setOnEditCommit(e -> {
            if(e.getRowValue().jour2Property().get() != null) {
                LocalTime newTempsFin = e.getNewValue();
                LocalTime tempsDebut = e.getRowValue().debut3Property().get();

                if (tempsDebut != null && newTempsFin.isAfter(tempsDebut)) {
                    e.getRowValue().fin2Property().set(e.getOldValue());
                } else {
                    e.getRowValue().debut2Property().set(newTempsFin);
                    if (tempsDebut == null) {
                        e.getRowValue().fin3Property().set(LocalTime.of(23, 30));
                    }
                    if (e.getRowValue().jour3Property().get() == null) {
                        e.getRowValue().jour3Property().set(DayOfWeek.MONDAY);
                    }
                }
            }else{
                e.getRowValue().fin3Property().set(e.getOldValue());
            }
        });

        // === Set Table Items ===
        listeCours.setItems(maListeCours);

        nbCreditsAjout.setItems(creditsPoss);

        jourSeance1Ajout.setItems(jours);
        jourSeance2Ajout.setItems(jours);
        jourSeance3Ajout.setItems(jours);

        tempsDebutSeance1Ajout.setItems(horaires);
        tempsFinSeance1Ajout.setItems(horaires);
        tempsDebutSeance2Ajout.setItems(horaires);
        tempsFinSeance2Ajout.setItems(horaires);
        tempsDebutSeance3Ajout.setItems(horaires);
        tempsFinSeance3Ajout.setItems(horaires);
    }

    /**
     * Cellule personnalisée pour permettre l'édition de dates dans un TableView avec un DatePicker.
     *
     * @param <T> Le type de l'objet de ligne.
     */
    public static class DatePickerCell<T> extends TableCell<T, LocalDate> {
        private final DatePicker datePicker = new DatePicker();

        public DatePickerCell() {
            datePicker.setOnAction(e -> {
                commitEdit(datePicker.getValue());
            });
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }

        @Override
        protected void updateItem(LocalDate item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                datePicker.setValue(item);
                setGraphic(datePicker);
            }
        }
    }

    /**
     * Cellule personnalisée pour permettre l'édition d'heures dans un TableView avec un ComboBox.
     *
     * @param <T> Le type de l'objet de ligne.
     */
    public static class TimePickerCell<T> extends TableCell<T, LocalTime> {
        private final ComboBox<LocalTime> comboBox = new ComboBox<>();

        public TimePickerCell() {
            ObservableList<LocalTime> times = FXCollections.observableArrayList();
            for (int h = 0; h < 24; h++) {
                times.add(LocalTime.of(h, 0));
                times.add(LocalTime.of(h, 30));
            }

            comboBox.setItems(times);
            comboBox.setConverter(new StringConverter<>() {
                private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

                @Override public String toString(LocalTime t) {
                    return t != null ? t.format(formatter) : "";
                }

                @Override public LocalTime fromString(String s) {
                    return LocalTime.parse(s, formatter);
                }
            });

            comboBox.setOnAction(e -> commitEdit(comboBox.getValue()));
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }

        @Override
        protected void updateItem(LocalTime item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                comboBox.setValue(item);
                setGraphic(comboBox);
            }
        }
    }

    /**
     * Supprime un cours sélectionné après confirmation.
     * Retire à la fois de la TableView et du modèle de données (CoursListe).
     *
     * @param actionEvent l'événement généré par le clic sur le bouton de suppression.
     */
    public void onDeleteSelectedClick(ActionEvent actionEvent) {
        CoursViewModel selectedVM = listeCours.getSelectionModel().getSelectedItem();

        if (selectedVM != null) {
            // Optionally confirm
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Supprimer le cours sélectionné?");
            alert.setContentText("Cette action est irréversible.");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Remove from TableView's observable list
                maListeCours.remove(selectedVM);

                // Remove from backend model list
                CoursClasse realCours = selectedVM.getModel();
                CoursListe.deleteCours(realCours);
            }
        } else {
            // Show error if nothing selected
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle("Aucun cours sélectionné");
            warning.setHeaderText(null);
            warning.setContentText("Veuillez sélectionner un cours à supprimer.");
            warning.showAndWait();
        }
    }


    /**
     * Active ou désactive dynamiquement les champs de saisie de séances selon le nombre sélectionné.
     *
     * @param actionEvent l'événement généré lors du changement du ComboBox nbSeances.
     */
    public void onSelectNumSeances(ActionEvent actionEvent) {
        Integer count = nbSeances.getValue();
        ajoutSeance2.setDisable(count < 2);
        ajoutSeance3.setDisable(count < 3);
    }

    /**
     * Traite l'ajout d'un cours en validant les champs saisis par l'utilisateur.
     * Vérifie notamment que les dates et les heures sont valides avant d'ajouter
     * le cours à la TableView et au modèle de données.
     *
     * @param actionEvent l'événement généré par le bouton d'ajout.
     */
    public void onAjoutCoursClick(ActionEvent actionEvent) {

        try {
            boolean tempsCorrect1 =  dateDebutAjout.getValue().isBefore(dateDebutAjout.getValue());;
            boolean tempsCorrect2 = true;
            boolean tempsCorrect3 = true;
            boolean dateCorrect = dateDebutAjout.getValue().isBefore(dateFinAjout.getValue());

            if (tempsCorrect1 && dateCorrect) {

                SeanceClass mySeance1 = new SeanceClass(jourSeance1Ajout.getValue(), tempsDebutSeance1Ajout.getValue(), tempsFinSeance1Ajout.getValue());
                SeanceClass mySeance2 = new SeanceClass(false);
                SeanceClass mySeance3 = new SeanceClass(false);

                if (!ajoutSeance2.isDisabled()) {
                    tempsCorrect2 = tempsDebutSeance2Ajout.getValue().isBefore(tempsFinSeance2Ajout.getValue());
                    if (tempsCorrect2)
                        mySeance2 = new SeanceClass(jourSeance2Ajout.getValue(), tempsDebutSeance2Ajout.getValue(), tempsFinSeance2Ajout.getValue());
                    if (!ajoutSeance3.isDisabled()) {
                        tempsCorrect3 = tempsDebutSeance3Ajout.getValue().isBefore(tempsFinSeance3Ajout.getValue());
                        if (tempsCorrect3)
                            mySeance3 = new SeanceClass(jourSeance3Ajout.getValue(), tempsDebutSeance3Ajout.getValue(), tempsFinSeance3Ajout.getValue());
                    }
                }
                if (tempsCorrect2 && tempsCorrect3) {
                    CoursClasse newCours = new CoursClasse(nomCoursAjout.getText(), nbCreditsAjout.getValue(), dateDebutAjout.getValue(), dateFinAjout.getValue(), mySeance1, mySeance2, mySeance3);
                    CoursListe.addCoursListe(newCours);
                    maListeCours.add(new CoursViewModel(newCours));
                    clearForm();
                } else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Formulaire invalide");
                    alert.setHeaderText("Heure de fin invalide");
                    alert.setContentText("L'heure de fin ne peut pas précéder l'heure de début.");
                    alert.showAndWait();
                }
            } else if (tempsCorrect1 && !dateCorrect) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Formulaire invalide");
                alert.setHeaderText("Date de fin invalide");
                alert.setContentText("La date de fin ne peut pas précéder la date de début.");
                alert.showAndWait();
            } else if (dateCorrect && !tempsCorrect1) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Formulaire invalide");
                alert.setHeaderText("Heure de fin invalide");
                alert.setContentText("L'heure de fin ne peut pas précéder l'heure de début.");
                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Formulaire invalide");
                alert.setHeaderText("Date Heure de fin invalide");
                alert.setContentText("Assurez-vous que les dates et heures de fin soient après celles de début.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Formulaire incomplet");
            alert.setHeaderText("Veuillez remplir toutes les cases.");
            alert.showAndWait();
        }
    }

    /**
     * Réinitialise tous les champs du formulaire après l'ajout d'un cours.
     */
    public void clearForm() {
        nbSeances.getSelectionModel().clearSelection();
        ajoutSeance2.setDisable(true);
        ajoutSeance3.setDisable(true);

        nomCoursAjout.setText("");
        nbCreditsAjout.getSelectionModel().clearSelection();
        dateDebutAjout.setValue(null);
        dateFinAjout.setValue(null);

        jourSeance1Ajout.setValue(null);
        jourSeance2Ajout.setValue(null);
        jourSeance3Ajout.setValue(null);

        tempsDebutSeance1Ajout.getSelectionModel().clearSelection();
        tempsDebutSeance2Ajout.getSelectionModel().clearSelection();
        tempsDebutSeance3Ajout.getSelectionModel().clearSelection();

        tempsFinSeance1Ajout.getSelectionModel().clearSelection();
        tempsFinSeance2Ajout.getSelectionModel().clearSelection();
        tempsFinSeance3Ajout.getSelectionModel().clearSelection();
    }
}
