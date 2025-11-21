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
        System.out.println(joueur.getNom() + " est immunisé et ne peut pas être éliminé !");
        LoggerSingleton.getInstance().log("STATE",
                "Élimination annulée grâce à l'immunité pour " + joueur.getNom());
    }

}
