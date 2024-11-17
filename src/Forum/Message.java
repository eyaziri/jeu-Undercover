/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Forum;

import GestionJoueur.Joueur;
import java.time.LocalDate;

/**
 *
 * @author eyazi
 */
public class Message {
    private String contenu;
    private LocalDate datePublication;
    private Joueur auteur;

    public Message(String contenu, Joueur auteur) {
        this.contenu = contenu;
        this.auteur = auteur;
        this.datePublication = LocalDate.now(); 
    }

 
    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
 
    public LocalDate getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }
    
    public Joueur getAuteur() {
        return auteur;
    }

    public void setAuteur(Joueur auteur) {
        this.auteur = auteur;
    }
    
    public void afficherMessage()
    {
        System.out.println("le message : "+this.contenu+" ,    est envoye par : "+this.auteur.getNom() + ",   le : " +this.datePublication);
    }
    
    @Override
    public String toString() {
        return "Le message : " + contenu + " , est envoyé par : " + 
               (auteur != null ? auteur.getNom() : "Anonyme") + 
               " , le : " + datePublication;
    }
}
