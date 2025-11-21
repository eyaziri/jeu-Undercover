package Model.GestionJoueur;

import Logging.LoggerSingleton;
import Model.Vote.Vote;

public class DoubleVoteDecorator extends JoueurDecorator {

    public DoubleVoteDecorator(Joueur joueur) {
        super(joueur);
        LoggerSingleton.getInstance().log("DECORATOR", "Joueur " + joueur.getNom() + " décoré avec Double Vote");
    }

    @Override
    public void voter(Joueur cible) {
        System.out.println("🎯 " + joueur.getNom() + " utilise un DOUBLE VOTE !");

        // Premier vote normal
        super.voter(cible);
        // Deuxième vote (double)
        super.voter(cible);

        LoggerSingleton.getInstance().log("DECORATOR",
                "Double vote appliqué par " + joueur.getNom() + " sur " + cible.getNom() +
                        " (Total votes: " + cible.getNombreDeVotesRecus() + ")");
    }

    // S'assurer que toutes les méthodes délèguent au joueur décoré
    @Override
    public String getNom() {
        return joueur.getNom();
    }

    @Override
    public String getRole() {
        return joueur.getRole();
    }

    @Override
    public boolean isVivant() {
        return joueur.isVivant();
    }

    @Override
    public void setEstVivant() {
        joueur.setEstVivant();
    }
}