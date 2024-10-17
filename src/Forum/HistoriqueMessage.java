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
public class HistoriqueMessage {
    private ArrayList<Message>historique;
   
    public  HistoriqueMessage(){
        this.historique= new ArrayList<>();  
}
    public void enregisterMessage(Message message){
        historique.add(message);
    }
    public List<Message> getHistorique(){
        return historique;
}
}
