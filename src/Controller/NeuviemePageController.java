/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import static Controller.DeuxiemePageController.gestionJoueur;
import static Controller.SixiemepageController.elimination;
import static Controller.SixiemepageController.gagnant;
import static Controller.SixiemepageController.score;
import static Model.Administration.GestionJoueur.joueurs;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eyazi
 */
public class NeuviemePageController implements Initializable {

    @FXML 
    private Button page11;
    
     @FXML
    private Label RoleGagnant;

   
    @FXML
    void allerpage11(ActionEvent event) throws IOException{
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/OnziemePage.fxml"));
        Parent root1 = fxmlLoader.load();
        // Réutiliser l'étape actuelle
        Stage stage = (Stage) page11.getScene().getWindow();
        stage.setTitle("cinquieme Page B");
        stage.setScene(new Scene(root1));
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RoleGagnant.setText(gagnant.getRole1());
        if(gagnant.getRole1().equalsIgnoreCase("MrWhite"))
        {
            System.out.println("Les MrWhite ont gagne !");
                gagnant.determinerGagnant(gestionJoueur.getListeJoueurs());
                for (Joueur joueur : joueurs) {
            if (joueur.getRole().equalsIgnoreCase(gagnant.getRole1())) {
                joueur.setScore(6);
                System.out.println(joueur.getScore());// Le gagnant obtient 6 points
            } else {
                joueur.setScore(0);
                System.out.println(joueur.getScore());

            }
            score.getScores().put(joueur, joueur.getScore());
                }
                 for (Joueur joueur : elimination.getJoueursElimines()) {
            if (joueur.getRole().equalsIgnoreCase(gagnant.getRole1())) {
                joueur.setScore(3);
                System.out.println(joueur.getScore());
            } else {
                joueur.setScore(0); 
                System.out.println(joueur.getScore());
            }
            score.getScores().put(joueur, joueur.getScore());
        } 
        }
        page11.setOnAction(event -> {
        
        try {
            allerpage11(event);  // Ou la méthode de votre choix
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
    }    
    
}
