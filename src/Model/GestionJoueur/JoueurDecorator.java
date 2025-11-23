package Model.GestionJoueur;

import Logging.LoggerSingleton;
import Model.Administration.GestionJoueur;
import Model.Forum.HistoriqueMessage;
import Model.Forum.Message;
import Model.Vote.Elimination;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;


public abstract non-sealed class JoueurDecorator extends Joueur {

    protected Joueur joueur;

    public JoueurDecorator(Joueur joueur) {
        this.joueur = joueur;
        LoggerSingleton.getInstance().log("DECORATOR", "Création de " + this.getClass().getSimpleName() + " pour: " + joueur.getNom());
    }

    // Délégation de toutes les méthodes à joueur
    @Override
    public String getNom() {
        return joueur.getNom();
    }

    @Override
    public int getIdJoueur() {
        return joueur.getIdJoueur();
    }

    @Override
    public void setEstVivant() {
        joueur.setEstVivant();
    }

    @Override
    public boolean getEstVivant() {
        return joueur.getEstVivant();
    }
    public boolean hasDoubleVote() {
        return false;
    }


    @Override
    public void setNom(String nom) {
        joueur.setNom(nom);
    }

    @Override
    public void setScore(int n) {
        joueur.setScore(n);
    }

    @Override
    public int getScore() {
        return joueur.getScore();
    }

    @Override
    public void setMot(String mot) {
        joueur.setMot(mot);
    }

    @Override
    public String getMot() {
        return joueur.getMot();
    }

    @Override
    public void setRole(String nomRole) {
        joueur.setRole(nomRole);
    }

    @Override
    public String getRole() {
        return joueur.getRole();
    }

    @Override
    public int getNombreDeVotesRecus() {
        return joueur.getNombreDeVotesRecus();
    }

    @Override
    public void setnombreDeVotesRecus() {
        joueur.setnombreDeVotesRecus();
    }

    @Override
    public void setAVote(boolean aVote) {
        joueur.setAVote(aVote);
    }

    @Override
    public boolean isAVote() {
        return joueur.isAVote();
    }

    @Override
    public Message EcrireMessage(HistoriqueMessage historiqueMessage) {
        return joueur.EcrireMessage(historiqueMessage);
    }

    @Override
    public Message EcrireMessages(HistoriqueMessage historiqueMessage, String contenu) {
        return joueur.EcrireMessages(historiqueMessage, contenu);
    }

    @Override
    public void estEliminer(GestionJoueur gestionJoueur) {
        joueur.estEliminer(gestionJoueur);
    }

    @Override
    public String toString() {
        return joueur.toString();
    }

    @Override
    public void voter(Joueur cible) {
        joueur.voter(cible);
    }

    @Override
    public boolean isVivant() {
        return joueur.isVivant();
    }

    public Joueur getJoueurDecore() {
        return joueur;
    }
}