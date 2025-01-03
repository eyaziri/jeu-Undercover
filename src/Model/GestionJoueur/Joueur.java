/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.GestionJoueur;
import Model.Administration.GestionJoueur;
import Model.Administration.Role;
import Model.Forum.HistoriqueMessage;
import Model.Forum.Message;
import Model.Vote.Elimination;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 *
 * @author eyazi
 */

import java.util.Scanner;
  public abstract sealed class Joueur permits Undercover, MrWhite, Civil,Gagnant{
            protected static int compteurId = 0;
            protected int idJoueur;
            protected String nom;
            protected int score;
            Role role;
            protected String mot;
            protected int nombreDeVotesRecus=0;
            protected boolean aVote = false;
            protected boolean estVivant =true;

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
            public void setEstVivant()
            {
                this.estVivant=false;
            }
            public boolean getEstVivant()
            {
                return this.estVivant;
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

            public Message EcrireMessage(HistoriqueMessage historiqueMessage) 
            {
                Scanner sc = new Scanner(System.in);
                System.out.println("\n - Bonjour " + this.nom + ", donnez une description du mot :\n");

                String contenu;
                try {
                    contenu = sc.nextLine();
                    if (contenu.isEmpty()) {
                        throw new NoSuchElementException();
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Aucune saisie n'a ete detectee. Message par defaut attribue.");
                    contenu = "Message non specifie";
                }

                Message msg = new Message(contenu, this);
                msg.setDatePublication(LocalDate.now());
                historiqueMessage.enregisterMessage(msg);

                System.out.println("\n ->   Votre message a ete publie le : " + msg.getDatePublication() + "\n");
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


             public void estEliminer(GestionJoueur gestionJoueur) 
             {
                 Elimination elimination = new Elimination();
                 ArrayList<Joueur> joueursElimines = elimination.getJoueursElimines();
                if (this.getRole().equalsIgnoreCase("Civil") || this.getRole().equalsIgnoreCase("Undercover")) 
                {
                     System.out.println("Le joueur " + this.getNom() + " est elimine avec " + this.getNombreDeVotesRecus() + " votes." + " qui a le role de : " + this.getRole());
 
                } 
                else if (this.getRole().equalsIgnoreCase("Mr.White")) 
                {
                    Scanner sc = new Scanner(System.in);
                    String motDevine;
                    System.out.println("\nVous etes Mr. White. Essayez de deviner le mot associe : ");
                    motDevine = sc.nextLine();
                    
                    for (int i = 0; i < gestionJoueur.longueurListe(); i++)
                    {
                            Joueur joueur = gestionJoueur.getListeJoueurs().get(i);
                            
                            if(joueur.getRole().equalsIgnoreCase("civile"))
                            {
                                String mot=joueur.getMot();
                                break;
                            }      
                    }
                    if (motDevine.equalsIgnoreCase(mot))
                    {
                        System.out.println("\nFelicitations ! Vous avez devine correctement, vous n'etes pas elimine");
                    } 
                    else 
                    {
                        System.out.println("\nEchec. Le mot etait " + mot + ". Vous etes elimine.");
                    }
                }
            }

               public Message EcrireMessages(HistoriqueMessage historiqueMessage,String contenu){
                Message msg = new Message(contenu, this);
                msg.setDatePublication(LocalDate.now());
                historiqueMessage.enregisterMessage(msg);

                System.out.println("\n ->   Votre message a ete publie le : " + msg.getDatePublication() + "\n");
                return msg;
              } 
              
               @Override
            public String toString() {
               return "Le Joueur : " + this.getNom() + " , est de role : " + this.getRole();
                      
           }

        }