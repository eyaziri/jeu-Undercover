package Model.Forum;

import java.util.ArrayList;
import java.util.List;

public class Discussion implements Sujet {

    private List<Message> messages = new ArrayList<>();
    private List<Observateur> observers = new ArrayList<>();

    @Override
    public void addObserver(Observateur o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observateur o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(Message message) {
        for (Observateur obs : observers) {
            obs.update(message);
        }
    }

    public void ajouterMessage(Message message) {
        messages.add(message);
        notifyObservers(message); // 🔥 Notifie tout le monde
    }

    public void afficherMessages() {
    for (Message msg : messages) {
        System.out.println(msg); // ou msg.getTexte() si Message a un getter
    }
}

}
