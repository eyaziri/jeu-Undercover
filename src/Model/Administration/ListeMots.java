/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Administration;

import Model.GestionJoueur.Joueur;
import java.util.ArrayList;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;
import java.util.Random;

public record ListeMots(List<Map.Entry<String, String>> mots, Random rand) {
    
            public ListeMots() {
        this(
            List.of(
                /*new SimpleEntry<>("lait", "boisson"),
                new SimpleEntry<>("eau", "liquide"),
                new SimpleEntry<>("ciel", "bleu"),
                new SimpleEntry<>("glace", "beurre"),
                new SimpleEntry<>("Lune", "etoile"),
                new SimpleEntry<>("maison", "batiment"),
                new SimpleEntry<>("Livre", "Page"),*/
                new SimpleEntry<>("Jus", "Soda")
            ),
            new Random()
        );
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