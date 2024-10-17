/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestionJoueur;
import Administration.*;
import Forum.*;
import java.time.LocalDate;

/**
 *
 * @author eyazi
 */
import java.util.ArrayList;
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
    
     public Joueur() {
        System.out.println("Donner le nom de joueur :");
        Scanner sc = new Scanner(System.in);
        String n = sc.nextLine();
        this.nom = n;
        this.score = 0; // Initialisation du score à 0
        this.idJoueur = ++compteurId; // Incrémente le compteur d'ID pour chaque joueur
    }
    public String getNom()
    {
        return nom;
    }
    public int getIdJoueur()
    {
        return idJoueur;
    }
    public String getRole()
    {
        return role.getNomRole();
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
    
    public void setMot(String mot)
    {
        this.mot=mot;
    }
    
    public String getMot()
    {
        return mot;
    }
    
    
    public void setRole()
    {
        Role role=new Role();
        this.role.setNomRole();
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
     
     
     
     
     public void Voter()
    {
        
        
    }
    public void estEliminer()
    {
        
    }

    
    
    
    
}
