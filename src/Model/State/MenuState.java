package Model.State;

import java.util.Scanner;

import Model.Administration.GestionPartie;

public class MenuState implements GameState {

    @Override
    public void entrerEtat(GestionPartie partie) {
        System.out.println("\n=== MENU PRINCIPAL ===");
    }

    @Override
    public void executerEtat(GestionPartie partie) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1 - Règles du jeu");
        System.out.println("2 - Démarrer la partie");
        System.out.println("3 - Quitter");

        int choix = sc.nextInt();

        switch (choix) {
            case 1 -> {
                partie.admin.annocerRegle();
                partie.changerEtat(new MenuState()); // revenir au menu
            }
            case 2 -> partie.changerEtat(new DistributionRoleState());
            case 3 -> partie.changerEtat(new GameOverState());
            default -> partie.changerEtat(new MenuState());
        }
    }

    @Override
    public void sortirEtat(GestionPartie partie) {
        // rien pour le moment
    }
}