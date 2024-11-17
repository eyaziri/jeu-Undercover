/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Forum;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author user
 */
public class Discussion {
    private List<Message> messages;

    public Discussion() {
        this.messages = new ArrayList<>();
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
