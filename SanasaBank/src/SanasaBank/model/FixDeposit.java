/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SanasaBank.model;

/**
 *
 * @author Kalhara
 */
public class FixDeposit {
    private String FDID;
    private String AID;
    private int duration;
    private double fullInterest;

    public FixDeposit() {
    }

    public FixDeposit(String FDID, String AID, int duration, double fullInterest) {
        this.FDID = FDID;
        this.AID = AID;
        this.duration = duration;
        this.fullInterest = fullInterest;
    }

    /**
     * @return the FDID
     */
    public String getFDID() {
        return FDID;
    }

    /**
     * @param FDID the FDID to set
     */
    public void setFDID(String FDID) {
        this.FDID = FDID;
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
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * @return the fullInterest
     */
    public double getFullInterest() {
        return fullInterest;
    }

    /**
     * @param fullInterest the fullInterest to set
     */
    public void setFullInterest(double fullInterest) {
        this.fullInterest = fullInterest;
    }
    
    
}
