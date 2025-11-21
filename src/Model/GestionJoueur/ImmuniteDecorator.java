package Model.GestionJoueur;

public class ImmuniteDecorator extends JoueurDecorator {

    public ImmuniteDecorator(Joueur joueur) {
        super(joueur);
    }

    @Override
    public void setEstVivant() {
        System.out.println(joueur.getNom() + " est immunisé : ne peut pas être éliminé !");
        // ne change pas l'état
    }
}
