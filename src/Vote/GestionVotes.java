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
    
    public int getVotes(Joueur joueur)
    {
        return resultatVote.getOrDefault(joueur, 0);  
    }
    
     public void afficherResultats()
     {
        for (HashMap.Entry<Joueur, Integer> entry : resultatVote.entrySet()) {
            Joueur joueur = entry.getKey();
            int votes = entry.getValue();
            System.out.println("Joueur: " + joueur.getNom() + " a recu " + votes + " votes.");
        }
    }
    
    public void eliminerJoueurApresVote(GestionJoueur gestionJoueur) {
    List<Joueur> joueurs = gestionJoueur.getJoueurs();
    Joueur joueurElimine = null;
    String mot = null; // Initialisation du mot à l'extérieur de la boucle
    
    // Trouver le joueur ayant reçu le plus de votes
    for (Joueur joueur : joueurs) {
        if (joueurElimine == null || joueur.getNombreDeVotesRecus() > joueurElimine.getNombreDeVotesRecus()) {
            joueurElimine = joueur;
        }
    }
    
    if (joueurElimine != null) {
        String role = joueurElimine.getRole().toLowerCase();
        
        if (role.equals("civile") || role.equals("undercover")) {
            System.out.println("Le joueur " + joueurElimine.getNom() + " est elimine avec " + joueurElimine.getNombreDeVotesRecus() + " votes." + " qui a le role de : " + joueurElimine.getRole());
            joueurElimine.estEliminer(gestionJoueur);
            elimination.ajouterJoueurElimine(joueurElimine);
            gestionJoueur.supprimerJoueur(joueurElimine);
        } else if (role.equals("mrwhite")) {
            // Récupérer le mot d'un joueur civile
            for (int i = 0; i < gestionJoueur.longueurListe(); i++) {
                Joueur joueur = gestionJoueur.getJoueurs().get(i);
                if (joueur.getRole().equalsIgnoreCase("civile")) {
                    mot = joueur.getMot();
                    break; // On arrête la recherche dès qu'on trouve un mot chez un civile
                }      
            }
            
            // Vérification du mot pour Mr. White
            Scanner sc = new Scanner(System.in);
            System.out.println("Vous etes Mr. White. Essayez de deviner le mot associe : ");
            String motDevine = sc.nextLine();
            
            if (mot != null && motDevine.equalsIgnoreCase(mot)) {
                System.out.println("Felicitations, vous avez devine correctement et vous n'etes pas elimine.");
            } else {
                System.out.println("Echec. Vous avez ete elimine. Le mot correct etait : " + mot);
                joueurElimine.estEliminer(gestionJoueur);
                elimination.ajouterJoueurElimine(joueurElimine);
                gestionJoueur.supprimerJoueur(joueurElimine);
            }
            sc.close();  
        }
    } else {
        System.out.println("Aucun joueur n'a ete trouve pour elimination.");
    }
}

    
    
    
    
}

