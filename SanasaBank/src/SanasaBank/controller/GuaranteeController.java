/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SanasaBank.controller;

import SanasaBank.db.DBConnection;
import SanasaBank.model.Guarantee;
import SanasaBank.other.AutoGenarateID;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Chamath
 */
public class GuaranteeController {

    public static int addGuarantors(Guarantee guarantee) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "insert into Guarantee values('" + AutoGenarateID.genarate(null, "G", getLastID()) + "','" + guarantee.getLID() + "','" + guarantee.getMID() + "')";
        int res = stm.executeUpdate(sql);

        return res;
    }

    private static String getLastID() throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select * from Guarantee order by 1 desc limit 1";
        ResultSet rst = stm.executeQuery(sql);
        if (rst.next()) {
            return rst.getString("GID");
        } else {
            return "G0000";
        }
    }

    public static boolean isGuarantor(String mid) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select * from Guarantee where MID='" + mid + "'";
        ResultSet rst = stm.executeQuery(sql);
        if (rst.next()) {
            return true;
        } else {
            return false;
        }
    }
}
