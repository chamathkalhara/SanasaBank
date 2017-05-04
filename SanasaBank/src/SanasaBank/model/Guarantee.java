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
public class Guarantee {
    
    private String GID;
    private String LID;
    private String MID;

    public Guarantee() {
    }

    public Guarantee(String GID, String LID, String MID) {
        this.GID = GID;
        this.LID = LID;
        this.MID = MID;
    }

    /**
     * @return the GID
     */
    public String getGID() {
        return GID;
    }

    /**
     * @param GID the GID to set
     */
    public void setGID(String GID) {
        this.GID = GID;
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

   
    
}
