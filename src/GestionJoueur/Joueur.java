/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestionJoueur;
import Administration.GestionJoueur;
import Administration.ListeMots;
import Administration.Role;
import Forum.*;
import java.time.LocalDate;

/**
 *
 * @author eyazi
 */

import java.util.Scanner;

        public abstract class Joueur {
            protected static int compteurId = 0;
            protected int idJoueur;
            protected String nom;
            protected int score;
            Role role;
            protected String mot;
            protected int nombreDeVotesRecus=0;
            protected boolean aVote = false;

             public Joueur() 
             {
                this.role = new Role();
                this.score = 0; 
                this.idJoueur = ++compteurId; 
            }
            public String getNom()
            {
                return nom;
            }
            public int getIdJoueur()
            {
                return idJoueur;
            }

            public void setNom(String nom)
            {
                this.nom=nom;
            }
            public void setScore(int n)
            {
                this.score=n;
            }

            public int getScore()
            {
                return score;
            }

            public void setMot(String mot) {
                this.mot = mot; 
            }

            public String getMot()
            {
                return mot;
            }

            public void setRole(String nomRole)
            {
                this.role = new Role(); 
                this.role.setNomRole(nomRole);
            }

            public String getRole()
            {
                return role.getNomRole();
            }
              public int getNombreDeVotesRecus() {
                return this.nombreDeVotesRecus;
            }

            public Message EcrireMessage() {
                Scanner sc = new Scanner(System.in);
                System.out.println("\n - Bonjour " + this.nom + ", donnez une description du mot :\n");

                String contenu = sc.nextLine();

                Message msg = new Message(contenu, this);  
                msg.setDatePublication(LocalDate.now());  

                System.out.println("\n ->   Votre message a ete publie le : " + msg.getDatePublication()+"\n");
                return msg;
            }

            public void setnombreDeVotesRecus()
            {
                this.nombreDeVotesRecus=this.nombreDeVotesRecus+1;
            }

             public void setAVote(boolean aVote) 
             {
                this.aVote = aVote;
             }

             public boolean isAVote() 
             {
                return aVote;
             }


             public void estEliminer(GestionJoueur gestionJoueur) {
                if (this.getRole().equalsIgnoreCase("Civil") || this.getRole().equalsIgnoreCase("Undercover")) {
                    System.out.println("\nLe joueur " + this.nom + " est maintenant elimine car il a recu le plus de votes.");
                } else if (this.getRole().equalsIgnoreCase("Mr.White")) {
                    Scanner sc = new Scanner(System.in);
                    String motDevine;
                    System.out.println("\nVous etes Mr. White. Essayez de deviner le mot associe : ");
                    motDevine = sc.nextLine();
                    for (int i = 0; i < gestionJoueur.longueurListe(); i++)
                    {
                            Joueur joueur = gestionJoueur.getJoueurs().get(i);
                            if(joueur.getRole().equalsIgnoreCase("civile"))
                            {
                                String mot=joueur.getMot();
                                break;
                            }      
                    }
                    if (motDevine.equalsIgnoreCase(mot)) {
                        System.out.println("\nFelicitations ! Vous avez devine correctement, vous n'etes pas elimine");
                    } else {
                        System.out.println("\nEchec. Le mot etait " + mot + ". Vous etes elimine.");
                    }
                }
            }


        }
