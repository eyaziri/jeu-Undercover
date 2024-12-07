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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PremierPageController implements Initializable {

    @FXML
    private Button deuxiemeButton;

    @FXML
    private Button premierBoutton;

    @FXML
    private AnchorPane premierPage;

    @FXML
    private void allerApage2(ActionEvent event) throws IOException {
        // Charger la première page
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/deuxiemePage.fxml"));
        Parent root1 = fxmlLoader.load();
        // Réutiliser l'étape actuelle
        Stage stage = (Stage) premierBoutton.getScene().getWindow();
        stage.setTitle("Deuxième Page");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    private void allerAuPage2b(ActionEvent event) throws IOException {
        // Charger la deuxième page
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/deuxiemePageB.fxml"));
        Parent root1 = fxmlLoader.load();
        // Réutiliser l'étape actuelle
        Stage stage = (Stage) deuxiemeButton.getScene().getWindow();
        stage.setTitle("Deuxième Page B");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        premierBoutton.setOnAction(event -> {
        
        try {
            allerApage2(event);  // Ou la méthode de votre choix
        } catch (IOException e) {
            e.printStackTrace();
        }
    });

    // Définir l'action pour le deuxième bouton
    deuxiemeButton.setOnAction(event -> {
        
        try {
            allerAuPage2b(event);  // Ou la méthode de votre choix
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
    
    }
}
