/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Administration;

import java.util.ArrayList;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.Random;
import GestionJoueur.*;


/**
 *
 * @author eyazi
 */
public class ListeMots {
   private ArrayList<Map.Entry<String, String>> mots;
   private Random rand;
    
    public ListeMots() {
        mots = new ArrayList<>();  // Initialisation de l'ArrayList
        
        // Ajout de couples (clé-valeur) dans l'ArrayList
        mots.add(new SimpleEntry<>("lait", "boisson"));
        mots.add(new SimpleEntry<>("eau", "liquide"));
        mots.add(new SimpleEntry<>("ciel", "bleu"));
        mots.add(new SimpleEntry<>("glace", "beurre"));
        mots.add(new SimpleEntry<>("Lune", "etoile"));
        mots.add(new SimpleEntry<>("maison", "bâtiment"));
        mots.add(new SimpleEntry<>("Livre", "Page"));
        mots.add(new SimpleEntry<>("Montre", "temps"));
        rand = new Random();
    }
    
    
    public void associerMotDeCivilEtDeUndercover(Civil civil, Undercover undercover) {
        int index = rand.nextInt(mots.size());
        Map.Entry<String, String> coupleAleatoire = mots.get(index); 

        civil.setMot(coupleAleatoire.getKey()); 
        undercover.setMot(coupleAleatoire.getValue()); 
    }   
    
}
