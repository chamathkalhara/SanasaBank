/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SanasaBank.controller;

import SanasaBank.db.DBConnection;
import SanasaBank.model.CashInHand;
import SanasaBank.view.cashInHand.UpdateCashInHand;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Kalhara
 */
public class CashInHandController {

    public static String getLastCashInHandID() throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select * from CashInHand order by 1 desc limit 1";
        ResultSet rst = stm.executeQuery(sql);
        rst.next();
        return rst.getString("CID");
    }

    public static void updateCashInHand(double amount) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();
        String cid = getLastCashInHandID();
        String sql = "select * from CashInHand where CID='" + cid + "'";
        ResultSet rst = stm.executeQuery(sql);
        rst.next();
        double balance = rst.getDouble("balance");
        double tranceaction = rst.getDouble("tranceactions");
        int res = -1;
        int res2 = -1;
        if (amount < 0) {
            if (balance >= -amount && balance > 0) {
                balance = balance + amount;
                tranceaction = tranceaction - amount;
                String sql2 = "update CashInHand set balance=" + balance + ",tranceactions=" + tranceaction + "  where CID='" + cid + "'";
                res = stm.executeUpdate(sql2);
            } else {
                int x = JOptionPane.showConfirmDialog(null, "Update Cash In Hand...", "Message", JOptionPane.OK_CANCEL_OPTION);
                if (x == 0) {
                    new UpdateCashInHand(null, true, searchCashInHand(cid)).setVisible(true);
                    String sql2 = "select * from CashInHand where CID='" + cid + "'";
                    ResultSet rst2 = stm.executeQuery(sql2);
                    rst2.next();
                    double balance2 = rst2.getDouble("balance");
                    balance2 += amount;
                    tranceaction = tranceaction - amount;
                    String sql3 = "update CashInHand set balance=" + balance2 + ",tranceactions=" + tranceaction + "  where CID='" + cid + "'";
                    res = stm.executeUpdate(sql3);
                }
            }
        } else {
            balance = balance + amount;
            tranceaction = tranceaction - amount;
            String sql2 = "update CashInHand set balance=" + balance + ",tranceactions=" + tranceaction + "  where CID='" + cid + "'";
            res2 = stm.executeUpdate(sql2);
        }

    }

    public static int addCashInHand(CashInHand cashInHand) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "insert into CashInHand values('" + cashInHand.getCID() + "','" + cashInHand.getDate() + "'," + cashInHand.getN5000() + "," + cashInHand.getN2000() + "," + cashInHand.getN1000() + "," + cashInHand.getN500() + "," + cashInHand.getN100() + "," + cashInHand.getN50() + "," + cashInHand.getN20() + "," + cashInHand.getN10() + "," + cashInHand.getC10() + "," + cashInHand.getC5() + "," + cashInHand.getC2() + "," + cashInHand.getC1() + "," + cashInHand.getAmount() + "," + cashInHand.getTranceactions() + "," + cashInHand.getBalance() + ",'" + cashInHand.getType() + "')";
        return stm.executeUpdate(sql);
    }

    public static String getLastCashInHandDate() throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select * from CashInHand order by 1 desc limit 1";
        ResultSet rst = stm.executeQuery(sql);
        rst.next();
        return rst.getString("date");
    }

    public static CashInHand searchCashInHand(String cid) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select * from CashInHand where CID='" + cid + "'";
        ResultSet rst = stm.executeQuery(sql);
        rst.next();
        CashInHand cashInHand = new CashInHand(cid, rst.getString("date"), rst.getInt("n5000"), rst.getInt("n2000"), rst.getInt("n1000"), rst.getInt("n500"), rst.getInt("n100"), rst.getInt("n50"), rst.getInt("n20"), rst.getInt("n10"), rst.getInt("c10"), rst.getInt("c5"), rst.getInt("c2"), rst.getInt("c1"), rst.getDouble("amount"), rst.getDouble("tranceactions"), rst.getDouble("balance"), "");
        return cashInHand;
    }

    public static int updateCashInHandAmount(CashInHand cashInHand) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "update CashInHand set n5000=n5000+" + cashInHand.getN5000() + ",n2000=n2000+" + cashInHand.getN2000() + ",n1000=n1000+" + cashInHand.getN1000() + ",n500=n500+" + cashInHand.getN500() + ",n100=n100+" + cashInHand.getN100() + ",n50=n50+" + cashInHand.getN50() + ",n20=n20+" + cashInHand.getN20() + ",n10=n10+" + cashInHand.getN10() + ",c10=c10+" + cashInHand.getC10() + ",c5=c5+" + cashInHand.getC5() + ",c2=c2+" + cashInHand.getC2() + ",c1=c1+" + cashInHand.getC1() + ",amount=" + cashInHand.getAmount() + ",balance=" + cashInHand.getBalance() + " where CID='" + cashInHand.getCID() + "'";
        return stm.executeUpdate(sql);
    }

    public static String getCashInHandID(String date) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select * from CashInHand where date='" + date + "'";
        ResultSet rst = stm.executeQuery(sql);
        if (rst.next()) {
            return rst.getString("CID");
        } else {
            return "";
        }
    }
}
