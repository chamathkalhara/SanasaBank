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
public class Account {
    private String AID;
    private String MID;
    private String ATID;
    private int accountNumber;
    private String openedDate;
    private double currentAmmount;
    private String lastUpdateDate;
    private double interest;
    private String nextUpdateDate;

    public Account() {
    }

    public Account(String AID, String MID, String ATID, int accountNumber, String openedDate, double currentAmmount, String lastUpdateDate, double interest, String nextUpdateDate) {
        this.AID = AID;
        this.MID = MID;
        this.ATID = ATID;
        this.accountNumber = accountNumber;
        this.openedDate = openedDate;
        this.currentAmmount = currentAmmount;
        this.lastUpdateDate = lastUpdateDate;
        this.interest = interest;
        this.nextUpdateDate = nextUpdateDate;
    }

    /**
     * @return the AID
     */
    public String getAID() {
        return AID;
    }

    /**
     * @param AID the AID to set
     */
    public void setAID(String AID) {
        this.AID = AID;
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
     * @return the ATID
     */
    public String getATID() {
        return ATID;
    }

    /**
     * @param ATID the ATID to set
     */
    public void setATID(String ATID) {
        this.ATID = ATID;
    }

    /**
     * @return the accountNumber
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return the openedDate
     */
    public String getOpenedDate() {
        return openedDate;
    }

    /**
     * @param openedDate the openedDate to set
     */
    public void setOpenedDate(String openedDate) {
        this.openedDate = openedDate;
    }

    /**
     * @return the currentAmmount
     */
    public double getCurrentAmmount() {
        return currentAmmount;
    }

    /**
     * @param currentAmmount the currentAmmount to set
     */
    public void setCurrentAmmount(double currentAmmount) {
        this.currentAmmount = currentAmmount;
    }

    /**
     * @return the lastUpdateDate
     */
    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * @param lastUpdateDate the lastUpdateDate to set
     */
    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * @return the interest
     */
    public double getInterest() {
        return interest;
    }

    /**
     * @param interest the interest to set
     */
    public void setInterest(double interest) {
        this.interest = interest;
    }

    /**
     * @return the nextUpdateDate
     */
    public String getNextUpdateDate() {
        return nextUpdateDate;
    }

    /**
     * @param nextUpdateDate the nextUpdateDate to set
     */
    public void setNextUpdateDate(String nextUpdateDate) {
        this.nextUpdateDate = nextUpdateDate;
    }

    
    
}
