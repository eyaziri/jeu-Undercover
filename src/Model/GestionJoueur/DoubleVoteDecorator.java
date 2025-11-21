package Model.GestionJoueur;

import Model.Vote.Vote;

public class DoubleVoteDecorator extends JoueurDecorator {

    public DoubleVoteDecorator(Joueur joueur) {
        super(joueur);
    }

    public void ajouterVote(Vote vote) {
        super.joueur.setnombreDeVotesRecus();
        super.joueur.setnombreDeVotesRecus();
        System.out.println(joueur.getNom() + " utilise un DOUBLE VOTE !");
    }
}
