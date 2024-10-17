/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Administration;

import GestionJoueur.Joueur;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author eyazi
 */
public class GestionRole {
    
    private Map<String, String> classification;
   
     public GestionRole() {
        this.classification = new HashMap<>();
    }

   
    public void ajouterJoueurRole(String nom, String role) {
        classification.put(nom, role); //hedhi chinlass9ouha maa ajouter joueur
    }

    public String obtenirRole(String nom) {
        return classification.get(nom); 
    }

    public void supprimerJoueur(String nom) {
        if (classification.containsKey(nom)) {
            classification.remove(nom);
            System.out.println(nom + " a été supprimé de la partie.");
        } else {
            System.out.println("Le joueur " + nom + " n'existe pas.");
        }
    }

    public void afficherJoueurs() {
        System.out.println("Liste des joueurs et leurs rôles :");
        for (Map.Entry<String, String> entry : classification.entrySet()) {
            System.out.println("Joueur : " + entry.getKey());
        }
    }
}
   
