/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetfoodapp.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import planetfoodapp.dbutil.DBConnection;
import planetfoodapp.pojo.Order;
import planetfoodapp.pojo.OrderDetail;

/**
 *
 * @author HP
 */
public class OrderDao {
    public static ArrayList<Order> getOrdersByDate(Date startDate,Date endDate)throws SQLException{
          Connection conn=DBConnection.getConnection();
          PreparedStatement ps=conn.prepareStatement("select * from orders where ord_date between ? and ?");
          long ms1=startDate.getTime();
          long ms2=endDate.getTime();
          java.sql.Date sdate=new java.sql.Date(ms1);
          java.sql.Date edate=new java.sql.Date(ms2);
          ps.setDate(1,sdate);
          ps.setDate(2, edate);
          ArrayList<Order>l=new ArrayList<>();
          ResultSet rs=ps.executeQuery();
          while(rs.next()){
              Order o=new Order();
              o.setOrdId(rs.getString(1));
              java.sql.Date d=rs.getDate(2);
              SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
              String str=sdf.format(d);
              o.setOrdDate(str);
              o.setGst(rs.getDouble(3));
              o.setGstAmount(rs.getDouble(4));
              o.setDiscount(rs.getDouble(5));
              o.setGrandTotal(rs.getDouble(6));
              o.setUserId(rs.getString(7));
              o.setOrdAmount(rs.getDouble(8));
              l.add(o);
          }
          return l;
    }
    public static ArrayList<Order> getAllOrders()throws SQLException{
         Connection conn=DBConnection.getConnection();
         Statement ps=conn.createStatement();
         ResultSet rs=ps.executeQuery("select * from orders");
          ArrayList<Order>l=new ArrayList<>();
          while(rs.next()){
              Order o=new Order();
              o.setOrdId(rs.getString(1));
              java.sql.Date d=rs.getDate(2);
              SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
              String str=sdf.format(d);
              o.setOrdDate(str);
              o.setGst(rs.getDouble(3));
              o.setGstAmount(rs.getDouble(4));
              o.setDiscount(rs.getDouble(5));
              o.setGrandTotal(rs.getDouble(6));
              o.setUserId(rs.getString(7));
              o.setOrdAmount(rs.getDouble(8));
              l.add(o);
          }
          return l;
    }
     public static String getNewId()throws SQLException{
         Connection conn=DBConnection.getConnection();
         Statement st=conn.createStatement();
         ResultSet rs=st.executeQuery("select count(*) from Orders");
         rs.next();
         int ans=rs.getInt(1);
         String str="OD"+String.valueOf((101+ans));
         return str;
    }
     public static boolean addOrder(Order o,ArrayList<OrderDetail>list)throws Exception{
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement("insert into orders values(?,?,?,?,?,?,?,?)");
           ps.setString(1,o.getOrdId());
           String str=o.getOrdDate();
            SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
            java.util.Date d1=sdf.parse(str);
            java.sql.Date d2=new java.sql.Date(d1.getTime());
            ps.setDate(2,d2);
           ps.setDouble(3,o.getGst());
           ps.setDouble(4,o.getGstAmount()); 
           ps.setDouble(5,o.getDiscount());
           ps.setDouble(6,o.getGrandTotal());
           ps.setString(7,o.getUserId());
           ps.setDouble(8,o.getOrdAmount());
           int x=ps.executeUpdate();
           PreparedStatement ps2=conn.prepareStatement("insert into order_details values(?,?,?,?)");
           int count=0,y;
           for(OrderDetail od:list){
               ps2.setString(1,od.getOrdId());
               ps2.setString(2,od.getProdId());
               ps2.setDouble(3,od.getQuantity());
               ps2.setDouble(4,od.getCost());
               y=ps2.executeUpdate();
               count=count+y;
           }
           if(x>0&&count==list.size()){
               return true;
           }
           return false;        
     }
}
