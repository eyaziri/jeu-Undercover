/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.GestionJoueur;

/**
 *
 * @author eyazi
 */

import Model.Administration.Role;
import java.util.ArrayList;



public final class Gagnant extends Joueur {
    private ArrayList<Joueur> gagnants; 
    String role1;

    public Gagnant() {
        super();
        this.gagnants = new ArrayList<>();
        
    }
    public String getRole1()
    {
        role1=gagnants.get(0).getRole();
        return role1;
    }
    
    public void ajouterGagnant(Joueur joueur) {
        if (joueur != null) {
            gagnants.add(joueur);
        }
    }


    public void determinerGagnant(ArrayList<Joueur> joueurs) {
        int undercoverCount = 0;
        int civilCount = 0;
        int mrsWhiteCount = 0;
        boolean mrsWhiteEnVie = false;

        for (Joueur joueur : joueurs) {
            if (joueur.getRole().equalsIgnoreCase(" Undercover")) {
                undercoverCount++;
            } else if (joueur.getRole().equalsIgnoreCase("Civile")) {
                civilCount++;
            } else if (joueur.getRole().equalsIgnoreCase("MrWhite")) {
                mrsWhiteCount++;
                mrsWhiteEnVie = true;
            }
        }

      
        if (undercoverCount == 0 && civilCount > 0 && !mrsWhiteEnVie) {
            for (Joueur joueur : joueurs) {
                if (joueur.getRole().equalsIgnoreCase("Civile")) {
                    gagnants.add(joueur); 
                }
            }
        }
        if (undercoverCount == 1 && civilCount >= 1 && !mrsWhiteEnVie) {
            for (Joueur joueur : joueurs) {
                if (joueur.getRole().equalsIgnoreCase("Civile")) {
                    gagnants.add(joueur); 
                }
            }
        }
        else if (undercoverCount > 0 && civilCount == 0 && !mrsWhiteEnVie) {
            for (Joueur joueur : joueurs) {
                if (joueur.getRole().equalsIgnoreCase(" Undercover")) {
                    gagnants.add(joueur); 
                }
            }
        }
        else if (mrsWhiteEnVie && undercoverCount == 0 && civilCount <= mrsWhiteCount) {
            for (Joueur joueur : joueurs) {
                if (joueur.getRole().equalsIgnoreCase("MrWhite")) {
                    gagnants.add(joueur); 
                }
            }
        }
        else if (mrsWhiteEnVie && civilCount == 0 && undercoverCount <= mrsWhiteCount) {
            for (Joueur joueur : joueurs) {
                if (joueur.getRole().equalsIgnoreCase("MrWhite")) {
                    gagnants.add(joueur); 
                }
            }
        }
    }

    // Get all winners as a list
    public ArrayList<Joueur> getGagnants() {
        return gagnants;
    }

    // Display the winners
    public void afficherGagnant() {
        if (gagnants.isEmpty()) {
            System.out.println("Il n'y a pas de gagnant pour cette partie.");
        } else {
            System.out.print("🎉 Les gagnants de cette partie sont : ");
            for (int i = 0; i < gagnants.size(); i++) {
                Joueur gagnant = gagnants.get(i);
                gagnant.setScore(6);
                System.out.print(gagnant.getNom() );
                if (i < gagnants.size() - 1) {
                    System.out.print(", ");
                }
            }
            
            System.out.println(); // Newline after winners
        }
    }
}
