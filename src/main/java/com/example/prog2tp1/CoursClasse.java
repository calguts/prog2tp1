package com.example.prog2tp1;

import java.util.LinkedList;

public class CoursClasse {

    String nomCours;
    int nbCredits;
    LinkedList<SectionClass> horaireCours;

    public CoursClasse() {}

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
}
