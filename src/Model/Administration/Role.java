/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Administration;

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
    
    public void setNomRole(String roleName) {
        if (roles.contains(roleName)) {
            this.nomRole = roleName;
        } else {
            this.nomRole = donnerRoleAleatoire(); 
        }
    }
    
    public String getNomRole() 
    {
        return nomRole;
    }
    
    public String donnerRoleAleatoire() 
    {
        Random rand = new Random();
        return roles.get(rand.nextInt(roles.size()));
    }
    
    public void addRole(String role) {
        roles.add(role);
    }
    
}