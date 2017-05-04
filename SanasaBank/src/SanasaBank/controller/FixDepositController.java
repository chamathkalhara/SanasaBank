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
 * @author Kalhara
 */
public class FixDepositController {
    public static String getLastFixDepositID() throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="select * from FixDeposit order by 1 desc limit 1";
        ResultSet rst=stm.executeQuery(sql);
        if(rst.next()){
            return rst.getString("FDID");
        }else{
            return "FD0000";
        }
    }
}
