/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import java.awt.event.MouseEvent;
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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eyazi
 */
public class CinquiemeBPageController implements Initializable {

    @FXML 
        private Button passerAuVote;
    
    @FXML
        private TextArea texteDescription;
    
    @FXML
    void Affichage(){
        
        texteDescription.setText(""); 
        texteDescription.setText(CinquiemePageController.discussion.AfficherMessages());
    }
    
    @FXML
    void allerPage6(ActionEvent event) throws IOException
    {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/sixiemepage.fxml"));
        Parent root1 = fxmlLoader.load();
        // Réutiliser l'étape actuelle
        Stage stage = (Stage) passerAuVote.getScene().getWindow();
        stage.setTitle("cinquieme Page B");
        stage.setScene(new Scene(root1));
        stage.show();
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Affichage();
        passerAuVote.setOnAction(event -> {
        
        try {
            allerPage6(event);  // Ou la méthode de votre choix
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
    }    
    
}
