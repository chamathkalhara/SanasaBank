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
public class LoanType {
    
    private String LTID;
    private String name;
    private String lType;
    private int rate;

    public LoanType() {
    }

    public LoanType(String LTID, String name, String lType, int rate) {
        this.LTID = LTID;
        this.name = name;
        this.lType = lType;
        this.rate = rate;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the lType
     */
    public String getlType() {
        return lType;
    }

    /**
     * @param lType the lType to set
     */
    public void setlType(String lType) {
        this.lType = lType;
    }

    /**
     * @return the rate
     */
    public int getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(int rate) {
        this.rate = rate;
    }
    
    
}
