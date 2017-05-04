/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SanasaBank.controller;

import SanasaBank.db.DBConnection;
import SanasaBank.model.Tranceaction;
import SanasaBank.other.AutoGenarateID;
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
public class TranceactionController {

    public static int tranceaction(Tranceaction tranceaction) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String newTranceactionId = getNewTranceactionId();

        String sql = "insert into Tranceaction values('" + newTranceactionId + "','" + tranceaction.getAID() + "','" + tranceaction.getCID() + "'," + tranceaction.getBillNumber() + ",'" + tranceaction.gettDate() + "','" + tranceaction.gettType() + "'," + tranceaction.getAmmount() + ")";
        return stm.executeUpdate(sql);
    }

    public static String getNewTranceactionId() throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select * from Tranceaction order by 1 desc limit 1";
        ResultSet rst = stm.executeQuery(sql);
        String lastTranceactionId;
        if (rst.next()) {
            lastTranceactionId = rst.getString("TID");
        } else {
            lastTranceactionId = "T0000";
        }

        String newTranceactionID = AutoGenarateID.genarate(null, "T", lastTranceactionId);
        return newTranceactionID;
    }

    public static DefaultTableModel getMemberTanceactions(String mid) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();

        String sql = "select * from account natural join accounttype natural join tranceaction where MID='" + mid + "'";
        ResultSet rst = stm.executeQuery(sql);

        Object[] column = {"Bill Number", "Date", "Description", "Type", "Amount", "Interest"};
        int count = 0;
        while (rst.next()) {
            count++;
        }
        rst.beforeFirst();
        Object[][] data = new Object[count][5];
        for (int i = 0; rst.next(); i++) {

            data[i] = new Object[]{rst.getInt("billNumber"), rst.getString("tDate"), rst.getString("name"), rst.getString("tType"), rst.getDouble("ammount"),""};

        }
        DefaultTableModel dtm = new DefaultTableModel(data, column);
        return dtm;
    }
    
    public static ArrayList<Tranceaction> getTranceactions(String aid) throws ClassNotFoundException, SQLException{
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();
        
        String sql="select * from Tranceaction where AID='"+aid+"'";
        ResultSet rst=stm.executeQuery(sql);
        ArrayList<Tranceaction> arrayList=new ArrayList<>();
        while(rst.next()){
            Tranceaction tranceaction=new Tranceaction();
            tranceaction.setBillNumber(rst.getInt("billNumber"));
            tranceaction.settDate(rst.getString("tDate"));
            tranceaction.settType(rst.getString("tType"));
            tranceaction.setAmmount(rst.getDouble("ammount"));
            arrayList.add(tranceaction);
        }
        return arrayList;
    }
    
    public static ArrayList<Tranceaction> getTranceactionsByDate(String date) throws ClassNotFoundException, SQLException{
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();
        
        String sql="select * from Tranceaction natural join Account natural join Member where tDate='"+date+"'";
        ResultSet rst=stm.executeQuery(sql);
        ArrayList<Tranceaction> arrayList=new ArrayList<>();
        while(rst.next()){
            Tranceaction tranceaction=new Tranceaction();
            tranceaction.setBillNumber(rst.getInt("billNumber"));
            tranceaction.setAID(rst.getString("AID"));
            tranceaction.setTID(rst.getString("name"));
            tranceaction.settType(rst.getString("tType"));
            tranceaction.setAmmount(rst.getDouble("ammount"));
            tranceaction.setCID(rst.getString("ATID"));
            arrayList.add(tranceaction);
        }
        return arrayList;
    }
    
    public static ArrayList<Tranceaction> getTranceactionsByDuration(String firstDate,String secondDate) throws ClassNotFoundException, SQLException{
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();
        
        String sql="select * from Tranceaction natural join Account natural join Member where tDate between '"+firstDate+"' and '"+secondDate+"'";
        ResultSet rst=stm.executeQuery(sql);
        ArrayList<Tranceaction> arrayList=new ArrayList<>();
        while(rst.next()){
            Tranceaction tranceaction=new Tranceaction();
            tranceaction.setBillNumber(rst.getInt("billNumber"));
            tranceaction.setAID(rst.getString("AID"));
            tranceaction.setTID(rst.getString("name"));
            tranceaction.settDate(rst.getString("tDate"));
            tranceaction.settType(rst.getString("tType"));
            tranceaction.setAmmount(rst.getDouble("ammount"));
            arrayList.add(tranceaction);
        }
        return arrayList;
    }
}
