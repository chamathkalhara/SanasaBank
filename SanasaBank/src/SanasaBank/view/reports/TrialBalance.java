/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SanasaBank.view.reports;

import SanasaBank.controller.AccountTypeController;
import SanasaBank.controller.CashInHandController;
import SanasaBank.controller.InstalmentController;
import SanasaBank.controller.LoanTypeController;
import SanasaBank.controller.TranceactionController;
import SanasaBank.model.CashInHand;
import SanasaBank.model.Instalment;
import SanasaBank.model.Tranceaction;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class TrialBalance extends javax.swing.JFrame {

    /**
     * Creates new form TrialBalance
     */
    public TrialBalance() {
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

        jXImagePanel1 = new org.jdesktop.swingx.JXImagePanel();
        jLabel1 = new javax.swing.JLabel();
        dateCombo = new org.freixas.jcalendar.JCalendarCombo();
        viewButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jXImagePanel1.setBackground(new java.awt.Color(255, 255, 255));
        jXImagePanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Trial Balance", 0, 0, new java.awt.Font("Bodoni MT", 3, 24), new java.awt.Color(0, 102, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Date  :");

        dateCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2014-04-23" }));
        dateCombo.setSelectedItem(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        dateCombo.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        dateCombo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        viewButton.setBackground(new java.awt.Color(51, 102, 255));
        viewButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        viewButton.setForeground(new java.awt.Color(255, 255, 255));
        viewButton.setText("VIEW");
        viewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jXImagePanel1Layout = new javax.swing.GroupLayout(jXImagePanel1);
        jXImagePanel1.setLayout(jXImagePanel1Layout);
        jXImagePanel1Layout.setHorizontalGroup(
            jXImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXImagePanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jXImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(viewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jXImagePanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dateCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jXImagePanel1Layout.setVerticalGroup(
            jXImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXImagePanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jXImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(dateCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addComponent(viewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jXImagePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXImagePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void viewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewButtonActionPerformed
        try {
            Object[] column = {"Bill Number", "Description", "Type", "Amount"};
            DefaultTableModel dtm = new DefaultTableModel(column, 0);
            HashMap<String, Object> map = new HashMap<>();
            Date date = dateCombo.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            CashInHand cashInHand = CashInHandController.searchCashInHand(CashInHandController.getCashInHandID(sdf.format(date)));
            double amount = cashInHand.getAmount();
            double balance = cashInHand.getBalance();

            ArrayList<Tranceaction> tranceactionsList = TranceactionController.getTranceactionsByDate(sdf.format(date));
            ArrayList<Instalment> instalmentsInDateList = InstalmentController.getAllInstalmentsInDate(sdf.format(date));

            double debit = 0;
            double credit = 0;

            debit = amount;
            credit = balance;

            for (Tranceaction tranceaction : tranceactionsList) {
                String atid = tranceaction.getCID();
                String type = AccountTypeController.getAccountType(atid);
                Object[] row = {tranceaction.getBillNumber(), type, tranceaction.gettType(), tranceaction.getAmmount()};
                dtm.addRow(row);
                if (tranceaction.gettType().equals("Withdraw")) {
                    credit += tranceaction.getAmmount();
                } else {
                    debit += tranceaction.getAmmount();
                }
            }
            dtm.addRow(new Object[]{"", "", "", ""});

            for (Instalment instalment : instalmentsInDateList) {
                String ltid = instalment.getCID();
                String type = LoanTypeController.getLoanType(ltid);
                Object[] row = {instalment.getBillNumber(), type, "Instalment", instalment.getiAmmount() + instalment.getiInterest()};
                dtm.addRow(row);
                debit += instalment.getiAmmount() + instalment.getiInterest();
            }
            map.put("amount", amount);
            map.put("balance", balance);
            map.put("date", sdf.format(date));
            map.put("credit", credit);
            map.put("debit", debit);

            JasperReport compileReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("../../report/TrialBalance.jrxml"));
            JRTableModelDataSource dateSource = new JRTableModelDataSource(dtm);
            JasperPrint fillReport = JasperFillManager.fillReport(compileReport, map, dateSource);
            JasperViewer.viewReport(fillReport, false);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MemberTranceactions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MemberTranceactions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(MemberTranceactions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_viewButtonActionPerformed

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
            java.util.logging.Logger.getLogger(TrialBalance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrialBalance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrialBalance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrialBalance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrialBalance().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.freixas.jcalendar.JCalendarCombo dateCombo;
    private javax.swing.JLabel jLabel1;
    private org.jdesktop.swingx.JXImagePanel jXImagePanel1;
    private javax.swing.JButton viewButton;
    // End of variables declaration//GEN-END:variables
}
