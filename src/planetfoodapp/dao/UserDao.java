/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetfoodapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import planetfoodapp.dbutil.DBConnection;
import planetfoodapp.pojo.User;

/**
 *
 * @author HP
 */
public class UserDao {
     public static  boolean addUser(User user)throws SQLException{
        Connection conn=DBConnection.getConnection();
         PreparedStatement ps=conn.prepareStatement("Select * from users where userid=? and usertype=? and empid=?");
         ps.setString(1,user.getUserId()); 
         ps.setString(2,user.getUserType());
         ps.setString(3,user.getEmpId()); 
         ResultSet rs=ps.executeQuery();
         if(rs.next()){
              return false;
         }
        ps=conn.prepareStatement("insert into users values(?,?,?,?)");
        ps.setString(1,user.getUserId());
        ps.setString(2,user.getEmpId()); 
        ps.setString(3,user.getPassword());
        ps.setString(4,user.getUserType());
        int ans=ps.executeUpdate();
        return ans!=0;
        
    }
    public static String validateUser(User u)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select userid from users where userid=? and password=? and usertype=?");
        ps.setString(1,u.getUserId());
        ps.setString(2,u.getPassword());
        ps.setString(3,u.getUserType());
        ResultSet rs=ps.executeQuery();
        String userid="";
        if(rs.next()){
           userid=rs.getString(1); 
        }
        return userid;         
    }
    public static boolean deleteUser(String eid)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("delete from users where empid=?");
        ps.setString(1,eid);
        int ans=ps.executeUpdate();
        return ans!=0;    
    }  
    public static User getCashierById(String uid)throws SQLException{
         Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from users where userid=? and usertype=?");
        ps.setString(1,uid);
        ps.setString(2,"Cashier");
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
           User u=new User();
           u.setUserId(rs.getString(1));
           u.setEmpId(rs.getString(2));
           u.setUserType(rs.getString(4));
           u.setPassword(rs.getString(3));
           return u;
        }
        return null;  
    }
}
