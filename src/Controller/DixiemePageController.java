package Controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */


import static Controller.DeuxiemePageController.gestionJoueur;
import static Controller.SixiemepageController.elimination;
import static Controller.SixiemepageController.gagnant;
import static Controller.SixiemepageController.joueur1;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eyazi
 */
public class DixiemePageController implements Initializable {

    @FXML
    private Button continuJeuBoutton;

    @FXML
    void ContinuerLeJeu(ActionEvent event) throws IOException {
        joueur1.estEliminer(gestionJoueur);
        joueur1.setEstVivant();
        //elimination.ajouterJoueurElimine(joueur1);
        gestionJoueur.supprimerJoueur(joueur1);
        gagnant.determinerGagnant(gestionJoueur.getListeJoueurs());
        
        
            int nombreDeJoueurRestantCivil = 0;
            int nombreDeJoueurRestantUndercover = 0;
            int nombreDeJoueurRestantMrWhite = 0;

            for (Joueur joueur : gestionJoueur.getListeJoueurs()) {
                if (joueur.getRole().equalsIgnoreCase("Civile")) {
                    nombreDeJoueurRestantCivil++;
                } else if (joueur.getRole().equalsIgnoreCase("Undercover")) {
                    nombreDeJoueurRestantUndercover++;
                } else if (joueur.getRole().equalsIgnoreCase("MrWhite")) {
                    nombreDeJoueurRestantMrWhite++;
                }
            }

            if (nombreDeJoueurRestantMrWhite == 0) {
                if (nombreDeJoueurRestantCivil >= nombreDeJoueurRestantUndercover) {
                    System.out.println("Les Civils ont gagne !");
                    gagnant.determinerGagnant(gestionJoueur.getListeJoueurs());
                    System.out.println("Winners: " + gagnant.getGagnants());
                    for (Joueur joueur : joueurs) {
            
                if (joueur.getRole().equalsIgnoreCase(gagnant.getRole1())) {
                    joueur.setScore(6);
                    System.out.println(joueur.getScore());// Le gagnant obtient 6 points
                } else {
                    joueur.setScore(0);
                    System.out.println(joueur.getScore());// Les autres survivants obtiennent 3 points
                }
                score.getScores().put(joueur, joueur.getScore());
                }

                for (Joueur joueur : elimination.getJoueursElimines()) {
                    if (joueur.getRole().equalsIgnoreCase(gagnant.getRole1())) {
                        joueur.setScore(3);
                        System.out.println(joueur.getScore());// Le gagnant obtient 3 points s'il est éliminé
                    } else {
                        joueur.setScore(0);
                        System.out.println(joueur.getScore());// Les autres éliminés obtiennent 0 point
                    }
                    score.getScores().put(joueur, joueur.getScore());
                } 
                            } else if (nombreDeJoueurRestantCivil < nombreDeJoueurRestantUndercover) {
                    System.out.println("Les Undercover ont gagne !");
                    gagnant.determinerGagnant(joueurs);
                    System.out.println("Winners: " + gagnant.getGagnants());
                    for (Joueur joueur : joueurs) {
                    if (joueur.getRole().equalsIgnoreCase(gagnant.getRole1())) {
                        joueur.setScore(6);
                        System.out.println(joueur.getScore());// Le gagnant obtient 6 points
                    } else {
                        joueur.setScore(0);
                        System.out.println(joueur.getScore());// Les autres survivants obtiennent 3 points
                    }
                    score.getScores().put(joueur, joueur.getScore());
                }

                for (Joueur joueur : elimination.getJoueursElimines()) {
                    if (joueur.getRole().equalsIgnoreCase(gagnant.getRole1())) {
                        joueur.setScore(3);
                        System.out.println(joueur.getScore());// Le gagnant obtient 3 points s'il est éliminé
                    } else {
                        joueur.setScore(0);
                        System.out.println(joueur.getScore());// Les autres éliminés obtiennent 0 point
                    }
                    score.getScores().put(joueur, joueur.getScore());
                } 
                                } 
            else if ((nombreDeJoueurRestantMrWhite >= 1&&nombreDeJoueurRestantCivil==0&&nombreDeJoueurRestantUndercover==0) || (nombreDeJoueurRestantMrWhite >= 1&&nombreDeJoueurRestantCivil==1 &&nombreDeJoueurRestantUndercover==0) || (nombreDeJoueurRestantMrWhite >= 1&&nombreDeJoueurRestantCivil==0 &&nombreDeJoueurRestantUndercover==1) )
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
 }


        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/neuviemePage.fxml"));
        Parent root1 = fxmlLoader.load();
        // Réutiliser l'étape actuelle
        Stage stage = (Stage) continuJeuBoutton.getScene().getWindow();
        stage.setTitle("9eme page");
        stage.setScene(new Scene(root1));
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        continuJeuBoutton.setOnAction(event -> {
        try {
            ContinuerLeJeu(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    });

    }    
    
}
