/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SanasaBank.view.cashInHand;

import SanasaBank.controller.CashInHandController;
import SanasaBank.model.CashInHand;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Kalhara
 */
public class CashInHandInDay extends javax.swing.JDialog {

    /**
     * Creates new form CashInHandInDay
     */


    public CashInHandInDay(java.awt.Frame parent, boolean modal, String day) throws ClassNotFoundException, SQLException {
        super(parent, modal);
        initComponents();
        String cid = CashInHandController.getCashInHandID(day);
        if (!cid.equals("")) {
            CashInHand cashInHand = CashInHandController.searchCashInHand(cid);

            openAmountText.setText(String.valueOf(cashInHand.getAmount()));
            tranceactionsText.setText(String.valueOf(cashInHand.getTranceactions()));
            balanceText.setText(String.valueOf(cashInHand.getBalance()));
            note5000Text.setText(String.valueOf(cashInHand.getN5000()));
            note2000Text.setText(String.valueOf(cashInHand.getN2000()));
            note1000Text.setText(String.valueOf(cashInHand.getN1000()));
            note500Text.setText(String.valueOf(cashInHand.getN500()));
            note100Text.setText(String.valueOf(cashInHand.getN100()));
            note50Text.setText(String.valueOf(cashInHand.getN50()));
            note20Text.setText(String.valueOf(cashInHand.getN20()));
            note10Text.setText(String.valueOf(cashInHand.getN10()));
            coin10Text.setText(String.valueOf(cashInHand.getC10()));
            coin5Text.setText(String.valueOf(cashInHand.getC5()));
            coin2Text.setText(String.valueOf(cashInHand.getC2()));
            coin1Text.setText(String.valueOf(cashInHand.getC1()));
        } else {
            JOptionPane.showMessageDialog(null, day + " has no cash in hand data");
            this.dispose();
            new SelectDay(null,true).setVisible(true);
        }

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

        imagePanel = new org.jdesktop.swingx.JXImagePanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        note5000Text = new javax.swing.JTextField();
        note1000Text = new javax.swing.JTextField();
        note2000Text = new javax.swing.JTextField();
        note500Text = new javax.swing.JTextField();
        note50Text = new javax.swing.JTextField();
        note100Text = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        note20Text = new javax.swing.JTextField();
        note10Text = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        coin10Text = new javax.swing.JTextField();
        coin5Text = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        coin2Text = new javax.swing.JTextField();
        coin1Text = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        openAmountText = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tranceactionsText = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        balanceText = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        imagePanel.setBackground(new java.awt.Color(255, 255, 255));
        imagePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cash In Hand", 0, 0, new java.awt.Font("Bodoni MT", 3, 24), new java.awt.Color(0, 153, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("5000 note  :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("2000 note  :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("1000 note  :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("100 note   :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("500 note   :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("50 note    :");

        note5000Text.setEditable(false);
        note5000Text.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        note5000Text.setText("0");
        note5000Text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                note5000TextActionPerformed(evt);
            }
        });
        note5000Text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                note5000TextKeyReleased(evt);
            }
        });

        note1000Text.setEditable(false);
        note1000Text.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        note1000Text.setText("0");
        note1000Text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                note1000TextActionPerformed(evt);
            }
        });
        note1000Text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                note1000TextKeyReleased(evt);
            }
        });

        note2000Text.setEditable(false);
        note2000Text.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        note2000Text.setText("0");
        note2000Text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                note2000TextActionPerformed(evt);
            }
        });
        note2000Text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                note2000TextKeyReleased(evt);
            }
        });

        note500Text.setEditable(false);
        note500Text.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        note500Text.setText("0");
        note500Text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                note500TextActionPerformed(evt);
            }
        });
        note500Text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                note500TextKeyReleased(evt);
            }
        });

        note50Text.setEditable(false);
        note50Text.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        note50Text.setText("0");
        note50Text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                note50TextActionPerformed(evt);
            }
        });
        note50Text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                note50TextKeyReleased(evt);
            }
        });

        note100Text.setEditable(false);
        note100Text.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        note100Text.setText("0");
        note100Text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                note100TextActionPerformed(evt);
            }
        });
        note100Text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                note100TextKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("20 note   :");

        note20Text.setEditable(false);
        note20Text.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        note20Text.setText("0");
        note20Text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                note20TextActionPerformed(evt);
            }
        });
        note20Text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                note20TextKeyReleased(evt);
            }
        });

        note10Text.setEditable(false);
        note10Text.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        note10Text.setText("0");
        note10Text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                note10TextActionPerformed(evt);
            }
        });
        note10Text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                note10TextKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("10 note   :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("10 voin    :");

        coin10Text.setEditable(false);
        coin10Text.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        coin10Text.setText("0");
        coin10Text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coin10TextActionPerformed(evt);
            }
        });
        coin10Text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                coin10TextKeyReleased(evt);
            }
        });

        coin5Text.setEditable(false);
        coin5Text.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        coin5Text.setText("0");
        coin5Text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coin5TextActionPerformed(evt);
            }
        });
        coin5Text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                coin5TextKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("5 coin    :");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("2 coin    :");

        coin2Text.setEditable(false);
        coin2Text.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        coin2Text.setText("0");
        coin2Text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coin2TextActionPerformed(evt);
            }
        });
        coin2Text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                coin2TextKeyReleased(evt);
            }
        });

        coin1Text.setEditable(false);
        coin1Text.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        coin1Text.setText("0");
        coin1Text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                coin1TextKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("1 coin    :");

        cancelButton.setBackground(new java.awt.Color(51, 102, 255));
        cancelButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelButton.setText("CANCEL");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Open Amount  :");

        openAmountText.setEditable(false);
        openAmountText.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("Rs");

        tranceactionsText.setEditable(false);
        tranceactionsText.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Transactions  :");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setText("Rs");

        balanceText.setEditable(false);
        balanceText.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Balance  :");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setText("Rs");

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanelLayout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(openAmountText, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(151, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imagePanelLayout.createSequentialGroup()
                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(imagePanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel18))
                        .addGap(18, 18, 18)
                        .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(balanceText, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tranceactionsText, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(imagePanelLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(note5000Text, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(note50Text, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(note100Text, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(note500Text, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(note1000Text, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(note2000Text, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(note20Text, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(coin1Text, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(coin2Text, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(coin5Text, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(coin10Text, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(note10Text, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(71, 71, 71))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imagePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(openAmountText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(imagePanelLayout.createSequentialGroup()
                        .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(note5000Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(imagePanelLayout.createSequentialGroup()
                                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(note2000Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(16, 16, 16)
                                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(note1000Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(note500Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(note100Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35))
                            .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(note50Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))))
                    .addGroup(imagePanelLayout.createSequentialGroup()
                        .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(note20Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(imagePanelLayout.createSequentialGroup()
                                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(note10Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(coin10Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(coin5Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(coin2Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35))
                            .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(coin1Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)))))
                .addGap(40, 40, 40)
                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(tranceactionsText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(balanceText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(29, 29, 29)
                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void note5000TextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_note5000TextActionPerformed
        note2000Text.requestFocus();
    }//GEN-LAST:event_note5000TextActionPerformed

    private void note5000TextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_note5000TextKeyReleased
    }//GEN-LAST:event_note5000TextKeyReleased

    private void note1000TextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_note1000TextActionPerformed
        note500Text.requestFocus();
    }//GEN-LAST:event_note1000TextActionPerformed

    private void note1000TextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_note1000TextKeyReleased
    }//GEN-LAST:event_note1000TextKeyReleased

    private void note2000TextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_note2000TextActionPerformed
        note1000Text.requestFocus();
    }//GEN-LAST:event_note2000TextActionPerformed

    private void note2000TextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_note2000TextKeyReleased
    }//GEN-LAST:event_note2000TextKeyReleased

    private void note500TextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_note500TextActionPerformed
        note100Text.requestFocus();
    }//GEN-LAST:event_note500TextActionPerformed

    private void note500TextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_note500TextKeyReleased
    }//GEN-LAST:event_note500TextKeyReleased

    private void note50TextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_note50TextActionPerformed
        note20Text.requestFocus();
    }//GEN-LAST:event_note50TextActionPerformed

    private void note50TextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_note50TextKeyReleased
    }//GEN-LAST:event_note50TextKeyReleased

    private void note100TextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_note100TextActionPerformed
        note50Text.requestFocus();
    }//GEN-LAST:event_note100TextActionPerformed

    private void note100TextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_note100TextKeyReleased
    }//GEN-LAST:event_note100TextKeyReleased

    private void note20TextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_note20TextActionPerformed
        note10Text.requestFocus();
    }//GEN-LAST:event_note20TextActionPerformed

    private void note20TextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_note20TextKeyReleased
    }//GEN-LAST:event_note20TextKeyReleased

    private void note10TextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_note10TextActionPerformed
        coin10Text.requestFocus();
    }//GEN-LAST:event_note10TextActionPerformed

    private void note10TextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_note10TextKeyReleased
    }//GEN-LAST:event_note10TextKeyReleased

    private void coin10TextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coin10TextActionPerformed
        coin5Text.requestFocus();
    }//GEN-LAST:event_coin10TextActionPerformed

    private void coin10TextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_coin10TextKeyReleased
    }//GEN-LAST:event_coin10TextKeyReleased

    private void coin5TextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coin5TextActionPerformed
        coin2Text.requestFocus();
    }//GEN-LAST:event_coin5TextActionPerformed

    private void coin5TextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_coin5TextKeyReleased
    }//GEN-LAST:event_coin5TextKeyReleased

    private void coin2TextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coin2TextActionPerformed
        coin1Text.requestFocus();
    }//GEN-LAST:event_coin2TextActionPerformed

    private void coin2TextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_coin2TextKeyReleased
    }//GEN-LAST:event_coin2TextKeyReleased

    private void coin1TextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_coin1TextKeyReleased
    }//GEN-LAST:event_coin1TextKeyReleased

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

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
            java.util.logging.Logger.getLogger(CashInHandInDay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CashInHandInDay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CashInHandInDay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CashInHandInDay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CashInHandInDay dialog = new CashInHandInDay(new javax.swing.JFrame(), true, "");
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CashInHandInDay.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CashInHandInDay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField balanceText;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField coin10Text;
    private javax.swing.JTextField coin1Text;
    private javax.swing.JTextField coin2Text;
    private javax.swing.JTextField coin5Text;
    private org.jdesktop.swingx.JXImagePanel imagePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField note1000Text;
    private javax.swing.JTextField note100Text;
    private javax.swing.JTextField note10Text;
    private javax.swing.JTextField note2000Text;
    private javax.swing.JTextField note20Text;
    private javax.swing.JTextField note5000Text;
    private javax.swing.JTextField note500Text;
    private javax.swing.JTextField note50Text;
    private javax.swing.JTextField openAmountText;
    private javax.swing.JTextField tranceactionsText;
    // End of variables declaration//GEN-END:variables
}
