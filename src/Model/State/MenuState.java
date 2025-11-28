package Model.State;

import java.util.Scanner;
import Logging.LoggerSingleton;
import Model.Administration.GestionPartie;

public class MenuState implements GameState {

    @Override
    public void entrerEtat(GestionPartie partie) {
        System.out.println("\n=== MENU PRINCIPAL ===");
        LoggerSingleton.getInstance().log("STATE", "Entrée dans l'état MenuState");
        System.out.println("🔍 DEBUG: MenuState -> DistributionRoleState");
        partie.changerEtat(new DistributionRoleState());
    }

    @Override
    public void executerEtat(GestionPartie partie) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1 - Règles du jeu");
        System.out.println("2 - Démarrer la partie");
        System.out.println("3 - Quitter");

        int choix = sc.nextInt();
        LoggerSingleton.getInstance().log("STATE", "Choix utilisateur dans MenuState: " + choix);

        switch (choix) {
            case 1 -> {
                LoggerSingleton.getInstance().log("STATE", "Affichage des règles du jeu");
                partie.admin.annocerRegle();
                partie.changerEtat(new MenuState()); // revenir au menu
                LoggerSingleton.getInstance().log("STATE", "Retour au MenuState après règles");
            }
            case 2 -> {
                LoggerSingleton.getInstance().log("STATE", "Démarrage de la partie -> DistributionRoleState");
                partie.changerEtat(new DistributionRoleState());
            }
            case 3 -> {
                LoggerSingleton.getInstance().log("STATE", "Quitter le jeu -> GameOverState");
                partie.changerEtat(new GameOverState());
            }
            default -> {
                LoggerSingleton.getInstance().log("STATE", "Choix invalide -> Retour au MenuState");
                partie.changerEtat(new MenuState());
            }
        }
    }

    @Override
    public void sortirEtat(GestionPartie partie) {
        LoggerSingleton.getInstance().log("STATE", "Sortie de l'état MenuState");
    }
}
