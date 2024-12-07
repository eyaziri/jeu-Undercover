/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import static Controller.DeuxiemePageController.admin;
import static Controller.DeuxiemePageController.gestionJoueur;
import Model.Administration.GestionJoueur;
import static Model.Administration.GestionJoueur.joueurs;
import Model.GestionJoueur.Gagnant;
import Model.GestionJoueur.Joueur;
import Model.GestionJoueur.Score;
import Model.Vote.Elimination;
import Model.Vote.GestionVotes;
import Model.Vote.PhaseVote;
import Model.Vote.Vote;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eyazi
 */
public class SixiemepageController implements Initializable {

    public static PhaseVote phaseVote=new PhaseVote();
    public static Elimination elimination=new Elimination();
    private GestionVotes gestionVote=new GestionVotes();
    private ArrayList<String>votes;
    private int playersRemaining=0;
    static public Joueur joueur1;
    private int currentPlayerIndex=0;
    public static Gagnant gagnant=new Gagnant();
    public static Score score=new Score();
    
    @FXML
    private TextField caseAvote;

    @FXML
    private TextField nomJoueur;

    @FXML
    private Button gopage7;

    /**
     * Initialise le nombre de joueurs et prépare les votes.
     */
    private void initializePlayerCount() {
        int playersRemaining = gestionJoueur.longueurListe();
        updatePlayerCounterDisplay();
    }

    private void updatePlayerCounterDisplay() {
        if (currentPlayerIndex < gestionJoueur.longueurListe()) {
            Joueur joueur = gestionJoueur.getListeJoueurs().get(currentPlayerIndex);
            nomJoueur.setText(joueur.getNom());
        }
    }
    
    
    
