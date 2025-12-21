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

/**
 * FXML Controller class
 */
public class TroisiemePageController implements Initializable {

    private Admin admin;
    private GestionJoueur gestionJoueur;
    private int playersRemaining;
    private int compteur = 0;
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
        // ✅ CORRECTION : Appeler donnerDecorateurUnique() ici aussi pour être sûr
        /*System.out.println("🔍 allerPage5 appelé - Attribution des pouvoirs");
        if (DeuxiemePageController.gestionJoueur != null) {
            DeuxiemePageController.gestionJoueur.donnerDecorateurUnique();
        } else {
            System.out.println("❌ ERREUR: gestionJoueur est null");
        }*/
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/cinquiemePage.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = (Stage) goPage5.getScene().getWindow();
        stage.setTitle("Cinquième Page");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void manipulerNomJoueur(ActionEvent event) throws IOException {
        // ✅ CORRECTION : Vérification null plus robuste
        if (DeuxiemePageController.admin == null || DeuxiemePageController.gestionJoueur == null) {
            System.out.println("❌ ERREUR: admin ou gestionJoueur est null");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Erreur d'initialisation. Veuillez redémarrer le jeu.");
            alert.showAndWait();
            return;
        }

        admin = DeuxiemePageController.admin;
        gestionJoueur = DeuxiemePageController.gestionJoueur;

        playersRemaining = admin.getNombreJoueur();
        String playerName = nomJoueur.getText().trim();

        // ✅ DEBUG : Logs de débogage détaillés
        System.out.println("🔍 DEBUG: manipulerNomJoueur appelé");
        System.out.println("🔍 DEBUG: compteur = " + compteur + ", admin.getNombreJoueur() = " + admin.getNombreJoueur());
        System.out.println("🔍 DEBUG: totalCivil = " + totalCivil + "/" + gestionJoueur.getNombreCivil());
        System.out.println("🔍 DEBUG: totalUndercover = " + totalUndercover + "/" + gestionJoueur.getNombreUndercover());
        System.out.println("🔍 DEBUG: totalMrWhite = " + totalMrWhite + "/" + gestionJoueur.getNombreMrWhite());
        System.out.println("🔍 DEBUG: Nom joueur = '" + playerName + "'");

        if (playersRemaining <= 0 || playerName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un nom valide ou vérifier le nombre de joueurs restant.");
            alert.showAndWait();
            return;
        }

        // Générer un rôle pour le joueur
        Joueur joueurRole = null;
        String role = new Role().donnerRoleAleatoire();
        System.out.println("🔍 DEBUG: Rôle aléatoire généré = " + role);

        boolean roleAttribue = false;
        int tentatives = 0;
        final int MAX_TENTATIVES = 10; // Éviter une boucle infinie

        while (!roleAttribue && tentatives < MAX_TENTATIVES) {
            tentatives++;
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
                        roleAttribue = true;
                        System.out.println("🔍 DEBUG: Civile attribué à " + playerName);
                    } else {
                        role = choisirRoleAlternatif(role);
                        System.out.println("🔍 DEBUG: Civile complet, nouvel essai avec: " + role);
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
                        totalUndercover++;
                        roleAttribue = true;
                        System.out.println("🔍 DEBUG: Undercover attribué à " + playerName);
                    } else {
                        role = choisirRoleAlternatif(role);
                        System.out.println("🔍 DEBUG: Undercover complet, nouvel essai avec: " + role);
                    }
                    break;

                case "MrWhite":
                    if (totalMrWhite < gestionJoueur.getNombreMrWhite()) {
                        joueurRole = new MrWhite();
                        joueurRole.setNom(playerName);
                        joueurRole.setRole("MrWhite");
                        joueurRole.setMot("Tu es Mr White!");
                        totalMrWhite++;
                        roleAttribue = true;
                        System.out.println("🔍 DEBUG: MrWhite attribué à " + playerName);
                    } else {
                        role = choisirRoleAlternatif(role);
                        System.out.println("🔍 DEBUG: MrWhite complet, nouvel essai avec: " + role);
                    }
                    break;

                default:
                    System.out.println("❌ ERREUR: Rôle inconnu: " + role);
                    role = new Role().donnerRoleAleatoire();
                    break;
            }
        }

        if (!roleAttribue || joueurRole == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Impossible d'attribuer un rôle valide après " + tentatives + " tentatives.");
            alert.showAndWait();
            return;
        }

        compteur++;
        gestionJoueur.ajouterJoueur(joueurRole);
        playersRemaining--;
        ouvrirQuatriemePage(joueurRole);

        System.out.println("🔍 DEBUG: Après ajout - compteur = " + compteur + ", admin.getNombreJoueur() = " + admin.getNombreJoueur());

        // ✅ CORRECTION : Vérification améliorée
        if (compteur >= admin.getNombreJoueur()) {
            System.out.println("🔍 ✅ CONDITION REMPLIE: Tous les joueurs ajoutés!");
            System.out.println("🔍 Attribution des pouvoirs spéciaux...");
            
            // Appeler donnerDecorateurUnique
            gestionJoueur.donnerDecorateurUnique();
            
            goPage5.setDisable(false);
            nomJoueur.setDisable(true);
            joueurPret.setText("Tous les joueurs ont reçu leur rôle et les pouvoirs sont attribués!");

            gestionJoueur.AffichageListeJoueurs();
        } else {
            System.out.println("🔍 ❌ CONDITION NON REMPLIE: compteur (" + compteur + ") < admin.getNombreJoueur() (" + admin.getNombreJoueur() + ")");
        }

        nomJoueur.clear();

        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Information");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Joueur " + playerName + " ajouté avec succès. Rôle: " + joueurRole.getRole());
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
        // Si tous les rôles sont complets, on retourne un rôle aléatoire
        return new Role().donnerRoleAleatoire();
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
        System.out.println("🔍 TroisiemePageController initialisé");
        goPage5.setDisable(true);

        goPage5.setOnAction(event -> {
            try {
                allerPage5(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}