/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vote;

/**
 *
 * @author eyazi
 */
import Administration.GestionJoueur;
import GestionJoueur.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Vote {
    private Joueur joueurCible;
    private Joueur joueurVotant;
    
    public Vote() {}
     
    public String getJoueurCible()
    {
        return this.joueurCible.getNom();
    }
     
    public String getJoueurVotant()
    {
        return this.joueurVotant.getNom();
    }
    
     public void ajouterVote(Joueur joueur) {
        System.out.println("\n" + joueur.getNom() + ", choisissez le joueur que vous croyez etre Mr. White : ");
        Scanner sc = new Scanner(System.in);
        Joueur joueurTrouve = null;

        while (joueurTrouve == null) { 
            String nom = sc.nextLine();
            joueurTrouve = trouverJoueurParNom(nom);

            if (joueurTrouve != null) {
                joueurTrouve.setnombreDeVotesRecus();
                System.out.println("Le joueur " + joueurTrouve.getNom() + " a recu un vote.");
            } else {
                System.out.println("Joueur non trouve. Veuillez saisir un nom valide : ");
            }
        }
    }
    
    public Joueur trouverJoueurParNom(String nom) {
        for (Joueur joueur : GestionJoueur.getListeJoueurs()) {
            if (joueur.getNom().equalsIgnoreCase(nom)) {
                return joueur;
            }
        }
        return null;
    }
            
    
}