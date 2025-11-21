package Model.GestionJoueur;

import Logging.LoggerSingleton;
import Model.Administration.GestionJoueur;
import Model.Administration.Role;
import Model.Forum.HistoriqueMessage;
import Model.Forum.Message;
import Model.Vote.Elimination;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public abstract sealed class Joueur permits Undercover, MrWhite, Civil, Gagnant, JoueurDecorator {

    protected static int compteurId = 0;
    protected int idJoueur;
    protected String nom;
    protected int score;
    Role role;
    protected String mot;
    protected int nombreDeVotesRecus = 0;
    protected boolean aVote = false;
    protected boolean estVivant = true;

    public Joueur() {
        this.role = new Role();
        this.score = 0;
        this.idJoueur = ++compteurId;
        LoggerSingleton.getInstance().log("STATE", "Création du joueur: ID=" + idJoueur);
    }

    public String getNom() { return nom; }
    public int getIdJoueur() { return idJoueur; }
    public void setEstVivant() { this.estVivant = false; }
    public boolean getEstVivant() { return estVivant; }
    public void setNom(String nom) { this.nom = nom; }
    public void setScore(int n) { this.score = n; }
    public int getScore() { return score; }
    public void setMot(String mot) { this.mot = mot; }
    public String getMot() { return mot; }
    public void setRole(String nomRole) {
        this.role = new Role();
        this.role.setNomRole(nomRole);
    }
    public String getRole() { return role.getNomRole(); }
    public int getNombreDeVotesRecus() { return nombreDeVotesRecus; }
    public void setnombreDeVotesRecus() {
        this.nombreDeVotesRecus++;
        LoggerSingleton.getInstance().log("STATE", "Vote reçu par " + nom + " -> Total: " + nombreDeVotesRecus);
    }
    public void setAVote(boolean aVote) { this.aVote = aVote; }
    public boolean isAVote() { return aVote; }

    public Message EcrireMessage(HistoriqueMessage historiqueMessage) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n - Bonjour " + this.nom + ", donnez une description du mot :\n");

        String contenu;
        try {
            contenu = sc.nextLine();
            if (contenu.isEmpty()) throw new NoSuchElementException();
        } catch (NoSuchElementException e) {
            System.out.println("Aucune saisie détectée. Message par défaut attribué.");
            contenu = "Message non spécifié";
        }

        Message msg = new Message(contenu, this);
        msg.setDatePublication(LocalDate.now());
        historiqueMessage.enregisterMessage(msg);

        LoggerSingleton.getInstance().log("STATE", "Message publié par " + nom + ": " + contenu);

        System.out.println("\n -> Votre message a été publié le : " + msg.getDatePublication() + "\n");
        return msg;
    }

    public Message EcrireMessages(HistoriqueMessage historiqueMessage, String contenu) {
        Message msg = new Message(contenu, this);
        msg.setDatePublication(LocalDate.now());
        historiqueMessage.enregisterMessage(msg);

        LoggerSingleton.getInstance().log("STATE", "Message publié par " + nom + " (programmé): " + contenu);

        System.out.println("\n -> Votre message a été publié le : " + msg.getDatePublication() + "\n");
        return msg;
    }

    public void estEliminer(GestionJoueur gestionJoueur) {
        Elimination elimination = new Elimination();
        ArrayList<Joueur> joueursElimines = elimination.getJoueursElimines();

        if (this.getRole().equalsIgnoreCase("Civile") || this.getRole().equalsIgnoreCase("Undercover")) {
            System.out.println("Le joueur " + this.getNom() + " est éliminé avec " + this.getNombreDeVotesRecus() + " votes, rôle: " + this.getRole());
            LoggerSingleton.getInstance().log("STATE", this.nom + " éliminé. Votes: " + nombreDeVotesRecus + ", rôle: " + this.getRole());
        }
        else if (this.getRole().equalsIgnoreCase("MrWhite")) {
            Scanner sc = new Scanner(System.in);
            System.out.println("\nVous êtes Mr. White. Essayez de deviner le mot associé : ");
            String motDevine = sc.nextLine();

            String motCorrect = null;
            for (Joueur joueur : gestionJoueur.getListeJoueurs()) {
                if (joueur.getRole().equalsIgnoreCase("Civile")) {
                    motCorrect = joueur.getMot();
                    break;
                }
            }

            boolean correct = motCorrect != null && motDevine.equalsIgnoreCase(motCorrect);

            if (correct) {
                System.out.println("\nFélicitations ! Vous avez deviné correctement, vous n'êtes pas éliminé.");
                LoggerSingleton.getInstance().log("STATE", "MrWhite a deviné correctement: " + motDevine);
            } else {
                System.out.println("\nÉchec. Le mot était " + motCorrect + ". Vous êtes éliminé.");
                LoggerSingleton.getInstance().log("STATE", "MrWhite a échoué. Tentative: " + motDevine + ", Mot correct: " + motCorrect);
            }
        }
    }

    @Override
    public String toString() {
        return "Le Joueur : " + this.getNom() + " , est de rôle : " + this.getRole();
    }
}
