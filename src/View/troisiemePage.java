/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;


import Administration.GestionJoueur;
import Administration.ListeMots;
import Administration.Role;
import GestionJoueur.Civil;
import GestionJoueur.Joueur;
import GestionJoueur.MrWhite;
import GestionJoueur.Undercover;
import javax.swing.JOptionPane;
import View.quatriemeRolePage;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;


/**
 *
 * @author eyazi
 */
public class troisiemePage extends javax.swing.JFrame {

    private int playersRemaining ;
    private String rolej;
    private GestionJoueur gestionJoueur; 
    private ArrayList<String>names;
    
    
    
    
    
    public troisiemePage(GestionJoueur gestionJoueur) 
    {
        initComponents();
        this.names=new ArrayList<>();
        this.gestionJoueur=gestionJoueur;
        initializePlayerCount() ;
    }
    public void setTextField2Editable(boolean editable) {
    jTextField2.setEditable(editable);
     }
    public void setButton3Enabled(boolean enabled) {
    jButton3.setEnabled(enabled);
    }
    
    private void initializePlayerCount() 
    {
    playersRemaining =  deuxiemePage.admin.getNombreJoueur();
    updatePlayerCounterDisplay();
    names = new ArrayList<>();
    for (int i = 0; i < playersRemaining; i++) {
        names.add(""); // Ajouter des chaînes vides pour chaque joueur
    }
    jButton3.setEnabled(false); 
    }
        private void updatePlayerCounterDisplay() {
        jLabel1.setText("Remplissez les informations du joueur " + ( deuxiemePage.admin.getNombreJoueur() - playersRemaining + 1));
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(161, 175, 246));

        jPanel2.setBackground(new java.awt.Color(161, 175, 246));

        jTextField2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jTextField2.setText("  ");
        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 153));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Jouer");
        jButton3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("                       Entrez le nom de joueur :   ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(205, 205, 205)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(251, 251, 251))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(191, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
    
    int totalCivil = 0;
    int totalUndercover = 0;
    int totalMrWhite = 0;

    ListeMots listeMotPartie = new ListeMots();
    Role roleGenerator = new Role();
    String motCivil = null;
    String motUndercover = null;

    
    String playerName = jTextField2.getText().trim();

    if (playersRemaining > 0 && !playerName.isEmpty()) {
        int currentIndex = deuxiemePage.admin.getNombreJoueur() - playersRemaining;

        
        if (currentIndex < names.size()) {
            names.set(currentIndex, playerName);
        } else {
            names.add(playerName);
        }

        System.out.println(playerName + " est ajoute");

        
        while (names.size() > currentIndex + 1) {
            names.remove(names.size() - 1);
        }

        jTextField2.setText("");
        playersRemaining--;
        updatePlayerCounterDisplay();

        if (playersRemaining == 0) {
            jButton3.setEnabled(true);
        }
    }

    int i = 0;
    while (playersRemaining == 0 && i < names.size()) {
        Joueur joueurRole;
        String role = roleGenerator.donnerRoleAleatoire();

        if (role.equals("Civile") && totalCivil < gestionJoueur.getNombreCivil()) {
            joueurRole = new Civil();
            joueurRole.setRole("Civile");
            joueurRole.setNom(names.get(i));

            if (motCivil == null) {
                listeMotPartie.associerMotDeCivilEtDeUndercover(joueurRole);
                motCivil = joueurRole.getMot();
                motUndercover = listeMotPartie.getMotUndercover(motCivil);
            } else {
                joueurRole.setMot(motCivil);
            }
            totalCivil++;
        } else if (role.equals("Undercover") && totalUndercover < gestionJoueur.getNombreUndercover()) {
            joueurRole = new Undercover();
            joueurRole.setRole("Undercover");
            joueurRole.setNom(names.get(i));

            if (motUndercover == null) {
                listeMotPartie.associerMotDeCivilEtDeUndercover(joueurRole);
                motUndercover = joueurRole.getMot();
                motCivil = listeMotPartie.getMotCivil(motUndercover);
            } else {
                joueurRole.setMot(motUndercover);
            }
            totalUndercover++;
        } else if (role.equals("MrWhite") && totalMrWhite < gestionJoueur.getNombreMrWhite()) {
            joueurRole = new MrWhite();
            joueurRole.setNom(names.get(i));
            joueurRole.setRole("MrWhite");
            joueurRole.setMot("Tu es Mr White!");
            totalMrWhite++;
        } else {
            continue; 
        }

      
        gestionJoueur.ajouterJoueur(joueurRole);
        String roleJoueur = joueurRole.getRole();
        String motJoueur = joueurRole.getMot();
        String nom=joueurRole.getNom();

    
        quatriemeRolePage quatriemeRolePage = new quatriemeRolePage(nom,motJoueur, roleJoueur, gestionJoueur, this);
        new Timer(2000,e ->{
            quatriemeRolePage.setVisible(true);
            new Timer(30000,e2 ->{
                quatriemeRolePage.setVisible(false);
                ((Timer)e2.getSource()).stop();
                }).start();
                ((Timer)e.getSource()).stop();

        }).start();

        JOptionPane.showMessageDialog(this, "Joueur " + names.get(i) + " ajouté avec succès.");

        // Afficher une notification pour le joueur ajouté

        i++;
    }

    // Une fois tous les joueurs assignés, ouvrir la cinquième page
    if (playersRemaining <= 0) {
        jButton3.setEnabled(true);
        this.setVisible(false);
        JOptionPane.showMessageDialog(this, "Tous les joueurs ont été ajoutés.");
        cinquiemePage cinq = new cinquiemePage(gestionJoueur);
        cinq.setVisible(true);
    }

    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        cinquiemePage cinq= new cinquiemePage(gestionJoueur);
        cinq.show();
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(troisiemePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(troisiemePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(troisiemePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(troisiemePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
        
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
