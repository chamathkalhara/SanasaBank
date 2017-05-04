/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SanasaBank.controller;

import SanasaBank.db.DBConnection;
import SanasaBank.model.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Chamath
 */
public class UserController {

    public static ArrayList<Object> getPassword(User user) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql = "select password,(select Password('"+user.getPassword()+"'))as NewPassword ,type from User where userName = '"+user.getUserName()+"' ";
        ResultSet rst=stm.executeQuery(sql);
        ArrayList<Object> passwordList=new ArrayList<>();
        if(rst.next()){
            passwordList.add(rst.getString("password"));
            passwordList.add(rst.getString("NewPassword"));
            passwordList.add(rst.getInt("type"));
            return passwordList;
        }else{
            return passwordList;
        }
    }
    
    public static ArrayList<String> getAllUsers() throws ClassNotFoundException, SQLException{
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="select * from User where type not like 1";
        ResultSet rst=stm.executeQuery(sql);
        ArrayList<String> userList=new ArrayList<>();
        while(rst.next()){
            userList.add(rst.getString("userName"));
        }
        return userList;
    }
    
    public static void deleteUser(String userName) throws ClassNotFoundException, SQLException{
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="delete from User where userName='"+userName+"'";
        stm.executeUpdate(sql);
    }
    
    public static void updateUser(String oldName,String newName) throws ClassNotFoundException, SQLException{
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="update User set userName='"+newName+"' where userName='"+oldName+"'";
        stm.executeUpdate(sql);
    }
    
    public static void newUser(String userName,String password) throws ClassNotFoundException, SQLException{
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm=conn.createStatement();
        
        String sql="insert into User values('"+userName+"',(select password('"+password+"')),0);";
        stm.executeUpdate(sql);
    }
}
