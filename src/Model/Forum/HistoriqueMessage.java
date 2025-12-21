package Model.Forum;

import Logging.LoggerSingleton;
import java.util.ArrayList;

/**
 * Historique des messages du forum, implémente Observateur pour recevoir les messages
 */
public class HistoriqueMessage implements Observateur {

    private final ArrayList<Message> historique = new ArrayList<>();

    @Override
    public void update(Message message) {
        if (message != null) {
            historique.add(message);
            System.out.println("Historique: Message ajouté -> " + message.getContenu());
            LoggerSingleton.getInstance().log("FORUM", "Message enregistré dans l'historique: \"" + message.getContenu() + "\" par " + (message.getAuteur() != null ? message.getAuteur().getNom() : "Anonyme"));
        }
    }

    public void enregisterMessage(Message message) {
        update(message);
    }

    public void afficherHistorique() {
        System.out.println("\n--- Historique des messages ---");
        for (Message msg : historique) {
            System.out.println(msg.getContenu() + " (par " + (msg.getAuteur() != null ? msg.getAuteur().getNom() : "Anonyme") + ")");
            LoggerSingleton.getInstance().log("FORUM", "Message affiché depuis l'historique: \"" + msg.getContenu() + "\"");
        }
        System.out.println("--- Fin de l'historique ---\n");
    }

    public ArrayList<Message> getHistorique() {
        return new ArrayList<>(historique);
    }
}
