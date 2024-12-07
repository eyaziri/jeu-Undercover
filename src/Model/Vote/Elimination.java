/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Vote;



import Model.GestionJoueur.Joueur;
import java.util.ArrayList;
import java.util.List;

public record Elimination(List<Joueur> joueursElimines) {

    // Constructeur par défaut qui initialise la liste des joueurs éliminés
    public Elimination() {
        this(new ArrayList<>());
    }
    public Joueur obtenirDernierJoueurElimine() {
        if (!joueursElimines.isEmpty()) {
            return joueursElimines.get(joueursElimines.size() - 1);
        }
        return null; 
    }

    public ArrayList<Joueur> getJoueursElimines() {
        return new ArrayList<>(joueursElimines);
    }

    public void ajouterJoueurElimine(Joueur joueur) {
        if (joueur != null) {
            joueursElimines.add(joueur);
        } else {
            System.out.println("Impossible d'ajouter un joueur null.");
        }
    }
    public void supprimerJoueur(Joueur joueur) 
        {
            joueursElimines.remove(joueur);
        }

    public void AffichageListeJoueursElimine(ArrayList<Joueur> joueursElimines) {
        if (joueursElimines.isEmpty()) {
            System.out.println("");
            return;
        }

        System.out.println("Liste des joueurs éliminés :");
        for (Joueur joueur : joueursElimines) {
            System.out.println( joueur.getNom());
        }
    }
}

