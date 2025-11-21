package Model.Vote;

import Model.Administration.GestionJoueur;
import Model.Forum.Discussion;
import Model.Forum.Forum;
import Model.Forum.HistoriqueMessage;
import Model.Forum.Message;
import Model.GestionJoueur.Gagnant;
import Model.GestionJoueur.Joueur;
import Model.GestionJoueur.Score;

import java.util.ArrayList;

public class PhaseVote {

    private boolean phaseTerminee = false;

    public void demarrerVote(GestionJoueur gestionJoueur,
                             Forum forum,
                             HistoriqueMessage historiqueMessage,
                             Score score,
                             Elimination elimination,
                             Gagnant gagnant) {

        GestionVotes gestionVote = new GestionVotes();

        // Boucle des tours de vote
        while (!phaseTerminee) {

            System.out.println("\n\n---------------- SESSION DE VOTE ----------------\n");

            // Nouvelle discussion
            Discussion discussion = new Discussion();
            forum.creerDiscussion(discussion);

            // Ajout des observateurs au sujet (Discussion)
            discussion.addObserver(historiqueMessage);  // Observer : HistoriqueMessage
            // Tu peux ajouter ScoreObservateur ici si tu veux :
            // discussion.addObserver(new ScoreObservateur());

            // 🔥 PHASE DISCUSSION
            for (Joueur joueur : gestionJoueur.getListeJoueurs()) {
                Message msg = joueur.EcrireMessage(historiqueMessage); // Le joueur décrit son mot
                discussion.ajouterMessage(msg); // Notifie TOUTES les observers
            }

            System.out.println("\nMessages échangés :\n");
            discussion.afficherMessages();

            // 🔥 PHASE DE VOTE
            for (Joueur joueur : gestionJoueur.getListeJoueurs()) {
                Vote vote = new Vote();
                vote.ajouterVote(joueur);  // Le joueur vote

                gestionVote.ajouterVote(joueur); // Comptabilise le vote
            }

            System.out.println("\n-------------- VOTE TERMINÉ --------------\n");

            gestionVote.eliminerJoueurApresVote(gestionJoueur, elimination, this, gagnant);

            System.out.println("\n\nJoueurs restants :\n");
            gestionJoueur.AffichageListeJoueurs();

            ArrayList<Joueur> joueursElimines = elimination.getJoueursElimines();

            System.out.println("\nJoueurs éliminés :\n");
            elimination.AffichageListeJoueursElimine(joueursElimines);

            // 🔥 PHASE DE VÉRIFICATION DES CONDITIONS DE VICTOIRE
            verifierConditionsVictoire(gestionJoueur, elimination, score, gagnant);
        }
    }


    /**
     * Vérifie toutes les conditions de victoire
     */
    private void verifierConditionsVictoire(GestionJoueur gestionJoueur,
                                            Elimination elimination,
                                            Score score,
                                            Gagnant gagnant) {

        int civils = 0;
        int undercovers = 0;
        int whites = 0;

        for (Joueur joueur : gestionJoueur.getListeJoueurs()) {
            switch (joueur.getRole()) {
                case "Civile" -> civils++;
                case "Undercover" -> undercovers++;
                case "MrWhite" -> whites++;
            }
        }

        // Condition Civils gagnent
        if (whites == 0 && civils >= undercovers) {
            System.out.println("\n🏆 Les Civils ont gagné !\n");
            gagnant.determinerGagnant(gestionJoueur.getListeJoueurs());
            score.attribuerScore(gagnant.getNom(),
                    gestionJoueur.getListeJoueurs(),
                    elimination.getJoueursElimines());
            terminerPhase();
            return;
        }

        // Condition Undercover gagnent
        if (whites == 0 && civils < undercovers) {
            System.out.println("\n🏆 Les Undercover ont gagné !\n");
            gagnant.determinerGagnant(gestionJoueur.getListeJoueurs());
            score.attribuerScore(gagnant.getNom(),
                    gestionJoueur.getListeJoueurs(),
                    elimination.getJoueursElimines());
            terminerPhase();
            return;
        }

        // Condition Mr.White gagne
        if (whites >= 1 && civils == 0 && undercovers == 0) {
            System.out.println("\n🏆 Mr.White a gagné !\n");
            gagnant.determinerGagnant(gestionJoueur.getListeJoueurs());
            score.attribuerScore(gagnant.getNom(),
                    gestionJoueur.getListeJoueurs(),
                    elimination.getJoueursElimines());
            terminerPhase();
            return;
        }

        // Sinon : la partie continue
        System.out.println("\n⚠ Le jeu continue : Mr.White est encore en jeu.\n");
    }


    public void terminerPhase() {
        this.phaseTerminee = true;
        System.out.println("\n\n---------------- PHASE DE VOTE TERMINÉE ----------------\n");
    }

    public boolean estPhaseTermine() {
        return phaseTerminee;
    }
}
