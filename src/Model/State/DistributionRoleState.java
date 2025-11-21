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
                        LoggerSingleton.getInstance().log("STATE", "Mot civil et undercover associés: " + motCivil + " / " + motUnder);
                    } else {
                        joueur.setMot(motCivil);
                        LoggerSingleton.getInstance().log("STATE", "Mot civil attribué: " + motCivil);
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
                        LoggerSingleton.getInstance().log("STATE", "Mot undercover et civil associés: " + motUnder + " / " + motCivil);
                    } else {
                        joueur.setMot(motUnder);
                        LoggerSingleton.getInstance().log("STATE", "Mot undercover attribué: " + motUnder);
                    }

                    totalUnder++;
                }
                case "MrWhite" -> {
                    if (totalWhite >= partie.gestionJoueur.getNombreMrWhite()) continue;
                    joueur = new MrWhite();
                    joueur.setRole("MrWhite");
                    joueur.setMot("Tu es Mr White !");
                    LoggerSingleton.getInstance().log("STATE", "MrWhite créé avec mot spécial");
                    totalWhite++;
                }
                default -> {continue;}
            }

            partie.gestionJoueur.ajouterJoueur(joueur);
            LoggerSingleton.getInstance().log("STATE", "Joueur ajouté: " + joueur.getNom() + " (Rôle: " + joueur.getRole() + ")");
        }

        // Déterminer le mot correct (civil)
        for (Joueur j : partie.gestionJoueur.getListeJoueurs()) {
            if (j.getRole().equals("Civile")) {
                partie.motCorrect = j.getMot();
                LoggerSingleton.getInstance().log("STATE", "Mot correct déterminé: " + partie.motCorrect);
                break;
            }
        }

        LoggerSingleton.getInstance().log("STATE", "Fin DistributionRoleState -> Passage à DiscussionState");
        partie.changerEtat(new DiscussionState());
    }

    @Override
    public void sortirEtat(GestionPartie partie) {
        LoggerSingleton.getInstance().log("STATE", "Sortie de DistributionRoleState");
    }
}
