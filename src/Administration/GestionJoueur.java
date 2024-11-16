/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Administration;
import java.util.ArrayList;
import GestionJoueur.Joueur;
import java.util.Scanner;
/**
 *
 * @author eyazi
 */
public class GestionJoueur {
    private static ArrayList<Joueur>joueurs;
    private int nombreCivil=3;
    private int nombreUndercover=1;
    private int nombreMrsWhite=1;

    public GestionJoueur()
    {
        GestionJoueur.joueurs=new ArrayList<>();
    }
    
     public void ajouterJoueur(Joueur joueur) {
        Scanner scanner = new Scanner(System.in);
        String nom = joueur.getNom();

       /*
        while (estNomDejaUtilise(nom)) {
            System.out.println("Un joueur avec le nom \"" + nom + "\" existe deja !! \n Veuillez entrer un autre nom :");
            nom = scanner.nextLine();
        }
*/

        joueur.setNom(nom); 
        joueurs.add(joueur); 
    }

   
    private boolean estNomDejaUtilise(String nom) {
        for (Joueur j : joueurs) {
            if (j.getNom().equals(nom)) {
                return true;
            }
        }
        return false;
    }

     
     public void supprimerJoueur(Joueur joueur) 
        {
            joueurs.remove(joueur);
        }
    
    public void setNombreCivil(int n)
    {
        this.nombreCivil=n;
    }
    
    public void setNombreUndercover(int n)
    {
        this.nombreUndercover=n;
    }
    
    public void setNombreMrWhite(int n)
    {
        this.nombreMrsWhite=n;
    }
    
    public int getNombreCivil()
    {
        return this.nombreCivil;
    }
    
    public int getNombreUndercover()
    {
        return this.nombreUndercover;
    }
    
    public int getNombreMrWhite()
    {
        return this.nombreMrsWhite;
    }
    
    public void gestionNombreJoueurs(Admin admin){
        if (admin.getNombreJoueur()>10)
        {
            setNombreMrWhite(2);
            setNombreUndercover(2);
            int a=admin.getNombreJoueur()-4;
            setNombreCivil(a);
        }
        else
        {
            setNombreMrWhite(1);
            setNombreUndercover(1);
            int a=admin.getNombreJoueur()-2;
            setNombreCivil(a);
        }
    }
   
   
    public Joueur obtenirJoueur(String nom){
        for (int i = 0; i < joueurs.size(); i++) {
           if (joueurs.get(i).getNom().equals(nom))
           {
               return joueurs.get(i);  
           }
        }
            return null;
    }
    
    public int longueurListe(){
        return joueurs.size();
    }
    
    
    public void AffichageListeJoueurs()
    {
        for(int i=0;i<joueurs.size();i++)
        {
            System.out.println(joueurs.get(i).getNom());
        }
    }
    
    public static ArrayList<Joueur> getListeJoueurs()
    {
        return joueurs;
    }
}
