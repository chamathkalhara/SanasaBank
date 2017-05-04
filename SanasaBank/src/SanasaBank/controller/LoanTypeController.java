/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SanasaBank.controller;

import SanasaBank.db.DBConnection;
import SanasaBank.model.LoanType;
import SanasaBank.other.AutoGenarateID;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Chamath
 */
public class LoanTypeController {
    public static String getLoanType(String ltid) throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="select * from LoanType where LTID='"+ltid+"'";
        ResultSet rst=stm.executeQuery(sql);
        rst.next();
        return rst.getString("name");
    }
    
    public static int getLoanRate(String name) throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="select * from LoanType where name='"+name+"'";
        ResultSet rst=stm.executeQuery(sql);
        rst.next();
        return rst.getInt("rate");
    }
    public static ArrayList<String> getAllLoanType() throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="select * from LoanType";
        ResultSet rst=stm.executeQuery(sql);
        ArrayList<String> loanTypeList=new ArrayList<>();
        while(rst.next()){
            loanTypeList.add(rst.getString("name"));
        }
        return loanTypeList;
    }
    public static String getLoanTypeID(String name) throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="select * from LoanType where name='"+name+"'";
        ResultSet rst=stm.executeQuery(sql);
        rst.next();
        return rst.getString("LTID");
    }

    public static void addNewLoanType(LoanType loanType) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "insert into LoanType values('" + AutoGenarateID.genarate2(null, "LT", getLastLoantypeID()) + "','" + loanType.getName() + "','medium'," + loanType.getRate() + ")";
        stm.executeUpdate(sql);
    }
    
    public static String getLastLoantypeID() throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select * from LoanType order by 1 desc limit 1";
        ResultSet rst = stm.executeQuery(sql);
        rst.next();
        return rst.getString("LTID");
    }
    
    public static int updateLoanType(LoanType loanType) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        if (!loanType.getName().equals("") && loanType.getRate() != 0) {
            String sql = "update LoanType set name='" + loanType.getName() + "',rate=" + loanType.getRate() + " where LTID='" + loanType.getLTID() + "'";
            return stm.executeUpdate(sql);
        } else if (!loanType.getName().equals("")) {
            String sql = "update LoanType set name='" + loanType.getName() + "' where LTID='" + loanType.getLTID() + "'";
            return stm.executeUpdate(sql);
        } else{
            String sql = "update LoanType set rate=" + loanType.getRate() + " where LTID='" + loanType.getLTID() + "'";
            return stm.executeUpdate(sql);
        }

    }
}
