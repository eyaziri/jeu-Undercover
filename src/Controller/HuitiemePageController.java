package Controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */


import static Controller.DeuxiemePageController.gestionJoueur;
import static Controller.SixiemepageController.elimination;
import static Controller.SixiemepageController.gagnant;
import static Controller.SixiemepageController.phaseVote;
import static Controller.SixiemepageController.score;
import static Model.Administration.GestionJoueur.joueurs;
import Model.GestionJoueur.Gagnant;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eyazi
 */
public class HuitiemePageController implements Initializable {

    private boolean MotEstJuste;
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private Button bouttonOk;

    @FXML
    private TextField motDevine;
    @FXML
    void devineMot(ActionEvent event) {

        String mot="";
         for (Joueur joueur : joueurs) 
                {
                    if (joueur.getRole().equalsIgnoreCase("civile")) 
                    {
                        mot = joueur.getMot();
                        break;
                    }      
                }
        String motJuste=motDevine.getText().trim();
        if (mot != null && motJuste != null && motJuste.equalsIgnoreCase(mot)) 
                {
                    gagnant.ajouterGagnant(SixiemepageController.joueur1);
                    phaseVote.terminerPhase();
                    MotEstJuste=true;
                    
                } 
        else{
            MotEstJuste=false;
        }

    }

    @FXML
    void allerPage(ActionEvent event) throws IOException {
        if(MotEstJuste==true)
        {
            gestionJoueur.ajouterJoueur(SixiemepageController.joueur1);
            elimination.supprimerJoueur(SixiemepageController.joueur1);
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/neuviemePage.fxml"));
        Parent root1 = fxmlLoader.load();
        // Réutiliser l'étape actuelle
        Stage stage = (Stage) bouttonOk.getScene().getWindow();
        stage.setTitle("neuvieme Page");
        stage.setScene(new Scene(root1));
        stage.show();
        
        }
        else{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/dixiemePage.fxml"));
        Parent root1 = fxmlLoader.load();
        // Réutiliser l'étape actuelle
        Stage stage = (Stage) bouttonOk.getScene().getWindow();
        stage.setTitle("dixieme Page");
        stage.setScene(new Scene(root1));
        stage.show();
            
        }
            

    }

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       bouttonOk.setOnAction(event -> {
        try {
            allerPage(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
    }    
    
}
