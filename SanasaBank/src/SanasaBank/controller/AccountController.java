/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SanasaBank.controller;

import SanasaBank.db.DBConnection;
import SanasaBank.model.Account;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chamath
 */
public class AccountController {

    public static int addAccount(Account account) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();
        String sql = "insert into Account values('" + account.getAID() + "','" + account.getMID() + "','" + account.getATID() + "'," + account.getAccountNumber() + ",'" + account.getOpenedDate() + "'," + account.getCurrentAmmount() + ",'" + account.getLastUpdateDate() + "'," + account.getInterest() + ",'" + account.getNextUpdateDate() + "')";
        return stm.executeUpdate(sql);
    }

    public static int updateInterest() throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "UPDATE Account set currentAmmount=currentAmmount+interest where (YEAR(nextUpdateDate)<=YEAR(CURRENT_DATE) && MONTH(nextUpdateDate)<=MONTH(CURRENT_DATE) && DAY(nextUpdateDate)<=DAY(CURRENT_DATE))";
        int res = stm.executeUpdate(sql);

        updateNextUpdateDate();
        updateColumnInterest();
        return res;
    }

    private static void updateNextUpdateDate() throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "SELECT * from Account where (YEAR(nextUpdateDate)<=YEAR(CURRENT_DATE) && MONTH(nextUpdateDate)<=MONTH(CURRENT_DATE) && DAY(nextUpdateDate)<=DAY(CURRENT_DATE))";
        ResultSet rst = stm.executeQuery(sql);


        while (rst.next()) {
            String date = rst.getString("nextUpdateDate");
            String[] nextUpdateDate = date.split("-");
            String newNextUpdateDate = "";
            if (Integer.parseInt(nextUpdateDate[1]) < 10) {
                newNextUpdateDate = nextUpdateDate[0] + "-" + (Integer.parseInt(nextUpdateDate[1]) + 3) + "-" + nextUpdateDate[2];
            } else {
                newNextUpdateDate = Integer.parseInt(nextUpdateDate[0]) + 1 + "-" + (Integer.parseInt(nextUpdateDate[1]) + 3 - 12) + "-" + nextUpdateDate[2];
            }
            String aid = rst.getString("AID");
            String sql2 = "update Account set nextUpdateDate='" + newNextUpdateDate + "' where AID='" + aid + "'";
            Statement stm1 = conn.createStatement();
            stm1.executeUpdate(sql2);
        }
    }

    private static void updateColumnInterest() throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "SELECT * from Account where (YEAR(nextUpdateDate)<=YEAR(CURRENT_DATE) && MONTH(nextUpdateDate)<=MONTH(CURRENT_DATE) && DAY(nextUpdateDate)<=DAY(CURRENT_DATE))";
        ResultSet rst = stm.executeQuery(sql);

        while (rst.next()) {
            String atid = rst.getString("ATID");
            int rate = Integer.parseInt(AccountTypeController.getRate(atid));
            double currentAmmount = Double.parseDouble(rst.getString("currentAmmount"));
            double interest = ((currentAmmount * rate) / 100) * 90 / 365;
            String sql2 = "update Account set interest='" + interest + "' where AID='" + rst.getString("AID") + "'";
            stm.executeUpdate(sql2);
        }
    }

    public static ArrayList<Account> searchAccount(String mid) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select * from Account where MID='" + mid + "'";
        ResultSet rst = stm.executeQuery(sql);
        ArrayList<Account> accountList = new ArrayList<>();
        Account account = null;
        while (rst.next()) {
            account = new Account();
            account.setATID(rst.getString("ATID"));
            account.setAccountNumber(rst.getInt("accountNumber"));
            account.setCurrentAmmount(rst.getDouble("currentAmmount"));
            accountList.add(account);
        }
        return accountList;
    }

    public static Account searchAccount(int accountNumber) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select * from Account where accountNumber=" + accountNumber + "";
        ResultSet rst = stm.executeQuery(sql);
        Account account = null;
        if (rst.next()) {
            account = new Account();
            account.setAID(rst.getString("AID"));
            account.setCurrentAmmount(rst.getDouble("currentAmmount"));
            account.setMID(rst.getString("MID"));
            return account;
        } else {
            return account;
        }
    }

    public static int withdrawOrDiposit(Account account) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "update Account set currentAmmount=" + account.getCurrentAmmount() + " ,lastUpdateDate='" + account.getLastUpdateDate() + "' where accountNumber=" + account.getAccountNumber() + "";
        return stm.executeUpdate(sql);
    }

    public static String getNewAccountNumber() throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select * from Account order by 4 desc limit 1";
        ResultSet rst = stm.executeQuery(sql);
        rst.next();
        return String.valueOf(rst.getInt("accountNumber") + 1);
    }

    public static String getLastAccountID() throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select * from Account order by 1 desc limit 1";
        ResultSet rst = stm.executeQuery(sql);
        if (rst.next()) {
            return rst.getString("AID");
        } else {
            return "A0000";
        }
    }

    public static int deleteAccount(int accountNumber) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "delete from Account where AccountNumber=" + accountNumber + "";
        return stm.executeUpdate(sql);
    }

    public static void getAllAccounts(DefaultTableModel dtm) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select * from Account natural join Member order by accountNumber";
        ResultSet rst = stm.executeQuery(sql);
        while (rst.next()) {
            String atid = rst.getString("ATID");
            String accountType = AccountTypeController.getAccountType(atid);
            Object[] row = {rst.getInt("accountNumber"), rst.getString("name"), accountType, rst.getString("openedDate"), rst.getDouble("currentAmmount"), rst.getDouble("interest")};
            dtm.addRow(row);
        }
    }
}
