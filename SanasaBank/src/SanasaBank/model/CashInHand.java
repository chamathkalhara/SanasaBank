/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SanasaBank.model;

/**
 *
 * @author Kalhara
 */
public class CashInHand {
    private String CID;
    private String date;
    private int n5000;
    private int n2000;
    private int n1000;
    private int n500;
    private int n100;
    private int n50;
    private int n20;
    private int n10;
    private int c10;
    private int c5;
    private int c2;
    private int c1;
    private double amount;
    private double tranceactions;
    private double balance;
    private String type;

    public CashInHand() {
    }

    public CashInHand(String CID, String date, int n5000, int n2000, int n1000, int n500, int n100, int n50, int n20, int n10, int c10, int c5, int c2, int c1, double amount, double tranceactions, double balance, String type) {
        this.CID = CID;
        this.date = date;
        this.n5000 = n5000;
        this.n2000 = n2000;
        this.n1000 = n1000;
        this.n500 = n500;
        this.n100 = n100;
        this.n50 = n50;
        this.n20 = n20;
        this.n10 = n10;
        this.c10 = c10;
        this.c5 = c5;
        this.c2 = c2;
        this.c1 = c1;
        this.amount = amount;
        this.tranceactions = tranceactions;
        this.balance = balance;
        this.type = type;
    }

    /**
     * @return the CID
     */
    public String getCID() {
        return CID;
    }

    /**
     * @param CID the CID to set
     */
    public void setCID(String CID) {
        this.CID = CID;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the n5000
     */
    public int getN5000() {
        return n5000;
    }

    /**
     * @param n5000 the n5000 to set
     */
    public void setN5000(int n5000) {
        this.n5000 = n5000;
    }

    /**
     * @return the n2000
     */
    public int getN2000() {
        return n2000;
    }

    /**
     * @param n2000 the n2000 to set
     */
    public void setN2000(int n2000) {
        this.n2000 = n2000;
    }

    /**
     * @return the n1000
     */
    public int getN1000() {
        return n1000;
    }

    /**
     * @param n1000 the n1000 to set
     */
    public void setN1000(int n1000) {
        this.n1000 = n1000;
    }

    /**
     * @return the n500
     */
    public int getN500() {
        return n500;
    }

    /**
     * @param n500 the n500 to set
     */
    public void setN500(int n500) {
        this.n500 = n500;
    }

    /**
     * @return the n100
     */
    public int getN100() {
        return n100;
    }

    /**
     * @param n100 the n100 to set
     */
    public void setN100(int n100) {
        this.n100 = n100;
    }

    /**
     * @return the n50
     */
    public int getN50() {
        return n50;
    }

    /**
     * @param n50 the n50 to set
     */
    public void setN50(int n50) {
        this.n50 = n50;
    }

    /**
     * @return the n20
     */
    public int getN20() {
        return n20;
    }

    /**
     * @param n20 the n20 to set
     */
    public void setN20(int n20) {
        this.n20 = n20;
    }

    /**
     * @return the n10
     */
    public int getN10() {
        return n10;
    }

    /**
     * @param n10 the n10 to set
     */
    public void setN10(int n10) {
        this.n10 = n10;
    }

    /**
     * @return the c10
     */
    public int getC10() {
        return c10;
    }

    /**
     * @param c10 the c10 to set
     */
    public void setC10(int c10) {
        this.c10 = c10;
    }

    /**
     * @return the c5
     */
    public int getC5() {
        return c5;
    }

    /**
     * @param c5 the c5 to set
     */
    public void setC5(int c5) {
        this.c5 = c5;
    }

    /**
     * @return the c2
     */
    public int getC2() {
        return c2;
    }

    /**
     * @param c2 the c2 to set
     */
    public void setC2(int c2) {
        this.c2 = c2;
    }

    /**
     * @return the c1
     */
    public int getC1() {
        return c1;
    }

    /**
     * @param c1 the c1 to set
     */
    public void setC1(int c1) {
        this.c1 = c1;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the tranceactions
     */
    public double getTranceactions() {
        return tranceactions;
    }

    /**
     * @param tranceactions the tranceactions to set
     */
    public void setTranceactions(double tranceactions) {
        this.tranceactions = tranceactions;
    }

    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
    
}
