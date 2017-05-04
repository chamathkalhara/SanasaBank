/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SanasaBank.view.transactions;

import SanasaBank.controller.AccountController;
import SanasaBank.controller.AccountTypeController;
import SanasaBank.controller.BillNumberContraller;
import SanasaBank.controller.CashInHandController;
import SanasaBank.controller.MemberController;
import SanasaBank.controller.TranceactionController;
import SanasaBank.model.Account;
import SanasaBank.model.Member;
import SanasaBank.model.Tranceaction;
import SanasaBank.other.AutoGenarateID;
import SanasaBank.other.Validations;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Kalhara
 */
public class DipositFrame extends javax.swing.JFrame {

    /**
     * Creates new form DipositFrame
     */
    DefaultTableModel dtm;

    public DipositFrame() throws ClassNotFoundException, SQLException {
        initComponents();
        ImageIcon icon = new ImageIcon(getClass().getResource("/image/label.png"));
        imagePanel.setImage(icon.getImage());
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String curDate = simpleDateFormat.format(date);

        dateLabel.setText(curDate);

        setBillNumber();

        memberIDText.requestFocus();

        dipositButton.setEnabled(false);
        setLocationRelativeTo(null);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("", 0, 16));
        table.setRowHeight(20);
    }

    private void emptyAll() {
        memberIDText.setText("");
        nameLabel.setText("");
        addressLabel1.setText("");
        addressLabel2.setText("");
        addressLabel3.setText("");
        nicLabel.setText("");
        telLabel.setText("");
        availableBalanceLabel.setText("");
        accountNumberLabel.setText("");
        dipositAmmountText.setText("");
        accountNoText.setText("");
        if (table.getRowCount() > 0) {
            dtm.setRowCount(0);
        }
    }

    private void emptyDetails() {
        memberIDText.setText("");
        nameLabel.setText("");
        addressLabel1.setText("");
        addressLabel2.setText("");
        addressLabel3.setText("");
        nicLabel.setText("");
        telLabel.setText("");
    }

    private void setBillNumber() throws ClassNotFoundException, SQLException {
        String lastNumber = BillNumberContraller.getLastNumber();
        AutoGenarateID.genarate(billNumberLabel, "", lastNumber);

    }

    private void memberAction(String mid) {
        DefaultTableModel dtm2 = (DefaultTableModel) table.getModel();
        dtm2.setRowCount(0);
        String memberId = mid;
        if (!memberId.equals("")) {

            try {
                Member member = MemberController.getMemberDetail(memberId);
                if (member != null) {
                    nameLabel.setText(member.getName());
                    String address = member.getAddress();
                    String[] addressData = address.split(",", 3);
                    if (addressData.length == 3) {
                        addressLabel1.setText(addressData[0].trim());
                        addressLabel2.setText(addressData[1].trim());
                        addressLabel3.setText(addressData[2].trim());
                    }
                    if (addressData.length == 2) {
                        addressLabel1.setText(addressData[0]);
                        addressLabel2.setText(addressData[1]);
                    }
                    if (addressData.length == 1) {
                        addressLabel1.setText(addressData[0]);
                    }
                    nicLabel.setText(member.getNic());
                    String codeNumber = Integer.toString(member.getContactNum()).substring(0, 2);
                    String restNumber = Integer.toString(member.getContactNum()).substring(2);
                    telLabel.setText("0" + codeNumber + "-" + restNumber);

                    ArrayList<Account> accountList = AccountController.searchAccount(memberId);
                    Object[] column = {"Account Number", "Type", "Balance"};
                    dtm = new DefaultTableModel(column, 0);
                    for (int i = 0; i < accountList.size(); i++) {
                        Account account = accountList.get(i);
                        String atid = account.getATID();
                        String type = AccountTypeController.getAccountType(atid);
                        Integer accountNumber = account.getAccountNumber();
                        Double curAmmount = account.getCurrentAmmount();
                        if (!type.equals("")) {
                            Object[] row = {accountNumber, type, curAmmount};
                            table.setModel(dtm);
                            dtm.addRow(row);
                        }
                    }
                    table.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, memberId + " member not found");
                    emptyDetails();
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(DipositFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            emptyDetails();
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

        detailPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        addressLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        addressLabel3 = new javax.swing.JLabel();
        addressLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nicLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        telLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        dipositAmmountText = new javax.swing.JTextField();
        dipositButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        cancelButton = new javax.swing.JButton();
        accountNumberLabel = new javax.swing.JLabel();
        availableBalanceLabel = new javax.swing.JLabel();
        backToMainbutton = new javax.swing.JButton();
        imagePanel = new org.jdesktop.swingx.JXImagePanel();
        dateLabel = new javax.swing.JLabel();
        billNumberLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        memberIDText = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        accountNoText = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        detailPanel.setBackground(new java.awt.Color(255, 255, 255));
        detailPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Details", 0, 0, new java.awt.Font("Bodoni MT", 3, 36), new java.awt.Color(0, 153, 255))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Name  :");

        addressLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addressLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Address  :");

        nameLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        addressLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addressLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        addressLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addressLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("NIC  :");

        nicLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nicLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Tel  :");

        telLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        telLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout detailPanelLayout = new javax.swing.GroupLayout(detailPanel);
        detailPanel.setLayout(detailPanelLayout);
        detailPanelLayout.setHorizontalGroup(
            detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(detailPanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(detailPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addressLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addressLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addressLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(173, 173, 173)
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(detailPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(detailPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(telLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        detailPanelLayout.setVerticalGroup(
            detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(nicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(telLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addressLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(addressLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Transfer Details", 0, 0, new java.awt.Font("Bodoni MT", 3, 36), new java.awt.Color(0, 153, 255))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Account Number  :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Available Balance  :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Rs ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Diposit Ammount  :");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Rs ");

        dipositAmmountText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        dipositAmmountText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dipositAmmountTextActionPerformed(evt);
            }
        });
        dipositAmmountText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dipositAmmountTextKeyReleased(evt);
            }
        });

        dipositButton.setBackground(new java.awt.Color(51, 102, 255));
        dipositButton.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        dipositButton.setForeground(new java.awt.Color(255, 255, 255));
        dipositButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/deposit.png"))); // NOI18N
        dipositButton.setText("DEPOSIT");
        dipositButton.setToolTipText("deposit");
        dipositButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dipositButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dipositButtonActionPerformed(evt);
            }
        });

        table.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Account Number", "Type", "Balance"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setRowHeight(20);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(table);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);

        cancelButton.setBackground(new java.awt.Color(51, 102, 255));
        cancelButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelButton.setText("CLEAR");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        accountNumberLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        accountNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        availableBalanceLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        availableBalanceLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        backToMainbutton.setBackground(new java.awt.Color(0, 102, 255));
        backToMainbutton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        backToMainbutton.setForeground(new java.awt.Color(255, 255, 255));
        backToMainbutton.setText("BACK TO MAIN");
        backToMainbutton.setToolTipText("Clear all...");
        backToMainbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backToMainbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToMainbuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(accountNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(availableBalanceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dipositAmmountText, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(dipositButton, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(backToMainbutton)
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(accountNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(availableBalanceLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(dipositAmmountText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backToMainbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dipositButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66))
        );

        dateLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        dateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dateLabel.setText("date");

        billNumberLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        billNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        billNumberLabel.setText("bill number");

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

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Account No  :");

        accountNoText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        accountNoText.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        accountNoText.setToolTipText("Search by account number...");
        accountNoText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountNoTextActionPerformed(evt);
            }
        });
        accountNoText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                accountNoTextKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imagePanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imagePanelLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(memberIDText, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(accountNoText, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(billNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanelLayout.createSequentialGroup()
                .addComponent(dateLabel)
                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(imagePanelLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(billNumberLabel))
                    .addGroup(imagePanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(memberIDText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(accountNoText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(detailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(detailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dipositAmmountTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dipositAmmountTextActionPerformed
        dipositButton.doClick();
    }//GEN-LAST:event_dipositAmmountTextActionPerformed

    private void dipositAmmountTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dipositAmmountTextKeyReleased
        Validations.balanceValidation(dipositAmmountText);

        if (!availableBalanceLabel.getText().equals("") && !dipositAmmountText.getText().equals("")) {
            dipositButton.setEnabled(true);
        } else {
            dipositButton.setEnabled(false);
        }
    }//GEN-LAST:event_dipositAmmountTextKeyReleased

    private void dipositButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dipositButtonActionPerformed
        int accountNumber = Integer.parseInt(accountNumberLabel.getText());
        double availableBalance = Double.valueOf(availableBalanceLabel.getText());
        double dipositAmmount = Double.valueOf(dipositAmmountText.getText());

        double balance = availableBalance + dipositAmmount;
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setCurrentAmmount(balance);
        account.setLastUpdateDate(dateLabel.getText());
        try {
            int res = AccountController.withdrawOrDiposit(account);
            Account account1 = AccountController.searchAccount(accountNumber);
            Tranceaction tranceaction = new Tranceaction("", account1.getAID(), CashInHandController.getLastCashInHandID(), Integer.parseInt(billNumberLabel.getText()), dateLabel.getText(), "Deposit", Double.parseDouble(dipositAmmountText.getText()));
            int res2 = TranceactionController.tranceaction(tranceaction);
            if (res > 0 && res2 > 0) {
                CashInHandController.updateCashInHand(dipositAmmount);
                JOptionPane.showMessageDialog(null, "Deposit Success");
                emptyAll();
                dipositButton.setEnabled(false);
                BillNumberContraller.addBillNumber(Integer.parseInt(billNumberLabel.getText()));
                setBillNumber();
                memberIDText.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Deposit failed");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DipositFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_dipositButtonActionPerformed

    private void tableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseReleased
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String accountNumber = String.valueOf(dtm.getValueAt(selectedRow, 0));
            accountNumberLabel.setText(accountNumber);
            String balance = String.valueOf(dtm.getValueAt(selectedRow, 2));
            availableBalanceLabel.setText(balance);
            dipositAmmountText.requestFocus();
        }
    }//GEN-LAST:event_tableMouseReleased

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        int res = JOptionPane.showConfirmDialog(null, "Are you sure that you want to clear this", "", JOptionPane.YES_NO_OPTION);
        if (res == 0) {
            emptyAll();
            memberIDText.requestFocus();
        }
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void memberIDTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberIDTextActionPerformed

        memberAction(memberIDText.getText());
        accountNumberLabel.setText("");
        availableBalanceLabel.setText("");
    }//GEN-LAST:event_memberIDTextActionPerformed

    private void accountNoTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountNoTextActionPerformed
        try {
            int accountNumber = Integer.parseInt(accountNoText.getText());
            Account account = AccountController.searchAccount(accountNumber);

            if (account != null) {
                String mid = account.getMID();
                memberIDText.setText(mid);
                memberAction(mid);

                accountNumberLabel.setText(accountNoText.getText());
                availableBalanceLabel.setText(String.valueOf(account.getCurrentAmmount()));
               
            } else {
                JOptionPane.showMessageDialog(null, accountNoText.getText() + " account number not found");
                emptyAll();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WithdrawalForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_accountNoTextActionPerformed

    private void accountNoTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_accountNoTextKeyReleased
        Validations.accountNumberValidation(accountNoText);
    }//GEN-LAST:event_accountNoTextKeyReleased

    private void backToMainbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToMainbuttonActionPerformed
        this.dispose();

    }//GEN-LAST:event_backToMainbuttonActionPerformed

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
            java.util.logging.Logger.getLogger(DipositFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DipositFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DipositFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DipositFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new DipositFrame().setVisible(true);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(DipositFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField accountNoText;
    private javax.swing.JLabel accountNumberLabel;
    private javax.swing.JLabel addressLabel1;
    private javax.swing.JLabel addressLabel2;
    private javax.swing.JLabel addressLabel3;
    private javax.swing.JLabel availableBalanceLabel;
    private javax.swing.JButton backToMainbutton;
    private javax.swing.JLabel billNumberLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JPanel detailPanel;
    private javax.swing.JTextField dipositAmmountText;
    private javax.swing.JButton dipositButton;
    private org.jdesktop.swingx.JXImagePanel imagePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField memberIDText;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nicLabel;
    private javax.swing.JTable table;
    private javax.swing.JLabel telLabel;
    // End of variables declaration//GEN-END:variables
}
