package com.example.prog2tp1;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Classe singleton représentant la liste globale des cours de l'application.
 * Elle permet de centraliser et gérer les instances de {@link CoursClasse} ajoutées ou supprimées.
 * Utilisée comme modèle backend par les contrôleurs.
 *
 * Cette classe est utilisée comme source de vérité pour l'ensemble des cours enregistrés.
 *
 */
public class CoursListe {
    private static final CoursListe instance = new CoursListe();

    /** Instance unique du singleton. */
    private static final ArrayList<CoursClasse> mesCours = new ArrayList<>();

    /** Constructeur privé pour empêcher l'instanciation externe. */
    public CoursListe() {

    }

    /**
     * Retourne l'instance unique du singleton.
     *
     * @return instance de {@code CoursListe}
     */
    public static CoursListe getInstance() {
        return instance;
    }

    /**
     * Retourne la liste complète des cours.
     *
     * @return {@code ArrayList} contenant tous les {@code CoursClasse}
     */
    public ArrayList<CoursClasse> getMesCours() {
        return mesCours;
    }

    /**
     * Ajoute un cours à la liste globale.
     *
     * @param monCours le cours à ajouter
     */
    public static void addCoursListe(CoursClasse monCours) {
        mesCours.add(monCours);
    }

    /**
     * Supprime un cours de la liste globale.
     *
     * @param monCours le cours à supprimer
     */
    public static void deleteCours(CoursClasse monCours) {
        mesCours.remove(monCours);
    }


    /**
     * Retourne un cours selon son index dans la liste.
     *
     * @param index l'indice du cours
     * @return le {@code CoursClasse} à cet index, ou {@code null} si l'index est invalide
     */
    public CoursClasse getCours(int index) {
        try {
            return mesCours.get(index);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retourne le nombre total de cours dans la liste.
     *
     * @return taille de la liste
     */
    public int size() {
        return mesCours.size();
    }
}
