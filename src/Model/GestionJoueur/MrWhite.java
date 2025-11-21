package Model.GestionJoueur;

import Logging.LoggerSingleton;
import java.util.Scanner;

/**
 * Classe représentant le joueur MrWhite
 */
public final class MrWhite extends Joueur {

    public MrWhite() {
        super();
        // Le mot de MrWhite est fixe
        this.mot = "Tu es Mr White !";
        LoggerSingleton.getInstance().log("STATE", "Création de MrWhite avec mot spécial");
    }

    /**
     * Permet à MrWhite de deviner le mot civil
     * @param motCivil mot correct des civils
     * @return true si MrWhite devine correctement, false sinon
     */
    public boolean devinerMot(String motCivil) {
        System.out.println("Donnez votre mot à votre avis :");
        Scanner sc = new Scanner(System.in);
        String motMrsWhite = sc.nextLine();

        boolean correct = motMrsWhite.equalsIgnoreCase(motCivil);
        if (correct) {
            System.out.println("Félicitations, vous avez deviné correctement !");
            LoggerSingleton.getInstance().log("STATE", "MrWhite a deviné correctement le mot: " + motMrsWhite);
        } else {
            System.out.println("Échec. Le mot correct était: " + motCivil);
            LoggerSingleton.getInstance().log("STATE", "MrWhite a échoué. Tentative: " + motMrsWhite + ", Mot correct: " + motCivil);
        }

        return correct;
    }
}
