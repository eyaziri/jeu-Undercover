package Model.GestionJoueur;

import Logging.LoggerSingleton;

/**
 * Classe abstraite de décorateur pour Joueur
 */
public abstract non-sealed class JoueurDecorator extends Joueur {

    protected Joueur joueur;

    public JoueurDecorator(Joueur joueur) {
        this.joueur = joueur;
        LoggerSingleton.getInstance().log("STATE", "Création de JoueurDecorator pour: " + joueur.getNom());
    }

    @Override
    public String getNom() {
        LoggerSingleton.getInstance().log("STATE", "getNom() appelé sur Decorator: " + joueur.getNom());
        return joueur.getNom();
    }

    @Override
    public String getRole() {
        LoggerSingleton.getInstance().log("STATE", "getRole() appelé sur Decorator: " + joueur.getRole());
        return joueur.getRole();
    }

    @Override
    public String getMot() {
        LoggerSingleton.getInstance().log("STATE", "getMot() appelé sur Decorator: " + joueur.getMot());
        return joueur.getMot();
    }

    @Override
    public int getScore() {
        LoggerSingleton.getInstance().log("STATE", "getScore() appelé sur Decorator: " + joueur.getScore());
        return joueur.getScore();
    }

    @Override
    public void voter(Joueur cible) {
        joueur.voter(cible);
    }

    // Tu peux déléguer d'autres méthodes de Joueur ici et ajouter des logs similaires
}
