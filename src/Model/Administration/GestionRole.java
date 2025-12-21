package Model.Administration;

import java.util.HashMap;
import java.util.Map;
import Logging.LoggerSingleton;

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
        if (classification.containsKey(nom)) {
            System.out.println("Le joueur " + nom + " existe déjà avec le rôle " + classification.get(nom));
            LoggerSingleton.getInstance().log("ROLE", "Tentative d'ajout joueur existant : " + nom);
        } else {
            classification.put(nom, role);
            System.out.println("Joueur " + nom + " ajouté avec le rôle " + role);
            LoggerSingleton.getInstance().log("ROLE", "Joueur ajouté : " + nom + " | Rôle : " + role);
        }
    }

    public String obtenirRole(String nom) {
        String role = classification.get(nom);
        LoggerSingleton.getInstance().log("ROLE", "Rôle obtenu pour " + nom + " : " + role);
        return role;
    }

    public void supprimerJoueur(String nom) {
        if (classification.containsKey(nom)) {
            classification.remove(nom);
            System.out.println(nom + " a été supprimé de la partie.");
            LoggerSingleton.getInstance().log("ROLE", "Joueur supprimé : " + nom);
        } else {
            System.out.println("Le joueur " + nom + " n'existe pas.");
            LoggerSingleton.getInstance().log("ROLE", "Tentative de suppression joueur inexistant : " + nom);
        }
    }

    public void afficherJoueurs() {
        System.out.println("Liste des joueurs et leurs rôles :");
        for (Map.Entry<String, String> entry : classification.entrySet()) {
            System.out.println("Joueur : " + entry.getKey() + ", Rôle : " + entry.getValue());
        }
        LoggerSingleton.getInstance().log("ROLE", "Affichage de tous les joueurs et rôles");
    }
}