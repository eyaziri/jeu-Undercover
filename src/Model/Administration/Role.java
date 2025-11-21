package Model.Administration;

import Logging.LoggerSingleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe représentant un rôle pour les joueurs
 */
public class Role {

    private final List<String> roles;
    private String nomRole;

    public Role() {
        roles = new ArrayList<>();
        roles.add("Civile");
        roles.add("Undercover");
        roles.add("MrWhite");
    }

    /**
     * Définit le rôle d’un joueur
     * @param roleName nom du rôle à assigner
     */
    public void setNomRole(String roleName) {
        if (roleName != null && roles.contains(roleName)) {
            this.nomRole = roleName;
        } else {
            this.nomRole = donnerRoleAleatoire();
            LoggerSingleton.getInstance().log("ROLE", "Rôle invalide fourni, rôle aléatoire assigné : " + this.nomRole);
        }
    }

    public String getNomRole() {
        return nomRole;
    }

    /**
     * Donne un rôle aléatoire parmi la liste des rôles disponibles
     */
    public String donnerRoleAleatoire() {
        Random rand = new Random();
        String roleAleatoire = roles.get(rand.nextInt(roles.size()));
        LoggerSingleton.getInstance().log("ROLE", "Rôle aléatoire généré : " + roleAleatoire);
        return roleAleatoire;
    }

    /**
     * Ajoute un rôle supplémentaire à la liste
     */
    public void addRole(String role) {
        if (role != null && !roles.contains(role)) {
            roles.add(role);
            LoggerSingleton.getInstance().log("ROLE", "Nouveau rôle ajouté : " + role);
        }
    }

    public List<String> getRolesDisponibles() {
        return new ArrayList<>(roles);
    }
}
