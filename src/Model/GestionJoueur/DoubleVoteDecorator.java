package Model.GestionJoueur;

import Logging.LoggerSingleton;
import Model.Vote.Vote;

/**
 * Décorateur qui permet à un joueur de compter son vote double
 */
public class DoubleVoteDecorator extends JoueurDecorator {

    public DoubleVoteDecorator(Joueur joueur) {
        super(joueur);
        LoggerSingleton.getInstance().log("STATE", "Joueur " + joueur.getNom() + " décoré avec Double Vote");
    }

    /**
     * Ajoute deux votes au joueur ciblé
     */
    public void ajouterVote(Vote vote) {
        super.joueur.setnombreDeVotesRecus();
        super.joueur.setnombreDeVotesRecus();
        System.out.println(joueur.getNom() + " utilise un DOUBLE VOTE !");
        LoggerSingleton.getInstance().log("STATE", joueur.getNom() + " a utilisé Double Vote");
    }
}
