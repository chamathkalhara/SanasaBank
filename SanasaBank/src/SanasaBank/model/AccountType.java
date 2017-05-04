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
public class AccountType {
    private String ATID;
    private String name;
    private int rate;

    public AccountType() {
    }

    public AccountType(String ATID, String name, int rate) {
        this.ATID = ATID;
        this.name = name;
        this.rate = rate;
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
