package Model.Forum;

import Logging.LoggerSingleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant une discussion dans le forum
 */
public class Discussion implements Sujet {

    private final List<Message> messages = new ArrayList<>();
    private final List<Observateur> observers = new ArrayList<>();

    @Override
    public void addObserver(Observateur o) {
        if (o != null) {
            observers.add(o);
            LoggerSingleton.getInstance().log("FORUM", "Observateur ajouté à la discussion.");
        }
    }

    @Override
    public void removeObserver(Observateur o) {
        observers.remove(o);
        LoggerSingleton.getInstance().log("FORUM", "Observateur retiré de la discussion.");
    }

    @Override
    public void notifyObservers(Message message) {
        for (Observateur obs : observers) {
            obs.update(message);
        }
    }

    /**
     * Ajoute un message à la discussion et notifie tous les observateurs
     */
    public void ajouterMessage(Message message) {
        if (message != null) {
            messages.add(message);
            notifyObservers(message);
            LoggerSingleton.getInstance().log("FORUM", "Message ajouté à la discussion: \"" + message.getContenu() + "\" par " + (message.getAuteur() != null ? message.getAuteur().getNom() : "Anonyme"));
        }
    }

    /**
     * Affiche tous les messages de la discussion
     */


    public String afficherMessages() {
        System.out.println("\n--- Messages de la discussion ---");
        StringBuilder sb = new StringBuilder();
        for (Message msg : messages) {
            sb.append(msg.toString()).append("\n");
            LoggerSingleton.getInstance().log("FORUM", "Message affiché: \"" + msg.getContenu() + "\" par " + (msg.getAuteur() != null ? msg.getAuteur().getNom() : "Anonyme"));
        }
        return sb.toString();
    }

    public List<Message> getMessages() {
        return new ArrayList<>(messages);
    }
}
