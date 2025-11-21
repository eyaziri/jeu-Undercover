package Model.State;

import Model.Administration.GestionPartie;

public interface GameState {
    void entrerEtat(GestionPartie partie);
    void executerEtat(GestionPartie partie);
    void sortirEtat(GestionPartie partie);
}
