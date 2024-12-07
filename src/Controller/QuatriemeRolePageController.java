/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.GestionJoueur.Joueur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class QuatriemeRolePageController implements Initializable {

    private Joueur joueur;
    
    @FXML 
        private Button gopage3;
    
    @FXML
     private TextField motJoueur;
    
    @FXML
     private Label nomJoueur;
    
     public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
        nomJoueur.setText(joueur.getNom());

    }
     
    @FXML
    void affichemot(ActionEvent event) {
        System.out.println(joueur.getMot());
        if (joueur != null) {
            motJoueur.setText(joueur.getMot());
        }
    }
    
    @FXML
    void affichenom(ActionEvent event) {
        System.out.println(joueur.getNom());
        if (joueur != null) {
            nomJoueur.setText(joueur.getNom());
        }
    }


    
    @FXML
    void allerpage3(ActionEvent event) throws IOException
    {
      Stage currentStage = (Stage) gopage3.getScene().getWindow();
      currentStage.close();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

         gopage3.setOnAction(event -> {
        
        try {
            allerpage3(event);  // Ou la méthode de votre choix
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
    }    
    
}
