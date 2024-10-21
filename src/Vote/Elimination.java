/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vote;


import GestionJoueur.Joueur;
import java.util.ArrayList;
import java.util.List;

public class Elimination {
    private ArrayList<Joueur> joueursElimines = new ArrayList<>();
    

    public ArrayList<Joueur> obtenirJoueursElimines() {
        return joueursElimines;
    }
    
    
    public Joueur obtenirDernierJoueurElimine() {
        if (!joueursElimines.isEmpty()) {
            return joueursElimines.get(joueursElimines.size() - 1);
        }
        return null; // Aucun joueur éliminé
    }
    
     public void ajouterJoueurElimine(Joueur joueur) {
        joueursElimines.add(joueur);
    }

    public List<Joueur> getJoueursElimines() {
        return joueursElimines;
    }
}

