package Model.GestionJoueur;

import Logging.LoggerSingleton;
import Model.Forum.Message;
import Model.Forum.Observateur;

public class ScoreObservateur implements Observateur {

    @Override
    public void update(Message msg) {
        Joueur auteur = msg.getAuteur();
        auteur.setScore(auteur.getScore() + 1);

        System.out.println("Score : " + auteur.getNom() + " reçoit 1 point pour activité !");
        LoggerSingleton.getInstance().log(
                "STATE",
                "Score mis à jour pour " + auteur.getNom() + " -> Nouveau score: " + auteur.getScore()
        );
    }
}
