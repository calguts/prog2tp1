package com.example.prog2tp1;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Représente une séance unique d'un cours dans l'emploi du temps.
 *
 * Une séance est définie par un jour de la semaine et une plage horaire.
 * Les séances peuvent être actives (avec jour et horaires définis) ou inactives (valeurs nulles).
 *
 * Utilisé notamment dans {@link CoursClasse} pour modéliser jusqu’à trois séances par cours.</p>
 */
public class SeanceClass {
    DayOfWeek jour;
    LocalTime debutTimeSection;
    LocalTime finTimeSection;

    /**
     * Crée une séance avec les valeurs complètes : jour, heure de début, et heure de fin.
     *
     * @param jour le jour de la semaine
     * @param debutTimeSection l’heure de début
     * @param finTimeSection l’heure de fin
     */
    public SeanceClass(DayOfWeek jour, LocalTime debutTimeSection, LocalTime finTimeSection) {
        this.jour = jour;
        this.debutTimeSection = debutTimeSection;
        this.finTimeSection = finTimeSection;
    }

    /**
     * Crée une séance vide (non définie) si {@code exists} est {@code false}.
     * Tous les champs sont mis à {@code null}.
     *
     * @param exists drapeau sans effet fonctionnel direct ici, utilisé pour signaler une séance inactive
     */
    public SeanceClass(boolean exists) {
        this.jour = null;
        this.debutTimeSection = null;
        this.finTimeSection = null;
    }

    /**
     * Retourne le jour de la séance.
     * @return le jour de la semaine
     */
    public DayOfWeek getJour() {
        return jour;
    }


    /**
     * Définit le jour de la séance.
     * @param jour le nouveau jour
     */
    public void setJour(DayOfWeek jour) {
        this.jour = jour;
    }

    /**
     * Retourne l’heure de début de la séance.
     * @return l’heure de début
     */
    public LocalTime getDebutTimeSection() {
        return debutTimeSection;
    }

    /**
     * Définit l’heure de début de la séance.
     * @param debutTimeSection la nouvelle heure de début
     */
    public void setDebutTimeSection(LocalTime debutTimeSection) {
        this.debutTimeSection = debutTimeSection;
    }

    /**
     * Retourne l’heure de fin de la séance.
     * @return l’heure de fin
     */
    public LocalTime getFinTimeSection() {
        return finTimeSection;
    }

    /**
     * Définit l’heure de fin de la séance.
     * @param finTimeSection la nouvelle heure de fin
     */
    public void setFinTimeSection(LocalTime finTimeSection) {
        this.finTimeSection = finTimeSection;
    }

}
