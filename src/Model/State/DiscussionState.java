package Model.State;

import Logging.LoggerSingleton;
import Model.Administration.GestionPartie;
import Model.Forum.Discussion;
import Model.Forum.Message;
import Model.GestionJoueur.Joueur;

public class DiscussionState implements GameState {

    @Override
    public void entrerEtat(GestionPartie partie) {
        System.out.println("\n=== DISCUSSION ===");
        LoggerSingleton.getInstance().log("STATE", "Entrée dans DiscussionState");
    }

    @Override
    public void executerEtat(GestionPartie partie) {

        LoggerSingleton.getInstance().log("STATE", "Création d'une nouvelle discussion");
        Discussion discussion = new Discussion();
        partie.forum.creerDiscussion(discussion);

        for (Joueur j : partie.gestionJoueur.getListeJoueurs()) {
            Message msg = j.EcrireMessage(partie.historique);
            discussion.ajouterMessage(msg);
            LoggerSingleton.getInstance().log("STATE", "Message écrit par: " + j.getNom() + " -> " + msg.getContenu());
        }

        LoggerSingleton.getInstance().log("STATE", "Affichage des messages de discussion");
        discussion.afficherMessages();

        LoggerSingleton.getInstance().log("STATE", "Fin DiscussionState -> Passage à VoteState");
        partie.changerEtat(new VoteState());
    }

    @Override
    public void sortirEtat(GestionPartie partie) {
        LoggerSingleton.getInstance().log("STATE", "Sortie de DiscussionState");
    }
}
