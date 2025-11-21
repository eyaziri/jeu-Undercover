package Model.GestionJoueur;

import Logging.LoggerSingleton;
import java.util.ArrayList;

public final class Gagnant extends Joueur {

    private final ArrayList<Joueur> gagnants;
    private String role1;

    public Gagnant() {
        super();
        this.gagnants = new ArrayList<>();
        LoggerSingleton.getInstance().log("STATE", "Création de l'objet Gagnant");
    }

    public String getRole1() {
        if (!gagnants.isEmpty()) {
            role1 = gagnants.get(0).getRole();
        }
        return role1;
    }

    public void ajouterGagnant(Joueur joueur) {
        if (joueur != null) {
            gagnants.add(joueur);
            LoggerSingleton.getInstance().log("STATE", "Ajout du gagnant: " + joueur.getNom() + " (" + joueur.getRole() + ")");
        }
    }

    public void determinerGagnant(ArrayList<Joueur> joueurs) {
        int undercoverCount = 0;
        int civilCount = 0;
        int mrsWhiteCount = 0;
        boolean mrsWhiteEnVie = false;

        for (Joueur joueur : joueurs) {
            String role = joueur.getRole().trim(); // suppression des espaces
            if (role.equalsIgnoreCase("Undercover")) {
                undercoverCount++;
            } else if (role.equalsIgnoreCase("Civile")) {
                civilCount++;
            } else if (role.equalsIgnoreCase("MrWhite")) {
                mrsWhiteCount++;
                mrsWhiteEnVie = true;
            }
        }

        // Log des comptes
        LoggerSingleton.getInstance().log("STATE", "Comptes: Civile=" + civilCount + ", Undercover=" + undercoverCount + ", MrWhite=" + mrsWhiteCount);

        // Conditions de victoire
        if (undercoverCount == 0 && civilCount > 0 && !mrsWhiteEnVie) {
            joueurs.stream().filter(j -> j.getRole().equalsIgnoreCase("Civile")).forEach(this::ajouterGagnant);
        } else if (undercoverCount > 0 && civilCount == 0 && !mrsWhiteEnVie) {
            joueurs.stream().filter(j -> j.getRole().equalsIgnoreCase("Undercover")).forEach(this::ajouterGagnant);
        } else if (mrsWhiteEnVie) {
            joueurs.stream().filter(j -> j.getRole().equalsIgnoreCase("MrWhite")).forEach(this::ajouterGagnant);
        }
    }

    public ArrayList<Joueur> getGagnants() {
        return gagnants;
    }

    public void afficherGagnant() {
        if (gagnants.isEmpty()) {
            System.out.println("Il n'y a pas de gagnant pour cette partie.");
            LoggerSingleton.getInstance().log("STATE", "Aucun gagnant cette partie");
        } else {
            System.out.print("🎉 Les gagnants de cette partie sont : ");
            for (int i = 0; i < gagnants.size(); i++) {
                Joueur gagnant = gagnants.get(i);
                gagnant.setScore(6); // attribution score
                System.out.print(gagnant.getNom());
                if (i < gagnants.size() - 1) System.out.print(", ");
            }
            System.out.println();
            LoggerSingleton.getInstance().log("STATE", "Gagnants affichés: " + gagnants);
        }
    }
}
