/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Administration;
import GestionJoueur.Civil;
import GestionJoueur.Joueur;
import GestionJoueur.MrWhite;
import GestionJoueur.Undercover;
import java.util.Scanner;

/**
 *
 * @author eyazi
 */
public class GestionPartie {
   
  public void demarrerPartie() {
    System.out.println("-----------------Bienvenue a notre jeu!------------------");

    Scanner sc = new Scanner(System.in);
    Admin admin = new Admin();
    int choix = 0;


    while (choix != 3) {
        System.out.println("\n\nVeuillez choisir entre ces options :\n\n");
        System.out.println("1 - Annoncer les regles du jeu.\n");
        System.out.println("2 - Commencer le jeu.\n");
        System.out.println("3 - Quitter le jeu.\n");

        choix = sc.nextInt();

        switch (choix) {
            case 1:
                admin.annocerRegle();
                break;
            case 2:
                admin.donnerNombreJoueur();
                    System.out.println("\n\n Le jeu commence!\n\n");

                    GestionJoueur gestionJoueur = new GestionJoueur();
                    int nombreJoueur = admin.getNombreJoueur();
                    gestionJoueur.gestionNombreJoueurs(admin);

                    int totalCivil = 0;
                    int totalUndercover = 0;
                    int totalMrWhite = 0;

                    ListeMots listeMotPartie = new ListeMots();
                    Role roleGenerator = new Role();

                    
                    String motCivil = null;
                    String motUndercover = null;

                    while (totalCivil < gestionJoueur.getNombreCivil() ||  totalUndercover < gestionJoueur.getNombreUndercover() || totalMrWhite < gestionJoueur.getNombreMrWhite())
                    {

                        Joueur joueur;
                        String role = roleGenerator.donnerRoleAleatoire();

                        if (role.equals("Civile") && totalCivil < gestionJoueur.getNombreCivil()) {
                            joueur = new Civil();
                            joueur.setRole("Civile");

                            if (motCivil == null) {
                                listeMotPartie.associerMotDeCivilEtDeUndercover(joueur);
                                motCivil = joueur.getMot();
                                motUndercover = listeMotPartie.getMotUndercover(motCivil);
                            } else {
                                joueur.setMot(motCivil);
                            }
                            totalCivil++;
                        } 
                        else if (role.equals("Undercover") && totalUndercover < gestionJoueur.getNombreUndercover()) {
                            joueur = new Undercover();
                            joueur.setRole("Undercover");

                            if (motUndercover == null) {
                                listeMotPartie.associerMotDeCivilEtDeUndercover(joueur);
                                motUndercover = joueur.getMot();
                                motCivil = listeMotPartie.getMotCivil(motUndercover);
                            } else {
                                joueur.setMot(motUndercover);
                            }
                            totalUndercover++;
                        } 
                        else if (role.equals("MrWhite") && totalMrWhite < gestionJoueur.getNombreMrWhite()) {
                            joueur = new MrWhite();
                            joueur.setRole("MrWhite");
                            joueur.setMot(" Tu dois decouvrir le mot ! ");
                            totalMrWhite++;
                        } 
                        else {
                            continue;
                        }

                        gestionJoueur.ajouterJoueur(joueur);
                        System.out.println(joueur.getNom() + " Votre role est :  - " + joueur.getRole() + " et le mot est : " + joueur.getMot());
                    }

                    if (totalCivil >= gestionJoueur.getNombreCivil() && totalUndercover >= gestionJoueur.getNombreUndercover() &&  totalMrWhite >= gestionJoueur.getNombreMrWhite())
                    {
                        System.out.println("Tous les joueurs ont recu leur role.");
                    }







                
                
                
                
                
                
                
                
                
                choix=3;
                break;
            case 3:
                System.out.println("Merci d'avoir joue! Au revoir.");
                break;
            default:
                System.out.println("Choix invalide. Veuillez choisir entre 1 et 4.");
                break;
        }
    }
}
  public void terminerPartie()
  {
     System.out.println("Felicitations vous avez termine cette partie .");
     System.out.println("Le gagnant est : ");
     System.out.println("Voila les statistique de jeu :");
  }
}

