package Model.Forum;

import Logging.LoggerSingleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant le forum contenant plusieurs discussions
 */
public class Forum {

    private final List<Discussion> discussions;

    public Forum() {
        this.discussions = new ArrayList<>();
    }

    /**
     * Crée une nouvelle discussion et l'ajoute au forum
     */
    public void creerDiscussion(Discussion discussion) {
        if (discussion != null) {
            discussions.add(discussion);
            LoggerSingleton.getInstance().log("FORUM", "Nouvelle discussion créée dans le forum.");
            System.out.println("Discussion ajoutée au forum.");
        } else {
            System.out.println("Impossible d'ajouter une discussion null.");
        }
    }

    /**
     * Retourne la liste des discussions du forum
     */
    public List<Discussion> listerDiscussions() {
        return new ArrayList<>(discussions);
    }
}
