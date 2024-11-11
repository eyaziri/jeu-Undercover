/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestionJoueur;

import java.util.ArrayList;
import Administration.*;
import java.util.Scanner;

/**
 *
 * @author eyazi
 */
public class Civil extends Joueur{
    
    public Civil() {
        super();
        System.out.println("Donner le nom de joueur :");
        Scanner sc = new Scanner(System.in);
        String n = sc.nextLine();
        this.nom = n;
        
    }
     

   
    
    
}
