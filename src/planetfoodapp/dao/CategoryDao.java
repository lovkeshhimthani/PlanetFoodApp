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
import java.sql.Statement;
import java.util.HashMap;
import planetfoodapp.dbutil.DBConnection;
import planetfoodapp.pojo.Category;

/**
 *
 * @author HP
 */
public class CategoryDao {
    public static HashMap<String,String> getAllCategoryId()throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select cat_name,cat_id from categories");
        HashMap<String,String>map=new HashMap<>();
        while(rs.next()){
            map.put(rs.getString(1),rs.getString(2));
        }
        return map;
    }
    public static boolean editCategory(String catid,String catname)throws SQLException{
         Connection conn=DBConnection.getConnection();
          PreparedStatement ps=conn.prepareStatement("update categories set cat_name=? where cat_id=?");
          ps.setString(1,catname);
          ps.setString(2,catid);
          int ans=ps.executeUpdate();
          return ans!=0;
    }
    public static String getNewId()throws SQLException{
         Connection conn=DBConnection.getConnection();
         Statement st=conn.createStatement();
         ResultSet rs=st.executeQuery("select count(*) from categories");
         rs.next();
         int ans=rs.getInt(1);
         String str="C"+String.valueOf((101+ans));
         return str;
    }
    public static boolean addCategory(Category c)throws SQLException{
          Connection conn=DBConnection.getConnection();
          PreparedStatement ps=conn.prepareStatement("insert into categories values(?,?)");
          ps.setString(1,c.getCatid());
          ps.setString(2,c.getCatname());
          int ans=ps.executeUpdate();
          return ans!=0;
    }
   
}
