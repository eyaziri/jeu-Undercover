/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vote;

import GestionJoueur.Joueur;
import java.util.HashMap;

/**
 *
 * @author eyazi
 */public class GestionVotes {
    
     private HashMap<Joueur, Integer> resultatVote;

    public GestionVotes() 
    {
        resultatVote = new HashMap<>();
    }
    
    public void ajouterVote(Joueur joueur) {
        if (resultatVote.containsKey(joueur)) {
            int votesActuels = resultatVote.get(joueur);
            resultatVote.put(joueur, votesActuels + 1);
        } else {
            resultatVote.put(joueur, 1);  
        }
    }
    public int getVotes(Joueur joueur) {
        return resultatVote.getOrDefault(joueur, 0);  
    }
    
     public void afficherResultats() {
        for (HashMap.Entry<Joueur, Integer> entry : resultatVote.entrySet()) {
            Joueur joueur = entry.getKey();
            int votes = entry.getValue();
            System.out.println("Joueur: " + joueur.getNom() + " a reçu " + votes + " votes.");
        }
    }
    
    
    
}

