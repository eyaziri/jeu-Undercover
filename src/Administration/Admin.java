/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Administration;

import GestionJoueur.Joueur;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author eyazi
 */
public class Admin {
    private int nombreJoueur=0;
   
    public Admin(){}
   
    public void setNombreJoueur(int nombre){
        this.nombreJoueur=nombre;
    }
    public int getNombreJoueur(){
       return nombreJoueur;
    }
    public void donnerNombreJoueur(){
    System.out.println("Donner le nombre de joueurs pendant cette session :");
    Scanner sc = new Scanner(System.in);
    int nombre = sc.nextInt();
    setNombreJoueur(nombre);
}
    public void annocerRegle(){
         System.out.println("*************Bienvenue dans le jeu Undercover!************");
        System.out.println("******************** Règles du jeu :***********************");
        System.out.println("1. Les joueurs sont divisés en trois rôles : Undercover, Mrs White, et les civils.");
        System.out.println("2. Les joueurs reçoivent des mots . Les civils reçoivent le même mot \n" +
                                "les Undercover reçoivent des mots différents \n" +
                                " Mr. White reçoit « Mr. White » .");
        System.out.println("3.  les joueurs peuvent discuter entre eux pour tenter de découvrir les rôles des autres joueurs.");
        System.out.println("4. Les Mrs White doivent essayer de rester cachés tout en faisant accuser les civils.");
        System.out.println("5. À chaque tour, une discussion a lieu, puis un vote est organisé pour éliminer un joueur.");
        System.out.println("6. Si le joueur éliminé est Mrs. White, elle a une dernière chance de deviner le mot des autres joueurs.\n"+""
                         + "Si elle devine correctement, elle gagne. Sinon, elle est définitivement éliminée.");
        System.out.println("7. Le jeu se termine lorsque tous les Mrs White sont éliminés ou lorsqu'ils ont réussi à faire éliminer tous les civils.");
       
        System.out.println("8. Le gagnant est soit l'équipe des civils, soit les Undercover, soit Mr White, selon le déroulement du jeu.");
        System.out.println("Amusez-vous bien et que le meilleur gagne !");
    }
}


