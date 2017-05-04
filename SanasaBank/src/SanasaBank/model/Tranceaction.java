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
public class Tranceaction {
    
    private String TID;
    private String AID;
    private String CID;
    private int billNumber;
    private String tDate;
    private String tType;
    private double ammount;

    public Tranceaction() {
    }

    public Tranceaction(String TID, String AID, String CID, int billNumber, String tDate, String tType, double ammount) {
        this.TID = TID;
        this.AID = AID;
        this.CID = CID;
        this.billNumber = billNumber;
        this.tDate = tDate;
        this.tType = tType;
        this.ammount = ammount;
    }

    /**
     * @return the TID
     */
    public String getTID() {
        return TID;
    }

    /**
     * @param TID the TID to set
     */
    public void setTID(String TID) {
        this.TID = TID;
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
     * @return the billNumber
     */
    public int getBillNumber() {
        return billNumber;
    }

    /**
     * @param billNumber the billNumber to set
     */
    public void setBillNumber(int billNumber) {
        this.billNumber = billNumber;
    }

    /**
     * @return the tDate
     */
    public String gettDate() {
        return tDate;
    }

    /**
     * @param tDate the tDate to set
     */
    public void settDate(String tDate) {
        this.tDate = tDate;
    }

    /**
     * @return the tType
     */
    public String gettType() {
        return tType;
    }

    /**
     * @param tType the tType to set
     */
    public void settType(String tType) {
        this.tType = tType;
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

    
    
}
