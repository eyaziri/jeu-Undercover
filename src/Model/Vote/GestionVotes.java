package Model.Vote;

import Logging.LoggerSingleton;
import Model.Administration.GestionJoueur;
import Model.GestionJoueur.Gagnant;
import Model.GestionJoueur.Joueur;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class GestionVotes {

    private Elimination elimination;
    private HashMap<Joueur, Integer> resultatVote;

    public GestionVotes() {
        resultatVote = new HashMap<>();
        this.elimination = new Elimination();
        LoggerSingleton.getInstance().log("STATE", "GestionVotes initialisé");
    }

    public void ajouterVote(Joueur joueur) {
        if (resultatVote.containsKey(joueur)) {
            int votesActuels = resultatVote.get(joueur);
            resultatVote.put(joueur, votesActuels + 1);
        } else {
            resultatVote.put(joueur, 1);
        }
        LoggerSingleton.getInstance().log("STATE", "Vote ajouté pour: " + joueur.getNom() + " (Total: " + getVotes(joueur) + ")");
    }

    public int getVotes(Joueur joueur) {
        return resultatVote.getOrDefault(joueur, 0);
    }

    public void afficherResultats() {
        for (HashMap.Entry<Joueur, Integer> entry : resultatVote.entrySet()) {
            Joueur joueur = entry.getKey();
            int votes = entry.getValue();
            System.out.println("Joueur: " + joueur.getNom() + " a recu " + votes + " votes.");
            LoggerSingleton.getInstance().log("STATE", "Résultat affiché: " + joueur.getNom() + " -> " + votes + " votes");
        }
    }

    public void eliminerJoueurApresVote(GestionJoueur gestionJoueur, Elimination elimination, PhaseVote phaseVote, Gagnant gagnant) {
        List<Joueur> joueurs = gestionJoueur.getListeJoueurs();

        Joueur joueurElimine = null;
        String mot = null;

        for (Joueur joueur : joueurs) {
            if (joueurElimine == null || joueur.getNombreDeVotesRecus() > joueurElimine.getNombreDeVotesRecus()) {
                joueurElimine = joueur;
            }
        }

        if (joueurElimine != null) {
            LoggerSingleton.getInstance().log("STATE", "Joueur sélectionné pour élimination: " + joueurElimine.getNom() + " (Votes: " + joueurElimine.getNombreDeVotesRecus() + ")");

            String role = joueurElimine.getRole().toLowerCase();

            if (role.equals("civile") || role.equals("undercover")) {
                System.out.println("Le joueur " + joueurElimine.getNom() + " est elimine avec " + joueurElimine.getNombreDeVotesRecus() + " votes. Role: " + joueurElimine.getRole());
                LoggerSingleton.getInstance().log("STATE", "Élimination joueur: " + joueurElimine.getNom() + " Role: " + joueurElimine.getRole());

                joueurElimine.estEliminer(gestionJoueur);
                joueurElimine.setEstVivant();
                elimination.ajouterJoueurElimine(joueurElimine);
                gestionJoueur.supprimerJoueur(joueurElimine);

            } else if (role.equals("mrwhite")) {
                LoggerSingleton.getInstance().log("STATE", "Mr.White doit deviner le mot");

                for (Joueur joueur : joueurs) {
                    if (joueur.getRole().equalsIgnoreCase("civile")) {
                        mot = joueur.getMot();
                        break;
                    }
                }

                Scanner sc = new Scanner(System.in);
                System.out.println("Vous êtes Mr. White. Essayez de deviner le mot associé : ");
                String motDevine = null;

                if (sc.hasNextLine()) {
                    motDevine = sc.nextLine();
                }

                if (mot != null && motDevine != null && motDevine.equalsIgnoreCase(mot)) {
                    System.out.println("Félicitations, vous avez deviné correctement.");
                    LoggerSingleton.getInstance().log("STATE", "Mr.White a deviné le mot correctement");
                    gagnant.ajouterGagnant(joueurElimine);
                    phaseVote.terminerPhase();
                } else {
                    System.out.println("Échec. Vous avez été éliminé. Le mot correct était : " + mot);
                    LoggerSingleton.getInstance().log("STATE", "Mr.White échoue à deviner le mot. Éliminé: " + joueurElimine.getNom());

                    joueurElimine.estEliminer(gestionJoueur);
                    joueurElimine.setEstVivant();
                    elimination.ajouterJoueurElimine(joueurElimine);
                    gestionJoueur.supprimerJoueur(joueurElimine);
                }
            }

        } else {
            System.out.println("Aucun joueur n'a été trouvé pour élimination.");
            LoggerSingleton.getInstance().log("STATE", "Aucun joueur trouvé pour élimination");
        }
    }
}
