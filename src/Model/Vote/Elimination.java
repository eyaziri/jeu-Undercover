package Model.Vote;
import Logging.LoggerSingleton;
import Model.GestionJoueur.ImmuniteDecorator;
import Model.GestionJoueur.Joueur;
import java.util.ArrayList;
import java.util.List;
public record Elimination(List<Joueur> joueursElimines) {
    // Constructeur par défaut qui initialise la liste des joueurs éliminés
    public Elimination() {
        this(new ArrayList<>());
        LoggerSingleton.getInstance().log("STATE", "Elimination initialisée");
    }
    public Joueur obtenirDernierJoueurElimine() {
        if (!joueursElimines.isEmpty()) {
            Joueur dernier = joueursElimines.get(joueursElimines.size() - 1);
            LoggerSingleton.getInstance().log("STATE", "Dernier joueur éliminé: " + dernier.getNom());
            return dernier;
        }
        LoggerSingleton.getInstance().log("STATE", "Aucun joueur éliminé pour obtenirDernierJoueurElimine");
        return null;
    }
    public ArrayList<Joueur> getJoueursElimines() {
        return new ArrayList<>(joueursElimines);
    }
    public void ajouterJoueurElimine(Joueur joueur) {
        if (joueur != null) {
            joueursElimines.add(joueur);
            LoggerSingleton.getInstance().log("STATE", "Joueur ajouté aux éliminés: " + joueur.getNom());
        } else {
            System.out.println("Impossible d'ajouter un joueur null.");
            LoggerSingleton.getInstance().log("STATE", "Tentative d'ajout d'un joueur null aux éliminés");
        }
    }
    public void supprimerJoueur(Joueur joueur) {
        if (joueursElimines.remove(joueur)) {
            LoggerSingleton.getInstance().log("STATE", "Joueur supprimé des éliminés: " + joueur.getNom());
        } else {
            LoggerSingleton.getInstance().log("STATE", "Tentative de suppression échouée: joueur non trouvé");
        }
    }
    public void AffichageListeJoueursElimine(ArrayList<Joueur> joueursElimines) {
        if (joueursElimines.isEmpty()) {
            System.out.println("");
            LoggerSingleton.getInstance().log("STATE", "Aucun joueur à afficher parmi les éliminés");
            return;
        }
        System.out.println("Liste des joueurs éliminés :");
        for (Joueur joueur : joueursElimines) {
            System.out.println(joueur.getNom());
            LoggerSingleton.getInstance().log("STATE", "Joueur éliminé affiché: " + joueur.getNom());
        }
    }
    public boolean eliminer(Joueur joueur) {
        try {
            // Déléguer toujours l'action d'élimination au joueur (le décorateur gère l'immunité)
            // ImmuniteDecorator.overridden setEstVivant() consomme l'immunité la première fois.
            joueur.setEstVivant();
            // Après l'appel, si le joueur est effectivement mort, on l'ajoute
            if (!joueur.isVivant()) {
                joueursElimines.add(joueur);
                System.out.println("❌ " + joueur.getNom() + " a été éliminé !");
                LoggerSingleton.getInstance().log("STATE", "Joueur éliminé: " + joueur.getNom());
                return true;
            } else {
                // Si le joueur est toujours vivant, cela peut être dû à l'immunité consommée ou autre logique.
                // Si le joueur est un ImmuniteDecorator et que l'immunité était active, on loggue ça.
                if (joueur instanceof ImmuniteDecorator deco) {
                    if (deco.isImmuniteActive()) {
                        // Normalement on ne devrait pas arriver ici car setEstVivant() consomme l'immunité,
                        // mais loggons l'état pour debugging.
                        LoggerSingleton.getInstance().log("DECORATOR",
                                "Tentative d'élimination bloquée par immunité pour: " + joueur.getNom());
                        System.out.println("🛡️ " + joueur.getNom() + " est immunisé et ne peut pas être éliminé !");
                    } else {
                        LoggerSingleton.getInstance().log("DECORATOR",
                                "Immunité déjà utilisée précédemment pour: " + joueur.getNom());
                    }
                }
            }
        } catch (Exception e) {
            LoggerSingleton.getInstance().log("ERROR",
                    "Erreur lors de l'élimination de " + joueur.getNom() + ": " + e.getMessage());
        }
        return false;
    }
}