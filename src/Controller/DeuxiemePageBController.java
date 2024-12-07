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
import javafx.stage.Stage;

public class DeuxiemePageBController implements Initializable{
    
   @FXML
   private Button bouttonJouer;
   
   @FXML
   void switchToSceneAccueil(ActionEvent event) throws IOException{
       
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/premierPage.fxml"));
        Parent root1 = fxmlLoader.load();
        // Réutiliser l'étape actuelle
        Stage stage = (Stage) bouttonJouer.getScene().getWindow();
        stage.setTitle("Deuxième Page B");
        stage.setScene(new Scene(root1));
        stage.show();
       
       
   }
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        bouttonJouer.setOnAction(event -> {
        
        try {
            switchToSceneAccueil(event);  // Ou la méthode de votre choix
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
    
    }
    
    
    
    
    
}