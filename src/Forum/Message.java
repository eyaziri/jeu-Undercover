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

    // Constructor to initialize Message with all fields
    public Message(String contenu, Joueur auteur) {
        this.contenu = contenu;
        this.auteur = auteur;
        this.datePublication = LocalDate.now(); // Set current date when message is created
    }

    // Getter for contenu
    public String getContenu() {
        return contenu;
    }

    // Setter for contenu
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    // Getter for datePublication
    public LocalDate getDatePublication() {
        return datePublication;
    }

    // Optional setter for datePublication if you want to change the date later
    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }

    // Getter for auteur
    public Joueur getAuteur() {
        return auteur;
    }

    // Setter for auteur
    public void setAuteur(Joueur auteur) {
        this.auteur = auteur;
    }
}
