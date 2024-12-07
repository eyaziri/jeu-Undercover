/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import static Controller.DeuxiemePageController.gestionJoueur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eyazi
 */
public class SeptiemePageController implements Initializable {

    @FXML
    private Button allerpage5;
    @FXML
    private TableColumn<String,String> tableViewJoueursRestants;
  
    @FXML
    private javafx.scene.control.TableView<String> tableView;
    
    @FXML
    void gopage5(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/cinquiemePage.fxml"));
        Parent root1 = fxmlLoader.load();
        // Réutiliser l'étape actuelle
        Stage stage = (Stage) allerpage5.getScene().getWindow();
        stage.setTitle("cinquieme page");
        stage.setScene(new Scene(root1));
        stage.show();
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         if (tableView == null) {
        System.out.println("TableView est null. Vérifiez votre fichier FXML.");
        return;
    }

    ObservableList<String> nomsJoueurs = FXCollections.observableArrayList(gestionJoueur.getListeNomsJoueurs());
    tableViewJoueursRestants.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
    tableView.setItems(nomsJoueurs);
    
        allerpage5.setOnAction(event -> {
            try {
                gopage5(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    
}
