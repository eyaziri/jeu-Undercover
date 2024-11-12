/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestionJoueur;

/**
 *
 * @author eyazi
 */

import java.util.ArrayList;



public class Gagnant extends Joueur {
    
    public Gagnant() {
        super();
    }

    public void determinerGagnant(ArrayList<Joueur> joueurs) {
        int undercoverCount = 0;
        int civilCount = 0;
        int mrsWhiteCount = 0;
        boolean mrsWhiteEnVie = false;

        for (Joueur joueur : joueurs) {
            if (joueur instanceof Undercover) {
                undercoverCount++;
            } else if (joueur instanceof Civil) {
                civilCount++;
            } else if (joueur instanceof MrWhite) {
                mrsWhiteCount++;
                mrsWhiteEnVie = true;
            }
        }

        if (undercoverCount == 0 && civilCount > 0) {
           
            for (Joueur joueur : joueurs) {
                if (joueur.getRole().equalsIgnoreCase("Civile")) {
                    this.setNom(joueur.getNom());
                    break;
                }
            }
        } else if (undercoverCount > 0 && civilCount == 0 && !mrsWhiteEnVie) {
            
            for (Joueur joueur : joueurs) {
                if (joueur.getRole().equalsIgnoreCase("Undercover")) {
                    this.setNom(joueur.getNom());
                    break;
                }
            }
        } else if (mrsWhiteEnVie && undercoverCount == 0 && civilCount <= mrsWhiteCount) {
      
            for (Joueur joueur : joueurs) {
                if (joueur.getRole().equalsIgnoreCase("MrWhite")) {
                    this.setNom(joueur.getNom());
                    break;
                }
            }
        } 
    }

    public String getGagnant() {
        return this.getNom();
    }

    public void afficherGagnant() {
        System.out.println("Le gagnant de cette partie est : " + this.getNom());
    }
}
