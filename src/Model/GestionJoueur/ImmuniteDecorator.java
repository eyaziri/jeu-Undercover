package Model.GestionJoueur;

import Logging.LoggerSingleton;

public class ImmuniteDecorator extends JoueurDecorator {

    public ImmuniteDecorator(Joueur joueur) {
        super(joueur);
        LoggerSingleton.getInstance().log("DECORATOR",
                "Joueur " + joueur.getNom() + " décoré avec Immunité");
    }

    @Override
    public void setEstVivant() {
        // Ne rien faire - le joueur est immunisé
        System.out.println("🛡️ " + joueur.getNom() + " est immunisé et ne peut pas être éliminé !");
        LoggerSingleton.getInstance().log("DECORATOR",
                "Élimination annulée grâce à l'immunité pour " + joueur.getNom());
    }

    @Override
    public boolean isVivant() {
        // Toujours vivant grâce à l'immunité
        return true;
    }

    // Délégation des autres méthodes
    @Override
    public String getNom() {
        return joueur.getNom();
    }

    @Override
    public String getRole() {
        return joueur.getRole();
    }

    @Override
    public void voter(Joueur cible) {
        joueur.voter(cible);
    }
}
