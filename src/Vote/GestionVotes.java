/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vote;

import Administration.GestionJoueur;
import Administration.ListeMots;
import GestionJoueur.Joueur;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author eyazi
 */public class GestionVotes {
    
     private Elimination elimination;
     private HashMap<Joueur, Integer> resultatVote;

    public GestionVotes() 
    {
        resultatVote = new HashMap<>();
        this.elimination = new Elimination(); 
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
    
      public void eliminerJoueurApresVote(ListeMots listeMots) {
        List<Joueur> joueurs = GestionJoueur.getJoueurs();
        Joueur joueurElimine = null;
        
        
        for (Joueur joueur : joueurs) {
            if (joueurElimine == null || joueur.getNombreDeVotesRecus() > joueurElimine.getNombreDeVotesRecus()) {
                joueurElimine = joueur;
            }
        }

     
        if (joueurElimine != null) {
            if (joueurElimine.getRole().equalsIgnoreCase("Civil") || joueurElimine.getRole().equalsIgnoreCase("Undercover")) {
                System.out.println("Le joueur " + joueurElimine.getNom() + " est éliminé avec " + joueurElimine.getNombreDeVotesRecus() + " votes.");
                joueurElimine.estEliminer(listeMots); 
                elimination.ajouterJoueurElimine(joueurElimine);
            } else if (joueurElimine.getRole().equalsIgnoreCase("Mr.White")) {
              
                Scanner sc = new Scanner(System.in);
                String motDevine;
                System.out.println("Vous êtes Mr. White. Essayez de deviner le mot associé : ");
                motDevine = sc.nextLine();

                if (motDevine.equalsIgnoreCase(joueurElimine.getMot())) {
                    System.out.println("Félicitations, vous avez deviné correctement et vous n'êtes pas éliminé.");
                } else {
                    System.out.println("Échec. Vous avez été éliminé. Le mot correct était : " + joueurElimine.getMot());
                    joueurElimine.estEliminer(listeMots);
                    elimination.ajouterJoueurElimine(joueurElimine);
                }
            }
        }
    }
    
    
}

