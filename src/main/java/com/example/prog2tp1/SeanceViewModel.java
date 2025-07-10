package com.example.prog2tp1;

import javafx.beans.property.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class SeanceViewModel {
    private final StringProperty nomCours;
    private final IntegerProperty nbCredits;
    private final ObjectProperty<LocalDate> dateDebut;
    private final ObjectProperty<LocalDate> dateFin;
    private final ObjectProperty<DayOfWeek> jour;
    private final ObjectProperty<LocalTime> debut;
    private final ObjectProperty<LocalTime> fin;

    public SeanceViewModel(CoursClasse cours, SeanceClass seance) {
        this.nomCours = new SimpleStringProperty(cours.getNomCours());
        this.nbCredits = new SimpleIntegerProperty(cours.getNbCredits());
        this.dateDebut = new SimpleObjectProperty<>(cours.getDateDebutCours());
        this.dateFin = new SimpleObjectProperty<>(cours.getDateFinCours());
        this.jour = new SimpleObjectProperty<>(seance.getJour());
        this.debut = new SimpleObjectProperty<>(seance.getDebutTimeSection());
        this.fin = new SimpleObjectProperty<>(seance.getFinTimeSection());
    }

    // Getters for bindings (e.g., nomCoursProperty(), jourProperty(), etc.)
    public StringProperty nomCoursProperty() { return nomCours; }
    public IntegerProperty nbCreditsProperty() { return nbCredits; }
    public ObjectProperty<LocalDate> dateDebutProperty() { return dateDebut; }
    public ObjectProperty<LocalDate> dateFinProperty() { return dateFin; }
    public ObjectProperty<DayOfWeek> jourProperty() { return jour; }
    public ObjectProperty<LocalTime> debutProperty() { return debut; }
    public ObjectProperty<LocalTime> finProperty() { return fin; }
}

