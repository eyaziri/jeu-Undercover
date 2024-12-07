/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Administration.GestionJoueur;
import Model.Forum.Discussion;
import Model.Forum.Forum;
import Model.Forum.HistoriqueMessage;
import Model.Forum.Message;
import Model.GestionJoueur.Joueur;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eyazi
 */
public class CinquiemePageController implements Initializable {

    
         private ArrayList<String>descriptions;
         private int playersRemaining ;
         private HistoriqueMessage historiqueMessage=new HistoriqueMessage();
         private Forum forum=new Forum();
         public static Discussion discussion = new Discussion();
         private int currentPlayerIndex = 0;
         
         
         
    private void initializePlayerCount() {
        int nombreJoueurs = DeuxiemePageController.admin.getNombreJoueur();
        descriptions = new ArrayList<>();
        for (int i = 0; i < nombreJoueurs; i++) {
            descriptions.add(""); // Initialiser les descriptions à vide
        }
        updatePlayerCounterDisplay();
    }
    
   
    @FXML
    private TextField nomJoueuer;
    
    @FXML
    private TextField caseEcrire;
    
    @FXML 
    private Button tableDiscussion;
    
   private void updatePlayerCounterDisplay() {
        if (currentPlayerIndex < DeuxiemePageController.gestionJoueur.longueurListe()) {
            String nomJoueur = DeuxiemePageController.gestionJoueur
                    .getListeJoueurs()
                    .get(currentPlayerIndex)
                    .getNom();
            caseEcrire.setPromptText("Description pour " + nomJoueur);
            nomJoueuer.setText(nomJoueur);
        }
    }

    @FXML
    void ecritureDescription(ActionEvent event) {
        String description = caseEcrire.getText().trim();
        
        if (!description.isEmpty() && currentPlayerIndex < descriptions.size()) {
            descriptions.set(currentPlayerIndex, description);

            Joueur joueur = DeuxiemePageController.gestionJoueur
                    .getListeJoueurs()
                    .get(currentPlayerIndex);
            // Ajouter le message pour ce joueur
            Message msg = joueur.EcrireMessages(historiqueMessage, description);
            
            forum.creerDiscussion(discussion);
            
            discussion.ajouterMessage(msg);

            // Préparer pour le joueur suivant
            currentPlayerIndex++;
            caseEcrire.clear();

            if (currentPlayerIndex < descriptions.size()) {
                updatePlayerCounterDisplay();
            } else {
                // Tous les joueurs ont écrit leur description
                discussion.afficherMessages();
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Tous les joueurs ont ajouté leur description.");
                successAlert.showAndWait();

                // Activer le bouton pour passer à la page suivante
                tableDiscussion.setDisable(false);
                caseEcrire.setDisable(true);
            }
        }
    }
    
    
    @FXML
    void allerpage5b(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/cinquiemeBPage.fxml"));
        Parent root1 = fxmlLoader.load();
        // Réutiliser l'étape actuelle
        Stage stage = (Stage) tableDiscussion.getScene().getWindow();
        stage.setTitle("cinquieme Page");
        stage.setScene(new Scene(root1));
        stage.show();
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         initializePlayerCount();
         tableDiscussion.setDisable(true);
        
         tableDiscussion.setOnAction(event -> {
        
        try {
            allerpage5b(event);  // Ou la méthode de votre choix
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
    }    
    
}
