/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SanasaBank.controller;

import SanasaBank.db.DBConnection;
import SanasaBank.model.Loan;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chamath
 */
public class LoanController {

    public static String getLastId() throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select * from Loan order by 1 desc limit 1";
        ResultSet rst = stm.executeQuery(sql);
        if (rst.next()) {
            return rst.getString("LID");
        } else {
            return "L0000";
        }

    }

    public static ArrayList<Loan> searchLoans(String mid) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select * from Loan where MID='" + mid + "'";
        ResultSet rst = stm.executeQuery(sql);
        ArrayList<Loan> loanList = new ArrayList<>();
        while (rst.next()) {
            Loan loan = new Loan();
            loan.setLID(rst.getString("LID"));
            loan.setLTID(rst.getString("LTID"));
            loan.setAmmount(rst.getDouble("ammount"));
            loan.setInstalmentCount(rst.getInt("instalmentCount"));
            loan.setlDate(rst.getString("lDate"));
            loan.setNextPaymentDate(rst.getString("nextPaymentDate"));
            loan.setBalance(rst.getDouble("balance"));
            loanList.add(loan);
        }
        return loanList;
    }

    public static int addLoan(Loan loan) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "insert into Loan values('" + loan.getLID() + "','" + loan.getMID() + "','" + loan.getLTID() + "','" + loan.getPCID() + "'," + loan.getAmmount() + "," + loan.getInstalmentCount() + ",'" + loan.getlDate() + "'," + loan.getBalance() + "," + loan.getInstalment() + ",'" + loan.getNextPaymentDate() + "','" + loan.getLastPaymentDate() + "'," + loan.getResevedFullInterest() + ")";
        return stm.executeUpdate(sql);
    }

    public static Loan searchLoan(String lid) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select * from loan where LID='" + lid + "'";
        ResultSet rst = stm.executeQuery(sql);
        Loan loan = null;
        if (rst.next()) {
            loan = new Loan();
            loan.setlDate(rst.getString("lDate"));
            loan.setInstalmentCount(rst.getInt("instalmentCount"));
            loan.setResevedFullInterest(rst.getDouble("resevedFullInterast"));
            loan.setInstalment(rst.getDouble("instalment"));
        }
        return loan;
    }

    public static Loan searchCurrentLoan(String mid) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select * from Loan where MID='" + mid + "' && balance>0";
        ResultSet rst = stm.executeQuery(sql);
        Loan loan = null;
        if (rst.next()) {
            loan = new Loan();
            loan.setLID(rst.getString("LID"));
            loan.setLTID(rst.getString("LTID"));
            loan.setAmmount(rst.getDouble("ammount"));
            loan.setLastPaymentDate(rst.getString("lastPaymentDate"));
            loan.setBalance(rst.getDouble("balance"));
            loan.setInstalment(rst.getDouble("instalment"));
            loan.setNextPaymentDate(rst.getString("nextPaymentDate"));
            loan.setResevedFullInterest(rst.getDouble("resevedFullInterast"));
        }
        return loan;
    }

    public static int updateLoan(Loan loan) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "update loan set balance=" + loan.getBalance() + ",nextPaymentDate='" + loan.getNextPaymentDate() + "',lastPaymentDate='" + loan.getLastPaymentDate() + "',resevedFullInterast=" + loan.getResevedFullInterest() + " where LID='" + loan.getLID() + "'";
        return stm.executeUpdate(sql);
    }

    public static void getAllLoans(DefaultTableModel dtm) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select * from Loan natural join Member order by LID desc";
        ResultSet rst = stm.executeQuery(sql);
        while (rst.next()) {
            String ltid = rst.getString("LTID");
            String loanType = LoanTypeController.getLoanType(ltid);
            Object[] row = {rst.getString("LID"), loanType, rst.getString("MID"), rst.getString("name"), rst.getString("lDate"), rst.getDouble("ammount"), rst.getDouble("instalment"), (int) (rst.getDouble("balance") / rst.getDouble("instalment")), rst.getDouble("resevedFullInterast")};
            dtm.addRow(row);
        }
    }

    public static void getCurrentLoans(DefaultTableModel dtm) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select * from Loan natural join Member where balance!=0 order by LID desc";
        ResultSet rst = stm.executeQuery(sql);
        while (rst.next()) {
            String ltid = rst.getString("LTID");
            String loanType = LoanTypeController.getLoanType(ltid);
            Object[] row = {rst.getString("LID"), loanType, rst.getString("MID"), rst.getString("name"), rst.getString("lDate"), rst.getDouble("ammount"), rst.getDouble("instalment"), (int) (rst.getDouble("balance") / rst.getDouble("instalment")), rst.getDouble("resevedFullInterast")};
            dtm.addRow(row);
        }
    }

    public static void getArrearsLoans(DefaultTableModel dtm) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select * from Loan natural join Member where (year(curdate())>=year(nextPaymentDate) && month(curdate())>=month(nextPaymentDate) && day(curdate())>=day(nextPaymentDate)) order by LID desc";
        ResultSet rst = stm.executeQuery(sql);
        while (rst.next()) {
            String ltid = rst.getString("LTID");
            String loanType = LoanTypeController.getLoanType(ltid);
            
            String[] lastPaymentDate = rst.getString("lastPaymentDate").split("-");
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String[] currentDate = sdf.format(date).split("-");
            int monthCount=0;
            if(Integer.parseInt(currentDate[0])==Integer.parseInt(lastPaymentDate[0])){
                if(Integer.parseInt(currentDate[2])>Integer.parseInt(lastPaymentDate[2])){
                    monthCount=Integer.parseInt(currentDate[1])-Integer.parseInt(lastPaymentDate[1]);
                }else{
                    monthCount=(Integer.parseInt(currentDate[1])-Integer.parseInt(lastPaymentDate[1])-1);
                }
            }else{
                monthCount=((12-Integer.parseInt(lastPaymentDate[1]))+Integer.parseInt(currentDate[1]));
            }
            monthCount=(Integer.parseInt(currentDate[0])-Integer.parseInt(lastPaymentDate[0]));
            
            Object[] row = {rst.getString("LID"), loanType, rst.getString("MID"), rst.getString("lDate"), rst.getDouble("ammount"), rst.getDouble("instalment"), (int) (rst.getDouble("balance") / rst.getDouble("instalment")), rst.getString("lastPaymentDate"),rst.getDouble("instalment")*monthCount};
            dtm.addRow(row);
        }
    }
}
