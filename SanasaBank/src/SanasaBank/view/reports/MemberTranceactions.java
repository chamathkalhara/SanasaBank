/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SanasaBank.view.reports;

import SanasaBank.controller.TranceactionController;
import SanasaBank.db.DBConnection;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Kalhara
 */
public class MemberTranceactions extends javax.swing.JFrame {

    /**
     * Creates new form MemberTranceactions
     */
    public MemberTranceactions() {
        initComponents();

        setLocationRelativeTo(null);


    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        memberTranceactionGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        memberIDText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nameCombo = new javax.swing.JComboBox();
        loanButton = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        accountButton = new javax.swing.JRadioButton();
        allButton = new javax.swing.JRadioButton();
        viewButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Member Tranceactions", 0, 0, new java.awt.Font("Bodoni MT", 3, 36), new java.awt.Color(0, 102, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Member ID  :");

        memberIDText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        memberIDText.setToolTipText("Search tranceactions by member ID...");
        memberIDText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberIDTextActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Name  :");

        nameCombo.setEditable(true);
        nameCombo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nameCombo.setToolTipText("Search tranceactions by member name...");
        nameCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameComboActionPerformed(evt);
            }
        });

        memberTranceactionGroup.add(loanButton);
        loanButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        loanButton.setToolTipText("Only loan tranceactions...");
        loanButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                loanButtonKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Account");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Loan");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("All");

        memberTranceactionGroup.add(accountButton);
        accountButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        accountButton.setSelected(true);
        accountButton.setToolTipText("Only account tranceactions...");
        accountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountButtonActionPerformed(evt);
            }
        });
        accountButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                accountButtonKeyReleased(evt);
            }
        });

        memberTranceactionGroup.add(allButton);
        allButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        allButton.setToolTipText("All tranceactions...");
        allButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allButtonActionPerformed(evt);
            }
        });
        allButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                allButtonKeyReleased(evt);
            }
        });

        viewButton.setBackground(new java.awt.Color(0, 153, 255));
        viewButton.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        viewButton.setForeground(new java.awt.Color(255, 255, 255));
        viewButton.setText("View");
        viewButton.setToolTipText("View...");
        viewButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        viewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewButtonActionPerformed(evt);
            }
        });

        closeButton.setBackground(new java.awt.Color(0, 153, 255));
        closeButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        closeButton.setForeground(new java.awt.Color(255, 255, 255));
        closeButton.setText("CLOSE");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(29, 29, 29)
                                .addComponent(allButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(accountButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(loanButton, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))))
                        .addGap(212, 212, 212))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(memberIDText, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nameCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(viewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(memberIDText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(nameCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(accountButton))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(loanButton))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(allButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void accountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_accountButtonActionPerformed

    private void viewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewButtonActionPerformed
        if (accountButton.isSelected()) {
            try {
                Connection conn = DBConnection.getInstance().getConnection();
                HashMap<String, Object> map = new HashMap<>();
                map.put("mid", memberIDText.getText());
                JasperReport compileReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("../../report/MemberTranceactionsAccount.jrxml"));
                JasperPrint fillReport = JasperFillManager.fillReport(compileReport, map, conn);
                JasperViewer.viewReport(fillReport,false);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MemberTranceactions.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MemberTranceactions.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JRException ex) {
                Logger.getLogger(MemberTranceactions.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (loanButton.isSelected()) {
            try {
                Connection conn = DBConnection.getInstance().getConnection();
                HashMap<String, Object> map = new HashMap<>();
                map.put("mid", memberIDText.getText());
                JasperReport compileReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("../../report/MemberTranceactionsLoan.jrxml"));
                JasperPrint fillReport = JasperFillManager.fillReport(compileReport, map, conn);
                JasperViewer.viewReport(fillReport,false);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MemberTranceactions.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MemberTranceactions.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JRException ex) {
                Logger.getLogger(MemberTranceactions.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (allButton.isSelected()) {
            try {
                DefaultTableModel dtm = TranceactionController.getMemberTanceactions(memberIDText.getText());
                HashMap<String, Object> map = new HashMap<>();
                map.put("mid", memberIDText.getText());
                JasperReport compileReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("../../report/MemberTranceactionsAll.jrxml"));
                JRTableModelDataSource dateSource = new JRTableModelDataSource(dtm);
                JasperPrint fillReport = JasperFillManager.fillReport(compileReport, map, dateSource);
                JasperViewer.viewReport(fillReport,false);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MemberTranceactions.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MemberTranceactions.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JRException ex) {
                Logger.getLogger(MemberTranceactions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_viewButtonActionPerformed

    private void nameComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameComboActionPerformed

    private void memberIDTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberIDTextActionPerformed
        accountButton.requestFocus();
        accountButton.setSelected(true);
    }//GEN-LAST:event_memberIDTextActionPerformed

    private void accountButtonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_accountButtonKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                viewButton.doClick();
                break;
            case KeyEvent.VK_DOWN:
                loanButton.setSelected(true);
                break;
        }
    }//GEN-LAST:event_accountButtonKeyReleased

    private void loanButtonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_loanButtonKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                viewButton.doClick();
                break;
            case KeyEvent.VK_DOWN:
                allButton.setSelected(true);
                break;
            case KeyEvent.VK_UP:
                accountButton.setSelected(true);
                break;
        }
    }//GEN-LAST:event_loanButtonKeyReleased

    private void allButtonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_allButtonKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                viewButton.doClick();
                break;
            case KeyEvent.VK_UP:
                loanButton.setSelected(true);
                break;
            case KeyEvent.VK_DOWN:
                viewButton.requestFocus();
                break;
        }
    }//GEN-LAST:event_allButtonKeyReleased

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void allButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_allButtonActionPerformed

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
            java.util.logging.Logger.getLogger(MemberTranceactions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MemberTranceactions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MemberTranceactions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MemberTranceactions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MemberTranceactions().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton accountButton;
    private javax.swing.JRadioButton allButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton loanButton;
    private javax.swing.JTextField memberIDText;
    private javax.swing.ButtonGroup memberTranceactionGroup;
    private javax.swing.JComboBox nameCombo;
    private javax.swing.JButton viewButton;
    // End of variables declaration//GEN-END:variables
}
