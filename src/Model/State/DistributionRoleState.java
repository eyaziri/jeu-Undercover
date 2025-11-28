package Model.State;

import Logging.LoggerSingleton;
import Model.Administration.GestionPartie;
import Model.Administration.Role;
import Model.Administration.ListeMots;
import Model.GestionJoueur.*;

public class DistributionRoleState implements GameState {

    @Override
    public void entrerEtat(GestionPartie partie) {
        System.out.println("\n=== DISTRIBUTION DES RÔLES ===");
        LoggerSingleton.getInstance().log("STATE", "Entrée dans DistributionRoleState");
    }

    @Override
    public void executerEtat(GestionPartie partie) {

        LoggerSingleton.getInstance().log("STATE", "Début de la distribution des rôles");
        partie.gestionJoueur.gestionNombreJoueurs(partie.admin);

        int totalCivil = 0;
        int totalUnder = 0;
        int totalWhite = 0;

        Role roleGen = new Role();
        ListeMots liste = new ListeMots();

        String motCivil = null;
        String motUnder = null;

        while (totalCivil < partie.gestionJoueur.getNombreCivil() ||
                totalUnder < partie.gestionJoueur.getNombreUndercover() ||
                totalWhite < partie.gestionJoueur.getNombreMrWhite()) {

            String role = roleGen.donnerRoleAleatoire();
            Joueur joueur;

            switch (role) {
                case "Civile" -> {
                    if (totalCivil >= partie.gestionJoueur.getNombreCivil()) continue;
                    joueur = new Civil();
                    joueur.setRole("Civile");

                    if (motCivil == null) {
                        liste.associerMotDeCivilEtDeUndercover(joueur);
                        motCivil = joueur.getMot();
                        motUnder = liste.getMotUndercover(motCivil);
                    } else {
                        joueur.setMot(motCivil);
                    }

                    totalCivil++;
                }
                case "Undercover" -> {
                    if (totalUnder >= partie.gestionJoueur.getNombreUndercover()) continue;
                    joueur = new Undercover();
                    joueur.setRole("Undercover");

                    if (motUnder == null) {
                        liste.associerMotDeCivilEtDeUndercover(joueur);
                        motUnder = joueur.getMot();
                        motCivil = liste.getMotCivil(motUnder);
                    } else {
                        joueur.setMot(motUnder);
                    }

                    totalUnder++;
                }
                case "MrWhite" -> {
                    if (totalWhite >= partie.gestionJoueur.getNombreMrWhite()) continue;
                    joueur = new MrWhite();
                    joueur.setRole("MrWhite");
                    joueur.setMot("Tu es Mr White !");
                    totalWhite++;
                }
                default -> { continue; }
            }

            partie.gestionJoueur.ajouterJoueur(joueur);
        }

        // Déterminer mot correct
        for (Joueur j : partie.gestionJoueur.getListeJoueurs()) {
            if (j.getRole().equals("Civile")) {
                partie.motCorrect = j.getMot();
                break;
            }
        }

        // ✅ CORRECTION : AJOUTER CET APPEL QUI MANQUE !
        System.out.println("🔍 DEBUG: Avant d'appeler donnerDecorateurUnique()");
        
        // 🎁 Donner UN SEUL super pouvoir
        partie.gestionJoueur.donnerDecorateurUnique();
        
        System.out.println("🔍 DEBUG: Après avoir appelé donnerDecorateurUnique()");
        
        // ✅ Vérification des pouvoirs
        System.out.println("\n=== VÉRIFICATION DES POUVOIRS ===");
        partie.gestionJoueur.afficherPouvoirsSpeciaux();
        System.out.println("================================\n");

        partie.changerEtat(new DiscussionState());
    }

    @Override
    public void sortirEtat(GestionPartie partie) {
        LoggerSingleton.getInstance().log("STATE", "Sortie de DistributionRoleState");
    }
}