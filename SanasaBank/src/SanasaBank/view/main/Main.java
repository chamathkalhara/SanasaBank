/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SanasaBank.view.main;

import SanasaBank.controller.CashInHandController;
import SanasaBank.controller.InstalmentController;
import SanasaBank.controller.TranceactionController;
import SanasaBank.model.CashInHand;
import SanasaBank.model.Instalment;
import SanasaBank.model.Tranceaction;
import SanasaBank.other.Backup;
import SanasaBank.view.account.DeleteAccountForm;
import SanasaBank.view.account.NewAccountForm;
import SanasaBank.view.account.ViewAllAccount;
import SanasaBank.view.accountType.NewAccountType;
import SanasaBank.view.accountType.UpdateAccountType;
import SanasaBank.view.cashInHand.Balance;
import SanasaBank.view.cashInHand.SelectDay;
import SanasaBank.view.cashInHand.UpdateCashInHand;
import SanasaBank.view.loan.ArrearsLoans;
import SanasaBank.view.loan.CheckForm;
import SanasaBank.view.loan.CurrentLoans;
import SanasaBank.view.loan.InstalmentForm;
import SanasaBank.view.loan.NewLoanForm;
import SanasaBank.view.loan.ViewAllLoan;
import SanasaBank.view.loanType.NewLoanType;
import SanasaBank.view.loanType.UpdateLoanType;
import SanasaBank.view.login.LoginPage;
import SanasaBank.view.member.UpdateMember;
import SanasaBank.view.reports.CashInHandDataInDuration;
import SanasaBank.view.reports.MemberDetails;
import SanasaBank.view.reports.MemberTranceactions;
import SanasaBank.view.reports.TransactionsInDuration;
import SanasaBank.view.reports.TrialBalance;
import SanasaBank.view.settings.Settings;
import SanasaBank.view.transactions.DipositFrame;
import SanasaBank.view.transactions.WithdrawalForm;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
 * @author Rashmila Rahuman
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    private boolean isAdmin;
    public Main(boolean isAdmin) {

        initComponents();
        
        this.isAdmin=isAdmin;
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/image/image1.jpg"));
        imagePanel.setImage(icon.getImage());
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);

    }
    
    private void todayTransaction(){
        try {
            Object[] column = {"Bill Number", "ID", "Member", "Type", "Amount", "Interest"};
            DefaultTableModel dtm = new DefaultTableModel(column, 0);
            ArrayList<Tranceaction> tranceactionsList = TranceactionController.getTranceactionsByDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            ArrayList<Instalment> instalmentsList = InstalmentController.getAllInstalmentsInDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

            double receipt=0;
            double expenses=0;
            double balance=0;
            
            for (Tranceaction tranceaction : tranceactionsList) {
                Object[] row = {tranceaction.getBillNumber(), tranceaction.getAID(), tranceaction.getTID(), tranceaction.gettType(), tranceaction.getAmmount(), "-"};
                dtm.addRow(row);
                if(tranceaction.gettType().equals("Withdraw")){
                    expenses+=tranceaction.getAmmount();
                }if(tranceaction.gettType().equals("Deposit")){
                    receipt+=tranceaction.getAmmount();
                }
            }
            Object[] columnEmpty = {"", "", "", "", "", ""};
            dtm.addRow(columnEmpty);
            for (Instalment instalment : instalmentsList) {
                Object[] row = {instalment.getBillNumber(), instalment.getLID(), instalment.getIID(), "Instalment", instalment.getiAmmount(), instalment.getiInterest()};
                dtm.addRow(row);
                receipt+=(instalment.getiAmmount()+instalment.getiInterest());
            }
            CashInHand cashInHand = CashInHandController.searchCashInHand(CashInHandController.getLastCashInHandID());
            balance=cashInHand.getBalance();
            HashMap<String, Object> map = new HashMap<>();
            map.put("receipt", receipt);
            map.put("expenses", expenses);
            map.put("balance", balance);
            JasperReport compileReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("../../report/TodayTransactions.jrxml"));
            JRTableModelDataSource dateSource = new JRTableModelDataSource(dtm);
            JasperPrint fillReport = JasperFillManager.fillReport(compileReport, map, dateSource);
            JasperViewer.viewReport(fillReport,false);

        } catch (JRException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
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

        imagePanel = new org.jdesktop.swingx.JXImagePanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        settingsButton = new javax.swing.JButton();
        todayTransactionsButton = new javax.swing.JButton();
        checkButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        arrearsLoanbutton = new javax.swing.JButton();
        trialBalanceButton = new javax.swing.JButton();
        imagePanel3 = new org.jdesktop.swingx.JXImagePanel();
        withdrawButton = new javax.swing.JButton();
        depositButton = new javax.swing.JButton();
        instalmentButton = new javax.swing.JButton();
        newAccountButton = new javax.swing.JButton();
        ReportButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exit = new javax.swing.JMenuItem();
        addNewAccount = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenu5 = new javax.swing.JMenu();
        withdraw = new javax.swing.JMenuItem();
        deposit = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        deleteAccount = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenu4 = new javax.swing.JMenu();
        newAccountType = new javax.swing.JMenuItem();
        updateAccountType = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        viewAllMembers = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        updateBalance = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jMenu13 = new javax.swing.JMenu();
        viewBalance = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        byDay = new javax.swing.JMenuItem();
        byDuration = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        newLoan = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        instalment = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        jMenu2 = new javax.swing.JMenu();
        newLoanType = new javax.swing.JMenuItem();
        updateLoanType = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenu11 = new javax.swing.JMenu();
        memberDetails = new javax.swing.JMenuItem();
        memberTransactions = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenu12 = new javax.swing.JMenu();
        editMemberDetails = new javax.swing.JMenuItem();
        view = new javax.swing.JMenu();
        trialBalance = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        transactions = new javax.swing.JMenu();
        today = new javax.swing.JMenuItem();
        inDay = new javax.swing.JMenuItem();
        inDuration = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        currentLoans = new javax.swing.JMenuItem();
        arrearsLoans = new javax.swing.JMenuItem();
        backup = new javax.swing.JMenu();
        jMenu15 = new javax.swing.JMenu();
        createBackup = new javax.swing.JMenuItem();
        restoreBackup = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        imagePanel.setBackground(new java.awt.Color(255, 255, 255));
        imagePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/2_1.png"))); // NOI18N
        jLabel2.setText("WELCOME TO SANASA BANK");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PATTIHENA DEWINUWARA");

        settingsButton.setBackground(new java.awt.Color(102, 153, 255));
        settingsButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        settingsButton.setForeground(new java.awt.Color(255, 255, 255));
        settingsButton.setText("SETTINGS");
        settingsButton.setToolTipText("New Loan...");
        settingsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        settingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsButtonActionPerformed(evt);
            }
        });

        todayTransactionsButton.setBackground(new java.awt.Color(102, 153, 255));
        todayTransactionsButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        todayTransactionsButton.setForeground(new java.awt.Color(255, 255, 255));
        todayTransactionsButton.setText("TODAY TRANSACTIONS");
        todayTransactionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todayTransactionsButtonActionPerformed(evt);
            }
        });

        checkButton.setBackground(new java.awt.Color(102, 153, 255));
        checkButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        checkButton.setForeground(new java.awt.Color(255, 255, 255));
        checkButton.setText("CHECK");
        checkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkButtonActionPerformed(evt);
            }
        });

        exitButton.setBackground(new java.awt.Color(0, 51, 102));
        exitButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        exitButton.setForeground(new java.awt.Color(255, 255, 255));
        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        arrearsLoanbutton.setBackground(new java.awt.Color(102, 153, 255));
        arrearsLoanbutton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        arrearsLoanbutton.setForeground(new java.awt.Color(255, 255, 255));
        arrearsLoanbutton.setText("ARREARS LOANS");
        arrearsLoanbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arrearsLoanbuttonActionPerformed(evt);
            }
        });

        trialBalanceButton.setBackground(new java.awt.Color(102, 153, 255));
        trialBalanceButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        trialBalanceButton.setForeground(new java.awt.Color(255, 255, 255));
        trialBalanceButton.setText("TRIAL BALANCE");
        trialBalanceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trialBalanceButtonActionPerformed(evt);
            }
        });

        imagePanel3.setBackground(new java.awt.Color(0, 51, 102));

        withdrawButton.setBackground(new java.awt.Color(102, 153, 255));
        withdrawButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        withdrawButton.setForeground(new java.awt.Color(255, 255, 255));
        withdrawButton.setText("WITHDROW");
        withdrawButton.setToolTipText("Withdrawal...");
        withdrawButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        withdrawButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withdrawButtonActionPerformed(evt);
            }
        });

        depositButton.setBackground(new java.awt.Color(102, 153, 255));
        depositButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        depositButton.setForeground(new java.awt.Color(255, 255, 255));
        depositButton.setText("DEPOSIT");
        depositButton.setToolTipText("Deposit...");
        depositButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        depositButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                depositButtonActionPerformed(evt);
            }
        });

        instalmentButton.setBackground(new java.awt.Color(102, 153, 255));
        instalmentButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        instalmentButton.setForeground(new java.awt.Color(255, 255, 255));
        instalmentButton.setText("INSTALMENT");
        instalmentButton.setToolTipText("Instalment...");
        instalmentButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        instalmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instalmentButtonActionPerformed(evt);
            }
        });

        newAccountButton.setBackground(new java.awt.Color(102, 153, 255));
        newAccountButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        newAccountButton.setForeground(new java.awt.Color(255, 255, 255));
        newAccountButton.setText("NEW ACCOUNT");
        newAccountButton.setToolTipText("New Account...");
        newAccountButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAccountButtonActionPerformed(evt);
            }
        });

        ReportButton.setBackground(new java.awt.Color(102, 153, 255));
        ReportButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ReportButton.setForeground(new java.awt.Color(255, 255, 255));
        ReportButton.setText("NEW LOAN");
        ReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout imagePanel3Layout = new javax.swing.GroupLayout(imagePanel3);
        imagePanel3.setLayout(imagePanel3Layout);
        imagePanel3Layout.setHorizontalGroup(
            imagePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(imagePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(depositButton, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(withdrawButton, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(instalmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        imagePanel3Layout.setVerticalGroup(
            imagePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(withdrawButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(depositButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(instalmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(newAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(ReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(imagePanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(imagePanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(imagePanelLayout.createSequentialGroup()
                        .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(imagePanelLayout.createSequentialGroup()
                                .addComponent(checkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)
                                .addComponent(settingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(imagePanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(todayTransactionsButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(arrearsLoanbutton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(trialBalanceButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                    .addComponent(jSeparator2)))
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanelLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(imagePanelLayout.createSequentialGroup()
                        .addComponent(trialBalanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(arrearsLoanbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(todayTransactionsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(checkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(settingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(imagePanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 49, Short.MAX_VALUE))
        );

        jMenu1.setText("File");
        jMenu1.add(jSeparator1);

        exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        jMenu1.add(exit);

        jMenuBar1.add(jMenu1);

        addNewAccount.setText("Account");

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Add new Account");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        addNewAccount.add(jMenuItem5);
        addNewAccount.add(jSeparator5);

        jMenu5.setText(" Transaction");

        withdraw.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        withdraw.setText("Withdraw");
        withdraw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withdrawActionPerformed(evt);
            }
        });
        jMenu5.add(withdraw);

        deposit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        deposit.setText(" Deposit");
        deposit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                depositActionPerformed(evt);
            }
        });
        jMenu5.add(deposit);

        addNewAccount.add(jMenu5);
        addNewAccount.add(jSeparator3);

        deleteAccount.setText("Delete Account");
        deleteAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAccountActionPerformed(evt);
            }
        });
        addNewAccount.add(deleteAccount);
        addNewAccount.add(jSeparator4);

        jMenu4.setText("Account type");

        newAccountType.setText("New");
        newAccountType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAccountTypeActionPerformed(evt);
            }
        });
        jMenu4.add(newAccountType);

        updateAccountType.setText("Update");
        updateAccountType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateAccountTypeActionPerformed(evt);
            }
        });
        jMenu4.add(updateAccountType);

        addNewAccount.add(jMenu4);
        addNewAccount.add(jSeparator9);

        viewAllMembers.setText("View all");
        viewAllMembers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewAllMembersActionPerformed(evt);
            }
        });
        addNewAccount.add(viewAllMembers);

        jMenuBar1.add(addNewAccount);

        jMenu6.setText(" Cash In Hand");

        updateBalance.setText(" Update Balance");
        updateBalance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBalanceActionPerformed(evt);
            }
        });
        jMenu6.add(updateBalance);
        jMenu6.add(jSeparator8);

        jMenu13.setText("View");

        viewBalance.setText("Balance");
        viewBalance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBalanceActionPerformed(evt);
            }
        });
        jMenu13.add(viewBalance);
        jMenu13.add(jSeparator15);

        byDay.setText("By Day");
        byDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byDayActionPerformed(evt);
            }
        });
        jMenu13.add(byDay);

        byDuration.setText("By Duration");
        byDuration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byDurationActionPerformed(evt);
            }
        });
        jMenu13.add(byDuration);

        jMenu6.add(jMenu13);

        jMenuBar1.add(jMenu6);

        jMenu7.setText("Loan");

        newLoan.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        newLoan.setText("New Loan");
        newLoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newLoanActionPerformed(evt);
            }
        });
        jMenu7.add(newLoan);
        jMenu7.add(jSeparator10);

        instalment.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        instalment.setText("Instalment");
        instalment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instalmentActionPerformed(evt);
            }
        });
        jMenu7.add(instalment);
        jMenu7.add(jSeparator11);

        jMenu2.setText("Loan Type");

        newLoanType.setText("New");
        newLoanType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newLoanTypeActionPerformed(evt);
            }
        });
        jMenu2.add(newLoanType);

        updateLoanType.setText(" Update");
        updateLoanType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateLoanTypeActionPerformed(evt);
            }
        });
        jMenu2.add(updateLoanType);

        jMenu7.add(jMenu2);
        jMenu7.add(jSeparator12);

        jMenuItem1.setText("View All");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem1);

        jMenuBar1.add(jMenu7);

        jMenu9.setText(" Member");

        jMenu11.setText("View");

        memberDetails.setText(" Member details");
        memberDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberDetailsActionPerformed(evt);
            }
        });
        jMenu11.add(memberDetails);

        memberTransactions.setText("Member Transactions");
        memberTransactions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberTransactionsActionPerformed(evt);
            }
        });
        jMenu11.add(memberTransactions);

        jMenu9.add(jMenu11);
        jMenu9.add(jSeparator7);

        jMenu12.setText("Edit");

        editMemberDetails.setText("Member Details");
        editMemberDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMemberDetailsActionPerformed(evt);
            }
        });
        jMenu12.add(editMemberDetails);

        jMenu9.add(jMenu12);

        jMenuBar1.add(jMenu9);

        view.setText("View");

        trialBalance.setText("Trial Balance");
        trialBalance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trialBalanceActionPerformed(evt);
            }
        });
        view.add(trialBalance);
        view.add(jSeparator6);

        transactions.setText("Transactions");

        today.setText("Today");
        today.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todayActionPerformed(evt);
            }
        });
        transactions.add(today);

        inDay.setText("In Day");
        inDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inDayActionPerformed(evt);
            }
        });
        transactions.add(inDay);

        inDuration.setText("In Duration");
        inDuration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inDurationActionPerformed(evt);
            }
        });
        transactions.add(inDuration);

        view.add(transactions);
        view.add(jSeparator13);

        currentLoans.setText("Current Loans");
        currentLoans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentLoansActionPerformed(evt);
            }
        });
        view.add(currentLoans);

        arrearsLoans.setText("Arrears Loans");
        arrearsLoans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arrearsLoansActionPerformed(evt);
            }
        });
        view.add(arrearsLoans);

        jMenuBar1.add(view);

        backup.setText("Tools");

        jMenu15.setText("Backup");

        createBackup.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        createBackup.setText("Create Backup");
        createBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBackupActionPerformed(evt);
            }
        });
        jMenu15.add(createBackup);

        restoreBackup.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        restoreBackup.setText("Restore Backup");
        restoreBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restoreBackupActionPerformed(evt);
            }
        });
        jMenu15.add(restoreBackup);

        backup.add(jMenu15);

        jMenuBar1.add(backup);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void withdrawButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withdrawButtonActionPerformed
        try {
            new WithdrawalForm().setVisible(true);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_withdrawButtonActionPerformed

    private void depositButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_depositButtonActionPerformed
        try {
            new SanasaBank.view.transactions.DipositFrame().setVisible(true);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_depositButtonActionPerformed

    private void instalmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instalmentButtonActionPerformed
        try {
            new InstalmentForm().setVisible(true);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_instalmentButtonActionPerformed

    private void settingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsButtonActionPerformed
        new Settings(this.isAdmin).setVisible(true);
    }//GEN-LAST:event_settingsButtonActionPerformed

    private void newAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAccountButtonActionPerformed
        try {
            new NewAccountForm().setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_newAccountButtonActionPerformed

    private void deleteAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAccountActionPerformed
        new DeleteAccountForm().setVisible(true);
    }//GEN-LAST:event_deleteAccountActionPerformed

    private void checkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkButtonActionPerformed
        try {
            new CheckForm().setVisible(true);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_checkButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        this.dispose();
        new LoginPage().setVisible(true);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void ReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportButtonActionPerformed
        try {
            new NewLoanForm().setVisible(true);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CheckForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ReportButtonActionPerformed

    private void todayTransactionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todayTransactionsButtonActionPerformed
        todayTransaction();
    }//GEN-LAST:event_todayTransactionsButtonActionPerformed

    private void arrearsLoanbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arrearsLoanbuttonActionPerformed
        try {
            new ArrearsLoans().setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_arrearsLoanbuttonActionPerformed

    private void createBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createBackupActionPerformed
        try {
            JFileChooser chooser = new JFileChooser();
            int showSaveDialog = chooser.showSaveDialog(null);
            //chooser.setSelectedFile("");
            File selectedFile = chooser.getSelectedFile();
            if (!selectedFile.getPath().equals("")) {
                Backup.writeBackup(selectedFile.getPath());
                JOptionPane.showMessageDialog(null, "Backup Created Success");
            } else {
                JOptionPane.showMessageDialog(null, "Please insert the file name");
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_createBackupActionPerformed

    private void restoreBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restoreBackupActionPerformed
        try {
            JFileChooser chooser = new JFileChooser();
            int showSaveDialog = chooser.showSaveDialog(null);
            //chooser.setSelectedFile("");
            File selectedFile = chooser.getSelectedFile();
            Backup.restoreBackup(selectedFile.getPath());
            JOptionPane.showMessageDialog(null, "Backup Restore Success");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_restoreBackupActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        System.exit(3);
        new LoginPage().setVisible(true);
    }//GEN-LAST:event_exitActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        try {
            new NewAccountForm().setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void withdrawActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withdrawActionPerformed
        try {
            new WithdrawalForm().setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_withdrawActionPerformed

    private void depositActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_depositActionPerformed
        try {
            new DipositFrame().setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_depositActionPerformed

    private void updateBalanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBalanceActionPerformed
        try {
            CashInHand cashInHand = CashInHandController.searchCashInHand(CashInHandController.getLastCashInHandID());
            new UpdateCashInHand(null, true, cashInHand).setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateBalanceActionPerformed

    private void newLoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newLoanActionPerformed
        try {
            new NewLoanForm().setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_newLoanActionPerformed

    private void instalmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instalmentActionPerformed
        try {
            new InstalmentForm().setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_instalmentActionPerformed

    private void memberTransactionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberTransactionsActionPerformed
        new MemberTranceactions().setVisible(true);
    }//GEN-LAST:event_memberTransactionsActionPerformed

    private void memberDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberDetailsActionPerformed
        try {
            new MemberDetails().setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_memberDetailsActionPerformed

    private void trialBalanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trialBalanceActionPerformed
    }//GEN-LAST:event_trialBalanceActionPerformed

    private void updateAccountTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateAccountTypeActionPerformed
        try {
            new UpdateAccountType(null, true).setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateAccountTypeActionPerformed

    private void newAccountTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAccountTypeActionPerformed
        new NewAccountType(null, true).setVisible(true);
    }//GEN-LAST:event_newAccountTypeActionPerformed

    private void updateLoanTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateLoanTypeActionPerformed
        try {
            new UpdateLoanType(null, true).setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateLoanTypeActionPerformed

    private void newLoanTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newLoanTypeActionPerformed
        new NewLoanType(null, true).setVisible(true);
    }//GEN-LAST:event_newLoanTypeActionPerformed

    private void editMemberDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMemberDetailsActionPerformed
        try {
            new UpdateMember(null, true).setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_editMemberDetailsActionPerformed

    private void trialBalanceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trialBalanceButtonActionPerformed
        new TrialBalance().setVisible(true);
    }//GEN-LAST:event_trialBalanceButtonActionPerformed

    private void viewAllMembersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewAllMembersActionPerformed
        try {
            new ViewAllAccount().setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_viewAllMembersActionPerformed

    private void viewBalanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewBalanceActionPerformed
        try {
            new Balance(null, true).setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_viewBalanceActionPerformed

    private void byDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_byDayActionPerformed
        new SelectDay(null, true).setVisible(true);
    }//GEN-LAST:event_byDayActionPerformed

    private void byDurationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_byDurationActionPerformed
        new CashInHandDataInDuration().setVisible(true);
    }//GEN-LAST:event_byDurationActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            new ViewAllLoan().setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void todayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todayActionPerformed
        todayTransaction();
    }//GEN-LAST:event_todayActionPerformed

    private void inDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inDayActionPerformed
        new SanasaBank.view.reports.TransactionsInDay().setVisible(true);
    }//GEN-LAST:event_inDayActionPerformed

    private void currentLoansActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currentLoansActionPerformed
        try {
            new CurrentLoans().setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_currentLoansActionPerformed

    private void arrearsLoansActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arrearsLoansActionPerformed
        try {
            new ArrearsLoans().setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_arrearsLoansActionPerformed

    private void inDurationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inDurationActionPerformed
        new TransactionsInDuration().setVisible(true);
    }//GEN-LAST:event_inDurationActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main(false).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ReportButton;
    private javax.swing.JMenu addNewAccount;
    private javax.swing.JButton arrearsLoanbutton;
    private javax.swing.JMenuItem arrearsLoans;
    private javax.swing.JMenu backup;
    private javax.swing.JMenuItem byDay;
    private javax.swing.JMenuItem byDuration;
    private javax.swing.JButton checkButton;
    private javax.swing.JMenuItem createBackup;
    private javax.swing.JMenuItem currentLoans;
    private javax.swing.JMenuItem deleteAccount;
    private javax.swing.JMenuItem deposit;
    private javax.swing.JButton depositButton;
    private javax.swing.JMenuItem editMemberDetails;
    private javax.swing.JMenuItem exit;
    private javax.swing.JButton exitButton;
    private org.jdesktop.swingx.JXImagePanel imagePanel;
    private org.jdesktop.swingx.JXImagePanel imagePanel3;
    private javax.swing.JMenuItem inDay;
    private javax.swing.JMenuItem inDuration;
    private javax.swing.JMenuItem instalment;
    private javax.swing.JButton instalmentButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator15;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JMenuItem memberDetails;
    private javax.swing.JMenuItem memberTransactions;
    private javax.swing.JButton newAccountButton;
    private javax.swing.JMenuItem newAccountType;
    private javax.swing.JMenuItem newLoan;
    private javax.swing.JMenuItem newLoanType;
    private javax.swing.JMenuItem restoreBackup;
    private javax.swing.JButton settingsButton;
    private javax.swing.JMenuItem today;
    private javax.swing.JButton todayTransactionsButton;
    private javax.swing.JMenu transactions;
    private javax.swing.JMenuItem trialBalance;
    private javax.swing.JButton trialBalanceButton;
    private javax.swing.JMenuItem updateAccountType;
    private javax.swing.JMenuItem updateBalance;
    private javax.swing.JMenuItem updateLoanType;
    private javax.swing.JMenu view;
    private javax.swing.JMenuItem viewAllMembers;
    private javax.swing.JMenuItem viewBalance;
    private javax.swing.JMenuItem withdraw;
    private javax.swing.JButton withdrawButton;
    // End of variables declaration//GEN-END:variables
}
