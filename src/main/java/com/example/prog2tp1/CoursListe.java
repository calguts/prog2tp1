package com.example.prog2tp1;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CoursListe {
    private static final CoursListe instance = new CoursListe();

    private static final ArrayList<CoursClasse> mesCours = new ArrayList<>();


    public CoursListe() {

    }

    public static CoursListe getInstance() {
        return instance;
    }


    public ArrayList<CoursClasse> getMesCours() {
        return mesCours;
    }
    
    public static void addCoursListe(CoursClasse monCours) {
        mesCours.add(monCours);
    }

    public static void deleteCours(CoursClasse monCours) {
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
