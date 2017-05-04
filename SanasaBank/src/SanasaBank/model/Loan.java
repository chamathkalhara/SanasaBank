/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SanasaBank.model;

/**
 *
 * @author Chamath
 */
public class Loan {
    
    private String LID;
    private String MID;
    private String LTID;
    private String PCID;
    private double ammount;
    private int instalmentCount;
    private String lDate;
    private double balance;
    private double instalment;
    private String nextPaymentDate;
    private String lastPaymentDate;
    private double resevedFullInterest;

    public Loan() {
    }

    public Loan(String LID, String MID, String LTID, String PCID, double ammount, int instalmentCount, String lDate, double balance, double instalment, String nextPaymentDate, String lastPaymentDate, double resevedFullInterest) {
        this.LID = LID;
        this.MID = MID;
        this.LTID = LTID;
        this.PCID = PCID;
        this.ammount = ammount;
        this.instalmentCount = instalmentCount;
        this.lDate = lDate;
        this.balance = balance;
        this.instalment = instalment;
        this.nextPaymentDate = nextPaymentDate;
        this.lastPaymentDate = lastPaymentDate;
        this.resevedFullInterest = resevedFullInterest;
    }

    /**
     * @return the LID
     */
    public String getLID() {
        return LID;
    }

    /**
     * @param LID the LID to set
     */
    public void setLID(String LID) {
        this.LID = LID;
    }

    /**
     * @return the MID
     */
    public String getMID() {
        return MID;
    }

    /**
     * @param MID the MID to set
     */
    public void setMID(String MID) {
        this.MID = MID;
    }

    /**
     * @return the LTID
     */
    public String getLTID() {
        return LTID;
    }

    /**
     * @param LTID the LTID to set
     */
    public void setLTID(String LTID) {
        this.LTID = LTID;
    }

    /**
     * @return the PCID
     */
    public String getPCID() {
        return PCID;
    }

    /**
     * @param PCID the PCID to set
     */
    public void setPCID(String PCID) {
        this.PCID = PCID;
    }

    /**
     * @return the ammount
     */
    public double getAmmount() {
        return ammount;
    }

    /**
     * @param ammount the ammount to set
     */
    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    /**
     * @return the instalmentCount
     */
    public int getInstalmentCount() {
        return instalmentCount;
    }

    /**
     * @param instalmentCount the instalmentCount to set
     */
    public void setInstalmentCount(int instalmentCount) {
        this.instalmentCount = instalmentCount;
    }

    /**
     * @return the lDate
     */
    public String getlDate() {
        return lDate;
    }

    /**
     * @param lDate the lDate to set
     */
    public void setlDate(String lDate) {
        this.lDate = lDate;
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
     * @return the instalment
     */
    public double getInstalment() {
        return instalment;
    }

    /**
     * @param instalment the instalment to set
     */
    public void setInstalment(double instalment) {
        this.instalment = instalment;
    }

    /**
     * @return the nextPaymentDate
     */
    public String getNextPaymentDate() {
        return nextPaymentDate;
    }

    /**
     * @param nextPaymentDate the nextPaymentDate to set
     */
    public void setNextPaymentDate(String nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
    }

    /**
     * @return the lastPaymentDate
     */
    public String getLastPaymentDate() {
        return lastPaymentDate;
    }

    /**
     * @param lastPaymentDate the lastPaymentDate to set
     */
    public void setLastPaymentDate(String lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    /**
     * @return the resevedFullInterest
     */
    public double getResevedFullInterest() {
        return resevedFullInterest;
    }

    /**
     * @param resevedFullInterest the resevedFullInterest to set
     */
    public void setResevedFullInterest(double resevedFullInterest) {
        this.resevedFullInterest = resevedFullInterest;
    }

    
    
    
      
    
}
