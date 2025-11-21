package Model.Forum;

import Logging.LoggerSingleton;
import Model.GestionJoueur.Joueur;
import java.time.LocalDate;

/**
 * Classe représentant un message envoyé par un joueur dans le forum
 */
public class Message {

    private String contenu;
    private LocalDate datePublication;
    private Joueur auteur;

    public Message(String contenu, Joueur auteur) {
        this.contenu = contenu;
        this.auteur = auteur;
        this.datePublication = LocalDate.now();
        LoggerSingleton.getInstance().log("FORUM", "Message créé par " + (auteur != null ? auteur.getNom() : "Anonyme"));
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }

    public Joueur getAuteur() {
        return auteur;
    }

    public void setAuteur(Joueur auteur) {
        this.auteur = auteur;
    }

    public void afficherMessage() {
        System.out.println("Le message : " + this.contenu +
                " , est envoyé par : " + (auteur != null ? auteur.getNom() : "Anonyme") +
                " , le : " + this.datePublication);
        LoggerSingleton.getInstance().log("FORUM", "Message affiché: \"" + contenu + "\" par " + (auteur != null ? auteur.getNom() : "Anonyme"));
    }

    @Override
    public String toString() {
        return "Le message : " + contenu +
                " , est envoyé par : " + (auteur != null ? auteur.getNom() : "Anonyme") +
                " , le : " + datePublication;
    }
}
