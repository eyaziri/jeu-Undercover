/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Administration;
import Forum.Discussion;
import Forum.Forum;
import Forum.HistoriqueMessage;
import Forum.Message;
import GestionJoueur.Civil;
import GestionJoueur.Gagnant;
import GestionJoueur.Joueur;
import GestionJoueur.MrWhite;
import GestionJoueur.Score;
import GestionJoueur.Undercover;
import Vote.Elimination;
import Vote.PhaseVote;
import java.util.Scanner;

/**
 *
 * @author eyazi
 */
    public class GestionPartie {
   
        public void demarrerPartie() {
          System.out.println("\n\n-----------------Bienvenue a notre jeu!------------------\n\n");

          Scanner sc = new Scanner(System.in);
          Admin admin = new Admin();
          Gagnant gagnant = new Gagnant();
          Elimination elimination = new Elimination();
          Score score = new Score();
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
                   
                      admin.donnerNombreJoueur();
                      System.out.println("\n\n Le jeu commence!\n\n");

                      GestionJoueur gestionJoueur = new GestionJoueur();
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

                      Forum forum = new Forum();
                      PhaseVote phaseVote = new PhaseVote();
                      phaseVote.demarrerVote(gestionJoueur, forum, historiqueMessage,score,elimination,gagnant);
                      terminerPartie(score,gagnant);
                      
                      choix = 3; 
                      break;
                  case 3:
                      System.out.println("Merci d'avoir joue! Au revoir.");
                      break;
                  default:
                      System.out.println("Choix invalide. Veuillez choisir entre 1 et 3.");
                      break;
              }
          }
         
      }

      public void terminerPartie(Score score,Gagnant gagnant) {
          System.out.println("\n\n--------------------- La partie est terminee ! -------------------------\n");
   
          gagnant.afficherGagnant();
          
          System.out.println("\n\n-------------------- Voici les scores de chaque joueur : ----------------------\n");

          score.afficherScores();
         
          System.out.println("\n\n------------ Que souhaitez-vous faire ensuite ? ------------\n");
          System.out.println("1 - Retourner au menu principal");
          System.out.println("2 - Quitter le jeu");

          Scanner sc = new Scanner(System.in);
          int choix = 0;

      
          while (true) 
          {
              if (sc.hasNextInt()) 
              {
                  choix = sc.nextInt();
                  if (choix == 1 || choix == 2) 
                  {
                      break;
                  }
              } else 
              {
                  sc.next(); 
              }
              System.out.println("\nChoix invalide. Veuillez choisir entre 1 et 2.");
          }

          if (choix == 1) 
          {
           demarrerPartie();
          } else
          {
              System.out.println("\n\n----------------- Merci d'avoir joue! Au revoir. --------------------");
          }
      }
    }