/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Administration;

/**
 *
 * @author eyazi
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Role {
    ArrayList<String> roles;
    private String nomRole;
    
    public Role() {
        roles = new ArrayList<>();
        roles.add("Civile");
        roles.add("Undercover");
        roles.add("MrWhite");
    }
    
    public void setNomRole() {
        this.nomRole = donnerRoleAleatoire();
    }
    
    public String getNomRole() {
        return nomRole;
    }
    
    public String donnerRoleAleatoire() {
        String nom;
        Random rand = new Random();
        nom= roles.get(rand.nextInt(roles.size()));
        return nom;
    }
    
    public void addRole(String role) {
        roles.add(role);
    }
    
}