    private void recordVote() throws IOException {
        String voteNom = caseAvote.getText().trim();
        if (voteNom.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Attention", "Veuillez entrer un nom avant de voter.");
            return;
        }

        Joueur joueurVote = gestionJoueur.getListeJoueurs()
                .stream()
                .filter(j -> j.getNom().equalsIgnoreCase(voteNom))
                .findFirst()
                .orElse(null);

        if (joueurVote == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Aucun joueur trouvé avec ce nom.");
            return;
        }

        joueurVote.setnombreDeVotesRecus();
        currentPlayerIndex++;

        if (currentPlayerIndex < gestionJoueur.longueurListe()) {
            updatePlayerCounterDisplay();
            caseAvote.clear();
        } else {
            handleVotesResult();
        }
    }
    
    
private Joueur handleVotesResult() throws IOException {
    Joueur joueurElimine = gestionJoueur.getListeJoueurs()
            .stream()
            .max((j1, j2) -> Integer.compare(j1.getNombreDeVotesRecus(), j2.getNombreDeVotesRecus()))
            .orElse(null);

    if (joueurElimine != null) {
        joueur1=joueurElimine;
        gestionJoueur.supprimerJoueur(joueurElimine);
        admin.setNombreJoueur(admin.getNombreJoueur()-1);
        elimination.ajouterJoueurElimine(joueurElimine);

        if (joueurElimine.getRole().equalsIgnoreCase("MrWhite")) {
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/huitiemePage.fxml"));
            Parent root1 = fxmlLoader.load();
            // Réutiliser l'étape actuelle
            Stage stage = (Stage) gopage7.getScene().getWindow();
            stage.setTitle("troisieme Page");
            stage.setScene(new Scene(root1));
            stage.show(); 
            } else {
            // Sinon, affiche l'alerte d'élimination et réinitialise pour le prochain tour
            showAlert(Alert.AlertType.INFORMATION, "Élimination", "Le joueur " + joueurElimine.getNom() +
                    " est éliminé avec " + joueurElimine.getNombreDeVotesRecus() + " votes. Rôle : " +
                    joueurElimine.getRole());
            
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
            
                if (joueur.getRole().equalsIgnoreCase(gagnant.getRole())) {
                    joueur.setScore(6);
                    System.out.println(joueur.getScore());// Le gagnant obtient 6 points
                } else {
                    joueur.setScore(0);
                    System.out.println(joueur.getScore());// Les autres survivants obtiennent 3 points
                }
                score.getScores().put(joueur, joueur.getScore());
                }

                for (Joueur joueur : elimination.getJoueursElimines()) {
                    if (joueur.getRole().equalsIgnoreCase(gagnant.getRole())) {
                        joueur.setScore(3);
                        System.out.println(joueur.getScore());// Le gagnant obtient 3 points s'il est éliminé
                    } else {
                        joueur.setScore(0);
                        System.out.println(joueur.getScore());// Les autres éliminés obtiennent 0 point
                    }
                    score.getScores().put(joueur, joueur.getScore());
                } 
            
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/neuviemePage.fxml"));
                    Parent root1 = fxmlLoader.load();
                    // Réutiliser l'étape actuelle
                    Stage stage = (Stage) gopage7.getScene().getWindow();
                    stage.setTitle("troisieme Page");
                    stage.setScene(new Scene(root1));
                    stage.show();

                } else if (nombreDeJoueurRestantCivil < nombreDeJoueurRestantUndercover) {
                    System.out.println("Les Undercover ont gagne !");
                    gagnant.determinerGagnant(joueurs);
                    System.out.println("Winners: " + gagnant.getGagnants());
                    for (Joueur joueur : joueurs) {
                    if (joueur.getRole().equalsIgnoreCase(gagnant.getRole())) {
                        joueur.setScore(6);
                        System.out.println(joueur.getScore());// Le gagnant obtient 6 points
                    } else {
                        joueur.setScore(0);
                        System.out.println(joueur.getScore());// Les autres survivants obtiennent 3 points
                    }
                    score.getScores().put(joueur, joueur.getScore());
                }

                for (Joueur joueur : elimination.getJoueursElimines()) {
                    if (joueur.getRole().equalsIgnoreCase(gagnant.getRole())) {
                        joueur.setScore(3);
                        System.out.println(joueur.getScore());// Le gagnant obtient 3 points s'il est éliminé
                    } else {
                        joueur.setScore(0);
                        System.out.println(joueur.getScore());// Les autres éliminés obtiennent 0 point
                    }
                    score.getScores().put(joueur, joueur.getScore());
                } 
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/neuviemePage.fxml"));
                    Parent root1 = fxmlLoader.load();
                    // Réutiliser l'étape actuelle
                    Stage stage = (Stage) gopage7.getScene().getWindow();
                    stage.setTitle("troisieme Page");
                    stage.setScene(new Scene(root1));
                    stage.show();

                }
            } 
            else if ((nombreDeJoueurRestantMrWhite >= 1&&nombreDeJoueurRestantCivil==0&&nombreDeJoueurRestantUndercover==0) || (nombreDeJoueurRestantMrWhite >= 1&&nombreDeJoueurRestantCivil==1 &&nombreDeJoueurRestantUndercover==0) || (nombreDeJoueurRestantMrWhite >= 1&&nombreDeJoueurRestantCivil==0 &&nombreDeJoueurRestantUndercover==1) )
            {
                System.out.println("Les MrWhite ont gagne !");
                gagnant.determinerGagnant(gestionJoueur.getListeJoueurs());
                for (Joueur joueur : joueurs) {
            if (joueur.getRole().equalsIgnoreCase(gagnant.getRole())) {
                joueur.setScore(6);
                System.out.println(joueur.getScore());// Le gagnant obtient 6 points
            } else {
                joueur.setScore(0);
                System.out.println(joueur.getScore());

            }
            score.getScores().put(joueur, joueur.getScore());
        }

        for (Joueur joueur : elimination.getJoueursElimines()) {
            if (joueur.getRole().equalsIgnoreCase(gagnant.getRole())) {
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
                    Stage stage = (Stage) gopage7.getScene().getWindow();
                    stage.setTitle("troisieme Page");
                    stage.setScene(new Scene(root1));
                    stage.show();
            }
            else if (nombreDeJoueurRestantMrWhite >= 1)
            {
                System.out.println("Le jeu continue, car Mr. White est encore en jeu.");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/septiemePage.fxml"));
                    Parent root1 = fxmlLoader.load();
                    // Réutiliser l'étape actuelle
                    Stage stage = (Stage) gopage7.getScene().getWindow();
                    stage.setTitle("troisieme Page");
                    stage.setScene(new Scene(root1));
                    stage.show();
            }

        }
                resetForNextRound();  

        }
        return joueurElimine;
}

    private void resetForNextRound() {
        currentPlayerIndex = 0;
        initializePlayerCount();
    }

    /**
     * Montre une alerte à l'utilisateur.
     */
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
 
    @FXML
    void allerPage7(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/septiemePage.fxml"));
        Parent root1 = fxmlLoader.load();
        // Réutiliser l'étape actuelle
        Stage stage = (Stage) gopage7.getScene().getWindow();
        stage.setTitle("troisieme Page");
        stage.setScene(new Scene(root1));
        stage.show();
        
    }
    
    

    
    


    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializePlayerCount();

        caseAvote.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER -> {
                    
                try {
                    recordVote();
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(SixiemepageController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                    
                }

                default -> {
                }
            }
        });
         gopage7.setOnAction(event -> {
        try {
            allerPage7(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    });

        
    }

}