


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Vote;

import Model.Administration.GestionJoueur;
import Model.GestionJoueur.Gagnant;
import Model.GestionJoueur.Joueur;
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
   

    
    public void eliminerJoueurApresVote(GestionJoueur gestionJoueur,Elimination elimination,PhaseVote phaseVote,Gagnant gagnant) {
        List<Joueur> joueurs = gestionJoueur.getListeJoueurs();
        
        Joueur joueurElimine = null;
        String mot = null; 

        for (Joueur joueur : joueurs) 
        {
            if (joueurElimine == null || joueur.getNombreDeVotesRecus() > joueurElimine.getNombreDeVotesRecus()) 
            {
                joueurElimine = joueur;
            }
        }

        if (joueurElimine != null) 
        {
            String role = joueurElimine.getRole().toLowerCase();

            if (role.equals("civile") || role.equals("undercover"))
            {
                System.out.println("Le joueur " + joueurElimine.getNom() + " est elimine avec " + joueurElimine.getNombreDeVotesRecus() + " votes." + " qui a le role de : " + joueurElimine.getRole());
                joueurElimine.estEliminer(gestionJoueur);
                joueurElimine.setEstVivant();
                elimination.ajouterJoueurElimine(joueurElimine);
                gestionJoueur.supprimerJoueur(joueurElimine);

            } 
            else if (role.equals("mrwhite")) 
            {

                for (Joueur joueur : joueurs) 
                {
                    if (joueur.getRole().equalsIgnoreCase("civile")) 
                    {
                        mot = joueur.getMot();
                        break;
                    }      
                }

                Scanner sc = new Scanner(System.in);
                System.out.println("Vous etes Mr. White. Essayez de deviner le mot associe : ");
                String motDevine = null;

                if (sc.hasNextLine()) 
                {
                    motDevine = sc.nextLine();
                }

                if (mot != null && motDevine != null && motDevine.equalsIgnoreCase(mot)) 
                {
                    System.out.println("Felicitations, vous avez devine correctement .");
                    gagnant.ajouterGagnant(joueurElimine);
                    phaseVote.terminerPhase();
                } 
                else 
                {
                    System.out.println("Echec. Vous avez ete elimine. Le mot correct etait : " );
                    joueurElimine.estEliminer(gestionJoueur);
                    joueurElimine.setEstVivant();
                    elimination.ajouterJoueurElimine(joueurElimine);

                    gestionJoueur.supprimerJoueur(joueurElimine);
                }
            }
            
          
        } 
        else 
        {
            System.out.println("Aucun joueur n'a ete trouve pour elimination.");
        }
         
    }
    
    
}
