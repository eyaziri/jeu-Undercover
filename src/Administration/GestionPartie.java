/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Administration;
import java.util.Scanner;

/**
 *
 * @author eyazi
 */
public class GestionPartie {
   
  public void demarrerPartie()
  {
        System.out.println("Bienvenue à notre jeu! Veuillez choisir entre ces deux options :");
        System.out.println("1 - Annoncer les regles du jeu.");
        System.out.println("2 - Donner le nombre de joueurs dans cette partie.");
       
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();

        Admin admin = new Admin();

        switch (choix) {
            case 1:
                admin.annocerRegle();
                break;
            case 2:
                admin.donnerNombreJoueur();
                break;
            default:
                System.out.println("Choix invalide. Veuillez choisir 1 ou 2.");
                break;
        }
    }
  public void terminerPartie()
  {
     
  }
}

