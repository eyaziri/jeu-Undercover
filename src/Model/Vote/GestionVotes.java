package Model.Vote;
import Logging.LoggerSingleton;
import Model.Administration.GestionJoueur;
import Model.GestionJoueur.Gagnant;
import Model.GestionJoueur.Joueur;
import Model.GestionJoueur.JoueurDecorator;
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
        int ajout;
        if (joueur instanceof JoueurDecorator decorator && decorator.hasDoubleVote()) {
            ajout = 2;
            LoggerSingleton.getInstance().log("DECORATOR",
                    "Double vote appliqué pour " + joueur.getNom());
        } else {
            ajout = 1;
        }
        resultatVote.put(joueur, resultatVote.getOrDefault(joueur, 0) + ajout);
        LoggerSingleton.getInstance().log("STATE",
                "Vote ajouté pour: " + joueur.getNom() +
                        " (+" + ajout + " vote(s), Total: " + getVotes(joueur) + ")");
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
    public void eliminerJoueurApresVote(GestionJoueur gestionJoueur, PhaseVote phaseVote, Gagnant gagnant) {
        if (resultatVote.isEmpty()) {
            System.out.println("Aucun vote enregistré.");
            return;
        }
        // 1️⃣ Déterminer le joueur le plus voté via la HashMap
        Joueur joueurElimine = null;
        int maxVotes = -1;
        for (var entry : resultatVote.entrySet()) {
            if (entry.getValue() > maxVotes) {
                joueurElimine = entry.getKey();
                maxVotes = entry.getValue();
            }
        }
        LoggerSingleton.getInstance().log("STATE",
                "Joueur choisi pour élimination : " + joueurElimine.getNom() + " (" + maxVotes + " votes)"
        );
        // 2️⃣ Cas normal : Civile ou Undercover
        if (joueurElimine.getRole().equalsIgnoreCase("civile") ||
                joueurElimine.getRole().equalsIgnoreCase("undercover")) {
            System.out.println("Le joueur " + joueurElimine.getNom() + " est éliminé !");
            elimination.eliminer(joueurElimine);
            gestionJoueur.supprimerJoueur(joueurElimine);
            return;
        }
        // 3️⃣ Cas spécial : Mr White doit deviner
        if (joueurElimine.getRole().equalsIgnoreCase("mrwhite")) {
            System.out.println("Mr White doit deviner le mot !");
            // Trouver le mot Civil
            String mot = gestionJoueur.getListeJoueurs().stream()
                    .filter(j -> j.getRole().equalsIgnoreCase("civile"))
                    .map(Joueur::getMot)
                    .findFirst()
                    .orElse(null);
            if (mot == null) {
                System.out.println("Erreur : Aucun mot civil trouvé !");
                return;
            }
            System.out.println("Essayez de deviner le mot : ");
            Scanner sc = new Scanner(System.in);
            String reponse = sc.nextLine();
            if (reponse.equalsIgnoreCase(mot)) {
                System.out.println("Bravo ! Mr White gagne !");
                gagnant.ajouterGagnant(joueurElimine);
                phaseVote.terminerPhase();
            } else {
                System.out.println("Mauvaise réponse ! Mr White est éliminé.");
                elimination.eliminer(joueurElimine);
                gestionJoueur.supprimerJoueur(joueurElimine);
            }
        }
    }
}