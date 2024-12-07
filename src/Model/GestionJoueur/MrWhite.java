/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.GestionJoueur;
import java.util.Scanner;

/**
 *
 * @author eyazi
 */

public final class MrWhite extends Joueur {
    public MrWhite() {
        super();
        /*System.out.println("Donner le nom de joueur :");
        Scanner sc = new Scanner(System.in);
        String n = sc.nextLine();
        setNom(n);
        this.mot = "YOU ARE MrsWhite !!!";*/  
    }

    public boolean devinerMot(String motCivil) {
        System.out.println("Donner moi le mot à votre avis :");
        Scanner sc = new Scanner(System.in);
        String motMrsWhite = sc.next();
        return (motMrsWhite.equals(motCivil));  
        
    }
}
