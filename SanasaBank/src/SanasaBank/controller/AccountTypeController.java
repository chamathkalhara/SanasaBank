/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SanasaBank.controller;

import SanasaBank.db.DBConnection;
import SanasaBank.model.AccountType;
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
public class AccountTypeController {

    public static String getRate(String atid) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select rate from AccountType where ATID='" + atid + "'";
        ResultSet rst = stm.executeQuery(sql);
        rst.next();
        String rate = rst.getString("rate");
        return rate;
    }

    public static String getAccountType(String atid) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();
        String sql = "select * from AccountType where ATID='" + atid + "'";
        ResultSet rst = stm.executeQuery(sql);
        if (rst.next()) {
            return rst.getString("name");
        } else {
            return "";
        }
    }

    public static ArrayList<String> getAllAccountType() throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select * from AccountType";
        ResultSet rst = stm.executeQuery(sql);
        ArrayList<String> accountTypeList = new ArrayList<>();
        while (rst.next()) {
            accountTypeList.add(rst.getString("name"));
        }
        return accountTypeList;
    }

    public static String getAccountTypeID(String name) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select * from AccountType where name='" + name + "'";
        ResultSet rst = stm.executeQuery(sql);
        rst.next();
        return rst.getString("ATID");
    }

    public static void addNewAccountType(AccountType accountType) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "insert into AccountType values('" + AutoGenarateID.genarate2(null, "AT", getLastAccounttypeID()) + "','" + accountType.getName() + "'," + accountType.getRate() + ")";
        stm.executeUpdate(sql);
    }

    public static String getLastAccounttypeID() throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select * from AccountType order by 1 desc limit 1";
        ResultSet rst = stm.executeQuery(sql);
        rst.next();
        return rst.getString("ATID");
    }

    public static int updateAccountType(AccountType accountType) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        if (!accountType.getName().equals("") && accountType.getRate() != 0) {
            String sql = "update AccountType set name='" + accountType.getName() + "',rate=" + accountType.getRate() + " where ATID='" + accountType.getATID() + "'";
            return stm.executeUpdate(sql);
        } else if (!accountType.getName().equals("")) {
            String sql = "update AccountType set name='" + accountType.getName() + "' where ATID='" + accountType.getATID() + "'";
            return stm.executeUpdate(sql);
        } else{
            String sql = "update AccountType set rate=" + accountType.getRate() + " where ATID='" + accountType.getATID() + "'";
            return stm.executeUpdate(sql);
        }

    }
}
