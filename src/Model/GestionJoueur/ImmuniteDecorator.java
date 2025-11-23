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
            System.out.println("🛡️ " + getNom() + " est immunisé et ne peut pas être éliminé !");
            LoggerSingleton.getInstance().log("DECORATOR",
                    "Élimination annulée grâce à l'immunité pour " + getNom());
            immuniteActive = false; // L'immunité ne fonctionne qu'une fois
        } else {
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