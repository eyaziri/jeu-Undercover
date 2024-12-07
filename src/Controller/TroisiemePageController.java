package Controller;

import Model.Administration.Admin;
import Model.Administration.GestionJoueur;
import Model.Administration.ListeMots;
import Model.Administration.Role;
import Model.GestionJoueur.Civil;
import Model.GestionJoueur.Joueur;
import Model.GestionJoueur.MrWhite;
import Model.GestionJoueur.Undercover;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 */
/*public class TroisiemePageController implements Initializable {

    private Admin admin;
    private GestionJoueur gestionJoueur;
    private int playersRemaining;
    private ArrayList<String> names;
    private int compteur=0;
    private int totalCivil = 0;
    private int totalUndercover = 0;
    private int totalMrWhite = 0;
    
    private ListeMots listeMotPartie = new ListeMots();
    @FXML
    private Button goPage5;

    @FXML
    private TextField nomJoueur;
    @FXML
    private Label joueurPret;
    

    @FXML
    void allerPage5(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/cinquiemePage.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = (Stage) goPage5.getScene().getWindow();
        stage.setTitle("Cinquième Page");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void manipulerNomJoueur(ActionEvent event) throws IOException {
        admin = DeuxiemePageController.admin;
        gestionJoueur = DeuxiemePageController.gestionJoueur;
        
        

        if (names == null || names.isEmpty()) {
            initializePlayerCount(); // Initialisation des joueurs restants et des noms
        }

        playersRemaining = admin.getNombreJoueur();

        String playerName = nomJoueur.getText().trim();
        if (names.contains(playerName)) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Nom déjà utilisé");
        alert.setContentText("Le nom de joueur est déjà utilisé. Veuillez choisir un autre nom.");
        alert.showAndWait();
        return; // Si le nom existe déjà, on arrête l'exécution de la méthode
    }
        if (playersRemaining <= 0 || playerName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un nom valide ou vérifier le nombre de joueurs restant.");
            alert.showAndWait();
            return;
        }

        // Gérer le nom dans la liste des joueurs
        int currentIndex = admin.getNombreJoueur() - playersRemaining;
        if (currentIndex < names.size()) {
            names.set(currentIndex, playerName); // Mettre à jour le nom si le joueur existe déjà dans la liste
        } else {
            names.add(playerName); // Ajouter un nouveau nom si le joueur n'existe pas encore dans la liste
        }

        // Générer un rôle pour le joueur
        Joueur joueurRole = null;
        String role = new Role().donnerRoleAleatoire();
        
        switch (role) {
            case "Civile":
                if (totalCivil < gestionJoueur.getNombreCivil()) {
                    joueurRole = new Civil();
                    joueurRole.setRole("Civile");
                    joueurRole.setNom(playerName);

                    String motCivil = joueurRole.getMot();
                    if (motCivil == null) {
                        listeMotPartie.associerMotDeCivilEtDeUndercover(joueurRole);
                        gestionJoueur.setMotCivil(joueurRole.getMot());
                        gestionJoueur.setMotUndercover(listeMotPartie.getMotUndercover(joueurRole.getMot()));
                    } else {
                        joueurRole.setMot(motCivil);
                    }
                    totalCivil=totalCivil+1;
                    compteur=compteur+1;
                    gestionJoueur.ajouterJoueur(joueurRole);
                    playersRemaining=playersRemaining-1;
                    ouvrirQuatriemePage(joueurRole);
                }
                break;

            case "Undercover":
                if (totalUndercover < gestionJoueur.getNombreUndercover()) {
                    joueurRole = new Undercover();
                    joueurRole.setRole("Undercover");
                    joueurRole.setNom(playerName);

                    String motUndercover = joueurRole.getMot();
                    if (motUndercover == null) {
                        listeMotPartie.associerMotDeCivilEtDeUndercover(joueurRole);
                        gestionJoueur.setMotUndercover(joueurRole.getMot());
                        gestionJoueur.setMotCivil(listeMotPartie.getMotCivil(joueurRole.getMot()));
                    } else {
                        joueurRole.setMot(motUndercover);
                    }
                    totalUndercover=totalUndercover+1;
                    compteur=compteur+1;
                    gestionJoueur.ajouterJoueur(joueurRole);
                    playersRemaining=playersRemaining-1;
                    ouvrirQuatriemePage(joueurRole);
                    
                }
                break;

            case "MrWhite":
                if (totalMrWhite < gestionJoueur.getNombreMrWhite()) {
                    joueurRole = new MrWhite();
                    joueurRole.setNom(playerName);
                    joueurRole.setRole("MrWhite");
                    joueurRole.setMot("Tu es Mr White!");
                    
                    totalMrWhite=totalMrWhite+1;
                    compteur=compteur+1;
                    gestionJoueur.ajouterJoueur(joueurRole);
                    playersRemaining=playersRemaining-1;
                    ouvrirQuatriemePage(joueurRole);
                    
                }
                break;

            default:
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Impossible d'attribuer un rôle valide.");
                alert.showAndWait();
                return;
                
        }

        if (compteur == admin.getNombreJoueur()) {

           
            goPage5.setDisable(false);
            nomJoueur.setDisable(true);
            joueurPret.setText("Tout les joueurs sont recu leur role ");
            gestionJoueur.AffichageListeJoueurs();

        }
        nomJoueur.clear();

        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Information");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Joueur " + playerName + " ajouté avec succès.");
        successAlert.showAndWait();
    }

    private void ouvrirQuatriemePage(Joueur joueur) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/quatriemeRolePage.fxml"));
            Parent root = loader.load();

            QuatriemeRolePageController controller = loader.getController();
            controller.setJoueur(joueur);
            Stage stage = new Stage();
            stage.setTitle("Rôle de " + joueur.getNom());
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible d'ouvrir la page suivante.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void initializePlayerCount() {
        playersRemaining = DeuxiemePageController.admin.getNombreJoueur();
        names = new ArrayList<>();
        for (int i = 0; i < playersRemaining; i++) {
            names.add(""); // Ajouter des chaînes vides pour chaque joueur
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        goPage5.setDisable(true);
    
        goPage5.setOnAction(event -> {
            try {
                allerPage5(event);  // Ou la méthode de votre choix
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}*/



