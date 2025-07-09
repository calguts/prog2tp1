package com.example.prog2tp1;

import java.time.LocalDate;
import java.util.LinkedList;

public class CoursClasse {

    String nomCours;
    int nbCredits;
    LocalDate dateDebutCours;
    LocalDate dateFinCours;
    LinkedList<SeanceClass> horaireCours;

    public CoursClasse(String nomCours, int nbCredits, LocalDate dateDebutCours, LocalDate dateFinCours, SeanceClass seanceClass1, SeanceClass seanceClass2, SeanceClass seanceClass3) {
        this.nomCours = nomCours;
        this.nbCredits = nbCredits;
        this.dateDebutCours = dateDebutCours;
        this.dateFinCours = dateFinCours;
        horaireCours.add(seanceClass1);

        if (seanceClass2 != null)
            horaireCours.add(seanceClass2);
        if (seanceClass3 != null)
            horaireCours.add(seanceClass3);
    }

    public String getNomCours() {
        return nomCours;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }

    public int getNbCredits() {
        return nbCredits;
    }

    public void setNbCredits(int nbCredits) {
        this.nbCredits = nbCredits;
    }

    public LocalDate getDateDebutCours() {
        return dateDebutCours;
    }

    public void setDateDebutCours(LocalDate dateDebutCours) {
        this.dateDebutCours = dateDebutCours;
    }

    public LocalDate getDateFinCours() {
        return dateFinCours;
    }

    public void setDateFinCours(LocalDate dateFinCours) {
        this.dateFinCours = dateFinCours;
    }

    public LinkedList<SeanceClass> getHoraireCours() {
        return horaireCours;
    }

    public void setHoraireCours(SeanceClass seanceClass1, SeanceClass seanceClass2, SeanceClass seanceClass3) {
        this.dateFinCours = dateFinCours;
        horaireCours.add(seanceClass1);
        horaireCours.add(seanceClass2);
        horaireCours.add(seanceClass3);
    }
}
