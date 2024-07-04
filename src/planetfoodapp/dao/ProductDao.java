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
import java.util.ArrayList;
import java.util.HashMap;
import planetfoodapp.dbutil.DBConnection;
import planetfoodapp.pojo.Product;

/**
 *
 * @author HP
 */
public class ProductDao {
    public static String getNewId()throws SQLException{
         Connection conn=DBConnection.getConnection();
         Statement st=conn.createStatement();
         ResultSet rs=st.executeQuery("select count(*) from products");
         rs.next();
         int ans=rs.getInt(1);
         String str="P"+String.valueOf((101+ans));
         return str;
    }
    public static boolean addProduct(Product p)throws SQLException{
          Connection conn=DBConnection.getConnection();
          PreparedStatement ps=conn.prepareStatement("insert into products values(?,?,?,?,?)");
          ps.setString(1,p.getProdid());
          ps.setString(2,p.getCatid());
          ps.setString(3,p.getProdName());
          ps.setDouble(4,p.getProdPrice());
          ps.setString(5,p.getIsActive());
          int ans=ps.executeUpdate();
          return ans!=0;
    }
    public static boolean deleteProduct(String pid)throws SQLException{
          Connection conn=DBConnection.getConnection();
          PreparedStatement ps=conn.prepareStatement("delete from products where prod_id=?");
          ps.setString(1,pid);
          int ans=ps.executeUpdate();
          return ans!=0;
    }
    public static HashMap<String,Product> getProductsByCategory(String cid)throws SQLException{
         Connection conn=DBConnection.getConnection();
         HashMap<String,Product>map=new HashMap<>();
         PreparedStatement ps=conn.prepareStatement("select * from products where cat_id=?");
         ps.setString(1,cid);
         ResultSet rs=ps.executeQuery();
         while(rs.next()){
             Product p=new Product();
             p.setCatid(cid);
             p.setIsActive(rs.getString(5));
             p.setProdName(rs.getString(3));
             p.setProdPrice(rs.getDouble(4));
             p.setProdid(rs.getString(1));
             map.put(rs.getString(1),p);
         }
         return map;
    }
    public static ArrayList<Product> getAllData()throws SQLException{
         Connection conn=DBConnection.getConnection();
         Statement st=conn.createStatement();
         ArrayList<Product>list=new ArrayList<>();
         ResultSet rs=st.executeQuery("select * from products");
         while(rs.next()){
              Product p=new Product();
             p.setCatid(rs.getString(2));
             p.setIsActive(rs.getString(5));
             p.setProdName(rs.getString(3));
             p.setProdPrice(rs.getDouble(4));
             p.setProdid(rs.getString(1));
             list.add(p);
         }
         return list;
    }
    public static boolean EditProduct(Product p)throws SQLException{
          Connection conn=DBConnection.getConnection();
          PreparedStatement ps=conn.prepareStatement("update products set cat_id=?,prod_name=?,prod_price=?,active=? where prod_id=?");
          ps.setString(5,p.getProdid());
          ps.setString(1,p.getCatid());
          ps.setString(2,p.getProdName());
          ps.setDouble(3,p.getProdPrice());
          ps.setString(4,p.getIsActive());
          int ans=ps.executeUpdate();
          return ans!=0;
    }
}
