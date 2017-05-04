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
public class Property {
    
    private String PID;
    private String LID;
    private double calValue;
    private String description;

    public Property() {
    }

    public Property(String PID, String LID, double calValue, String description) {
        this.PID = PID;
        this.LID = LID;
        this.calValue = calValue;
        this.description = description;
    }

    /**
     * @return the PID
     */
    public String getPID() {
        return PID;
    }

    /**
     * @param PID the PID to set
     */
    public void setPID(String PID) {
        this.PID = PID;
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
     * @return the calValue
     */
    public double getCalValue() {
        return calValue;
    }

    /**
     * @param calValue the calValue to set
     */
    public void setCalValue(double calValue) {
        this.calValue = calValue;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
