package com.example.prog2tp1;

import java.time.LocalDate;
import java.util.LinkedList;

/**
 * Représente un cours avec son nom, son nombre de crédits, ses dates de début et de fin,
 * ainsi qu'une liste de séances associées.
 */
public class CoursClasse {
    String nomCours;
    int nbCredits;
    LocalDate dateDebutCours;
    LocalDate dateFinCours;
    LinkedList<SeanceClass> horaireCours = new LinkedList<>();

    /**
     * Constructeur du cours avec ses trois séances.
     *
     * @param nomCours        le nom du cours
     * @param nbCredits       le nombre de crédits du cours
     * @param dateDebutCours  la date de début du cours
     * @param dateFinCours    la date de fin du cours
     * @param seanceClass1    première séance (obligatoire)
     * @param seanceClass2    deuxième séance (facultative)
     * @param seanceClass3    troisième séance (facultative)
     */
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

    /**
     * @return le nom du cours
     */
    public String getNomCours() {
        return nomCours;
    }

    /**
     * Det le nom du cours.
     * @param nomCours le nom à définir
     */
    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }

    /**
     * @return le nombre de crédits du cours
     */
    public int getNbCredits() {
        return nbCredits;
    }

    /**
     * Set le nombre de crédits du cours.
     * @param nbCredits le nombre de crédits
     */
    public void setNbCredits(int nbCredits) {
        this.nbCredits = nbCredits;
    }

    /**
     * @return la date de début du cours
     */
    public LocalDate getDateDebutCours() {
        return dateDebutCours;
    }


    /**
     * Définit la date de début du cours.
     * @param dateDebutCours la date de début
     */
    public void setDateDebutCours(LocalDate dateDebutCours) {
        this.dateDebutCours = dateDebutCours;
    }

    /**
     * @return la date de fin du cours
     */
    public LocalDate getDateFinCours() {
        return dateFinCours;
    }

    /**
     * Définit la date de fin du cours.
     * @param dateFinCours la date de fin
     */
    public void setDateFinCours(LocalDate dateFinCours) {
        this.dateFinCours = dateFinCours;
    }

    /**
     * @return la liste des séances du cours
     */
    public LinkedList<SeanceClass> getHoraireCours() {
        return horaireCours;
    }

    /**
     * Redéfinit entièrement la liste des séances du cours.
     * @param seanceClass1 première séance
     * @param seanceClass2 deuxième séance
     * @param seanceClass3 troisième séance
     */
    public void setHoraireCours(SeanceClass seanceClass1, SeanceClass seanceClass2, SeanceClass seanceClass3) {
        horaireCours.clear();
        if (seanceClass1 != null)
            horaireCours.add(seanceClass1);
        if (seanceClass2 != null)
            horaireCours.add(seanceClass2);
        if (seanceClass3 != null)
            horaireCours.add(seanceClass3);
    }
}
