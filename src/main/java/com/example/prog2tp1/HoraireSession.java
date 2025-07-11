package com.example.prog2tp1;

import java.util.ArrayList;
import java.util.Objects;

public class HoraireSession {


    private static final HoraireSession instance = new HoraireSession();
    private static final ArrayList<CoursClasse> monHoraire = new ArrayList<>();

    public HoraireSession() {
    }


    private ArrayList<CoursClasse> gererMonHoraire(int numCredits) {
        for(int i = 0; i < 10; i++){
            for(int j = i + 1; j < 10; j++){
                for(int k =  j + 1; k < 10; k++){
                    if (aucunConflitHoraire(i, j, k)){
                        if (bonNombreCredits(i, j, k)){
                            monHoraire.add(CoursListe.getCours(i));
                            monHoraire.add(CoursListe.getCours(j));
                            monHoraire.add(CoursListe.getCours(k));
                            return monHoraire;
                        }
                    }
                }
            }
        }
        return monHoraire;
    }

    private boolean aucunConflitHoraire(int indexCours1, int indexCours2, int indexCours3){
        if return true;
    }

    private boolean bonNombreCredits(int indexCours1, int indexCours2, int indexCours3, int numCredits){
        return CoursListe.getCours(indexCours1).getNbCredits()
        + CoursListe.getCours(indexCours2).getNbCredits()
        + CoursListe.getCours(indexCours3).getNbCredits() == numCredits;
    }
}