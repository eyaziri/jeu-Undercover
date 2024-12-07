/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

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
import javax.swing.JOptionPane;
import Model.Administration.*;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author eyazi
 */
public class DeuxiemePageController implements Initializable {

    public static Admin admin;
    public static GestionJoueur gestionJoueur ;

  
    @FXML
    private Button allerPage3;

    @FXML
    private Button minusButton;

    @FXML
    private TextField nombreTotJoueur;
    
    @FXML
    private TextField NombreTotCivil;

    @FXML
    private TextField nombreTotUndercover;
   
    @FXML
    private TextField nombreTotMrWhite;

    @FXML
    private Button plusButton;

    @FXML
    void ajouter1(ActionEvent event) {
        try 
        {
            int valeurActuelle = Integer.parseInt(nombreTotJoueur.getText());
            valeurActuelle=valeurActuelle+1;
            nombreTotJoueur.setText(String.valueOf(valeurActuelle));
        } 
        catch (NumberFormatException e) 
        {
            nombreTotJoueur.setText("1");
        }
    }

    @FXML
    void decrementer1(ActionEvent event) {
         try 
        {
            int valeurActuelle = Integer.parseInt(nombreTotJoueur.getText());
            valeurActuelle=valeurActuelle-1;
            nombreTotJoueur.setText(String.valueOf(valeurActuelle));
        } 
        catch (NumberFormatException e) 
        {
            nombreTotJoueur.setText("1");
        }

    }
    
    @FXML
    void manipulerNombreJoueur(ActionEvent event) 
    {
        
        try {
            int playerCount = Integer.parseInt(nombreTotJoueur.getText());
            if (playerCount < 3) {
            showErrorAlert("Le nombre de joueurs doit être au moins de 3.");                return;}
        admin = new Model.Administration.Admin();
        gestionJoueur = new Model.Administration.GestionJoueur();
       
        int nombreJoueur = Integer.parseInt(nombreTotJoueur.getText());
        admin.setNombreJoueur(nombreJoueur); 
        
        gestionJoueur.gestionNombreJoueurs(admin);

        NombreTotCivil.setText(String.valueOf(gestionJoueur.getNombreCivil()));
        nombreTotMrWhite.setText(String.valueOf(gestionJoueur.getNombreMrWhite()));
        nombreTotUndercover.setText(String.valueOf(gestionJoueur.getNombreUndercover()));
        
    } catch (NumberFormatException e) {
        
        JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide pour le nombre de joueurs et superieur a 3.", "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    }

    
    @FXML
    void goPage3(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/troisiemePage.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = (Stage) allerPage3.getScene().getWindow();
        stage.setTitle("deuxiemPage Page");
        stage.setScene(new Scene(root1));
        stage.show();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        allerPage3.setOnAction(event -> {
        
        try {
            goPage3(event);  
        } catch (IOException e) {
            e.printStackTrace();
        }
          });
        
        plusButton.setOnAction(event -> {
        ajouter1(event);  
        });
        
        minusButton.setOnAction(event -> {
        decrementer1(event);  
        }); 
        
        
        
        
    }
     private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText(message);
        alert.showAndWait();
    }
    
     public Admin getAdmin() {
        return admin;
    }
    
     public GestionJoueur getGestionJoueur() {
        return gestionJoueur;
    }
    
    
    
}
