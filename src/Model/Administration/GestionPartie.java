package Model.Administration;

import Logging.LoggerSingleton;
import Model.Forum.Forum;
import Model.Forum.HistoriqueMessage;
import Model.GestionJoueur.Gagnant;
import Model.GestionJoueur.Score;
import Model.State.GameState;
import Model.State.MenuState;
import Model.Vote.Elimination;

public class GestionPartie {

    private GameState etatCourant;
    
    // toutes tes données utiles
    public Admin admin = new Admin();
    public GestionJoueur gestionJoueur = new GestionJoueur();
    public Elimination elimination = new Elimination();
    public Gagnant gagnant = new Gagnant();
    public HistoriqueMessage historique = new HistoriqueMessage();
    public Forum forum = new Forum();
    public Score score = new Score();

    public String motCorrect;

    public GestionPartie() {
        this.etatCourant = new MenuState(); // état initial
    }

    public void changerEtat(GameState nouvelEtat) {
        if (etatCourant != null) {
            etatCourant.sortirEtat(this);
        }

        LoggerSingleton.getInstance().log(
            "STATE",
            etatCourant.getClass().getSimpleName() + " -> " + nouvelEtat.getClass().getSimpleName()
        );

        etatCourant = nouvelEtat;
        etatCourant.entrerEtat(this);
    }

    public void demarrer() {
        etatCourant.entrerEtat(this);
        etatCourant.executerEtat(this);
    }
}
