/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Vote;

/**
 *
 * @author eyazi
 */

import Logging.LoggerSingleton;
import Model.Administration.GestionJoueur;
import Model.GestionJoueur.Joueur;
import java.util.ArrayList;
import java.util.Scanner;

public class Vote {
    private Joueur joueurCible;
    private Joueur joueurVotant;
    private GestionVotes gestionVotes;

    public Vote() {}

    public String getJoueurCible() {
        return this.joueurCible.getNom();
    }

    public String getJoueurVotant() {
        return this.joueurVotant.getNom();
    }

    public void setJoueurVotant(Joueur joueur) {
        this.joueurVotant = joueur;
        LoggerSingleton.getInstance().log("STATE", "Joueur votant défini: " + joueur.getNom());
    }

    public void ajouterVote(Joueur joueur) {
        LoggerSingleton.getInstance().log("STATE", "Début méthode ajouterVote pour " + joueur.getNom());

        System.out.println("\n" + joueur.getNom() + ", choisissez le joueur que vous croyez être Mr. White : ");
        Scanner sc = new Scanner(System.in);
        Joueur joueurTrouve = null;

        while (joueurTrouve == null) { // Boucle jusqu'à ce qu'un joueur valide soit trouvé
            String nom = sc.nextLine();
            LoggerSingleton.getInstance().log("STATE", "Saisie utilisateur: " + nom);

            joueurTrouve = trouverJoueurParNom(nom);

            if (joueurTrouve != null) {
                joueurTrouve.setnombreDeVotesRecus();
                System.out.println("Le joueur " + joueurTrouve.getNom() + " a reçu un vote.");
                LoggerSingleton.getInstance().log("STATE", "Vote ajouté pour: " + joueurTrouve.getNom());
            } else {
                System.out.println("Joueur non trouvé. Veuillez saisir un nom valide : ");
                LoggerSingleton.getInstance().log("STATE", "Joueur non trouvé: " + nom);
            }
        }
    }

    public Joueur trouverJoueurParNom(String nom) {
        LoggerSingleton.getInstance().log("STATE", "Recherche du joueur: " + nom);
        for (Joueur joueur : GestionJoueur.getListeJoueurs()) {
            if (joueur.getNom().equalsIgnoreCase(nom)) {
                LoggerSingleton.getInstance().log("STATE", "Joueur trouvé: " + joueur.getNom());
                return joueur;
            }
        }
        LoggerSingleton.getInstance().log("STATE", "Aucun joueur trouvé pour: " + nom);
        return null;
    }
}