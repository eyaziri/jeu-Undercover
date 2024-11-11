/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestionJoueur;

/**
 *
 * @author eyazi
 */
import java.util.HashMap;
import java.util.Map;

public class Score {
   
    private Map<Joueur, Integer> scores;

    public Score() {
        this.scores = new HashMap<>();
    }

    public void attribuerScore(Joueur joueur, int points) {
        if (scores.containsKey(joueur)) {
            int nouveauScore = scores.get(joueur) + points;
            scores.put(joueur, nouveauScore);
        } else {
            scores.put(joueur, points);
        }
    }

    public int consulterScore(Joueur joueur) {
        return scores.getOrDefault(joueur, 0);
    }
   
    public void afficherScores() {
        for (Map.Entry<Joueur, Integer> entry : scores.entrySet()) {
            System.out.println("Joueur: " + entry.getKey().getNom() + ", Score: " + entry.getValue());
        }
    }
}
