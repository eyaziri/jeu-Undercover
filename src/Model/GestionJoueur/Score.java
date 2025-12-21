package Model.GestionJoueur;

import Logging.LoggerSingleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Score {

    private Map<Joueur, Integer> scores;

    public Score() {
        this.scores = new HashMap<>();
        LoggerSingleton.getInstance().log("STATE", "Score initialisé");
    }

    public Map<Joueur, Integer> getScores() {
        return this.scores;
    }

    // Méthode pour attribuer les scores
    public void attribuerScore(String gagnant, ArrayList<Joueur> joueurs, ArrayList<Joueur> joueursElimines) {
        LoggerSingleton.getInstance().log("STATE", "Attribution des scores -> Gagnant: " + gagnant);

        // Joueurs restants
        for (Joueur joueur : joueurs) {
            if (joueur.getNom().equalsIgnoreCase(gagnant)) {
                joueur.setScore(6); // Le gagnant obtient 6 points
            } else {
                joueur.setScore(0); // Les autres survivants obtiennent 0 point
            }
            scores.put(joueur, joueur.getScore());
            LoggerSingleton.getInstance().log("STATE", "Score attribué à " + joueur.getNom() + ": " + joueur.getScore());
        }

        // Joueurs éliminés
        for (Joueur joueur : joueursElimines) {
            if (joueur.getNom().equalsIgnoreCase(gagnant)) {
                joueur.setScore(3); // Le gagnant obtient 3 points s'il est éliminé
            } else {
                joueur.setScore(0); // Les autres éliminés obtiennent 0 point
            }
            scores.put(joueur, joueur.getScore());
            LoggerSingleton.getInstance().log("STATE", "Score attribué à éliminé " + joueur.getNom() + ": " + joueur.getScore());
        }
    }

    // Consulter le score d'un joueur spécifique
    public int consulterScore(Joueur joueur) {
        int score = scores.getOrDefault(joueur, 0);
        LoggerSingleton.getInstance().log("STATE", "Consultation du score de " + joueur.getNom() + ": " + score);
        return score;
    }

    public void afficherScores(ArrayList<Joueur> joueursRestants, ArrayList<Joueur> joueursElimines) {
        System.out.println("\n-------------------- Résultats de la partie --------------------");
        LoggerSingleton.getInstance().log("STATE", "Affichage des scores des joueurs restants et éliminés");

        // Joueurs restants
        System.out.println("\nLes joueurs :");
        for (Joueur joueur : joueursRestants) {
            int score = joueur.getScore();
            if (score == 6) {
                System.out.println("🎉 Gagnant: " + joueur.getNom() + " (" + joueur.getRole() + "), Score: " + score);
            } else {
                System.out.println("Joueur: " + joueur.getNom() + " (" + joueur.getRole() + "), Score: " + score);
            }
        }

        // Joueurs éliminés
        for (Joueur joueur : joueursElimines) {
            System.out.println("Joueur: " + joueur.getNom() + " (" + joueur.getRole() + "), Score: " + joueur.getScore());
        }

        System.out.println("\n--------------------- Fin des résultats ---------------------");
    }

    public void afficherScoresTries(ArrayList<Joueur> joueursRestants, ArrayList<Joueur> joueursElimines) {
        System.out.println("\n-------------------- Résultats triés par score --------------------");
        LoggerSingleton.getInstance().log("STATE", "Affichage des scores triés par score décroissant");

        List<Joueur> tousLesJoueurs = new ArrayList<>();
        tousLesJoueurs.addAll(joueursRestants);
        tousLesJoueurs.addAll(joueursElimines);

        List<Joueur> joueursTries = tousLesJoueurs.stream()
                .sorted((j1, j2) -> Integer.compare(j2.getScore(), j1.getScore()))
                .collect(Collectors.toList());

        joueursTries.forEach(joueur -> {
            System.out.println("Joueur: " + joueur.getNom() + " (" + joueur.getRole() + "), Score: " + joueur.getScore());
            LoggerSingleton.getInstance().log("STATE", "Score trié: " + joueur.getNom() + " -> " + joueur.getScore());
        });

        System.out.println("\n--------------------- Fin des résultats triés ---------------------");
    }
}
