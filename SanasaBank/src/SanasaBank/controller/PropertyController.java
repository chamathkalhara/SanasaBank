/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SanasaBank.controller;

import SanasaBank.db.DBConnection;
import SanasaBank.model.Property;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Chamath
 */
public class PropertyController {

    public static String getLastPropertyID() throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select * from Property order by 1 desc limit 1";
        ResultSet rst = stm.executeQuery(sql);
        if (rst.next()) {
            return rst.getString("PID");
        } else {
            return "P0000";
        }
    }

    public static void addProperty(Property property) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "insert into Property values('" + property.getPID() + "','" + property.getLID() + "'," + property.getCalValue() + ",'" + property.getDescription() + "')";
        stm.executeUpdate(sql);
    }
}
