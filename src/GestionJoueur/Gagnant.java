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
    
    private static final String ROLE_UNDERCOVER = "Undercover";
    private static final String ROLE_CIVILE = "Civile";
    private static final String ROLE_MR_WHITE = "MrWhite";
    
    public Gagnant() {
        super();
    }

    public Gagnant determinerGagnant(ArrayList<Joueur> joueurs) {
        int undercoverCount = 0;
        int civilCount = 0;
        int mrsWhiteCount = 0;
        boolean mrsWhiteEnVie = false;

      
        for (Joueur joueur : joueurs) {
            String role = joueur.getRole();
            if (role.equalsIgnoreCase(ROLE_UNDERCOVER)) {
                undercoverCount++;
            } else if (role.equalsIgnoreCase(ROLE_CIVILE)) {
                civilCount++;
            } else if (role.equalsIgnoreCase(ROLE_MR_WHITE)) {
                mrsWhiteCount++;
                mrsWhiteEnVie = true;
            }
        }
        Gagnant gagnant = new Gagnant();

        if (undercoverCount == 0 && civilCount > 0) {
           
            for (Joueur joueur : joueurs) {
                if (joueur.getRole().equalsIgnoreCase(ROLE_CIVILE)) {
                    
                    gagnant.setNom(joueur.getNom());
                    break;
                }
            }
            return gagnant;
           
            
        } else if (undercoverCount > 0 && civilCount == 0 && !mrsWhiteEnVie) {
           
            for (Joueur joueur : joueurs) {
                if (joueur.getRole().equalsIgnoreCase(ROLE_UNDERCOVER)) {
                    
                    gagnant.setNom(joueur.getNom());
                    break;
                }
            }
            return gagnant;
        } else if (mrsWhiteEnVie && undercoverCount == 0 && civilCount <= mrsWhiteCount) {
           
            for (Joueur joueur : joueurs) {
                if (joueur.getRole().equalsIgnoreCase(ROLE_MR_WHITE)) {
                    
                   gagnant.setNom(joueur.getNom());
                    break;
                }
            }
            return gagnant;
        } 
         return null;
    }


    public String getGagnant() {
        return this.getNom();
    }

    public void afficherGagnant() {
        if (this.getNom() != null && !this.getNom().isEmpty()) {
            System.out.println("Le gagnant de cette partie est : " + this.getNom());
        } else {
            System.out.println("Aucun gagnant n'a ete determine pour cette partie.");
        }
    }
}
