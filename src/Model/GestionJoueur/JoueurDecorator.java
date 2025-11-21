package Model.GestionJoueur;

public abstract non-sealed class JoueurDecorator extends Joueur {

    protected Joueur joueur;

    public JoueurDecorator(Joueur joueur) {
        this.joueur = joueur;
    }

    @Override
    public String getNom() {
        return joueur.getNom();
    }

    @Override
    public String getRole() {
        return joueur.getRole();
    }

    @Override
    public String getMot() {
        return joueur.getMot();
    }

    @Override
    public int getScore() {
        return joueur.getScore();
    }

    // Et ainsi de suite pour les méthodes que tu veux déléguer
}
