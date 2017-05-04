/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SanasaBank.view.loan;

import SanasaBank.controller.BillNumberContraller;
import SanasaBank.controller.CashInHandController;
import SanasaBank.controller.InstalmentController;
import SanasaBank.controller.LoanController;
import SanasaBank.controller.LoanTypeController;
import SanasaBank.controller.MemberController;
import SanasaBank.model.Loan;
import SanasaBank.model.Member;
import SanasaBank.other.AutoGenarateID;
import SanasaBank.other.ComboSearch;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

/**
 *
 * @author Kalhara
 */
public class InstalmentForm extends javax.swing.JFrame {

    /**
     * Creates new form InstalmentForm
     */
    private Date date;
    private double instalment;
    private String nextPaymentDate;
    private double resevedFullInterest;

    public InstalmentForm() throws ClassNotFoundException, SQLException {
        initComponents();


        ImageIcon icon = new ImageIcon(getClass().getResource("/image/label.png"));
        imagePanel1.setImage(icon.getImage());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);

        countSpinner.setValue(1);

        date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String curDate = simpleDateFormat.format(date);

        dateLabel.setText(curDate);

        setBillNumber();

        ArrayList<String> memberNameList = MemberController.getAllMemberName();
        for (String name : memberNameList) {
            memberNameCombo.addItem(name);
        }
        memberNameCombo.setSelectedIndex(-1);

        setLocationRelativeTo(null);

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
                            String memberID = member.getMID();
                            action(memberID);
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

    private void setBillNumber() throws ClassNotFoundException, SQLException {
        String lastNumber = BillNumberContraller.getLastNumber();
        AutoGenarateID.genarate(billNumberLabel, "", lastNumber);

    }

    private void emptyAll() {
        memberIDText.setText("");
        memberNameCombo.setSelectedIndex(-1);
        nameLabel.setText("");
        nicNoLabel.setText("");
        amountLabel.setText("");
        loanIDLabel.setText("");
        lastPaymentDateLabel.setText("");
        balanceLabel.setText("");
        instalmentLabel.setText("");
        interestLabel.setText("");
        countSpinner.setValue(1);
    }

