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
import java.util.List;


public class Gagnant {
    private String gagnant;

    public Gagnant() {
        this.gagnant = null;
    }


    public void determinerGagnant(ArrayList<Joueur> joueursElimines) {
        int undercoverCount = 0;
        int civilCount = 0;
        int mrsWhiteCount = 0;

        boolean mrsWhiteEnVie = false;

        for (Joueur joueur : joueursElimines) {
            if (joueur instanceof Undercover) {
                undercoverCount++;
            } else if (joueur instanceof Civil) {
                civilCount++;
            }
            else if (joueur instanceof MrWhite) {
                mrsWhiteCount++;
            }
            else if (joueur instanceof MrWhite) {
                mrsWhiteEnVie = true;
            }
        }

        if (undercoverCount == 0 && civilCount > 0) {
            this.gagnant = "Civils";
            System.out.println("Les Civils ont gagne !");
        } else if (undercoverCount > 0 && civilCount == 0 && !mrsWhiteEnVie) {
            this.gagnant = "Undercover";
            System.out.println("Les Undercover ont gagne !");
        } else if (mrsWhiteEnVie && undercoverCount == 0 && civilCount <= mrsWhiteCount) {
            this.gagnant = "Mrs. White";
            System.out.println("Mr. White a gagne !");
        } else {
            System.out.println("Le jeu est encore en cours.");
        }
    }

    public String getGagnant() {
        return gagnant;
    }

    public void reinitialiserGagnant() {
        this.gagnant = null;
        System.out.println("Le gagnant a ete renitialise.");
    }
    
    public void afficherGagnant() {
        if (gagnant != null) {
            System.out.println("Le gagnant de cette partie est : " + gagnant);
        } else {
            System.out.println("Il n'y a pas encore de gagnant.");
        }
    }
}
