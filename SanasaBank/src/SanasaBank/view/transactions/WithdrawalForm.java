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
public class WithdrawalForm extends javax.swing.JFrame {

    /**
     * Creates new form WithdrawalForm
     */
    DefaultTableModel dtm;

    public WithdrawalForm() throws ClassNotFoundException, SQLException {
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

        withdrawButton.setEnabled(false);
        
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("", 0, 16));

        setLocationRelativeTo(null);
    }

    public WithdrawalForm(String mid) throws ClassNotFoundException, SQLException {
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

        withdrawButton.setEnabled(false);

        setLocationRelativeTo(null);
        
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("", 0, 16));

        memberAction(mid);
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
        withdrawAmmountText.setText("");
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
                    memberIDText.setText(mid);
                    nameLabel.setText(member.getName());
                    String address = member.getAddress();
                    String[] addressData = address.split(",", 3);
                    if (addressData.length == 3) {
                        addressLabel1.setText(addressData[0].trim());
                        addressLabel2.setText(addressData[1].trim());
                        addressLabel3.setText(addressData[2].trim());
                    }
                    if (addressData.length == 2) {
                        addressLabel1.setText(addressData[0].trim());
                        addressLabel2.setText(addressData[1].trim());
                    }
                    if (addressData.length == 1) {
                        addressLabel1.setText(addressData[0].trim());
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
                Logger.getLogger(WithdrawalForm.class.getName()).log(Level.SEVERE, null, ex);
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
        withdrawAmmountText = new javax.swing.JTextField();
        withdrawButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        clearButton = new javax.swing.JButton();
        accountNumberLabel = new javax.swing.JLabel();
        availableBalanceLabel = new javax.swing.JLabel();
        backToMainButton = new javax.swing.JButton();
        imagePanel = new org.jdesktop.swingx.JXImagePanel();
        dateLabel = new javax.swing.JLabel();
        billNumberLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        memberIDText = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        accountNoText = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        detailPanel.setBackground(new java.awt.Color(255, 255, 255));
        detailPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Details", 0, 0, new java.awt.Font("Bodoni MT", 3, 24), new java.awt.Color(0, 153, 255))); // NOI18N

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
                .addGap(18, 18, 18)
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addressLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addressLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addressLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(115, 115, 115)
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(detailPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(detailPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(telLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        detailPanelLayout.setVerticalGroup(
            detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailPanelLayout.createSequentialGroup()
                .addContainerGap()
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addressLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Transfer Details", 0, 0, new java.awt.Font("Bodoni MT", 3, 24), new java.awt.Color(0, 153, 204))); // NOI18N

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
        jLabel9.setText("Withdraw Ammount  :");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Rs ");

        withdrawAmmountText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        withdrawAmmountText.setToolTipText("Insert withdraw amount...");
        withdrawAmmountText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withdrawAmmountTextActionPerformed(evt);
            }
        });
        withdrawAmmountText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                withdrawAmmountTextKeyReleased(evt);
            }
        });

        withdrawButton.setBackground(new java.awt.Color(51, 51, 255));
        withdrawButton.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        withdrawButton.setForeground(new java.awt.Color(255, 255, 255));
        withdrawButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/withdraw.png"))); // NOI18N
        withdrawButton.setText("WITHDRAW");
        withdrawButton.setToolTipText("Withdraw");
        withdrawButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        withdrawButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withdrawButtonActionPerformed(evt);
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
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class
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
        table.setToolTipText("Account details...");
        table.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(table);

        clearButton.setBackground(new java.awt.Color(51, 51, 255));
        clearButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        clearButton.setForeground(new java.awt.Color(255, 255, 255));
        clearButton.setText("CLEAR");
        clearButton.setToolTipText("Clear all...");
        clearButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        accountNumberLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        accountNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        availableBalanceLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        availableBalanceLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        backToMainButton.setBackground(new java.awt.Color(0, 102, 255));
        backToMainButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        backToMainButton.setForeground(new java.awt.Color(255, 255, 255));
        backToMainButton.setText("BACK TO MAIN");
        backToMainButton.setToolTipText("Clear all...");
        backToMainButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backToMainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToMainButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(accountNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(availableBalanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(withdrawButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(withdrawAmmountText, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 861, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(backToMainButton)
                        .addGap(23, 23, 23))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(accountNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8))
                    .addComponent(availableBalanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(withdrawAmmountText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(withdrawButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backToMainButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(0, 37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(detailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void withdrawAmmountTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withdrawAmmountTextActionPerformed
        withdrawButton.doClick();
    }//GEN-LAST:event_withdrawAmmountTextActionPerformed

    private void withdrawAmmountTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_withdrawAmmountTextKeyReleased
        Validations.balanceValidation(withdrawAmmountText);
        if (!availableBalanceLabel.getText().equals("") && !withdrawAmmountText.getText().equals("")) {
            withdrawButton.setEnabled(true);
        } else {
            withdrawButton.setEnabled(false);
        }
    }//GEN-LAST:event_withdrawAmmountTextKeyReleased

    private void withdrawButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withdrawButtonActionPerformed
        int accountNumber = Integer.parseInt(accountNumberLabel.getText());
        double availableBalance = Double.valueOf(availableBalanceLabel.getText());
        double withdrawAmmount = Double.valueOf(withdrawAmmountText.getText());

        double balance = availableBalance - withdrawAmmount;
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setCurrentAmmount(balance);
        account.setLastUpdateDate(dateLabel.getText());
        try {
            int res = AccountController.withdrawOrDiposit(account);
            Account account1 = AccountController.searchAccount(accountNumber);
            Tranceaction tranceaction = new Tranceaction("", account1.getAID(), CashInHandController.getLastCashInHandID(), Integer.parseInt(billNumberLabel.getText()), dateLabel.getText(), "Withdraw", Double.parseDouble(withdrawAmmountText.getText()));
            int res2 = TranceactionController.tranceaction(tranceaction);
            if (res > 0 && res2 > 0) {
                CashInHandController.updateCashInHand(-withdrawAmmount);
                JOptionPane.showMessageDialog(null, "Withdraw Success");
                emptyAll();
                withdrawButton.setEnabled(false);
                BillNumberContraller.addBillNumber(Integer.parseInt(billNumberLabel.getText()));
                setBillNumber();
                memberIDText.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Withdraw failed");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(WithdrawalForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_withdrawButtonActionPerformed

    private void tableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseReleased
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String accountNumber = String.valueOf(dtm.getValueAt(selectedRow, 0));
            accountNumberLabel.setText(accountNumber);
            accountNoText.setText(accountNumber);
            String balance = String.valueOf(dtm.getValueAt(selectedRow, 2));
            availableBalanceLabel.setText(balance);
            withdrawAmmountText.requestFocus();
        }
    }//GEN-LAST:event_tableMouseReleased

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        int res = JOptionPane.showConfirmDialog(null, "Are you sure to cancel this", "", JOptionPane.YES_NO_OPTION);
        if (res == 0) {
            emptyAll();
            memberIDText.requestFocus();
        }
    }//GEN-LAST:event_clearButtonActionPerformed

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

    private void backToMainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToMainButtonActionPerformed
        this.dispose();
        
    }//GEN-LAST:event_backToMainButtonActionPerformed

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
            java.util.logging.Logger.getLogger(WithdrawalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WithdrawalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WithdrawalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WithdrawalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new WithdrawalForm().setVisible(true);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(WithdrawalForm.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JButton backToMainButton;
    private javax.swing.JLabel billNumberLabel;
    private javax.swing.JButton clearButton;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JPanel detailPanel;
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
    private javax.swing.JTextField withdrawAmmountText;
    private javax.swing.JButton withdrawButton;
    // End of variables declaration//GEN-END:variables
}
