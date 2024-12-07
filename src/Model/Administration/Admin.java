/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Administration;


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
         System.out.println("***************************************************************************************************Bienvenue dans le jeu Undercover!************************************************************************************************************\n\n");
         System.out.println("********************************************************************************************************** Regles du jeu :**********************************************************************************************************************\n\n");
         System.out.println("**                                                                            1. Les joueurs sont divises en trois roles : Undercover, Mrs White, et les civils.                                                                              **\n");
         System.out.println("**                                               2. Les joueurs recoivent des mots . Les civils recoivent le meme mot et les Undercover recoivent des mots differents Mr. White recoit - Mr. White - .                                        **\n");
        System.out.println("**                                                                    3.  les joueurs peuvent discuter entre eux pour tenter de decouvrir les roles des autres joueurs.                                                                       **\n");           
        System.out.println("**                                                                             4. Les Mrs White doivent essayer de rester caches tout en faisant accuser les civils.                                                                          **\n");
        System.out.println("**                                                                    5. A chaque tour, une discussion a lieu, puis un vote est organise pour eliminer un joueur.                                                                             **\n");                
        System.out.println("**               6. Si le joueur elimine est Mr. White, il a une derniere chance de deviner le mot des autres joueurs.  Si il devine correctementle mot , il gagne et le jeu se termine. Sinon, il est definitivement elimine.                **\n");
        System.out.println("**                                                              7. Le jeu se termine lorsque tous les Mrs White sont elimines ou lorsqu'ils ont reussi a faire eliminer tous  les civils.                                                     **\n");
        System.out.println("**                                                                            8. Le gagnant est soit l'equipe des civils, soit les Undercover, soit Mr White, selon le deroulement du jeu.                                                    **\n\n");
        System.out.println("************************************************************************************************ Amusez-vous bien et que le meilleur gagne !*****************************************************************************************************\n");
    }
}

