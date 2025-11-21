package Model.Forum;
import Logging.LoggerSingleton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
/**
 * Classe représentant une discussion dans le forum
 * - Composite (contient des Message)
 * - Sujet pour le pattern Observer (notifie les Observateurs quand on ajoute un message)
 */
public class Discussion implements Sujet, ElementForum {
    private final List<Message> messages = new ArrayList<>();
    private final List<Observateur> observers = new ArrayList<>();
    private String titre = "Discussion";
    public Discussion() {}
    public Discussion(String titre) {
        this.titre = titre == null ? "Discussion" : titre;
    }
    @Override
    public void addObserver(Observateur o) {
        if (o == null) return;
        synchronized (observers) {
            if (!observers.contains(o)) {
                observers.add(o);
                LoggerSingleton.getInstance().log("FORUM", "Observateur ajouté à la discussion: " + o.getClass().getSimpleName());
            }
        }
    }
    @Override
    public void removeObserver(Observateur o) {
        synchronized (observers) {
            if (observers.remove(o)) {
                LoggerSingleton.getInstance().log("FORUM", "Observateur retiré de la discussion: " + o.getClass().getSimpleName());
            }
        }
    }
    @Override
    public void notifyObservers(Message message) {
        // copie défensive pour éviter ConcurrentModificationException si un observer modifie la liste
        List<Observateur> copy;
        synchronized (observers) {
            copy = new ArrayList<>(observers);
        }
        for (Observateur obs : copy) {
            try {
                obs.update(message);
            } catch (Exception ex) {
                // log mais on continue la notification pour les autres observers
                LoggerSingleton.getInstance().log("FORUM", "Erreur lors de la notification d'un observateur: " + ex.getMessage());
            }
        }
    }
    /**
     * Ajoute un message à la discussion et notifie tous les observateurs
     */
    public void ajouterMessage(Message message) {
        if (message == null) return;
        synchronized (messages) {
            messages.add(message);
        }
        notifyObservers(message);
        LoggerSingleton.getInstance().log("FORUM", "Message ajouté à la discussion \"" + titre + "\": \"" + message.getContenu() + "\" par " + (message.getAuteur() != null ? message.getAuteur().getNom() : "Anonyme"));
    }
    /**
     * Affiche tous les messages de la discussion (et log l'affichage)
     * @return une représentation textuelle de tous les messages
     */
    public String afficherMessages() {
        StringBuilder sb = new StringBuilder();
        System.out.println("\n--- Messages de la discussion : " + titre + " ---");
        List<Message> copy;
        synchronized (messages) {
            copy = new ArrayList<>(messages);
        }
        if (copy.isEmpty()) {
            System.out.println("(Aucun message)");
            return "(Aucun message)";
        }
        for (Message msg : copy) {
            sb.append(msg.toString()).append("\n");
            LoggerSingleton.getInstance().log("FORUM", "Message affiché: \"" + msg.getContenu() + "\" par " + (msg.getAuteur() != null ? msg.getAuteur().getNom() : "Anonyme"));
            System.out.println(msg.toString());
        }
        return sb.toString();
    }
    /**
     * Méthode du Composite : afficher() permet de traiter Discussion comme ElementForum
     */
    @Override
    public void afficher() {
        afficherMessages();
    }
    public List<Message> getMessages() {
        synchronized (messages) {
            return new ArrayList<>(messages);
        }
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre == null ? this.titre : titre;
    }
}