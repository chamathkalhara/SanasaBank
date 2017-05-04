/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SanasaBank.controller;

import SanasaBank.db.DBConnection;
import SanasaBank.model.Member;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Chamath
 */
public class MemberController {
    public static ArrayList<String> getAllMemberId() throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="select * from Member";
        ResultSet rst=stm.executeQuery(sql);
        ArrayList<String> allMemberIdList=new ArrayList<>();
        
        while(rst.next()){
            allMemberIdList.add(rst.getString("MID"));
        }
        return allMemberIdList;
    }
    
    public static Member getMemberDetail(String memberId) throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="select * from Member where MID='"+memberId+"'";
        ResultSet rst=stm.executeQuery(sql);
        Member member=null;
        if(rst.next()){
            member=new Member(rst.getString("MID"), rst.getString("name"), rst.getString("address"), rst.getString("NIC"),rst.getString("dob"),rst.getString("gender"), rst.getString("occupation"), rst.getInt("contactNum"));
        }
        return member;
    }
    
    public static ArrayList<String> getAllMemberName() throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="select * from Member";
        ResultSet rst=stm.executeQuery(sql);
        ArrayList<String> allMemberIdList=new ArrayList<>();
        
        while(rst.next()){
            allMemberIdList.add(rst.getString("name"));
        }
        return allMemberIdList;
    }
    
    public static Member getMemberDetailByName(String name) throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="select * from Member where name='"+name+"'";
        ResultSet rst=stm.executeQuery(sql);
        Member member=null;
        if(rst.next()){
            member=new Member(rst.getString("MID"), rst.getString("name"), rst.getString("address"), rst.getString("NIC"),rst.getString("dob"),rst.getString("gender"), rst.getString("occupation"), rst.getInt("contactNum"));
        }
        return member;
    }
    
    public static String getLastMemberID() throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="select * from Member order by 1 desc limit 1";
        ResultSet rst=stm.executeQuery(sql);
        if(rst.next()){
            return rst.getString("MID");
        }else{
            return "M0000";
        }
    }
    
    public static int addMember(Member member) throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="insert into Member values('"+member.getMID()+"','"+member.getName()+"','"+member.getAddress()+"','"+member.getNic()+"','"+member.getDob()+"','"+member.getGender()+"','"+member.getOccupation()+"',"+member.getContactNum()+")";
        return stm.executeUpdate(sql);
    }
    
    public static int deleteMember(String mid) throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="delete from Member where MID='"+mid+"'";
        return stm.executeUpdate(sql);
    }
    
    public static int updateMember(Member member) throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="update Member set name='"+member.getName()+"',address='"+member.getAddress()+"',NIC='"+member.getNic()+"',dob='"+member.getDob()+"',gender='"+member.getGender()+"',occupation='"+member.getOccupation()+"',contactNum="+member.getContactNum()+" where MID='"+member.getMID()+"'";
        return stm.executeUpdate(sql);
    }
}
