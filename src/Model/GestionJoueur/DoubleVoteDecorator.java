package Model.GestionJoueur;

import Logging.LoggerSingleton;
import Model.Vote.Vote;

public class DoubleVoteDecorator extends JoueurDecorator {

    private boolean pouvoirUtilise = false;

    public DoubleVoteDecorator(Joueur joueur) {
        super(joueur);
        LoggerSingleton.getInstance().log("DECORATOR",
                "Joueur " + joueur.getNom() + " décoré avec DOUBLE VOTE");
    }

    @Override
    public void voter(Joueur cible) {
        if (!pouvoirUtilise) {
            System.out.println("🎯 " + getNom() + " utilise son DOUBLE VOTE !");

            // Premier vote
            super.voter(cible);
            // Deuxième vote
            super.voter(cible);

            pouvoirUtilise = true;
            LoggerSingleton.getInstance().log("DECORATOR",
                    "Double vote utilisé par " + getNom() + " sur " + cible.getNom() +
                            " (Total votes: " + cible.getNombreDeVotesRecus() + ")");
        } else {
            System.out.println("ℹ️ " + getNom() + " a déjà utilisé son double vote. Vote normal.");
            super.voter(cible);
        }
    }

    public boolean isPouvoirUtilise() {
        return pouvoirUtilise;
    }
    public boolean hasDoubleVote() {
        return !pouvoirUtilise;    // peut encore doubler son vote
    }
}