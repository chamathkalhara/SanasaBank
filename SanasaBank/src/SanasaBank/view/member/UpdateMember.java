/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SanasaBank.view.member;

import SanasaBank.controller.MemberController;
import SanasaBank.model.Member;
import SanasaBank.other.ComboSearch;
import SanasaBank.other.Validations;
import SanasaBank.view.loan.InstalmentForm;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Kalhara
 */
public class UpdateMember extends javax.swing.JDialog {

    /**
     * Creates new form UpdateMember
     */
    private String memberID;

    public UpdateMember(java.awt.Frame parent, boolean modal) throws ClassNotFoundException, SQLException {
        super(parent, modal);
        initComponents();

        ImageIcon icon = new ImageIcon(getClass().getResource("/image/label.png"));
        imagePanel1.setImage(icon.getImage());
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);

        ArrayList<String> arrayList = MemberController.getAllMemberName();
        for (String name : arrayList) {
            memberNameCombo.addItem(name);
        }
        memberNameCombo.setSelectedIndex(-1);

        ComboSearch comboSearch = new ComboSearch();
        comboSearch.search(memberNameCombo, true, "No member...");

        JTextField text = (JTextField) memberNameCombo.getEditor().getEditorComponent();
        text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (memberNameCombo.getSelectedIndex() != -1) {
                        Member member = MemberController.getMemberDetailByName((String) memberNameCombo.getSelectedItem());
                        if (member != null) {
                            memberIDText.setText(member.getMID());
                            action();
                        } else {
                            JOptionPane.showMessageDialog(null, "member not found");
                        }
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(InstalmentForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(InstalmentForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void action() throws ClassNotFoundException, SQLException {
        this.memberID = memberIDText.getText();
        Member member = MemberController.getMemberDetail(memberID);

        if (member != null) {
            nameText.setText(member.getName());
            memberNameCombo.setSelectedItem(nameText.getText());
            addressText.setText(member.getAddress());
            nicNoText.setText(member.getNic());
            birthDatetext.setText(member.getDob());
            String gender = member.getGender();
            if (gender.equals("male")) {
                maleButton.setSelected(true);
            } else {
                femaleButton.setSelected(true);
            }
            occupationText.setText(member.getOccupation());
            String codeNumber = Integer.toString(member.getContactNum()).substring(0, 2);
            String restNumber = Integer.toString(member.getContactNum()).substring(2);
            contactNumberText.setText("0" + codeNumber + "-" + restNumber);
        } else {
            JOptionPane.showMessageDialog(null, memberID + " Member Not Found");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        nameText = new javax.swing.JTextField();
        addressText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nicNoText = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        birthDatetext = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        maleButton = new javax.swing.JRadioButton();
        femaleButton = new javax.swing.JRadioButton();
        occupationText = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        contactNumberText = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        imagePanel1 = new org.jdesktop.swingx.JXImagePanel();
        jLabel1 = new javax.swing.JLabel();
        memberIDText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        memberNameCombo = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Update Member", 0, 0, new java.awt.Font("Bodoni MT", 3, 30), new java.awt.Color(51, 51, 255))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Name  :");

        nameText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nameText.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        addressText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addressText.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Address  :");

        nicNoText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nicNoText.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("NIC No  :");

        birthDatetext.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        birthDatetext.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Birth Date  :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Gender  :");

        buttonGroup1.add(maleButton);
        maleButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        maleButton.setText("Male");

        buttonGroup1.add(femaleButton);
        femaleButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        femaleButton.setText("Female");

        occupationText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        occupationText.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Occupation  :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Contact Number  :");

        contactNumberText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        contactNumberText.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        contactNumberText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                contactNumberTextKeyReleased(evt);
            }
        });

        cancelButton.setBackground(new java.awt.Color(51, 102, 255));
        cancelButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelButton.setText("CANCEL");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        updateButton.setBackground(new java.awt.Color(51, 102, 255));
        updateButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        updateButton.setForeground(new java.awt.Color(255, 255, 255));
        updateButton.setText("UPDATE");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(occupationText, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(maleButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(femaleButton))
                                    .addComponent(nicNoText, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(79, 79, 79)))
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addressText, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(birthDatetext, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(contactNumberText, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(251, 251, 251))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(addressText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nicNoText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(birthDatetext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(maleButton)
                    .addComponent(femaleButton))
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(occupationText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(contactNumberText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Member ID  :");

        memberIDText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        memberIDText.setToolTipText("Search by member ID...");
        memberIDText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberIDTextActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Name  :");

        memberNameCombo.setEditable(true);
        memberNameCombo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        memberNameCombo.setToolTipText("Search by name...");
        memberNameCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                memberNameComboItemStateChanged(evt);
            }
        });
        memberNameCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberNameComboActionPerformed(evt);
            }
        });
        memberNameCombo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                memberNameComboKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout imagePanel1Layout = new javax.swing.GroupLayout(imagePanel1);
        imagePanel1.setLayout(imagePanel1Layout);
        imagePanel1Layout.setHorizontalGroup(
            imagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imagePanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(memberIDText, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(156, 156, 156)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(memberNameCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(479, Short.MAX_VALUE))
        );
        imagePanel1Layout.setVerticalGroup(
            imagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(imagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(memberIDText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(memberNameCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imagePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(imagePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void memberIDTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberIDTextActionPerformed
        try {
            action();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateMember.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateMember.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_memberIDTextActionPerformed

    private void memberNameComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_memberNameComboItemStateChanged
    }//GEN-LAST:event_memberNameComboItemStateChanged

    private void memberNameComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberNameComboActionPerformed
    }//GEN-LAST:event_memberNameComboActionPerformed

    private void memberNameComboKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_memberNameComboKeyReleased
    }//GEN-LAST:event_memberNameComboKeyReleased

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        try {
            String gender = "";
            if (maleButton.isSelected()) {
                gender = "male";
            } else {
                gender = "female";
            }
            String conNumber[]=contactNumberText.getText().split("-");
            int contactNumber=Integer.parseInt(conNumber[0]+conNumber[1]);
            Member member = new Member(this.memberID, nameText.getText(), addressText.getText(), nicNoText.getText(), birthDatetext.getText(), gender, occupationText.getText(), contactNumber);
            int res = MemberController.updateMember(member);
            if(res>0){
                JOptionPane.showMessageDialog(null, "Update Success");
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(null, "Update Failed");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateMember.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateMember.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void contactNumberTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contactNumberTextKeyReleased
        Validations.tpNumberValidation(contactNumberText);
    }//GEN-LAST:event_contactNumberTextKeyReleased

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
            java.util.logging.Logger.getLogger(UpdateMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdateMember dialog = new UpdateMember(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UpdateMember.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(UpdateMember.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressText;
    private javax.swing.JTextField birthDatetext;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField contactNumberText;
    private javax.swing.JRadioButton femaleButton;
    private org.jdesktop.swingx.JXImagePanel imagePanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton maleButton;
    private javax.swing.JTextField memberIDText;
    private javax.swing.JComboBox memberNameCombo;
    private javax.swing.JTextField nameText;
    private javax.swing.JTextField nicNoText;
    private javax.swing.JTextField occupationText;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
