/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SanasaBank.view.account;

import SanasaBank.controller.AccountController;
import SanasaBank.controller.AccountTypeController;
import SanasaBank.controller.CashInHandController;
import SanasaBank.controller.MemberController;
import SanasaBank.model.Account;
import SanasaBank.model.FixDeposit;
import SanasaBank.model.Member;
import SanasaBank.other.AutoGenarateID;
import SanasaBank.other.ComboSearch;
import SanasaBank.other.Validations;
import SanasaBank.view.loan.InstalmentForm;
import SanasaBank.view.member.NewMemberForm;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class NewAccountForm extends javax.swing.JFrame {

    /**
     * Creates new form NewAccountForm
     */
    public static double amount;
    public static FixDeposit fixDeposit;

    public NewAccountForm() throws ClassNotFoundException, SQLException {
        initComponents();
        
        ImageIcon icon=new ImageIcon(getClass().getResource("/image/label.png"));
        imagePanel.setImage(icon.getImage());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String curDate = simpleDateFormat.format(date);

        dateLabel.setText(curDate);
        memberIDText.requestFocus();

        ArrayList<String> memberNameList = MemberController.getAllMemberName();
        for (String name : memberNameList) {
            memberNameCombo.addItem(name);
        }
        memberNameCombo.setSelectedIndex(-1);
        
        ComboSearch comboSearch = new ComboSearch();
        comboSearch.search(memberNameCombo, true, "No member...");
        
        setLocationRelativeTo(null);

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

    private void emptyAll() {
        memberIDText.requestFocus();
        memberIDText.setText("");
        nameLabel.setText("");
        nicNoLabel.setText("");
        accountNumberLabel.setText("");
        typeCombo.setSelectedIndex(-1);
        amountText.setText("");
        createButton.setEnabled(false);
        fixDepositDetailsButton.setEnabled(false);
    }
    
    private void action(){
        String memberID = memberIDText.getText();
        try {

            Member member = MemberController.getMemberDetail(memberID);
            if (member != null) {
                nameLabel.setText(member.getName());
                nicNoLabel.setText(member.getNic());
                accountNumberLabel.setText(AccountController.getNewAccountNumber());

                ArrayList<String> accountTypeList = new ArrayList<>();
                accountTypeList = AccountTypeController.getAllAccountType();
                for (String accountType : accountTypeList) {
                    typeCombo.addItem(accountType);
                }
                typeCombo.requestFocus();

            } else {
                JOptionPane.showMessageDialog(null, memberID + " Member not found");
                memberIDText.setText("");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(NewAccountForm.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nicNoLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        accountNumberLabel = new javax.swing.JLabel();
        typeCombo = new javax.swing.JComboBox();
        fixDepositDetailsButton = new javax.swing.JButton();
        amountText = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        createButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        cancelButton1 = new javax.swing.JButton();
        imagePanel = new org.jdesktop.swingx.JXImagePanel();
        dateLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        memberIDText = new javax.swing.JTextField();
        newButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        memberNameCombo = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MEMBER", 0, 0, new java.awt.Font("Bodoni MT", 3, 24), new java.awt.Color(0, 153, 255))); // NOI18N
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Name  :");

        nameLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("NIC No  :");

        nicNoLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nicNoLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nicNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(490, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(nicNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ACCOUNT", 0, 0, new java.awt.Font("Bodoni MT", 3, 24), new java.awt.Color(0, 102, 255))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Account Number  :");

        accountNumberLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        accountNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        typeCombo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        typeCombo.setToolTipText("Select account type...");
        typeCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeComboActionPerformed(evt);
            }
        });
        typeCombo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                typeComboKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                typeComboKeyReleased(evt);
            }
        });

        fixDepositDetailsButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fixDepositDetailsButton.setText("Fix deposit details");
        fixDepositDetailsButton.setToolTipText("Fix deposit details...");
        fixDepositDetailsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        fixDepositDetailsButton.setEnabled(false);
        fixDepositDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fixDepositDetailsButtonActionPerformed(evt);
            }
        });

        amountText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        amountText.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        amountText.setToolTipText("Add first amount...");
        amountText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountTextActionPerformed(evt);
            }
        });
        amountText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                amountTextKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Rs");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Amount  :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Type  :");

        createButton.setBackground(new java.awt.Color(0, 102, 255));
        createButton.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        createButton.setForeground(new java.awt.Color(255, 255, 255));
        createButton.setText("CREATE");
        createButton.setToolTipText("Create...");
        createButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        createButton.setEnabled(false);
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        cancelButton.setBackground(new java.awt.Color(0, 102, 255));
        cancelButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelButton.setText("CLEAR");
        cancelButton.setToolTipText("Clear all...");
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        cancelButton1.setBackground(new java.awt.Color(0, 102, 255));
        cancelButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cancelButton1.setForeground(new java.awt.Color(255, 255, 255));
        cancelButton1.setText("BACK TO MAIN");
        cancelButton1.setToolTipText("Clear all...");
        cancelButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(typeCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(amountText, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(accountNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fixDepositDetailsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(cancelButton1)
                .addGap(17, 17, 17))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(accountNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(typeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fixDepositDetailsButton))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(amountText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cancelButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(64, 64, 64))
        );

        imagePanel.setBackground(new java.awt.Color(255, 255, 255));

        dateLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        dateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dateLabel.setText("date");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Member ID  :");

        memberIDText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        memberIDText.setToolTipText("Select member by ID...");
        memberIDText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberIDTextActionPerformed(evt);
            }
        });

        newButton.setBackground(new java.awt.Color(0, 102, 255));
        newButton.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        newButton.setForeground(new java.awt.Color(255, 255, 255));
        newButton.setText("New");
        newButton.setToolTipText("New member...");
        newButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Name  :");

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

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imagePanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(imagePanelLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(memberIDText, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(memberNameCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addComponent(newButton, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(318, 318, 318))
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanelLayout.createSequentialGroup()
                .addComponent(dateLabel)
                .addGap(18, 18, 18)
                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(memberIDText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(memberNameCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(newButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void memberIDTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberIDTextActionPerformed
        action();
    }//GEN-LAST:event_memberIDTextActionPerformed

    private void typeComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeComboActionPerformed

    }//GEN-LAST:event_typeComboActionPerformed

    private void typeComboKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_typeComboKeyPressed
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                if (fixDepositDetailsButton.isEnabled()) {
                    fixDepositDetailsButton.requestFocus();
                } else {
                    amountText.requestFocus();
                }
                break;

        }
    }//GEN-LAST:event_typeComboKeyPressed

    private void amountTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountTextActionPerformed
        this.amount = Double.parseDouble(amountText.getText());
        createButton.doClick();
    }//GEN-LAST:event_amountTextActionPerformed

    private void fixDepositDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fixDepositDetailsButtonActionPerformed
        try {
            String accountTypeID = AccountTypeController.getAccountTypeID(String.valueOf(typeCombo.getSelectedItem()));
            new FixDepositDetails(this, true, accountTypeID).setVisible(true);
            if (this.fixDeposit != null && this.amount != 0) {
                amountText.setText(String.valueOf(this.amount));
                createButton.setEnabled(true);
                createButton.requestFocus();
                amountText.setEditable(false);
            } else {
                JOptionPane.showMessageDialog(null, "Fill the fix deposit form...");
                fixDepositDetailsButton.requestFocus();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(NewAccountForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_fixDepositDetailsButtonActionPerformed

    private void typeComboKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_typeComboKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (String.valueOf(typeCombo.getSelectedItem()).equals("Member Fixed Deposit")) {
                    fixDepositDetailsButton.setEnabled(true);
                } else {
                    fixDepositDetailsButton.setEnabled(false);
                }
                break;
            case KeyEvent.VK_DOWN:
                if (String.valueOf(typeCombo.getSelectedItem()).equals("Member Fixed Deposit")) {
                    fixDepositDetailsButton.setEnabled(true);
                } else {
                    fixDepositDetailsButton.setEnabled(false);
                }
        }
    }//GEN-LAST:event_typeComboKeyReleased

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.roll(Calendar.MONTH, 3);
            Date nextUpdateDate = calendar.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String nextDate = sdf.format(nextUpdateDate);
            Account account = new Account(AutoGenarateID.genarate(null, "A", AccountController.getLastAccountID()), memberIDText.getText(), AccountTypeController.getAccountTypeID(String.valueOf(typeCombo.getSelectedItem())), Integer.parseInt(accountNumberLabel.getText()), dateLabel.getText(), this.amount, dateLabel.getText(), 0, nextDate);
            int res = AccountController.addAccount(account);
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Account created..");
                CashInHandController.updateCashInHand(this.amount);
                emptyAll();
                this.dispose();

            } else {
                JOptionPane.showMessageDialog(null, "Can not create account..");
                memberIDText.selectAll();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewAccountForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NewAccountForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_createButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        int res = JOptionPane.showConfirmDialog(null, "Are you sure that you want to clear this","Confirm",JOptionPane.YES_NO_OPTION);
        if(res==0){
            emptyAll();
        }
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void amountTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountTextKeyReleased
        Validations.balanceValidation(amountText);
        boolean chek = !nameLabel.getText().equals("") && !nicNoLabel.getText().equals("") && typeCombo.getSelectedIndex() != -1 && !amountText.getText().equals("");
        if (chek) {
            createButton.setEnabled(true);
        } else {
            createButton.setEnabled(false);
        }
    }//GEN-LAST:event_amountTextKeyReleased

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        try {
            new NewMemberForm(null, true,memberIDText).setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewAccountForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NewAccountForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_newButtonActionPerformed

    private void cancelButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButton1ActionPerformed

    private void memberNameComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_memberNameComboItemStateChanged
        //        try {
            //            if (memberNameCombo.getSelectedIndex() != -1) {
                //                Member member = MemberController.getMemberDetailByName((String) memberNameCombo.getSelectedItem());
                //                if (member != null) {
                    //                    String memberID = member.getMID();
                    //                    action(memberID);
                    //                } else {
                    //                    JOptionPane.showMessageDialog(null, "member not found");
                    //                }
                //            }
            //        } catch (ClassNotFoundException ex) {
            //            Logger.getLogger(InstalmentForm.class.getName()).log(Level.SEVERE, null, ex);
            //        } catch (SQLException ex) {
            //            Logger.getLogger(InstalmentForm.class.getName()).log(Level.SEVERE, null, ex);
            //        }
    }//GEN-LAST:event_memberNameComboItemStateChanged

    private void memberNameComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberNameComboActionPerformed

    }//GEN-LAST:event_memberNameComboActionPerformed

    private void memberNameComboKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_memberNameComboKeyReleased

    }//GEN-LAST:event_memberNameComboKeyReleased

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
            java.util.logging.Logger.getLogger(NewAccountForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewAccountForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewAccountForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewAccountForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new NewAccountForm().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(NewAccountForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(NewAccountForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel accountNumberLabel;
    private javax.swing.JTextField amountText;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton cancelButton1;
    private javax.swing.JButton createButton;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JButton fixDepositDetailsButton;
    private org.jdesktop.swingx.JXImagePanel imagePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField memberIDText;
    private javax.swing.JComboBox memberNameCombo;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton newButton;
    private javax.swing.JLabel nicNoLabel;
    private javax.swing.JComboBox typeCombo;
    // End of variables declaration//GEN-END:variables
}
