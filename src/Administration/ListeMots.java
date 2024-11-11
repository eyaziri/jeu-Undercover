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
                 mots = new ArrayList<>();  // Initialize the ArrayList

                 // Add key-value pairs to the ArrayList
                 mots.add(new SimpleEntry<>("lait", "boisson"));
                 mots.add(new SimpleEntry<>("eau", "liquide"));
                 mots.add(new SimpleEntry<>("ciel", "bleu"));
                 mots.add(new SimpleEntry<>("glace", "beurre"));
                 mots.add(new SimpleEntry<>("Lune", "etoile"));
                 mots.add(new SimpleEntry<>("maison", "batiment"));
                 mots.add(new SimpleEntry<>("Livre", "Page"));
                 mots.add(new SimpleEntry<>("Montre", "temps"));
                 rand = new Random();
             }

            
             public void associerMotDeCivilEtDeUndercover(Joueur joueur) {
                 int index = rand.nextInt(mots.size());
                 Map.Entry<String, String> coupleAleatoire = mots.get(index);

                 String motCivil = coupleAleatoire.getKey();
                 String motUndercover = coupleAleatoire.getValue();

                 if (joueur.getRole().equalsIgnoreCase("Civile")) {
                     joueur.setMot(motCivil);
                 } else if (joueur.getRole().equalsIgnoreCase("Undercover")) {
                     joueur.setMot(motUndercover);
                 }
             }

     
             public String getMotUndercover(String motCivil) {
                 for (Map.Entry<String, String> entry : mots) {
                     if (entry.getKey().equalsIgnoreCase(motCivil)) {
                         return entry.getValue();
                     }
                 }
                 return null;
             }

             public String getMotCivil(String motUndercover) {
                 for (Map.Entry<String, String> entry : mots) {
                     if (entry.getValue().equalsIgnoreCase(motUndercover)) 
                     { 
                         return entry.getKey();
                     }
                 }
                 return null;
             }
         }