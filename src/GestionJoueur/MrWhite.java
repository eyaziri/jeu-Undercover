/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestionJoueur;
import java.util.Scanner;

/**
 *
 * @author eyazi
 */
public class MrWhite extends Joueur {
    public MrWhite() {
        super();
        this.mot="YOU ARE MrWhite !!!";
        System.out.println("Donner le nom de joueur :");
        Scanner sc = new Scanner(System.in);
        String n = sc.nextLine();
        this.nom = n;
    }
       
      public boolean devinerMot(String motCivil) {
        System.out.println("donner moi le mot a votre avis :");
        Scanner sc = new Scanner(System.in);
        String motMrWhite=sc.next();
        return (motMrWhite.equals(motCivil)) ;
      }
    
   
}
