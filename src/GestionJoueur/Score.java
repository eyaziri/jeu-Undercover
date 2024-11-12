/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestionJoueur;

/**
 *
 * @author eyazi
 */
import Vote.Elimination;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class Score {

    private Map<Joueur, Integer> scores;

    public Score() {
        this.scores = new HashMap<>();
    }

    public void attribuerScore(String gagnant, ArrayList<Joueur> joueurs, ArrayList<Joueur> joueursElimines) {
     
        for (Joueur joueur : joueurs) {
            if (joueur.getNom().equalsIgnoreCase(gagnant)) {
                joueur.setScore(6); 
            } else {
                joueur.setScore(0); 
            }
            scores.put(joueur, joueur.getScore());
        }

    
        for (Joueur joueur : joueursElimines) {
            if (joueur.getNom().equalsIgnoreCase(gagnant)) {
                joueur.setScore(3); 
            } else {
                joueur.setScore(0); 
            }
            scores.put(joueur, joueur.getScore());
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