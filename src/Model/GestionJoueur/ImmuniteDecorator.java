package Model.GestionJoueur;

import Logging.LoggerSingleton;

public class ImmuniteDecorator extends JoueurDecorator {

    private boolean immuniteActive = true;

    public ImmuniteDecorator(Joueur joueur) {
        super(joueur);
        LoggerSingleton.getInstance().log("DECORATOR",
                "Joueur " + joueur.getNom() + " décoré avec Immunité");
    }

    @Override
    public void setEstVivant() {
        if (immuniteActive) {
            // Immunité activée - le joueur ne peut pas être éliminé
            System.out.println("🛡️ " + getNom() + " est immunisé et ne peut pas être éliminé !");
            LoggerSingleton.getInstance().log("DECORATOR",
                    "Élimination annulée grâce à l'immunité pour " + getNom());
            immuniteActive = false; // L'immunité ne fonctionne qu'une fois
        } else {
            // Immunité déjà utilisée - élimination normale
            System.out.println("ℹ️ " + getNom() + " a déjà utilisé son immunité. Élimination normale.");
            super.setEstVivant();
        }
    }

    @Override
    public boolean isVivant() {
        if (immuniteActive) {
            return true; // Toujours vivant si l'immunité est active
        }
        return super.isVivant();
    }

    // ✅ AJOUT : Méthode pour vérifier si l'immunité est active
    public boolean isImmuniteActive() {
        return immuniteActive;
    }
}