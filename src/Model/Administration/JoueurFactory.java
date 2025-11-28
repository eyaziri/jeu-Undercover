package Model.Administration;

import Model.GestionJoueur.Civil;
import Model.GestionJoueur.Joueur;
import Model.GestionJoueur.MrWhite;
import Model.GestionJoueur.Undercover;

public class JoueurFactory {

    public static Joueur creerJoueur(String role) {
        return switch(role.toLowerCase()) {
            case "civile"      -> new Civil();
            case "undercover"  -> new Undercover();
            case "mrwhite"     -> new MrWhite();
            default -> throw new IllegalArgumentException("Role inconnu : " + role);
        };
    }
}