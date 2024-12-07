/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Forum;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author user
 */
public record Discussion(List<Message> messages) {

    // Constructeur par défaut pour initialiser la liste de messages
    public Discussion() {
        this(new ArrayList<>());
    }

    public void ajouterMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getMessages() {
        return new ArrayList<>(messages); 
    }
    
    public void afficherMessages() 
    {
    if (messages.isEmpty()) {
        System.out.println("Aucun message dans la discussion.");
    } else {
        for (Message message : messages) {
            message.afficherMessage();  
        }
    }
    
    }
    
    public String AfficherMessages() {
    if (messages.isEmpty()) {
        return "Aucun message dans la discussion.";
    } else {
        StringBuilder message = new StringBuilder();
        for (Message msg : messages) {
            message.append(msg.toString()).append("\n"); 
        }
        return message.toString();
    }
    }
    
}
