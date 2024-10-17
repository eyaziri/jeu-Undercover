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
    private GestionVotes gestionVotes;
    
    public Vote() {}
     
    public String getJoueurCible()
    {
        return this.joueurCible.getNom();
    }
     
    public String getJoueurVotant()
    {
        return this.joueurVotant.getNom();
    }
    
    public void ajouterVote() {
        System.out.println("Choisir le joueur que vous croyez être Mr. White : ");
        Scanner sc = new Scanner(System.in);
        String nom = sc.nextLine();

        Joueur joueurTrouve = trouverJoueurParNom(nom);

        if (joueurTrouve != null) {
            joueurTrouve.setnombreDeVotesRecus();
            System.out.println("Le joueur " + joueurTrouve.getNom() + " a reçu un vote.");
        } else {
            System.out.println("Joueur non trouvé.");
        }
    }
    
    public Joueur trouverJoueurParNom(String nom) {
        for (Joueur joueur : GestionJoueur.getJoueurs()) {
            if (joueur.getNom().equalsIgnoreCase(nom)) {
                return joueur;
            }
        }
        return null;
    }
            
    
}