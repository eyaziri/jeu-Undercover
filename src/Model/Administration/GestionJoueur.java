package Model.Administration;

import Model.GestionJoueur.Joueur;
import Logging.LoggerSingleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionJoueur {
    public static ArrayList<Joueur> joueurs;
    private int nombreCivil = 3;
    private int nombreUndercover = 1;
    private int nombreMrsWhite = 1;
    private String motCivil;
    private String motUndercover;

    public GestionJoueur() {
        GestionJoueur.joueurs = new ArrayList<>();
    }

    public void ajouterJoueur(Joueur joueur) {
        Scanner scanner = new Scanner(System.in);
        String nom = joueur.getNom();

        // Boucle tant qu'un joueur avec le même nom existe déjà
        while (estNomDejaUtilise(nom)) {
            System.out.println("Un joueur avec le nom \"" + nom + "\" existe déjà. Veuillez entrer un autre nom :");
            nom = scanner.nextLine();
        }

        joueur.setNom(nom);
        joueurs.add(joueur);
        LoggerSingleton.getInstance().log(
                "GESTION_JOUEUR",
                "Joueur ajouté : " + nom + " avec le rôle " + joueur.getRole()
        );
    }

    private boolean estNomDejaUtilise(String nom) {
        for (Joueur j : joueurs) {
            if (j.getNom().equals(nom)) {
                return true;
            }
        }
        return false;
    }

    public void supprimerJoueur(Joueur joueur) {
        if (joueurs.remove(joueur)) {
            System.out.println(joueur.getNom() + " a été supprimé de la partie.");
            LoggerSingleton.getInstance().log(
                    "GESTION_JOUEUR",
                    "Joueur supprimé : " + joueur.getNom()
            );
        }
    }

    public void setNombreCivil(int n) { this.nombreCivil = n; }
    public void setNombreUndercover(int n) { this.nombreUndercover = n; }
    public void setNombreMrWhite(int n) { this.nombreMrsWhite = n; }
    public int getNombreCivil() { return nombreCivil; }
    public int getNombreUndercover() { return nombreUndercover; }
    public int getNombreMrWhite() { return nombreMrsWhite; }

    public Joueur obtenirJoueur(String nom) {
        for (Joueur j : joueurs) {
            if (j.getNom().equals(nom)) return j;
        }
        return null;
    }

    public int longueurListe() { return joueurs.size(); }

    public static ArrayList<Joueur> getListeJoueurs() { return joueurs; }

    public void AffichageListeJoueurs() {
        System.out.println("Liste des joueurs :");
        for (Joueur j : joueurs) {
            System.out.println(j.getNom() + " (" + j.getRole() + ")");
        }
    }

    public List<String> getListeNomsJoueurs() {
        List<String> noms = new ArrayList<>();
        for (Joueur j : joueurs) {
            noms.add(j.getNom());
        }
        return noms;
    }

    public void setMotCivil(String mot) { this.motCivil = mot; }
    public void setMotUndercover(String mot) { this.motUndercover = mot; }
    public String getMotCivil() { return motCivil; }
    public String getMotUndercover() { return motUndercover; }

    // Méthodes pour gérer le nombre restant par rôle
    public int getNombreCivilRestant() {
        return (int) joueurs.stream().filter(j -> j.getRole().equals("Civile")).count();
    }

    public int getNombreUndercoverRestant() {
        return (int) joueurs.stream().filter(j -> j.getRole().equals("Undercover")).count();
    }

    public int getNombreMrWhiteRestant() {
        return (int) joueurs.stream().filter(j -> j.getRole().equals("MrWhite")).count();
    }
}
