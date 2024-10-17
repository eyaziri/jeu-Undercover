/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vote;

import Administration.GestionJoueur;
import GestionJoueur.Joueur;
import java.util.Scanner;

/**
 *
 * @author eyazi
 */
public class PhaseVote {
    
    boolean phaseTermine;
    
    public void demarrerVote() {
        System.out.println(" ********* La session de vote commence ********* ");
        boolean voteEnCours = true;

        while (voteEnCours) {
            Vote vote = new Vote();
            vote.ajouterVote();

            if (tousLesJoueursOntVote()) {
                terminerPhase();
                voteEnCours = false;
            }
        }
    }

    private boolean tousLesJoueursOntVote() {
        for (Joueur joueur : GestionJoueur.getJoueurs()) {
            if (!joueur.isAVote()) {
                return false; 
            }
        }
        return true;
    }
     
     
     public void terminerPhase() {
        this.phaseTermine = true;
        System.out.println(" ********* La session de vote est terminée ********* ");
    }
     public boolean estPhaseTermine() {
        return phaseTermine;
    }

}
