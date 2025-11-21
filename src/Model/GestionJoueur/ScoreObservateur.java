package Model.GestionJoueur;

import Model.Forum.Message;
import Model.Forum.Observateur;

public class ScoreObservateur implements Observateur {

    @Override
    public void update(Message msg) {
        System.out.println("Score : " + msg.getAuteur().getNom() + " reçoit 1 point pour activité !");
        msg.getAuteur().setScore(msg.getAuteur().getScore() + 1);
    }
}
