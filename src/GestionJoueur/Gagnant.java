/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestionJoueur;

/**
 *
 * @author eyazi
 */

import java.util.List;


public class Gagnant {
    private String gagnant;

    // Constructeur
    public Gagnant() {
        this.gagnant = null;
    }

    // Déterminer le gagnant selon les règles du jeu Undercover
    public void determinerGagnant(List<Joueur> joueursRestants) {
        int undercoverCount = 0;
        int civilCount = 0;
        int mrsWhiteCount = 0;

        boolean mrsWhiteEnVie = false;

        // Parcourir les joueurs restants et compter les rôles
        for (Joueur joueur : joueursRestants) {
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

        // Vérifier les conditions de victoire
        if (undercoverCount == 0 && civilCount > 0) {
            this.gagnant = "Civils";
            System.out.println("Les Civils ont gagné !");
        } else if (undercoverCount > 0 && civilCount == 0 && !mrsWhiteEnVie) {
            this.gagnant = "Undercover";
            System.out.println("Les Undercover ont gagné !");
        } else if (mrsWhiteEnVie && undercoverCount == 0 && civilCount <= mrsWhiteCount) {
            this.gagnant = "Mrs. White";
            System.out.println("Mrs. White a gagné !");
        } else {
            System.out.println("Le jeu est encore en cours.");
        }
    }

    // Obtenir le nom du gagnant
    public String getGagnant() {
        return gagnant;
    }

    // Réinitialiser le gagnant pour une nouvelle partie
    public void reinitialiserGagnant() {
        this.gagnant = null;
        System.out.println("Le gagnant a été réinitialisé.");
    }
}
