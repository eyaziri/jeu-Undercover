package Model.GestionJoueur;

import Logging.LoggerSingleton;

/**
 * Décorateur qui rend un joueur immunisé contre l'élimination
 */
public class ImmuniteDecorator extends JoueurDecorator {

    public ImmuniteDecorator(Joueur joueur) {
        super(joueur);
        LoggerSingleton.getInstance().log("STATE", "Joueur " + joueur.getNom() + " décoré avec Immunité");
    }

    @Override
    public void setEstVivant() {
        System.out.println(joueur.getNom() + " est immunisé : ne peut pas être éliminé !");
        LoggerSingleton.getInstance().log("STATE", "Tentative d'élimination de " + joueur.getNom() + " bloquée par Immunité");
        // ne change pas l'état
    }
}
