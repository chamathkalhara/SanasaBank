/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SanasaBank.view.loan;

import SanasaBank.controller.AccountController;
import SanasaBank.controller.AccountTypeController;
import SanasaBank.controller.GuaranteeController;
import SanasaBank.controller.LoanController;
import SanasaBank.controller.LoanTypeController;
import SanasaBank.controller.MemberController;
import SanasaBank.model.Account;
import SanasaBank.model.Loan;
import SanasaBank.model.Member;
import SanasaBank.other.ComboSearch;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Kalhara
 */
public class CheckForm extends javax.swing.JFrame {

    /**
     * Creates new form NewLoanForm
     */
    DefaultTableModel dtmAccount;
    DefaultTableModel dtmLoan;
    private ArrayList<String> memberNameList;

    public CheckForm() throws ClassNotFoundException, SQLException {
        initComponents();


        ImageIcon icon = new ImageIcon(getClass().getResource("/image/label.png"));
        imagePanel.setImage(icon.getImage());
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String curDate = simpleDateFormat.format(date);

        dateLabel.setText(curDate);

        memberIDText.requestFocus();

        comboSearch();
        memberNameCombo.setSelectedIndex(-1);

        JTableHeader tableHeader = tableAccount.getTableHeader();
        tableHeader.setFont(new Font("", 0, 16));
        JTableHeader tableHeader2 = tableLoan.getTableHeader();
        tableHeader2.setFont(new Font("", 0, 16));

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

    public void comboSearch() throws ClassNotFoundException, SQLException {
        memberNameList = MemberController.getAllMemberName();
        for (int i = 0; i < memberNameList.size(); i++) {
            memberNameCombo.addItem(memberNameList.get(i));
        }

        ComboSearch comboSearch = new ComboSearch();
        comboSearch.search(memberNameCombo, true, "No nane found ");
    }

    private void emptyAll(boolean memberID) {
        if (memberID) {
            memberIDText.setText("");
        }

        nameLabel.setText("");
        addressLabel1.setText("");
        addressLabel2.setText("");
        addressLabel3.setText("");
        nicLabel.setText("");
        telLabel.setText("");
        if (tableAccount.getModel() == dtmAccount) {
            dtmAccount.setRowCount(0);
        }
        if (tableLoan.getModel() == dtmLoan) {
            dtmLoan.setRowCount(0);
        }
    }

    private void action() {
        String memberID = memberIDText.getText();
        emptyAll(false);

        try {
            Member member = MemberController.getMemberDetail(memberID);
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

                ArrayList<Account> accountList = AccountController.searchAccount(memberID);
                Object[] column = {"Account Number", "Type", "Balance"};
                dtmAccount = new DefaultTableModel(column, 0);
                for (int i = 0; i < accountList.size(); i++) {
                    Account account = accountList.get(i);
                    String atid = account.getATID();
                    String type = AccountTypeController.getAccountType(atid);
                    Integer accountNumber = account.getAccountNumber();
                    Double curAmmount = account.getCurrentAmmount();
                    if (!type.equals("")) {
                        Object[] row = {accountNumber, type, curAmmount};
                        tableAccount.setModel(dtmAccount);
                        dtmAccount.addRow(row);
                    }
                }

                ArrayList<Loan> loanList = LoanController.searchLoans(memberID);
                Object[] column2 = {"Loan ID", "Type", "Date", "Amount", "Is Closed"};
                dtmLoan = new DefaultTableModel(column2, 0);
                for (int i = 0; i < loanList.size(); i++) {
                    Loan loan = loanList.get(i);
                    String ltid = loan.getLTID();
                    String type = LoanTypeController.getLoanType(ltid);
                    String date = loan.getlDate();
                    double amount = loan.getAmmount();
                    int instalmentCount = loan.getInstalmentCount();
                    boolean isClosed = loan.getBalance() == 0;
                    String isClosedNew;
                    if (isClosed) {
                        isClosedNew = "Yes";
                    } else {
                        isClosedNew = "No";
                    }

                    Object[] row = {loan.getLID(), type, date, amount, isClosedNew};
                    tableLoan.setModel(dtmLoan);
                    dtmLoan.addRow(row);
                }

                boolean isGuarantor = GuaranteeController.isGuarantor(memberID);
                if (isGuarantor) {
                    currentGuaranteerLabel.setText(nameLabel.getText() + " is already guarantor...");
                } else {
                    currentGuaranteerLabel.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(detailPanel, memberID + " member not found");
                memberIDText.setText("");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CheckForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (tableLoan.getRowCount() > 0) {
            moreButton.setEnabled(true);
        } else {
            moreButton.setEnabled(false);
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
        currentGuaranteerLabel = new javax.swing.JLabel();
        imagePanel = new org.jdesktop.swingx.JXImagePanel();
        dateLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        memberIDText = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        memberNameCombo = new javax.swing.JComboBox();
        imagePanel2 = new org.jdesktop.swingx.JXImagePanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableLoan = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableAccount = new javax.swing.JTable();
        moreButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        backToMainButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        detailPanel.setBackground(new java.awt.Color(255, 255, 255));
        detailPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Member", 0, 0, new java.awt.Font("Bodoni MT", 3, 36), new java.awt.Color(0, 102, 255))); // NOI18N

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
        jLabel3.setText("NIC No :");

        nicLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nicLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Tel  :");

        telLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        telLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        currentGuaranteerLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        currentGuaranteerLabel.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout detailPanelLayout = new javax.swing.GroupLayout(detailPanel);
        detailPanel.setLayout(detailPanelLayout);
        detailPanelLayout.setHorizontalGroup(
            detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(addressLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addressLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addressLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(173, 173, 173)
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(detailPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(detailPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(telLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(currentGuaranteerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 1029, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        detailPanelLayout.setVerticalGroup(
            detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailPanelLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
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
                .addGroup(detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(currentGuaranteerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addressLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                .addContainerGap())
        );

        imagePanel.setBackground(new java.awt.Color(255, 255, 255));

        dateLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        dateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dateLabel.setText("date");

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

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Name  :");

        memberNameCombo.setEditable(true);
        memberNameCombo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        memberNameCombo.setToolTipText("Search by member name...");
        memberNameCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberNameComboActionPerformed(evt);
            }
        });
        memberNameCombo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                memberNameComboKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                memberNameComboKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanelLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(memberIDText, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(memberNameCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(619, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imagePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(dateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(memberIDText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(memberNameCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        imagePanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("BatangChe", 3, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Loan Details");

        tableLoan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tableLoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Loan ID", "Type", "Bring Date", "Ammount", "Is Closed"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableLoan.setToolTipText("Loan details...");
        tableLoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableLoanMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tableLoan);

        jLabel8.setFont(new java.awt.Font("BatangChe", 3, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Account Details");

        tableAccount.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tableAccount.setModel(new javax.swing.table.DefaultTableModel(
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
        tableAccount.setToolTipText("Account details...");
        tableAccount.setEnabled(false);
        tableAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableAccountMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tableAccount);

        moreButton.setBackground(new java.awt.Color(0, 153, 255));
        moreButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        moreButton.setForeground(new java.awt.Color(255, 255, 255));
        moreButton.setText("More");
        moreButton.setToolTipText("More loan details...");
        moreButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        moreButton.setEnabled(false);
        moreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moreButtonActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 153, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("NEW LOAN");
        jButton1.setToolTipText("Confirm...");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        backToMainButton.setBackground(new java.awt.Color(0, 153, 255));
        backToMainButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        backToMainButton.setForeground(new java.awt.Color(255, 255, 255));
        backToMainButton.setText("Back To Main");
        backToMainButton.setToolTipText("More loan details...");
        backToMainButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backToMainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToMainButtonActionPerformed(evt);
            }
        });

        clearButton.setBackground(new java.awt.Color(0, 153, 255));
        clearButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        clearButton.setForeground(new java.awt.Color(255, 255, 255));
        clearButton.setText("Clear");
        clearButton.setToolTipText("More loan details...");
        clearButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout imagePanel2Layout = new javax.swing.GroupLayout(imagePanel2);
        imagePanel2.setLayout(imagePanel2Layout);
        imagePanel2Layout.setHorizontalGroup(
            imagePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanel2Layout.createSequentialGroup()
                .addGroup(imagePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 727, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(imagePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imagePanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(199, 199, 199)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(imagePanel2Layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(moreButton, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(backToMainButton, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );
        imagePanel2Layout.setVerticalGroup(
            imagePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(imagePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(imagePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(imagePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(moreButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backToMainButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(detailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1363, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(imagePanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imagePanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void memberIDTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberIDTextActionPerformed
        action();
    }//GEN-LAST:event_memberIDTextActionPerformed

    private void memberNameComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberNameComboActionPerformed
    }//GEN-LAST:event_memberNameComboActionPerformed

    private void memberNameComboKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_memberNameComboKeyPressed
    }//GEN-LAST:event_memberNameComboKeyPressed

    private void tableAccountMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAccountMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tableAccountMouseReleased

    private void tableLoanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLoanMouseReleased
    }//GEN-LAST:event_tableLoanMouseReleased

    private void moreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moreButtonActionPerformed
        if (tableLoan.getSelectedRow() != -1) {
            MoreLoanDetails moreLoanDetails = new MoreLoanDetails(null, true, (String) dtmLoan.getValueAt(tableLoan.getSelectedRow(), 0));
            moreLoanDetails.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Select the loan");
        }
    }//GEN-LAST:event_moreButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            new NewLoanForm().setVisible(true);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CheckForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void memberNameComboKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_memberNameComboKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tableAccount.getRowCount() > 0) {
                dtmAccount.setRowCount(0);
            }
            if (tableLoan.getRowCount() > 0) {
                dtmLoan.setRowCount(0);
            }
            try {
                Member member = MemberController.getMemberDetailByName(String.valueOf(memberNameCombo.getSelectedItem()));
                if (member != null) {
                    nameLabel.setText(member.getName());
                    String address = member.getAddress();
                    String[] addressData = address.split(",", 3);
                    if (addressData.length == 3) {
                        addressLabel1.setText(addressData[0]);
                        addressLabel2.setText(addressData[1]);
                        addressLabel3.setText(addressData[2]);
                    }
                    if (addressData.length == 2) {
                        addressLabel1.setText(addressData[0]);
                        addressLabel2.setText(addressData[1]);
                    }
                    if (addressData.length == 1) {
                        addressLabel1.setText(addressData[0]);
                    }
                    nicLabel.setText(member.getNic());
                    telLabel.setText(Integer.toString(member.getContactNum()));

                    ArrayList<Account> accountList = AccountController.searchAccount(memberIDText.getText());
                    Object[] column = {"Account Number", "Type", "Balance"};
                    dtmAccount = new DefaultTableModel(column, 0);
                    for (int i = 0; i < accountList.size(); i++) {
                        Account account = accountList.get(i);
                        String atid = account.getATID();
                        String type = AccountTypeController.getAccountType(atid);
                        Integer accountNumber = account.getAccountNumber();
                        Double curAmmount = account.getCurrentAmmount();
                        if (!type.equals("")) {
                            Object[] row = {accountNumber, type, curAmmount};
                            tableAccount.setModel(dtmAccount);
                            dtmAccount.addRow(row);
                        }
                    }

                    ArrayList<Loan> loanList = LoanController.searchLoans(memberIDText.getText());
                    Object[] column2 = {"Loan ID", "Type", "Date", "Amount", "Is Closed"};
                    dtmLoan = new DefaultTableModel(column2, 0);
                    for (int i = 0; i < loanList.size(); i++) {
                        Loan loan = loanList.get(i);
                        String ltid = loan.getLTID();
                        String type = LoanTypeController.getLoanType(ltid);
                        String date = loan.getlDate();
                        double amount = loan.getAmmount();
                        int instalmentCount = loan.getInstalmentCount();
                        boolean isClosed = loan.getBalance() == 0;
                        String isClosedNew;
                        if (isClosed) {
                            isClosedNew = "Yes";
                        } else {
                            isClosedNew = "No";
                        }

                        Object[] row = {loan.getLID(), type, date, amount, isClosedNew};
                        tableLoan.setModel(dtmLoan);
                        dtmLoan.addRow(row);
                    }

                    boolean isGuarantor = GuaranteeController.isGuarantor(memberIDText.getText());
                    if (isGuarantor) {
                        currentGuaranteerLabel.setText(nameLabel.getText() + " is already guarantor...");
                    } else {
                        currentGuaranteerLabel.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(detailPanel, memberIDText.getText() + " member not found");
                    memberIDText.setText("");
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(CheckForm.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (tableLoan.getRowCount() > 0) {
                moreButton.setEnabled(true);
            } else {
                moreButton.setEnabled(false);
            }
        }
    }//GEN-LAST:event_memberNameComboKeyReleased

    private void backToMainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToMainButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_backToMainButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        emptyAll(true);
    }//GEN-LAST:event_clearButtonActionPerformed

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
            java.util.logging.Logger.getLogger(CheckForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new CheckForm().setVisible(true);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(CheckForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addressLabel1;
    private javax.swing.JLabel addressLabel2;
    private javax.swing.JLabel addressLabel3;
    private javax.swing.JButton backToMainButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JLabel currentGuaranteerLabel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JPanel detailPanel;
    private org.jdesktop.swingx.JXImagePanel imagePanel;
    private org.jdesktop.swingx.JXImagePanel imagePanel2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField memberIDText;
    private javax.swing.JComboBox memberNameCombo;
    private javax.swing.JButton moreButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nicLabel;
    private javax.swing.JTable tableAccount;
    private javax.swing.JTable tableLoan;
    private javax.swing.JLabel telLabel;
    // End of variables declaration//GEN-END:variables
}
