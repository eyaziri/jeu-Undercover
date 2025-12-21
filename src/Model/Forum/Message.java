package Model.Forum;
import Logging.LoggerSingleton;
import Model.GestionJoueur.Joueur;
import java.time.LocalDate;
import java.util.Objects;
/**
 * Classe représentant un message envoyé par un joueur dans le forum
 * - Feuille du Composite (implémente ElementForum)
 */
public class Message implements ElementForum {
    private String contenu;
    private LocalDate datePublication;
    private Joueur auteur;
    public Message(String contenu, Joueur auteur) {
        this.contenu = contenu == null ? "" : contenu;
        this.auteur = auteur;
        this.datePublication = LocalDate.now();
        LoggerSingleton.getInstance().log("FORUM", "Message créé par " + (auteur != null ? auteur.getNom() : "Anonyme"));
    }
    public String getContenu() {
        return contenu;
    }
    public void setContenu(String contenu) {
        this.contenu = contenu == null ? "" : contenu;
    }
    public LocalDate getDatePublication() {
        return datePublication;
    }
    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = Objects.requireNonNullElse(datePublication, LocalDate.now());
    }
    public Joueur getAuteur() {
        return auteur;
    }
    public void setAuteur(Joueur auteur) {
        this.auteur = auteur;
    }
    /**
     * Affiche le message sur la console et logge l'affichage.
     */
    public void afficherMessage() {
        String auteurNom = (auteur != null ? auteur.getNom() : "Anonyme");
        System.out.println("Le message : " + this.contenu +
                " , est envoyé par : " + auteurNom +
                " , le : " + this.datePublication);
        LoggerSingleton.getInstance().log("FORUM", "Message affiché: \"" + contenu + "\" par " + auteurNom);
    }
    /**
     * Méthode du Composite : afficher() permet de traiter Message comme ElementForum
     */
    @Override
    public void afficher() {
        afficherMessage();
    }
    @Override
    public String toString() {
        String auteurNom = (auteur != null ? auteur.getNom() : "Anonyme");
        return "Le message : " + contenu +
                " , est envoyé par : " + auteurNom +
                " , le : " + datePublication;
    }
}
