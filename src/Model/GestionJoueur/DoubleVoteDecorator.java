package Model.GestionJoueur;
import Logging.LoggerSingleton;
import Model.Vote.Vote;


public class DoubleVoteDecorator extends JoueurDecorator {
    private boolean pouvoirUtilise = false;

    public DoubleVoteDecorator(Joueur joueur) {
        super(joueur);
        LoggerSingleton.getInstance().log("DECORATOR",
                "Joueur " + joueur.getNom() + " décoré avec DOUBLE VOTE");
    }

    @Override
public void voter(Joueur cible) {
    System.out.println("🎯 DOUBLE_VOTE_DECORATOR.voter() appelé pour " + getNom());
    
    if (!pouvoirUtilise) {
        System.out.println("🎯 " + getNom() + " utilise son DOUBLE VOTE !");
        // Premier vote
        joueur.voter(cible);
        // Deuxième vote  
        joueur.voter(cible);
        pouvoirUtilise = true;
        LoggerSingleton.getInstance().log("DECORATOR",
                "Double vote utilisé par " + getNom() + " sur " + cible.getNom() +
                        " (Total votes: " + cible.getNombreDeVotesRecus() + ")");
    } else {
        System.out.println("ℹ️ " + getNom() + " a déjà utilisé son double vote. Vote normal.");
        joueur.voter(cible);
    }
}

    @Override
    public boolean hasDoubleVote() {
        return !pouvoirUtilise;
    }
    
    // ✅ AJOUT : Méthode pour récupérer le joueur décoré
    @Override
    public Joueur getJoueurDecore() {
        return joueur;
    }
}