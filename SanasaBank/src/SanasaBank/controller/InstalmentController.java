/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SanasaBank.controller;

import SanasaBank.db.DBConnection;
import SanasaBank.model.Instalment;
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
public class InstalmentController {
    
    public static int payableInstalmentCount(String lid) throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="select count('LID') from Instalment where LID='"+lid+"'";
        ResultSet rst=stm.executeQuery(sql);
        if(rst.next()){
            return rst.getInt("count('LID')");
        }else{
            return 0;
        }
    }
    
    public static ArrayList<Instalment> getAllInstalments(String lid) throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="select * from Instalment where LID='"+lid+"'";
        ResultSet rst=stm.executeQuery(sql);
        ArrayList<Instalment> instalmentList=new ArrayList<>();
        while(rst.next()){
            Instalment instalment=new Instalment();
            instalment.setIID(rst.getString("IID"));
            instalment.setCount(rst.getInt("count"));
            instalment.setiAmmount(rst.getDouble("iAmmount"));
            instalment.setiDate(rst.getString("iDate"));
            instalment.setiInterest(rst.getDouble("iInterest"));
            instalmentList.add(instalment);
        }
        return instalmentList;
    }
    
    public static String getLastInstalmentID() throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="select * from Instalment order by 1 desc limit 1";
        ResultSet rst=stm.executeQuery(sql);
        if(rst.next()){
            return rst.getString("IID");
        }else{
            return "I0000";
        }
        
    }
    
    public static int addInstalment(Instalment instalment) throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="insert into Instalment values('"+instalment.getIID()+"','"+instalment.getLID()+"','"+instalment.getCID()+"',"+instalment.getBillNumber()+",'"+instalment.getiDate()+"',"+instalment.getiAmmount()+","+instalment.getCount()+","+instalment.getiInterest()+")";
        return stm.executeUpdate(sql);
    }
    
    public static DefaultTableModel getMemberInstalments(String mid) throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="select * from Loan natural join LoanType natural join Instalment where MID='"+mid+"'";
        ResultSet rst = stm.executeQuery(sql);
        
        Object[] column = {"Bill Number2", "Date2", "Loan Type", "Instalment", "Interest"};
        int count=0;
        while(rst.next()){
            count++;
        }
        rst.beforeFirst();
        Object[][] data=new Object[count][5];
        for (int i=0;rst.next();i++) {
            for (int j = 0; j < 5; j++) {
                data[i]=new Object[]{rst.getInt("billNumber"),rst.getString("iDate"),rst.getString("name"),rst.getString("iAmmount"),rst.getDouble("iInterest")};
            }
        }
        DefaultTableModel dtm = new DefaultTableModel(data, column);
        return dtm;
    }
    
    public static ArrayList<Instalment> getAllInstalmentsInDate(String date) throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="select * from Instalment natural join Loan natural join Member where iDate='"+date+"'";
        ResultSet rst=stm.executeQuery(sql);
        ArrayList<Instalment> instalmentList=new ArrayList<>();
        while(rst.next()){
            Instalment instalment=new Instalment();
            instalment.setBillNumber(rst.getInt("billNumber"));
            instalment.setLID(rst.getString("LID"));
            instalment.setIID(rst.getString("name"));
            instalment.setiAmmount(rst.getDouble("iAmmount"));
            instalment.setiInterest(rst.getDouble("iInterest"));
            instalment.setCID(rst.getString("LTID"));
            instalmentList.add(instalment);
        }
        return instalmentList;
    }
    
    public static ArrayList<Instalment> getAllInstalmentsInDuration(String firstDate,String secondDate) throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="select * from Instalment natural join Loan natural join Member where iDate between '"+firstDate+"' and '"+secondDate+"'";
        ResultSet rst=stm.executeQuery(sql);
        ArrayList<Instalment> instalmentList=new ArrayList<>();
        while(rst.next()){
            Instalment instalment=new Instalment();
            instalment.setBillNumber(rst.getInt("billNumber"));
            instalment.setLID(rst.getString("LID"));
            instalment.setIID(rst.getString("name"));
            instalment.setiDate(rst.getString("iDate"));
            instalment.setiAmmount(rst.getDouble("iAmmount"));
            instalment.setiInterest(rst.getDouble("iInterest"));
            instalmentList.add(instalment);
        }
        return instalmentList;
    }
}
