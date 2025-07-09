package com.example.prog2tp1;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CoursListe {
  ArrayList<CoursClasse> mesCours;

    public CoursListe() {
        ArrayList<CoursClasse> coursList = new ArrayList<>();
    }

    public ArrayList<CoursClasse> getMesCours() {
        return mesCours;
    }
    
    public void addCoursListe(CoursClasse monCours) {
        mesCours.add(monCours);
    }

    public void deleteCours(CoursClasse monCours) {
        mesCours.remove(monCours);
    }

    public CoursClasse getCours(int index) {
        try {
            return mesCours.get(index);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int size() {
        return mesCours.size();
    }
}
