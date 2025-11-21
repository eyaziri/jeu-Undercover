/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Administration;
import Logging.LoggerSingleton;
import Model.GestionJoueur.DoubleVoteDecorator;
import Model.GestionJoueur.ImmuniteDecorator;
import Model.GestionJoueur.Joueur;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author eyazi
 */
public class GestionJoueur {
    public static ArrayList<Joueur>joueurs;
    private int nombreCivil=3;
    private int nombreUndercover=1;
    private int nombreMrsWhite=1;
    private String motCivil;
    private String motUndercover;
    public GestionJoueur()
    {
        GestionJoueur.joueurs=new ArrayList<>();
    }

    public void ajouterJoueur(Joueur joueur) {

        Scanner scanner = new Scanner(System.in);
        String nom = joueur.getNom();
        // Boucle tant qu'un joueur avec le même nom existe déjà
        while (estNomDejaUtilise(nom)) {
            System.out.println("Un joueur avec le nom \"" + nom + "\" existe déjà. Veuillez entrer un autre nom :");
            nom = scanner.nextLine();
        }
        joueur.setNom(nom); // Met à jour le nom du joueur avec le nom unique
        joueurs.add(joueur); // Ajoute le joueur comme vivant par défaut avec le nom unique
    }

    // Méthode pour vérifier si un nom est déjà utilisé
    private boolean estNomDejaUtilise(String nom) {
        for (Joueur j : joueurs) {
            if (j.getNom().equals(nom)) {
                return true;
            }
        }
        return false;
    }

    public void supprimerJoueur(Joueur joueur)
    {
        joueurs.remove(joueur);
    }

    public void setNombreCivil(int n)
    {
        this.nombreCivil=n;
    }

    public void setNombreUndercover(int n)
    {
        this.nombreUndercover=n;
    }

    public void setNombreMrWhite(int n)
    {
        this.nombreMrsWhite=n;
    }

    public int getNombreCivil()
    {
        return this.nombreCivil;
    }

    public int getNombreUndercover()
    {
        return this.nombreUndercover;
    }

    public int getNombreMrWhite()
    {
        return this.nombreMrsWhite;
    }


    public void gestionNombreJoueurs(Admin admin) {
        if (admin.getNombreJoueur() < 3) {
            throw new Exception2("Nombre de joueurs insuffisant pour démarrer la partie. Minimum requis : 3 joueurs.");
        }
        if (admin.getNombreJoueur() > 10) {
            setNombreMrWhite(2);
            setNombreUndercover(2);
            int a = admin.getNombreJoueur() - 4;
            setNombreCivil(a);
        } else {
            setNombreMrWhite(1);
            setNombreUndercover(1);
            int a = admin.getNombreJoueur() - 2;
            setNombreCivil(a);
        }
    }

    public Joueur obtenirJoueur(String nom){
        for (int i = 0; i < joueurs.size(); i++) {
            if (joueurs.get(i).getNom().equals(nom))
            {
                return joueurs.get(i);
            }
        }
        return null;
    }

    public int longueurListe(){
        return joueurs.size();
    }


    public void AffichageListeJoueurs()
    {
        for(int i=0;i<joueurs.size();i++)
        {
            System.out.println(joueurs.get(i).getNom());
        }
    }

    public static ArrayList<Joueur> getListeJoueurs()
    {
        return joueurs;
    }
    public String affichageListeJoueurs()
    {
        for(int i=0;i<joueurs.size();i++)
        {
            return(joueurs.get(i).getNom());
        }
        StringBuilder joueur = new StringBuilder();
        for (Joueur j : joueurs) {
            joueur.append(j.toString()).append("\n");
        }
        return joueur.toString();
    }


