package com.example.prog2tp1;

import javafx.beans.property.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

/**
 * Classe de type ViewModel utilisée pour faire le lien entre le modèle {@link CoursClasse}
 * et l’interface graphique JavaFX via des {@code Property}.
 *
 * Cette classe permet à la TableView d’afficher les attributs modifiables du cours,
 * et de répercuter les changements faits dans la vue vers le modèle sous-jacent.
 *
 * Elle gère jusqu’à trois séances (jour, heure de début, heure de fin) par cours.
 */
public class CoursViewModel {

    /** Modèle de données original associé à ce ViewModel. */
    private CoursClasse model;

    // === Propriétés JavaFX pour lier à l'interface graphique ===
    private final StringProperty nomCours = new SimpleStringProperty();
    private final IntegerProperty nbCredits = new SimpleIntegerProperty();
    private final ObjectProperty<LocalDate> dateDebutCours = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> dateFinCours = new SimpleObjectProperty<>();

    private final ObjectProperty<DayOfWeek> jour1 = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalTime> debut1 = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalTime> fin1 = new SimpleObjectProperty<>();

    private final ObjectProperty<DayOfWeek> jour2 = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalTime> debut2 = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalTime> fin2 = new SimpleObjectProperty<>();

    private final ObjectProperty<DayOfWeek> jour3 = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalTime> debut3 = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalTime> fin3 = new SimpleObjectProperty<>();

    /**
     * Constructeur qui initialise les propriétés à partir du modèle {@link CoursClasse}
     * et établit des écouteurs pour synchroniser les modifications vers le modèle.
     *
     * @param model instance de {@code CoursClasse} à représenter dans la vue
     */
    public CoursViewModel(CoursClasse model) {
        this.model = model;

        // Fill JavaFX properties from model
        nomCours.set(model.getNomCours());
        nbCredits.set(model.getNbCredits());
        dateDebutCours.set(model.getDateDebutCours());
        dateFinCours.set(model.getDateFinCours());

        LinkedList<SeanceClass> seances = model.getHoraireCours();

        if (seances.size() > 0) {
            jour1.set(seances.get(0).getJour());
            debut1.set(seances.get(0).getDebutTimeSection());
            fin1.set(seances.get(0).getFinTimeSection());
        }
        if (seances.size() > 1) {
            jour2.set(seances.get(1).getJour());
            debut2.set(seances.get(1).getDebutTimeSection());
            fin2.set(seances.get(1).getFinTimeSection());
        }
        if (seances.size() > 2) {
            jour3.set(seances.get(2).getJour());
            debut3.set(seances.get(2).getDebutTimeSection());
            fin3.set(seances.get(2).getFinTimeSection());
        }

        // Synchronisation modèle <— vue
        nomCours.addListener((obs, old, val) -> model.setNomCours(val));
        nbCredits.addListener((obs, old, val) -> model.setNbCredits(val.intValue()));
        dateDebutCours.addListener((obs, old, val) -> model.setDateDebutCours(val));
        dateFinCours.addListener((obs, old, val) -> model.setDateFinCours(val));
    }

    /**
     * Change le modèle associé à ce ViewModel.
     * @param model nouvelle instance de {@code CoursClasse}
     */
    public void setCoursViewModel(CoursClasse model) {
        this.model = model;
    }

    /**
     * Change le modèle associé à ce ViewModel.
     * @param model nouvelle instance de {@code CoursClasse}
     */
    public CoursClasse getModel() {
        return model;
    }

    // === Getters pour les propriétés JavaFX (pour FXML/TableView) ===
    public StringProperty nomCoursProperty() { return nomCours; }
    public IntegerProperty nbCreditsProperty() { return nbCredits; }
    public ObjectProperty<LocalDate> dateDebutCoursProperty() { return dateDebutCours; }
    public ObjectProperty<LocalDate> dateFinCoursProperty() { return dateFinCours; }

    public ObjectProperty<DayOfWeek> jour1Property() { return jour1; }
    public ObjectProperty<LocalTime> debut1Property() { return debut1; }
    public ObjectProperty<LocalTime> fin1Property() { return fin1; }

    public ObjectProperty<DayOfWeek> jour2Property() { return jour2; }
    public ObjectProperty<LocalTime> debut2Property() { return debut2; }
    public ObjectProperty<LocalTime> fin2Property() { return fin2; }

    public ObjectProperty<DayOfWeek> jour3Property() { return jour3; }
    public ObjectProperty<LocalTime> debut3Property() { return debut3; }
    public ObjectProperty<LocalTime> fin3Property() { return fin3; }
}
