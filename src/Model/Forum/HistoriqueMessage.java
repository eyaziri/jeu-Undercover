package Model.Forum;

import java.util.ArrayList;

public class HistoriqueMessage implements Observateur {

    private ArrayList<Message> historique = new ArrayList<>();

    @Override
    public void update(Message message) {
        historique.add(message);
        System.out.println("Historique: Message ajouté -> " + message.getContenu());
    }
    public void enregisterMessage(Message message) {
        update(message); 
    }

    public void afficherHistorique() {
        for (Message msg : historique) {
            System.out.println(msg.getContenu());
        }
    }
}
