/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SanasaBank.controller;

import SanasaBank.db.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Chamath
 */
public class BillNumberContraller {
    
    public static String getLastNumber() throws ClassNotFoundException, SQLException{
        
        Connection conn=DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="select * from billNumber order by 1 desc limit 1";
        ResultSet rst=stm.executeQuery(sql);
        if(rst.next()){
            return rst.getString("billNumber");
        }else{
            return "0000";
        }
    }
    public static void addBillNumber(int billNumber) throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="insert into billNumber values("+billNumber+")";
        stm.executeUpdate(sql);
    }
}
