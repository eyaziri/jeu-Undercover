package Model.Administration;

import Model.GestionJoueur.Joueur;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;
import java.util.Random;
import Logging.LoggerSingleton;

/**
 * Classe pour gérer les couples de mots entre Civile et Undercover
 */
public record ListeMots(List<Map.Entry<String, String>> mots, Random rand) {

    public ListeMots() {
        this(
                List.of(
                        new SimpleEntry<>("Jus", "Soda")
                        // Tu peux ajouter d'autres couples ici
                ),
                new Random()
        );
    }

    /**
     * Associe un mot au joueur selon son rôle
     */
    public void associerMotDeCivilEtDeUndercover(Joueur joueur) {
        int index = rand.nextInt(mots.size());
        Map.Entry<String, String> coupleAleatoire = mots.get(index);

        String motCivil = coupleAleatoire.getKey();
        String motUndercover = coupleAleatoire.getValue();

        if (joueur.getRole().equalsIgnoreCase("Civile")) {
            joueur.setMot(motCivil);
            LoggerSingleton.getInstance().log("MOTS", "Mot attribué au Civile " + joueur.getNom() + ": " + motCivil);
        } else if (joueur.getRole().equalsIgnoreCase("Undercover")) {
            joueur.setMot(motUndercover);
            LoggerSingleton.getInstance().log("MOTS", "Mot attribué à l'Undercover " + joueur.getNom() + ": " + motUndercover);
        }
    }

    /**
     * Récupère le mot associé à l'Undercover à partir du mot Civil
     */
    public String getMotUndercover(String motCivil) {
        for (Map.Entry<String, String> entry : mots) {
            if (entry.getKey().equalsIgnoreCase(motCivil)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Récupère le mot associé au Civil à partir du mot Undercover
     */
    public String getMotCivil(String motUndercover) {
        for (Map.Entry<String, String> entry : mots) {
            if (entry.getValue().equalsIgnoreCase(motUndercover)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
