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
        return messages;
    }
}
