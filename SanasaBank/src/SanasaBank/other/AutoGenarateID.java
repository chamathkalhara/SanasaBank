/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SanasaBank.other;

import java.sql.SQLException;
import java.text.NumberFormat;
import javax.swing.JLabel;

/**
 *
 * @author Kalhara
 */
public class AutoGenarateID {

    /**
     *
     * @author cmjd
     * @param idLabel
     * @param preficx
     * @param lastID
     * @return
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static String genarate(JLabel idLabel, String preficx, String lastID) throws ClassNotFoundException, SQLException {

        if (lastID.equals("")) {
            lastID = "C000";
        }

        if (!preficx.equals("")) {
            String[] id = lastID.split(preficx);
            int number = Integer.parseInt(id[1]);
            number++;
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMinimumIntegerDigits(4);
            String value = nf.format(number);
            String[] newID = value.split(",");
            String newValue = "";
            for (String next : newID) {
                newValue += next;
            }
            if (idLabel != null) {
                idLabel.setText(preficx + newValue);
            }
            return preficx + newValue;
        } else {
            int number = Integer.parseInt(lastID);
            number++;
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMinimumIntegerDigits(4);
            String value = nf.format(number);
            String[] newID = value.split(",");
            String newValue = "";
            for (String next : newID) {
                newValue += next;
            }
            if (idLabel != null) {
                idLabel.setText(preficx + newValue);
            }
            return preficx + newValue;
        }
    }
    
    public static String genarate2(JLabel idLabel, String preficx, String lastID) throws ClassNotFoundException, SQLException {

        if (lastID.equals("")) {
            lastID = "C000";
        }

        if (!preficx.equals("")) {
            String[] id = lastID.split(preficx);
            int number = Integer.parseInt(id[1]);
            number++;
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMinimumIntegerDigits(3);
            String value = nf.format(number);
            String[] newID = value.split(",");
            String newValue = "";
            for (String next : newID) {
                newValue += next;
            }
            if (idLabel != null) {
                idLabel.setText(preficx + newValue);
            }
            return preficx + newValue;
        } else {
            int number = Integer.parseInt(lastID);
            number++;
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMinimumIntegerDigits(3);
            String value = nf.format(number);
            String[] newID = value.split(",");
            String newValue = "";
            for (String next : newID) {
                newValue += next;
            }
            if (idLabel != null) {
                idLabel.setText(preficx + newValue);
            }
            return preficx + newValue;
        }
    }
}