/**
 * FXML Controller class
 */
public class TroisiemePageController implements Initializable {

    private Admin admin;
    private GestionJoueur gestionJoueur;
    private int playersRemaining;
    private int compteur=0;
    private int totalCivil = 0;
    private int totalUndercover = 0;
    private int totalMrWhite = 0;
    private ListeMots listeMotPartie = new ListeMots();
    @FXML
    private Button goPage5;

    @FXML
    private TextField nomJoueur;
     @FXML
    private Label joueurPret;

    @FXML
    void allerPage5(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/cinquiemePage.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = (Stage) goPage5.getScene().getWindow();
        stage.setTitle("Cinquième Page");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void manipulerNomJoueur(ActionEvent event) throws IOException {
        admin = DeuxiemePageController.admin;
        gestionJoueur = DeuxiemePageController.gestionJoueur;

        playersRemaining = admin.getNombreJoueur();

        String playerName = nomJoueur.getText().trim();

        if (playersRemaining <= 0 || playerName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un nom valide ou vérifier le nombre de joueurs restant.");
            alert.showAndWait();
            return;
        }

        // Gérer le nom dans la liste des joueurs
        Joueur joueurRole = null;
        String role = new Role().donnerRoleAleatoire();

        while (true) { // Boucle pour gérer automatiquement un rôle valide
            switch (role) {
                case "Civile":
                    if (totalCivil < gestionJoueur.getNombreCivil()) {
                        joueurRole = new Civil();
                        joueurRole.setRole("Civile");
                        joueurRole.setNom(playerName);

                        String motCivil = joueurRole.getMot();
                        if (motCivil == null) {
                            listeMotPartie.associerMotDeCivilEtDeUndercover(joueurRole);
                            gestionJoueur.setMotCivil(joueurRole.getMot());
                            gestionJoueur.setMotUndercover(listeMotPartie.getMotUndercover(joueurRole.getMot()));
                        } else {
                            joueurRole.setMot(motCivil);
                        }
                        totalCivil++;
                        break; // Rôle trouvé, on sort de la boucle
                    }
                    role = choisirRoleAlternatif(role);
                    break;

                case "Undercover":
                    if (totalUndercover < gestionJoueur.getNombreUndercover()) {
                        joueurRole = new Undercover();
                        joueurRole.setRole("Undercover");
                        joueurRole.setNom(playerName);

                        String motUndercover = joueurRole.getMot();
                        if (motUndercover == null) {
                            listeMotPartie.associerMotDeCivilEtDeUndercover(joueurRole);
                            gestionJoueur.setMotUndercover(joueurRole.getMot());
                            gestionJoueur.setMotCivil(listeMotPartie.getMotCivil(joueurRole.getMot()));
                        } else {
                            joueurRole.setMot(motUndercover);
                        }
                        totalUndercover++;
                        break; // Rôle trouvé, on sort de la boucle
                    }
                    role = choisirRoleAlternatif(role);
                    break;

                case "MrWhite":
                    if (totalMrWhite < gestionJoueur.getNombreMrWhite()) {
                        joueurRole = new MrWhite();
                        joueurRole.setNom(playerName);
                        joueurRole.setRole("MrWhite");
                        joueurRole.setMot("Tu es Mr White!");
                        totalMrWhite++;
                        break; // Rôle trouvé, on sort de la boucle
                    }
                    role = choisirRoleAlternatif(role);
                    break;

                default:
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Impossible d'attribuer un rôle valide.");
                    alert.showAndWait();
                    return;
            }

            if (joueurRole != null) {
                break; // Sortie de la boucle principale si un joueur est bien créé
            }
        }

        compteur++;
        gestionJoueur.ajouterJoueur(joueurRole);
        playersRemaining--;
        ouvrirQuatriemePage(joueurRole);

        if (compteur == admin.getNombreJoueur()) {
            goPage5.setDisable(false);
            nomJoueur.setDisable(true);
            joueurPret.setText("Tous les joueurs ont recu leur role ");

            gestionJoueur.AffichageListeJoueurs();
        }
        nomJoueur.clear();

        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Information");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Joueur " + playerName + " ajouté avec succès.");
        successAlert.showAndWait();
    }
    private String choisirRoleAlternatif(String currentRole) {
        if (!"Civile".equals(currentRole) && totalCivil < gestionJoueur.getNombreCivil()) {
            return "Civile";
        } else if (!"Undercover".equals(currentRole) && totalUndercover < gestionJoueur.getNombreUndercover()) {
            return "Undercover";
        } else if (!"MrWhite".equals(currentRole) && totalMrWhite < gestionJoueur.getNombreMrWhite()) {
            return "MrWhite";
        }
        return null; // Si tous les rôles sont atteints (ne devrait jamais arriver)
    }
    private void ouvrirQuatriemePage(Joueur joueur) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/quatriemeRolePage.fxml"));
            Parent root = loader.load();

            QuatriemeRolePageController controller = loader.getController();
            controller.setJoueur(joueur);
            Stage stage = new Stage();
            stage.setTitle("Rôle de " + joueur.getNom());
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible d'ouvrir la page suivante.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        goPage5.setDisable(true);
    
        goPage5.setOnAction(event -> {
            try {
                allerPage5(event);  // Ou la méthode de votre choix
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}