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
public class Instalment {
    
    private String IID;
    private String LID;
    private String CID;
    private int billNumber;
    private String iDate;
    private double iAmmount;
    private int count;
    private double iInterest;

    public Instalment() {
    }

    public Instalment(String IID, String LID, String CID, int billNumber, String iDate, double iAmmount, int count, double iInterest) {
        this.IID = IID;
        this.LID = LID;
        this.CID = CID;
        this.billNumber = billNumber;
        this.iDate = iDate;
        this.iAmmount = iAmmount;
        this.count = count;
        this.iInterest = iInterest;
    }

    /**
     * @return the IID
     */
    public String getIID() {
        return IID;
    }

    /**
     * @param IID the IID to set
     */
    public void setIID(String IID) {
        this.IID = IID;
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
     * @return the iDate
     */
    public String getiDate() {
        return iDate;
    }

    /**
     * @param iDate the iDate to set
     */
    public void setiDate(String iDate) {
        this.iDate = iDate;
    }

    /**
     * @return the iAmmount
     */
    public double getiAmmount() {
        return iAmmount;
    }

    /**
     * @param iAmmount the iAmmount to set
     */
    public void setiAmmount(double iAmmount) {
        this.iAmmount = iAmmount;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return the iInterest
     */
    public double getiInterest() {
        return iInterest;
    }

    /**
     * @param iInterest the iInterest to set
     */
    public void setiInterest(double iInterest) {
        this.iInterest = iInterest;
    }

    
   
   
}
