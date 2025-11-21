package Model.State;

import Model.Administration.GestionPartie;
import java.util.Scanner;

public class GameOverState implements GameState {

    @Override
    public void entrerEtat(GestionPartie partie) {
        System.out.println("\n=== FIN DE PARTIE ===");
        partie.gagnant.afficherGagnant();
        System.out.println("Mot correct : " + partie.motCorrect);
    }

    @Override
    public void executerEtat(GestionPartie partie) {

        Scanner sc = new Scanner(System.in);
        System.out.println("1 - Retour au menu");
        System.out.println("2 - Quitter");

        int c = sc.nextInt();

        if (c == 1) partie.changerEtat(new MenuState());
        else System.out.println("Merci d'avoir joué !");
    }

    @Override
    public void sortirEtat(GestionPartie partie) {}
}
