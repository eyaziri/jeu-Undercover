package Model.State;

import Logging.LoggerSingleton;
import Model.Administration.GestionPartie;
import Model.Vote.PhaseVote;

public class VoteState implements GameState {

    @Override
    public void entrerEtat(GestionPartie partie) {
        System.out.println("\n=== PHASE DE VOTE ===");
        LoggerSingleton.getInstance().log("STATE", "Entrée dans l'état VoteState");
    }

    @Override
    public void executerEtat(GestionPartie partie) {

        LoggerSingleton.getInstance().log("STATE", "Exécution de l'état VoteState");

        PhaseVote pv = new PhaseVote();

        pv.demarrerVote(
                partie.gestionJoueur,
                partie.forum,
                partie.historique,
                partie.score,
                partie.elimination,
                partie.gagnant
        );

        // SI LA PHASE DE VOTE EST TERMINÉE → GameOverState
        if (pv.estPhaseTermine()) {
            LoggerSingleton.getInstance().log("STATE", "Phase de vote terminée -> GameOverState");
            partie.changerEtat(new GameOverState());
        }
        // SINON → un nouveau tour de vote
        else {
            LoggerSingleton.getInstance().log("STATE", "Phase de vote continue -> nouveau VoteState");
            partie.changerEtat(new VoteState());
        }
    }

    @Override
    public void sortirEtat(GestionPartie partie) {
        LoggerSingleton.getInstance().log("STATE", "Sortie de l'état VoteState");
    }
}
