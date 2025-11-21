package Model.State;

import Logging.LoggerSingleton;
import Model.Administration.GestionPartie;

import java.util.Scanner;

public class GameOverState implements GameState {

    @Override
    public void entrerEtat(GestionPartie partie) {
        System.out.println("\n=== FIN DE PARTIE ===");
        partie.gagnant.afficherGagnant();
        System.out.println("Mot correct : " + partie.motCorrect);

        LoggerSingleton.getInstance().log("STATE", "Entrée dans GameOverState");
        LoggerSingleton.getInstance().log("STATE", "Gagnant affiché: " + partie.gagnant.getNom());
        LoggerSingleton.getInstance().log("STATE", "Mot correct: " + partie.motCorrect);
    }

    @Override
    public void executerEtat(GestionPartie partie) {

        Scanner sc = new Scanner(System.in);
        int choix = -1;

        while (choix != 1 && choix != 2) {

            System.out.println("1 - Retour au menu");
            System.out.println("2 - Quitter");
            System.out.print("Votre choix : ");

            if (sc.hasNextInt()) {
                choix = sc.nextInt();
            } else {
                System.out.println("❌ Entrée invalide. Veuillez taper 1 ou 2.");
                sc.next(); // Clear la mauvaise entrée
                continue;
            }

            LoggerSingleton.getInstance().log("STATE", "Choix utilisateur dans GameOverState: " + choix);

            switch (choix) {
                case 1 -> {
                    LoggerSingleton.getInstance().log("STATE", "Retour au MenuState");
                    partie.changerEtat(new MenuState());
                    return;
                }
                case 2 -> {
                    System.out.println("Merci d'avoir joué !");
                    LoggerSingleton.getInstance().log("STATE", "Fin du jeu");
                    return;
                }
                default -> System.out.println("❌ Choix invalide. Essayez encore.");
            }
        }
    }

    @Override
    public void sortirEtat(GestionPartie partie) {
        LoggerSingleton.getInstance().log("STATE", "Sortie de GameOverState");
    }
}
