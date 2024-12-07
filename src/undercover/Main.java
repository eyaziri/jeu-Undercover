/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package undercover;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author eyazi
 * 
 */


public class Main extends Application {
    public Main() {
    }
    
    @Override
public void start(Stage primaryStage) throws Exception{
    
    try {
        Parent root = FXMLLoader.load(getClass().getResource("/views/premierPage.fxml"));
       
        Scene scene = new Scene(root);
        primaryStage.setTitle("Premier Page");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        String filepath = "C:\\Users\\eyazi\\Downloads\\Undercoveroff\\jazz.wav.wav";
        PlayMusic(filepath);
        
        
    } catch (IOException e) { 
        System.err.println("Erreur : Impossible de charger le fichier FXML."); 
    }
}


    public static void main(String[] args) {
                launch(args);

    }
    public static void PlayMusic (String location)
    {
        try{
            File musicPath = new File(location);
            if(musicPath.exists())
            {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                
            } else {
                System.out.println("file not found");
            }
        }catch(Exception e )
        {
            System.out.println(e);
        }
    }
    
}
