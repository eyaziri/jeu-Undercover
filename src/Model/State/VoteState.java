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
        pv.demarrerVote(partie.gestionJoueur, partie.forum, partie.historique,
                partie.score, partie.elimination, partie.gagnant);

        LoggerSingleton.getInstance().log("STATE", "Fin de PhaseVote, changement vers GameOverState");
        partie.changerEtat(new GameOverState());
    }

    @Override
    public void sortirEtat(GestionPartie partie) {
        LoggerSingleton.getInstance().log("STATE", "Sortie de l'état VoteState");
    }
}
