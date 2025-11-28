/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Administration;
import Logging.LoggerSingleton;
import Model.GestionJoueur.DoubleVoteDecorator;
import Model.GestionJoueur.ImmuniteDecorator;
import Model.GestionJoueur.Joueur;
import Model.GestionJoueur.JoueurDecorator;

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

public void donnerDecorateurUnique() {
    System.out.println("🎮 DONNER_DECORATEUR_UNIQUE - APPELÉ !");
    System.out.println("🔍 DEBUG: donnerDecorateurUnique() - DÉBUT");
    System.out.println("🔍 DEBUG: Nombre de joueurs: " + joueurs.size());
    
    if (joueurs.isEmpty()) {
        System.out.println("❌ Aucun joueur disponible pour donner un pouvoir spécial.");
        return;
    }
    
    // ✅ VÉRIFICATION : Y a-t-il déjà des joueurs avec des pouvoirs ?
    boolean dejaUnPouvoir = false;
    for (Joueur joueur : joueurs) {
        if (joueur instanceof JoueurDecorator) {
            System.out.println("⚠️ ATTENTION: Un pouvoir existe déjà sur " + joueur.getNom() + 
                             " (" + joueur.getClass().getSimpleName() + ") - Arrêt de l'attribution");
            dejaUnPouvoir = true;
            break;
        }
    }
    
    if (dejaUnPouvoir) {
        System.out.println("❌ Des pouvoirs ont déjà été attribués dans cette partie. Aucun nouveau pouvoir attribué.");
        afficherPouvoirsSpeciaux();
        return; // 🛑 STOP - pas de nouveaux pouvoirs
    }
    
    // Vérifier qu'il y a au moins 2 joueurs pour donner 2 pouvoirs
    if (joueurs.size() < 2) {
        System.out.println("❌ Pas assez de joueurs pour attribuer 2 pouvoirs. Minimum requis: 2 joueurs.");
        return;
    }
    
    // Afficher tous les joueurs avant attribution
    System.out.println("🔍 DEBUG: Liste des joueurs avant attribution:");
    for (Joueur j : joueurs) {
        System.out.println("  - " + j.getNom() + " (" + j.getClass().getSimpleName() + ")");
    }

    // ✅ MODIFICATION : Attribuer DEUX pouvoirs à DEUX joueurs différents
    Random rand = new Random();
    
    // Premier joueur - Double Vote
    int indexDoubleVote = rand.nextInt(joueurs.size());
    Joueur joueurDoubleVote = joueurs.get(indexDoubleVote);
    
    System.out.println("🔍 DEBUG: Joueur sélectionné pour DOUBLE VOTE: " + joueurDoubleVote.getNom() + " (index: " + indexDoubleVote + ")");
    
    Joueur joueurAvecDoubleVote = new DoubleVoteDecorator(joueurDoubleVote);
    System.out.println("\n🎁 " + joueurDoubleVote.getNom() + " a reçu le pouvoir DOUBLE VOTE !");
    LoggerSingleton.getInstance().log("DECORATOR", "DoubleVoteDecorator appliqué à: " + joueurDoubleVote.getNom());
    
    joueurs.set(indexDoubleVote, joueurAvecDoubleVote);
    
    // Deuxième joueur - Immunité (doit être différent du premier)
    int indexImmunite;
    do {
        indexImmunite = rand.nextInt(joueurs.size());
    } while (indexImmunite == indexDoubleVote); // Assurer que c'est un joueur différent
    
    Joueur joueurImmunite = joueurs.get(indexImmunite);
    
    // Si le joueur sélectionné est déjà décoré (ne devrait pas arriver), prendre le joueur original
    if (joueurImmunite instanceof JoueurDecorator) {
        joueurImmunite = ((JoueurDecorator) joueurImmunite).getJoueurDecore();
    }
    
    System.out.println("🔍 DEBUG: Joueur sélectionné pour IMMUNITÉ: " + joueurImmunite.getNom() + " (index: " + indexImmunite + ")");
    
    Joueur joueurAvecImmunite = new ImmuniteDecorator(joueurImmunite);
    System.out.println("\n🎁 " + joueurImmunite.getNom() + " a reçu le pouvoir IMMUNITÉ !");
    LoggerSingleton.getInstance().log("DECORATOR", "ImmuniteDecorator appliqué à: " + joueurImmunite.getNom());
    
    joueurs.set(indexImmunite, joueurAvecImmunite);
    
    // Afficher tous les joueurs après attribution
    System.out.println("🔍 DEBUG: Liste des joueurs après attribution:");
    for (Joueur j : joueurs) {
        System.out.println("  - " + j.getNom() + " (" + j.getClass().getSimpleName() + ")");
    }
    
    System.out.println("🔍 DEBUG: donnerDecorateurUnique() - FIN");
    
    // Afficher le résumé des pouvoirs attribués
    System.out.println("\n🎉 DEUX POUVOIRS ATTRIBUÉS :");
    System.out.println("🎯 " + joueurDoubleVote.getNom() + " - DOUBLE VOTE");
    System.out.println("🛡️ " + joueurImmunite.getNom() + " - IMMUNITÉ");
    System.out.println("================================\n");

    afficherPouvoirsSpeciaux();
}    
    public void afficherPouvoirsSpeciaux() {
    System.out.println("\n=== POUVOIRS SPÉCIAUX ACTIFS ===");
    boolean aucunPouvoir = true;

    for (Joueur joueur : joueurs) {
        if (joueur instanceof DoubleVoteDecorator) {
            DoubleVoteDecorator decorator = (DoubleVoteDecorator) joueur;
            String etat = decorator.hasDoubleVote() ? "ACTIF" : "UTILISÉ";
            System.out.println("🎯 " + joueur.getNom() + " - DOUBLE VOTE (" + etat + ")");
            aucunPouvoir = false;
        } else if (joueur instanceof ImmuniteDecorator) {
            ImmuniteDecorator decorator = (ImmuniteDecorator) joueur;
            String etat = decorator.isImmuniteActive() ? "ACTIVE" : "UTILISÉE";
            System.out.println("🛡️ " + joueur.getNom() + " - IMMUNITÉ (" + etat + ")");
            aucunPouvoir = false;
        }
    }

    if (aucunPouvoir) {
        System.out.println("Aucun pouvoir spécial actif");
    }
    System.out.println("================================\n");
}

}