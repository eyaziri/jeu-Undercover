package Model.Forum;
import Logging.LoggerSingleton;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe représentant le forum contenant plusieurs discussions
 * - Composite de plus haut niveau (peut implémenter ElementForum pour appel unique .afficher())
 */
public class Forum implements ElementForum {
    private final List<Discussion> discussions;
    public Forum() {
        this.discussions = new ArrayList<>();
    }
    /**
     * Crée une nouvelle discussion et l'ajoute au forum
     */
    public void creerDiscussion(Discussion discussion) {
        if (discussion == null) {
            System.out.println("Impossible d'ajouter une discussion null.");
            return;
        }
        synchronized (discussions) {
            discussions.add(discussion);
        }
        LoggerSingleton.getInstance().log("FORUM", "Nouvelle discussion créée dans le forum: " + discussion.getTitre());
        System.out.println("Discussion ajoutée au forum.");
    }
    /**
     * Retourne la liste des discussions du forum (copie défensive)
     */
    public List<Discussion> listerDiscussions() {
        synchronized (discussions) {
            return new ArrayList<>(discussions);
        }
    }
    /**
     * Méthode du Composite : afficher tout le forum
     */
    @Override
    public void afficher() {
        System.out.println("\n=== FORUM ===");
        List<Discussion> copy;
        synchronized (discussions) {
            copy = new ArrayList<>(discussions);
        }
        if (copy.isEmpty()) {
            System.out.println("(Aucune discussion)");
            return;
        }
        for (Discussion d : copy) {
            d.afficher();
        }
    }
}