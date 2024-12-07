/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Administration;
import Model.Forum.Forum;
import Model.Forum.HistoriqueMessage;
import Model.GestionJoueur.Civil;
import Model.GestionJoueur.Gagnant;
import Model.GestionJoueur.Joueur;
import Model.GestionJoueur.MrWhite;
import Model.GestionJoueur.Undercover;
import Model.GestionJoueur.Score;
import Model.Vote.Elimination;
import Model.Vote.PhaseVote;
import java.util.ArrayList;

import java.util.Scanner;


/**
 *
 * @author eyazi
 */
   public record  GestionPartie (int nombreJoueurs, 
                     ArrayList<Joueur> joueurs, 
                     ArrayList<Joueur> joueursElimines, 
                     String motCorrect) {
   
        public void demarrerPartie() {
          System.out.println("\n\n-----------------Bienvenue a notre jeu!------------------\n\n");

          Scanner sc = new Scanner(System.in);
          Admin admin = new Admin();
          Gagnant gagnant = new Gagnant();
           
           GestionJoueur gestionJoueur = new GestionJoueur();
          Elimination elimination = new Elimination();
          ArrayList<Joueur> joueursElimines;
          joueursElimines = elimination.getJoueursElimines();
          ArrayList<Joueur> joueurs = gestionJoueur.getListeJoueurs();
          int choix = 0;

          while (choix != 3) {
              System.out.println("\n\nVeuillez choisir entre ces options :\n");
              System.out.println("\n1 - Annoncer les regles du jeu.\n");
              System.out.println("\n2 - Commencer le jeu.\n");
              System.out.println("\n3 - Quitter le jeu.\n");

              if (sc.hasNextInt()) {
                  choix = sc.nextInt();
              } else {
                  sc.next(); 
                  System.out.println("Veuillez entrer un nombre valide.");
                  continue;
              }

              switch (choix) {
                  case 1:
                      admin.annocerRegle();
                      break;
                  case 2:
                      
                   boolean nbreValide=false;
                   while (!nbreValide){
                    try {
                            System.out.println("\n\nVeuillez entrer le nombre de joueurs :\n");
                            admin.donnerNombreJoueur();

                            gestionJoueur.gestionNombreJoueurs(admin); // Vérifie et configure les rôles

                            System.out.println("\nLe jeu commence avec " + admin.getNombreJoueur() + " joueurs.\n");
                            nbreValide = true;

                            // Lancer la logique de la partie ici...

                        
                         
                        
                            System.out.println("\n\n Le jeu commence!\n\n");

                     
                      HistoriqueMessage historiqueMessage = new HistoriqueMessage();
                      int nombreJoueur = admin.getNombreJoueur();
                      gestionJoueur.gestionNombreJoueurs(admin);

                      int totalCivil = 0;
                      int totalUndercover = 0;
                      int totalMrWhite = 0;

                      ListeMots listeMotPartie = new ListeMots();
                      Role roleGenerator = new Role();

                      String motCivil = null;
                      String motUndercover = null;

                      while (totalCivil < gestionJoueur.getNombreCivil() || totalUndercover < gestionJoueur.getNombreUndercover() || totalMrWhite < gestionJoueur.getNombreMrWhite())
                      {
                          Joueur joueur;
                          String role = roleGenerator.donnerRoleAleatoire();

                          if (role.equals("Civile") && totalCivil < gestionJoueur.getNombreCivil()) 
                          {
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
                          else if (role.equals("Undercover") && totalUndercover < gestionJoueur.getNombreUndercover()) 
                          {
                              joueur = new Undercover();
                              joueur.setRole("Undercover");
                              if (motUndercover == null) 
                              {
                                  listeMotPartie.associerMotDeCivilEtDeUndercover(joueur);
                                  motUndercover = joueur.getMot();
                                  motCivil = listeMotPartie.getMotCivil(motUndercover);
                              } 
                              else 
                              {
                                  joueur.setMot(motUndercover);
                              }
                              totalUndercover++;
                          } 
                          else if (role.equals("MrWhite") && totalMrWhite < gestionJoueur.getNombreMrWhite()) 
                          {
                              joueur = new MrWhite();
                              joueur.setRole("MrWhite");
                              joueur.setMot("Tu es Mr White!");
                              totalMrWhite++;
                          } 
                          else 
                          {
                              continue;
                          }

                          gestionJoueur.ajouterJoueur(joueur);
                          System.out.println("\n" + joueur.getNom() + " ton mot est : " + joueur.getMot() + "\n");
                      }

                      if (totalCivil >= gestionJoueur.getNombreCivil() && totalUndercover >= gestionJoueur.getNombreUndercover() && totalMrWhite >= gestionJoueur.getNombreMrWhite())
                      {
                          System.out.println("\nTous les joueurs ont recu leur role.\n");
                      }
                        Score score = new Score();
                         
                      Forum forum = new Forum();
                           String motCorrect= null;
                       for (Joueur joueur : joueurs) 
                {
                    if (joueur.getRole().equalsIgnoreCase("civile")) 
                    {
                         motCorrect = joueur.getMot();
                        break;
                    }      
                }
                      
                      PhaseVote phaseVote = new PhaseVote();
                      phaseVote.demarrerVote(gestionJoueur, forum, historiqueMessage,score,elimination,gagnant);
                        // Exemple: si vous avez une méthode pour suivre les éliminés
                 
                  joueursElimines = elimination.getJoueursElimines();

                      terminerPartie(score,gagnant,joueurs,joueursElimines,motCorrect);
                      } catch (Exception2 e) {
                            System.err.println("Erreur : " + e.getMessage());
                            System.out.println("Veuillez réessayer avec un nombre suffisant de joueurs.");
                       } finally {
                            System.out.println("Merci d'avoir joué !");
                        }}
                      choix = 3;
                      break;
                  case 3 :
                      System.out.println("Merci d'avoir joue! Au revoir.");
                      break;
                  default:
                      System.out.println("Choix invalide. Veuillez choisir entre 1 et 3.");
                      break;
              
          }
         
          }}

    
        public void terminerPartie(Score score, Gagnant gagnant, ArrayList<Joueur> joueurs, ArrayList<Joueur> joueursElimines, String motCorrect) {
    System.out.println("\n\n--------------------- La partie est terminée ! -------------------------\n");

    gagnant.afficherGagnant();
    System.out.println("Le mot correct était : " + motCorrect + "\n");

    System.out.println("\n\n-------------------- Voici les joueurs éliminés : ----------------------\n");
    if (joueursElimines.isEmpty()) {
        System.out.println("");
    } else {
        for (Joueur joueur : joueursElimines) {
            System.out.println("- " + joueur.getNom() + " (Rôle : " + joueur.getRole() + ")");
        }
    }

    System.out.println("\n\n-------------------- Voici les scores de chaque joueur : ----------------------\n");
    
    score.afficherScores(joueurs, joueursElimines);

    System.out.println("\n\n------------ Que souhaitez-vous faire ensuite ? ------------\n");
    System.out.println("1 - Retourner au menu principal");
    System.out.println("2 - Quitter le jeu");

    Scanner sc = new Scanner(System.in);
    int choix = 0;

    while (true) {
        if (sc.hasNextInt()) {
            choix = sc.nextInt();
            if (choix == 1 || choix == 2) {
                break;
            }
        } else {
            sc.next(); 
        }
        System.out.println("\nChoix invalide. Veuillez choisir entre 1 et 2.");
    }

    if (choix == 1) {
        demarrerPartie();
    } else {
        System.out.println("\n\n----------------- Merci d'avoir joué ! Au revoir. --------------------");
    }
}

    }