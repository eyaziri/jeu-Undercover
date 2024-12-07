package Controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */


import static Controller.DeuxiemePageController.gestionJoueur;
import static Controller.SixiemepageController.elimination;
import static Controller.SixiemepageController.gagnant;
import static Controller.SixiemepageController.score;
import static Model.Administration.GestionJoueur.joueurs;
import Model.GestionJoueur.Joueur;
import Model.GestionJoueur.Score;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eyazi
 */
public class OnziemePageController implements Initializable {

    
    @FXML
    private Button allerpage1;

    @FXML
    private Button allerpage12;
    
    @FXML
    private TableColumn<Joueur,String> RoleColmun;
    @FXML
    private TableColumn<Joueur,String> RoleColmun2;

    @FXML
    private TableColumn<Joueur, String> nomJoueur;

    @FXML
    private TableColumn<Joueur, Integer> scoreColumn;

    @FXML
    private TableView<Joueur> joueurScore;
    @FXML
    private TableColumn<Joueur, String> nomJoueur2;

    @FXML
    private TableColumn<Joueur, Integer> scoreColumn2;

    @FXML
    private TableView<Joueur> joueurScore2;

    ObservableList<Joueur> list=FXCollections.observableArrayList(gestionJoueur.getListeJoueurs());
    ObservableList<Joueur> list2=FXCollections.observableArrayList(elimination.getJoueursElimines());
    

   
    
    @FXML
    void gopage1(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/premierPage.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = (Stage) allerpage1.getScene().getWindow();
        stage.setTitle("premierpage");
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
    @FXML
    void gopage12(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/douzeiemePage.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = (Stage) allerpage12.getScene().getWindow();
        stage.setTitle("douzeiemepage");
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        nomJoueur.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        scoreColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getScore()).asObject());
        RoleColmun.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRole()));
        // Ajouter les joueurs au TableView
        joueurScore.setItems(list);
        
        
        nomJoueur2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        scoreColumn2.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getScore()).asObject());
        RoleColmun2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRole()));
        // Ajouter les joueurs au TableView
        joueurScore2.setItems(list2);
        
        
        allerpage1.setOnAction(event -> {
        
        try {
            gopage1(event); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
          allerpage12.setOnAction(event -> {
        
        try {
            gopage12(event);  
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
    }    
    
}