    public int getNombreCivilRestant() {
        int count = 0;
        for (Joueur joueur : joueurs) {
            if (joueur.getRole().equals("Civile")) {
                count++;
            }
        }
        return nombreCivil - count;
    }
    public int getNombreUndercoverRestant() {
        int count = 0;
        for (Joueur joueur : joueurs) {
            if (joueur.getRole().equals("Undercover")) {
                count++;
            }
        }
        return nombreUndercover - count;
    }
    public int getNombreMrWhiteRestant() {
        int count = 0;
        for (Joueur joueur : joueurs) {
            if (joueur.getRole().equals("MrWhite")) {
                count++;
            }
        }
        return nombreMrsWhite - count;
    }

    public void setMotCivil(String mot) {
        this.motCivil = mot;
    }
    public void setMotUndercover(String mot) {
        this.motUndercover = mot;
    }
    // Méthodes pour récupérer les mots des rôles
    public String getMotCivil() {
        return this.motCivil;
    }
    public String getMotUndercover() {
        return this.motUndercover;
    }

    public List<String> getListeNomsJoueurs() {
        List<String> noms = new ArrayList<>();
        for (int i = 0; i < joueurs.size(); i++) {
            noms.add(joueurs.get(i).getNom());
        }
        return noms;
    }

    public void donnerDecorateurAleatoire() {
        if (joueurs.isEmpty()) return;

        Random rand = new Random();
        int index = rand.nextInt(joueurs.size());
        Joueur joueur = joueurs.get(index);

        boolean doubleVote = rand.nextBoolean();

        Joueur decorateur;

        if (doubleVote) {
            decorateur = new DoubleVoteDecorator(joueur);
            System.out.println(joueur.getNom() + " a reçu le pouvoir DOUBLE VOTE !");
        } else {
            decorateur = new ImmuniteDecorator(joueur);
            System.out.println(joueur.getNom() + " a reçu le pouvoir IMMUNITÉ !");
        }

        // Remplacer le joueur dans la liste
        joueurs.set(index, decorateur);
    }


    public void donnerDecorateurUnique() {
        if (joueurs.isEmpty()) {
            System.out.println("❌ Aucun joueur disponible pour donner un pouvoir spécial.");
            return;
        }

        Random rand = new Random();
        int index = rand.nextInt(joueurs.size());
        Joueur joueurOriginal = joueurs.get(index);

        boolean doubleVote = rand.nextBoolean();
        Joueur joueurAvecPouvoir;

        if (doubleVote) {
            joueurAvecPouvoir = new DoubleVoteDecorator(joueurOriginal);
            System.out.println("\n🎁 " + joueurOriginal.getNom() + " a reçu le pouvoir DOUBLE VOTE !");
            LoggerSingleton.getInstance().log("DECORATOR",
                    "DoubleVoteDecorator appliqué à: " + joueurOriginal.getNom());
        } else {
            joueurAvecPouvoir = new ImmuniteDecorator(joueurOriginal);
            System.out.println("\n🎁 " + joueurOriginal.getNom() + " a reçu le pouvoir IMMUNITÉ !");
            LoggerSingleton.getInstance().log("DECORATOR",
                    "ImmuniteDecorator appliqué à: " + joueurOriginal.getNom());
        }

        // Remplacer le joueur dans la liste
        joueurs.set(index, joueurAvecPouvoir);

        // Afficher les pouvoirs pour débogage
        afficherPouvoirsSpeciaux();
    }

    // Méthode pour afficher les pouvoirs spéciaux (à ajouter)
    public void afficherPouvoirsSpeciaux() {
        System.out.println("\n=== POUVOIRS SPÉCIAUX ACTIFS ===");
        boolean aucunPouvoir = true;

        for (Joueur joueur : joueurs) {
            if (joueur instanceof DoubleVoteDecorator) {
                System.out.println("🎯 " + joueur.getNom() + " - DOUBLE VOTE");
                aucunPouvoir = false;
            } else if (joueur instanceof ImmuniteDecorator) {
                System.out.println("🛡️ " + joueur.getNom() + " - IMMUNITÉ");
                aucunPouvoir = false;
            }
        }

        if (aucunPouvoir) {
            System.out.println("Aucun pouvoir spécial actif");
        }
        System.out.println("================================\n");
    }


}
