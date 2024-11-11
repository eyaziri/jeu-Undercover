/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestionJoueur;
import Administration.ListeMots;
import Administration.Role;
import Forum.*;
import java.time.LocalDate;

/**
 *
 * @author eyazi
 */

import java.util.Scanner;

public abstract class Joueur {
    protected static int compteurId = 0;
    protected int idJoueur;
    protected String nom;
    protected int score;
    Role role;
    protected String mot;
    protected int nombreDeVotesRecus=0;
    protected boolean aVote = false;
    
     public Joueur() 
     {
        this.role = new Role();
        this.score = 0; 
        this.idJoueur = ++compteurId; 
    }
    public String getNom()
    {
        return nom;
    }
    public int getIdJoueur()
    {
        return idJoueur;
    }
    
    public void setNom(String nom)
    {
        this.nom=nom;
    }
    public void setScore(int n)
    {
        this.score=n;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public void setMot(String mot) {
        this.mot = mot; 
    }
    
    public String getMot()
    {
        return mot;
    }
    
    public void setRole(String nomRole)
    {
        this.role = new Role(); 
        this.role.setNomRole(nomRole);
    }
    
    public String getRole()
    {
        return role.getNomRole();
    }
      public int getNombreDeVotesRecus() {
        return this.nombreDeVotesRecus;
    }
      
    public void EcrireMessage() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bonjour " + this.nom + ", écrivez votre message :");

        String contenu = sc.nextLine();

        Message msg = new Message(contenu, this);  
        msg.setDatePublication(LocalDate.now());  
        
        System.out.println("Votre message a été publié le " + msg.getDatePublication());
    }
    
    public void setnombreDeVotesRecus()
    {
        this.nombreDeVotesRecus=this.nombreDeVotesRecus+1;
    }
    
     public void setAVote(boolean aVote) 
     {
        this.aVote = aVote;
     }
     
     public boolean isAVote() 
     {
        return aVote;
     }
     
     
     public void estEliminer(ListeMots listeMots) {
        if (this.getRole().equalsIgnoreCase("Civil") || this.getRole().equalsIgnoreCase("Undercover")) {
            System.out.println("Le joueur " + this.nom + " est maintenant éliminé car il a reçu le plus de votes.");
        } else if (this.getRole().equalsIgnoreCase("Mr.White")) {
            Scanner sc = new Scanner(System.in);
            String motDevine;
            System.out.println("Vous êtes Mr. White. Essayez de deviner le mot associé : ");
            motDevine = sc.nextLine();
            
            if (motDevine.equalsIgnoreCase(this.mot)) {
                System.out.println("Félicitations ! Vous avez deviné correctement, vous n'êtes pas éliminé.");
            } else {
                System.out.println("Échec. Le mot était " + this.mot + ". Vous êtes éliminé.");
            }
        }
    }

    
}
