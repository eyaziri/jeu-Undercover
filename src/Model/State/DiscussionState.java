package Model.State;

import Model.Administration.GestionPartie;
import Model.Forum.Discussion;
import Model.Forum.Message;
import Model.GestionJoueur.Joueur;

public class DiscussionState implements GameState {

    @Override
    public void entrerEtat(GestionPartie partie) {
        System.out.println("\n=== DISCUSSION ===");
    }

    @Override
    public void executerEtat(GestionPartie partie) {

        Discussion discussion = new Discussion();
        partie.forum.creerDiscussion(discussion);

        for (Joueur j : partie.gestionJoueur.getListeJoueurs()) {
            Message msg = j.EcrireMessage(partie.historique);
            discussion.ajouterMessage(msg);
        }

        discussion.afficherMessages();

        partie.changerEtat(new VoteState());
    }

    @Override
    public void sortirEtat(GestionPartie partie) {}
}
