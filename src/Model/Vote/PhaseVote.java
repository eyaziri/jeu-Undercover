package Model.Vote;

import Logging.LoggerSingleton;
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

        LoggerSingleton.getInstance().log("STATE", "PhaseVote démarrée");

        GestionVotes gestionVote = new GestionVotes();

        while (!phaseTerminee) {

            System.out.println("\n\n---------------- SESSION DE VOTE ----------------\n");
            LoggerSingleton.getInstance().log("STATE", "Nouvelle session de vote");

            // 📌 Création d’une nouvelle discussion
            Discussion discussion = new Discussion();
            forum.creerDiscussion(discussion);
            discussion.addObserver(historiqueMessage);

            // 📌 Phase discussion
            for (Joueur joueur : gestionJoueur.getListeJoueurs()) {
                Message msg = joueur.EcrireMessage(historiqueMessage);
                discussion.ajouterMessage(msg);
            }

            System.out.println("\nMessages échangés :\n");
            discussion.afficherMessages();

            // 📌 Phase de vote
            System.out.println("\n--- Début de la phase de vote ---\n");

            for (Joueur joueur : gestionJoueur.getListeJoueurs()) {

                LoggerSingleton.getInstance().log("STATE","Le joueur " + joueur.getNom() + " vote.");

                // ⚠ ACTUELLEMENT : chaque joueur vote pour lui-même
                // Si tu veux changer ça : je peux te coder un système de vote réel.
                gestionVote.ajouterVote(joueur);
            }

            System.out.println("\n-------------- VOTE TERMINÉ --------------\n");

            gestionVote.eliminerJoueurApresVote(
                    gestionJoueur,
                    this,
                    gagnant
            );

            System.out.println("\n\nJoueurs restants :\n");
            gestionJoueur.AffichageListeJoueurs();

            ArrayList<Joueur> joueursElimines = elimination.getJoueursElimines();
            System.out.println("\nJoueurs éliminés :\n");
            elimination.AffichageListeJoueursElimine(joueursElimines);

            // 📌 Vérification conditions de victoire
            verifierConditionsVictoire(gestionJoueur, elimination, score, gagnant);
        }
    }

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

        if (whites == 0 && civils >= undercovers) {
            System.out.println("\n🏆 Les Civils ont gagné !\n");
            gagnant.determinerGagnant(gestionJoueur.getListeJoueurs());
            score.attribuerScore(gagnant.getNom(),
                    gestionJoueur.getListeJoueurs(),
                    elimination.getJoueursElimines());
            terminerPhase();
            return;
        }

        if (whites == 0 && civils < undercovers) {
            System.out.println("\n🏆 Les Undercover ont gagné !\n");
            gagnant.determinerGagnant(gestionJoueur.getListeJoueurs());
            score.attribuerScore(gagnant.getNom(),
                    gestionJoueur.getListeJoueurs(),
                    elimination.getJoueursElimines());
            terminerPhase();
            return;
        }

        if (whites >= 1 && civils == 0 && undercovers == 0) {
            System.out.println("\n🏆 Mr.White a gagné !\n");
            gagnant.determinerGagnant(gestionJoueur.getListeJoueurs());
            score.attribuerScore(gagnant.getNom(),
                    gestionJoueur.getListeJoueurs(),
                    elimination.getJoueursElimines());
            terminerPhase();
            return;
        }

        System.out.println("\n⚠ Le jeu continue : Mr.White est encore en jeu.\n");
    }

    public void terminerPhase() {
        this.phaseTerminee = true;
        System.out.println("\n\n---------------- PHASE DE VOTE TERMINÉE ----------------\n");
        LoggerSingleton.getInstance().log("STATE", "PhaseVote terminée");
    }

    public boolean estPhaseTermine() {
        return phaseTerminee;
    }
}
