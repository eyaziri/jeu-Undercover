package Model.State;

import Model.Administration.GestionPartie;
import Model.Vote.PhaseVote;

public class VoteState implements GameState {

    @Override
    public void entrerEtat(GestionPartie partie) {
        System.out.println("\n=== PHASE DE VOTE ===");
    }

    @Override
    public void executerEtat(GestionPartie partie) {

        PhaseVote pv = new PhaseVote();
        pv.demarrerVote(partie.gestionJoueur, partie.forum, partie.historique,
                        partie.score, partie.elimination, partie.gagnant);

        partie.changerEtat(new GameOverState());
    }

    @Override
    public void sortirEtat(GestionPartie partie) {}
}
