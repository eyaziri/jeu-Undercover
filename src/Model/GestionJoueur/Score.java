/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.GestionJoueur;

/**
 *
 * @author eyazi
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.util.stream.Collectors;

public class Score {

    private Map<Joueur, Integer> scores;

    public Score() {
        this.scores = new HashMap<>();
    }
    
    public Map<Joueur, Integer> getScores() 
    {
    return this.scores;
    }


    // Méthode pour attribuer les scores
    public void attribuerScore(String gagnant, ArrayList<Joueur> joueurs, ArrayList<Joueur> joueursElimines) {
        // Attribuer des scores pour les joueurs restants
        for (Joueur joueur : joueurs) {
            if (joueur.getNom().equalsIgnoreCase(gagnant)) {
                joueur.setScore(6); // Le gagnant obtient 6 points
            } else {
                joueur.setScore(0); // Les autres survivants obtiennent 3 points
            }
            scores.put(joueur, joueur.getScore());
        }

        // Attribuer des scores pour les joueurs éliminés
        for (Joueur joueur : joueursElimines) {
            if (joueur.getNom().equalsIgnoreCase(gagnant)) {
                joueur.setScore(3); // Le gagnant obtient 3 points s'il est éliminé
            } else {
                joueur.setScore(0); // Les autres éliminés obtiennent 0 point
            }
            scores.put(joueur, joueur.getScore());
        }
    }

    // Consulter le score d'un joueur spécifique
    public int consulterScore(Joueur joueur) {
        return scores.getOrDefault(joueur, 0);
    }

   
     public void afficherScores(ArrayList<Joueur> joueursRestants, ArrayList<Joueur> joueursElimines) {
    System.out.println("\n-------------------- Résultats de la partie --------------------");

    // Affichage des joueurs restants
    System.out.println("\nLes joueurs  :");
    for (Joueur joueur : joueursRestants) {
        int score = joueur.getScore();
        if (score == 6) {
            System.out.println("🎉 Gagnant: " + joueur.getNom() +  "("+joueur.getRole()+")"+", Score: " + score);
        } else {
            System.out.println("Joueur: " + joueur.getNom()+ "("+joueur.getRole()+")" + ", Score: " + score);
        }
    }

    // Affichage des joueurs éliminés
   
    for (Joueur joueur : joueursElimines) {
        System.out.println("Joueur: " + joueur.getNom() + "("+joueur.getRole()+")"+ ", Score: " + joueur.getScore());
    }

    System.out.println("\n--------------------- Fin des résultats ---------------------");
}
     
     // Afficher les scores triés (lambda + stream)
public void afficherScoresTries(ArrayList<Joueur> joueursRestants, ArrayList<Joueur> joueursElimines) {
    System.out.println("\n-------------------- Résultats triés par score --------------------");

    // Combiner joueurs restants et éliminés
    List<Joueur> tousLesJoueurs = new ArrayList<>();
    tousLesJoueurs.addAll(joueursRestants);
    tousLesJoueurs.addAll(joueursElimines);

    // Trier les joueurs par score décroissant
    List<Joueur> joueursTries = tousLesJoueurs.stream()
            .sorted((j1, j2) -> Integer.compare(j2.getScore(), j1.getScore()))
            .collect(Collectors.toList());

    // Afficher les joueurs triés
    joueursTries.forEach(joueur -> System.out.println(
            "Joueur: " + joueur.getNom() + " (" + joueur.getRole() + "), Score: " + joueur.getScore()
    ));

    System.out.println("\n--------------------- Fin des résultats triés ---------------------");
}


}

