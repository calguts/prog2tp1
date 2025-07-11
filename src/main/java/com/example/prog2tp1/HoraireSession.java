package com.example.prog2tp1;

import java.util.ArrayList;

public class HoraireSession {

    private static final HoraireSession instance = new HoraireSession();
    private ArrayList<CoursClasse> monHoraire = new ArrayList<>();

    private HoraireSession() {}

    public static HoraireSession getInstance() {
        return instance;
    }

    /**
     * Cherche une combinaison de cours dont la somme des crédits est égale à numCredits
     * et sans conflits d'horaires.
     * Retourne une liste des cours sélectionnés ou une liste vide si aucune combinaison.
     */
    public ArrayList<CoursClasse> gererMonHoraire(int numCredits) {
        monHoraire.clear();
        ArrayList<CoursClasse> tousLesCours = CoursListe.getInstance().getMesCours();

        ArrayList<CoursClasse> resultat = new ArrayList<>();
        if (chercherCombinaison(tousLesCours, 0, numCredits, resultat)) {
            return resultat;
        }
        return new ArrayList<>(); // Rien trouvé
    }

    /**
     * Recherche récursive des combinaisons
     */
    private boolean chercherCombinaison(ArrayList<CoursClasse> tousLesCours, int startIndex,
                                        int creditsRestants, ArrayList<CoursClasse> selection) {
        if (creditsRestants == 0) {
            // Somme exacte atteinte, combinaison valide
            return true;
        }
        if (creditsRestants < 0 || startIndex >= tousLesCours.size()) {
            // Pas de solution ici
            return false;
        }

        for (int i = startIndex; i < tousLesCours.size(); i++) {
            CoursClasse cours = tousLesCours.get(i);

            // Vérifier conflit avec les cours déjà sélectionnés
            boolean conflit = false;
            for (CoursClasse c : selection) {
                if (conflitEntreCours(c, cours)) {
                    conflit = true;
                    break;
                }
            }
            if (conflit) continue;

            // Ajouter le cours et continuer la recherche
            selection.add(cours);
            if (chercherCombinaison(tousLesCours, i + 1, creditsRestants - cours.getNbCredits(), selection)) {
                return true;
            }
            // Backtrack : retirer le cours
            selection.remove(selection.size() - 1);
        }
        return false;
    }

    /**
     * Vérifie s’il existe un conflit horaire entre deux cours donnés.
     * Un conflit existe si au moins une séance du premier cours chevauche
     * une séance du deuxième cours, le même jour.
     *
     * @param c1 Le premier cours à comparer.
     * @param c2 Le deuxième cours à comparer.
     * @return {@code true} s’il y a un conflit d’horaire entre les deux cours,
     *         {@code false} sinon.
     */
    private boolean conflitEntreCours(CoursClasse c1, CoursClasse c2) {
        for (SeanceClass s1 : c1.getHoraireCours()) {
            if (!existeSeance(s1)) continue;
            for (SeanceClass s2 : c2.getHoraireCours()) {
                if (!existeSeance(s2)) continue;
                if (seancesEnConflit(s1, s2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Vérifie si une séance est définie (c’est-à-dire, qu’elle a un jour et des heures valides).
     *
     * @param s La séance à vérifier.
     * @return {@code true} si la séance est valide (non nulle et bien définie),
     *         {@code false} sinon.
     */
    private boolean existeSeance(SeanceClass s) {
        return (s.getJour() != null) && (s.getDebutTimeSection() != null) && (s.getFinTimeSection() != null);
    }

    /**
     * Vérifie si deux séances ont un conflit horaire.
     * Deux séances sont en conflit si elles ont lieu le même jour et si leurs horaires se chevauchent.
     *
     * @param s1 La première séance.
     * @param s2 La deuxième séance.
     * @return {@code true} si les deux séances sont en conflit,
     *         {@code false} sinon.
     */
    private boolean seancesEnConflit(SeanceClass s1, SeanceClass s2) {
        if (!s1.getJour().equals(s2.getJour())) return false;
        return !(s1.getFinTimeSection().isBefore(s2.getDebutTimeSection()) || s2.getFinTimeSection().isBefore(s1.getDebutTimeSection()));
    }
}