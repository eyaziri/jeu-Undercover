/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vote;


import GestionJoueur.Joueur;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class StatistiqueVote {
    private int nombreTotalDeVotes;
    private Map<Joueur, Integer> votesParJoueur;

    public StatistiqueVote() {
        this.nombreTotalDeVotes = 0;
        this.votesParJoueur = new HashMap<>();
    }

    public void ajouterVote(Joueur joueur) {
        this.nombreTotalDeVotes++;
        this.votesParJoueur.put(joueur, this.votesParJoueur.getOrDefault(joueur, 0) + 1);
    }

    public int getNombreDeVotes(Joueur joueur) {
        return this.votesParJoueur.getOrDefault(joueur, 0);
    }

    public Joueur getJoueurLePlusVote() {
        Joueur joueurLePlusVote = null;
        int maxVotes = 0;

        for (Map.Entry<Joueur, Integer> entry : this.votesParJoueur.entrySet()) {
            if (entry.getValue() > maxVotes) {
                joueurLePlusVote = entry.getKey();
                maxVotes = entry.getValue();
            }
        }

        return joueurLePlusVote;
    }

    public List<Joueur> getJoueursAvecZeroVotes(List<Joueur> listeJoueurs) {
        List<Joueur> joueursAvecZeroVotes = new ArrayList<>();

        for (Joueur joueur : listeJoueurs) {
            if (this.getNombreDeVotes(joueur) == 0) {
                joueursAvecZeroVotes.add(joueur);
            }
        }

        return joueursAvecZeroVotes;
    }

    public void afficherStatistiques(List<Joueur> listeJoueurs) {
        System.out.println("Statistiques de vote : ");
        for (Joueur joueur : listeJoueurs) {
            System.out.println(joueur.getNom() + " a reçu " + getNombreDeVotes(joueur) + " vote(s).");
        }

        Joueur joueurLePlusVote = getJoueurLePlusVote();
        if (joueurLePlusVote != null) {
            System.out.println("Le joueur le plus voté est " + joueurLePlusVote.getNom() + " avec " + getNombreDeVotes(joueurLePlusVote) + " vote(s).");
        } else {
            System.out.println("Aucun vote enregistré.");
        }
    }
}