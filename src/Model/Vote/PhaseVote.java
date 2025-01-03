/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Vote;

import Model.Administration.GestionJoueur;
import Model.Forum.Discussion;
import Model.Forum.Forum;
import Model.Forum.HistoriqueMessage;
import Model.Forum.Message;
import Model.GestionJoueur.Gagnant;
import Model.GestionJoueur.Joueur;
import Model.GestionJoueur.Score;
import java.util.ArrayList;
import java.util.Scanner;




/**
 *
 * @author eyazi
 */
public class PhaseVote {
    
    boolean phaseTermine;
    
 
    public void demarrerVote(GestionJoueur gestionJoueur,Forum forum,HistoriqueMessage historiqueMessage,Score score,Elimination elimination ,Gagnant gagnant)
{
    GestionVotes gestionVote = new GestionVotes();
    while (!estPhaseTermine()) {System.out.println("\n\n ------------------------------  La session de vote commence   ----------------------\n\n");
        Discussion discussion =new Discussion();
        forum.creerDiscussion(discussion);
                    
         for (int i = 0; i < gestionJoueur.longueurListe(); i++)
        {
        Joueur joueur = gestionJoueur.getListeJoueurs().get(i);  
        Message msg = new Message(" ", joueur);
        msg = joueur.EcrireMessage(historiqueMessage);
        discussion.ajouterMessage(msg);
        }

        discussion.afficherMessages();
        for (int i = 0; i < gestionJoueur.longueurListe(); i++) {
            Joueur joueur = gestionJoueur.getListeJoueurs().get(i);
            Vote vote = new Vote();
            vote.ajouterVote(joueur);  
            gestionVote.ajouterVote(joueur); 

        }

        System.out.println("\n -------------------  La session de vote est terminee  --------------");

        gestionVote.eliminerJoueurApresVote(gestionJoueur,elimination,this,gagnant);
        System.out.println("\n\n La liste des Joueurs restants \n\n");
        gestionJoueur.AffichageListeJoueurs();
        
        ArrayList<Joueur> joueursElimines=elimination.getJoueursElimines();
        elimination.AffichageListeJoueursElimine(joueursElimines);

        int nombreDeJoueurRestantCivil = 0;
        int nombreDeJoueurRestantUndercover = 0;
        int nombreDeJoueurRestantMrWhite = 0;

        for (Joueur joueur : gestionJoueur.getListeJoueurs()) {
            if (joueur.getRole().equalsIgnoreCase("Civile")) {
                nombreDeJoueurRestantCivil++;
            } else if (joueur.getRole().equalsIgnoreCase("Undercover")) {
                nombreDeJoueurRestantUndercover++;
            } else if (joueur.getRole().equalsIgnoreCase("MrWhite")) {
                nombreDeJoueurRestantMrWhite++;
            }
        }

        if (nombreDeJoueurRestantMrWhite == 0) {
            if (nombreDeJoueurRestantCivil >= nombreDeJoueurRestantUndercover) {
                System.out.println("Les Civils ont gagne !");
                gagnant.determinerGagnant(gestionJoueur.getListeJoueurs());
                score.attribuerScore(gagnant.getNom(), gestionJoueur.getListeJoueurs(), elimination.getJoueursElimines());
                this.terminerPhase();
                
            } else if (nombreDeJoueurRestantCivil < nombreDeJoueurRestantUndercover) {
                System.out.println("Les Undercover ont gagne !");
                gagnant.determinerGagnant(gestionJoueur.getListeJoueurs());
                score.attribuerScore(gagnant.getNom(), gestionJoueur.getListeJoueurs(), elimination.getJoueursElimines());
                this.terminerPhase();
                
                
            }
        } 
        else if ((nombreDeJoueurRestantMrWhite >= 1&&nombreDeJoueurRestantCivil==0&&nombreDeJoueurRestantUndercover==0) || (nombreDeJoueurRestantMrWhite >= 1&&nombreDeJoueurRestantCivil==1 &&nombreDeJoueurRestantUndercover==0) || (nombreDeJoueurRestantMrWhite >= 1&&nombreDeJoueurRestantCivil==0 &&nombreDeJoueurRestantUndercover==1) )
        {
            System.out.println("Les MrWhite ont gagne !");
            gagnant.determinerGagnant(gestionJoueur.getListeJoueurs());
            score.attribuerScore(gagnant.getNom(), gestionJoueur.getListeJoueurs(), elimination.getJoueursElimines());
            this.terminerPhase();
        }
        else if ((nombreDeJoueurRestantMrWhite >= 1)&&(this.phaseTermine!=true))
        {
            System.out.println("Le jeu continue, car Mr. White est encore en jeu.");
        }
        
    }
}

        public void terminerPhase() 
        {
            this.phaseTermine = true;
            System.out.println(" \n\n -------------------  La phase de vote est terminee  --------------\n\n ");
            System.out.println(" \n\n -------------------  Merci de Participer a Notre Jeu !  --------------\n\n ");
            
        }

        public boolean estPhaseTermine() 
        {
            return phaseTermine;
        }
  
}