    private void action(String memberID) {
        try {

            Member member = MemberController.getMemberDetail(memberID);
            if (member != null) {
                nameLabel.setText(member.getName());
                nicNoLabel.setText(member.getNic());
                memberNameCombo.setSelectedItem(member.getName());
                Loan loan = LoanController.searchCurrentLoan(memberID);
                if (loan != null) {
                    loanIDLabel.setText(loan.getLID());
                    amountLabel.setText(Double.toString(loan.getAmmount()));
                    lastPaymentDateLabel.setText(loan.getLastPaymentDate());
                    balanceLabel.setText(Double.toString(loan.getBalance()));
                    this.instalment = loan.getInstalment();
                    instalmentLabel.setText(Double.toString(this.instalment));
                    this.nextPaymentDate = loan.getNextPaymentDate();
                    this.resevedFullInterest = loan.getResevedFullInterest();

                    int loanRate = LoanTypeController.getLoanRate(LoanTypeController.getLoanType(loan.getLTID()));

                    String[] date = lastPaymentDateLabel.getText().split("-");
                    int[] dateNew = new int[3];
                    for (int i = 0; i < 3; i++) {
                        dateNew[i] = Integer.parseInt(date[i]);
                    }

                    Calendar lastPD = Calendar.getInstance();
                    lastPD.set(dateNew[0], dateNew[1] - 1, dateNew[2]);
                    int dayOfYerLPD = lastPD.get(Calendar.DAY_OF_YEAR);

                    Calendar curDate = Calendar.getInstance();
                    curDate.setTime(this.date);
                    int dayOfYearCD = curDate.get(Calendar.DAY_OF_YEAR);

                    int days = 0;
                    if (dayOfYearCD >= dayOfYerLPD) {
                        days = dayOfYearCD - dayOfYerLPD;

                    } else {
                        days = (365 - dayOfYerLPD) + dayOfYearCD;
                    }

                    double interest = loan.getBalance() * loanRate * days / (100 * 365);
                    interestLabel.setText(String.format("%.2f", interest));


                    proccessButton.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, memberID + " member has no current loan");
                    memberIDText.selectAll();
                }
            } else {
                JOptionPane.showMessageDialog(null, memberID + " Member not found");
                memberIDText.setText("");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(InstalmentForm.class.getName()).log(Level.SEVERE, null, ex);
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
        proccessButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        instalmentLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        countSpinner = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        interestLabel = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lastPaymentDateLabel = new javax.swing.JLabel();
        loanIDLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        balanceLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        amountLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nicNoLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        imagePanel1 = new org.jdesktop.swingx.JXImagePanel();
        dateLabel = new javax.swing.JLabel();
        billNumberLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        memberIDText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        memberNameCombo = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        imagePanel.setBackground(new java.awt.Color(255, 255, 255));

        proccessButton.setBackground(new java.awt.Color(51, 102, 255));
        proccessButton.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        proccessButton.setForeground(new java.awt.Color(255, 255, 255));
        proccessButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/instalment.png"))); // NOI18N
        proccessButton.setText("PAY");
        proccessButton.setToolTipText("Pay...");
        proccessButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        proccessButton.setEnabled(false);
        proccessButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proccessButtonActionPerformed(evt);
            }
        });

        clearButton.setBackground(new java.awt.Color(51, 102, 255));
        clearButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        clearButton.setForeground(new java.awt.Color(255, 255, 255));
        clearButton.setText("Clear");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Instalment  :");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Rs");

        instalmentLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        instalmentLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Count  :");

        countSpinner.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        countSpinner.setToolTipText("Select instalment count...");
        countSpinner.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        countSpinner.setName(""); // NOI18N
        countSpinner.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                countSpinnerMouseReleased(evt);
            }
        });
        countSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                countSpinnerStateChanged(evt);
            }
        });
        countSpinner.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                countSpinnerKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Interest  :");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Rs");

        interestLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        interestLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Last Payment Date :");

        lastPaymentDateLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lastPaymentDateLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        loanIDLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        loanIDLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Loan ID  :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Balance  :");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Rs");

        balanceLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        balanceLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Amount  :");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Rs");

        amountLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        amountLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Name  :");

        nameLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("NIC No  :");

        nicNoLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        nicNoLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

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
                .addGroup(imagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(imagePanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(memberIDText, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(memberNameCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 448, Short.MAX_VALUE)
                        .addComponent(billNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(imagePanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );
        imagePanel1Layout.setVerticalGroup(
            imagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanel1Layout.createSequentialGroup()
                .addComponent(dateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(imagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(memberIDText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(memberNameCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(billNumberLabel))
                .addGap(0, 43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanelLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(imagePanelLayout.createSequentialGroup()
                        .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(imagePanelLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(37, 37, 37)
                                .addComponent(lastPaymentDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(imagePanelLayout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imagePanelLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(loanIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(165, 165, 165))
                    .addGroup(imagePanelLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(instalmentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(countSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)))
                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(imagePanelLayout.createSequentialGroup()
                        .addComponent(proccessButton, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imagePanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imagePanelLayout.createSequentialGroup()
                                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(imagePanelLayout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(imagePanelLayout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(26, 26, 26)
                                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(amountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(balanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(imagePanelLayout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nicNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(imagePanelLayout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(interestLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(99, 99, 99))))
            .addGroup(imagePanelLayout.createSequentialGroup()
                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(imagePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(imagePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanelLayout.createSequentialGroup()
                .addComponent(imagePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(nicNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loanIDLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addComponent(amountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastPaymentDateLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(balanceLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(imagePanelLayout.createSequentialGroup()
                        .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(instalmentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addComponent(countSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(124, 124, 124)
                        .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(proccessButton)))
                    .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(interestLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imagePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void memberIDTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberIDTextActionPerformed
        String memberID = memberIDText.getText();
        action(memberID);
    }//GEN-LAST:event_memberIDTextActionPerformed

    private void countSpinnerMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_countSpinnerMouseReleased
    }//GEN-LAST:event_countSpinnerMouseReleased

    private void countSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_countSpinnerStateChanged
        if(!memberIDText.getText().equals("")){
            int spinnerValue = Integer.parseInt(String.valueOf(countSpinner.getValue()));
            if(spinnerValue==0){
                countSpinner.setValue(1);
                spinnerValue=1;
            }
            int maxCount = (int) (Double.parseDouble(balanceLabel.getText()) / instalment);
            if (maxCount >= spinnerValue) {
                instalmentLabel.setText(Double.toString((instalment) * spinnerValue));
            } else {
                countSpinner.setValue(maxCount);
            }
        }

    }//GEN-LAST:event_countSpinnerStateChanged

    private void countSpinnerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_countSpinnerKeyReleased
    }//GEN-LAST:event_countSpinnerKeyReleased

    private void proccessButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proccessButtonActionPerformed
        try {

            String lastInstalmentID = InstalmentController.getLastInstalmentID();
            SanasaBank.model.Instalment instalment = new SanasaBank.model.Instalment(AutoGenarateID.genarate(null, "I", lastInstalmentID), loanIDLabel.getText(), CashInHandController.getLastCashInHandID(), Integer.parseInt(billNumberLabel.getText()), dateLabel.getText(), Double.parseDouble(instalmentLabel.getText()), Integer.parseInt(String.valueOf(countSpinner.getValue())), Double.parseDouble(interestLabel.getText()));
            int res = InstalmentController.addInstalment(instalment);
            if (res > 0) {
                BillNumberContraller.addBillNumber(Integer.parseInt(billNumberLabel.getText()));
                Loan loan = new Loan();
                loan.setBalance(Double.parseDouble(balanceLabel.getText()) - Double.parseDouble(instalmentLabel.getText()));

                String[] nextDate = this.nextPaymentDate.split("-");
                int[] newNextDate = new int[3];
                for (int i = 0; i < 3; i++) {
                    newNextDate[i] = Integer.parseInt(nextDate[i]);
                }

                Calendar nextPD = Calendar.getInstance();
                nextPD.set(newNextDate[0], newNextDate[1] - 1, newNextDate[2]);

                nextPD.roll(Calendar.MONTH, 1);
                Date newDate = nextPD.getTime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String newNextPaymentDate = simpleDateFormat.format(newDate);

                loan.setNextPaymentDate(newNextPaymentDate);
                loan.setLastPaymentDate(dateLabel.getText());
                double resevedFullInterest = this.resevedFullInterest + Double.parseDouble(interestLabel.getText());
                loan.setResevedFullInterest(resevedFullInterest);

                loan.setLID(loanIDLabel.getText());
                int res2 = LoanController.updateLoan(loan);
                if (res2 > 0) {
                    CashInHandController.updateCashInHand(this.instalment + Double.parseDouble(interestLabel.getText()));
                    JOptionPane.showMessageDialog(null, "Success....");
                    this.dispose();

                } else {
                    JOptionPane.showMessageDialog(null, "Eroor......");
                }
            } else {
                JOptionPane.showMessageDialog(null, "error.....");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(InstalmentForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_proccessButtonActionPerformed

    private void memberNameComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_memberNameComboItemStateChanged
    }//GEN-LAST:event_memberNameComboItemStateChanged

    private void memberNameComboKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_memberNameComboKeyReleased
    }//GEN-LAST:event_memberNameComboKeyReleased

    private void memberNameComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberNameComboActionPerformed
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
    }//GEN-LAST:event_memberNameComboActionPerformed

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
            java.util.logging.Logger.getLogger(InstalmentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InstalmentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InstalmentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InstalmentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new InstalmentForm().setVisible(true);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(InstalmentForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel amountLabel;
    private javax.swing.JLabel balanceLabel;
    private javax.swing.JLabel billNumberLabel;
    private javax.swing.JButton clearButton;
    private javax.swing.JSpinner countSpinner;
    private javax.swing.JLabel dateLabel;
    private org.jdesktop.swingx.JXImagePanel imagePanel;
    private org.jdesktop.swingx.JXImagePanel imagePanel1;
    private javax.swing.JLabel instalmentLabel;
    private javax.swing.JLabel interestLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lastPaymentDateLabel;
    private javax.swing.JLabel loanIDLabel;
    private javax.swing.JTextField memberIDText;
    private javax.swing.JComboBox memberNameCombo;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nicNoLabel;
    private javax.swing.JButton proccessButton;
    // End of variables declaration//GEN-END:variables
}
