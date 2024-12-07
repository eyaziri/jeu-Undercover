package Model.Vote;

import Model.GestionJoueur.Joueur;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

// Interface Fonctionnelle
@FunctionalInterface
public interface StatistiqueVote {

    // Méthode abstraite principale (fonction lambda)
    Joueur calculerStatistique(List<Joueur> listeJoueurs, Map<Joueur, Integer> votesParJoueur);

    // Méthode par défaut pour obtenir les votes d'un joueur
    default int getNombreDeVotes(Joueur joueur, Map<Joueur, Integer> votesParJoueur) {
        return votesParJoueur.getOrDefault(joueur, 0);
    }

    // Méthode par défaut pour obtenir le joueur le plus voté
    default Joueur getJoueurLePlusVote(Map<Joueur, Integer> votesParJoueur) {
        Joueur joueurLePlusVote = null;
        int maxVotes = 0;

        for (Map.Entry<Joueur, Integer> entry : votesParJoueur.entrySet()) {
            if (entry.getValue() > maxVotes) {
                joueurLePlusVote = entry.getKey();
                maxVotes = entry.getValue();
            }
        }

        return joueurLePlusVote;
    }

    // Méthode par défaut pour afficher les statistiques
    default void afficherStatistiques(List<Joueur> listeJoueurs, Map<Joueur, Integer> votesParJoueur) {
        System.out.println("Statistiques de vote : ");
        for (Joueur joueur : listeJoueurs) {
            System.out.println(joueur.getNom() + " a reçu " + getNombreDeVotes(joueur, votesParJoueur) + " vote(s).");
        }

        Joueur joueurLePlusVote = getJoueurLePlusVote(votesParJoueur);
        if (joueurLePlusVote != null) {
            System.out.println("Le joueur le plus voté est " + joueurLePlusVote.getNom() + " avec " + getNombreDeVotes(joueurLePlusVote, votesParJoueur) + " vote(s).");
        } else {
            System.out.println("Aucun vote enregistré.");
        }
    }
    
    // Méthode pour calculer les joueurs avec 0 votes (fonction lambda)
    default List<Joueur> getJoueursAvecZeroVotes(List<Joueur> listeJoueurs, Map<Joueur, Integer> votesParJoueur) {
        List<Joueur> joueursAvecZeroVotes = new ArrayList<>();
        
        // Utilisation de lambda pour filtrer les joueurs avec 0 votes
        listeJoueurs.stream()
            .filter(joueur -> getNombreDeVotes(joueur, votesParJoueur) == 0)
            .forEach(joueursAvecZeroVotes::add);

        return joueursAvecZeroVotes;
    }
}

// Classe qui implémente l'interface fonctionnelle
class StatistiqueVoteImpl implements StatistiqueVote {

    @Override
    public Joueur calculerStatistique(List<Joueur> listeJoueurs, Map<Joueur, Integer> votesParJoueur) {
        // Fonction lambda pour trouver le joueur le plus voté
        return listeJoueurs.stream()
            .max((joueur1, joueur2) -> Integer.compare(votesParJoueur.getOrDefault(joueur1, 0), votesParJoueur.getOrDefault(joueur2, 0)))
            .orElse(null);
    }
}
