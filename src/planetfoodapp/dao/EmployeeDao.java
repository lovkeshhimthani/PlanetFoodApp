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
import planetfoodapp.dbutil.DBConnection;
import planetfoodapp.pojo.Employee;
import planetfoodapp.pojo.Product;

/**
 *
 * @author HP
 */
public class EmployeeDao {
     public static String getNewId()throws SQLException{
         Connection conn=DBConnection.getConnection();
         Statement st=conn.createStatement();
         ResultSet rs=st.executeQuery("select count(*) from employee");
         rs.next();
         int ans=rs.getInt(1);
         String str="E"+String.valueOf((101+ans));
         return str;
    }
    public static boolean addEmployee(Employee e)throws SQLException{
          Connection conn=DBConnection.getConnection();
          PreparedStatement ps=conn.prepareStatement("insert into employee values(?,?,?,?)");
          ps.setString(1,e.getEmpid());
          ps.setString(2,e.geteName());
          ps.setString(3,e.getJob());
          ps.setDouble(4,e.getSal());
          int ans=ps.executeUpdate();
          return ans!=0;
    }
    public static boolean deleteEmployee(String eid)throws SQLException{
          Connection conn=DBConnection.getConnection();
          PreparedStatement ps=conn.prepareStatement("delete from Employee where empid=?");
          ps.setString(1,eid);
          UserDao.deleteUser(eid);
          int ans=ps.executeUpdate();
          return ans!=0;
    }
    public static boolean getEmployee(Employee e)throws SQLException{
          Connection conn=DBConnection.getConnection();
          PreparedStatement ps=conn.prepareStatement("select * from employee where empid=? and empname=? and job=? and salary=?");
           ps.setString(1,e.getEmpid());
          ps.setString(2,e.geteName());
          ps.setString(3,e.getJob());
          ps.setDouble(4,e.getSal());
          ResultSet rs=ps.executeQuery();
          return rs.next();
    }
    public static boolean editEmployee(Employee e)throws SQLException{
          Connection conn=DBConnection.getConnection();
          PreparedStatement ps=conn.prepareStatement("update employee set empname=?,job=?,salary=? where empid=?");
          ps.setString(4,e.getEmpid());
          ps.setString(1,e.geteName());
          ps.setString(2,e.getJob());
          ps.setDouble(3,e.getSal());
          int ans=ps.executeUpdate();
          return ans!=0;
    }
    public static ArrayList<String> getEid()throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select empid from employee");
        ArrayList<String>list=new ArrayList<>();
        while(rs.next()){
            list.add(rs.getString(1));
        }
        return list;
    }
    public static Employee getEmployeeById(String eid)throws SQLException{
          Connection conn=DBConnection.getConnection();
          PreparedStatement ps=conn.prepareStatement("select * from employee where empid=?");
          ps.setString(1,eid);
          ResultSet rs=ps.executeQuery();
          Employee e=new Employee();
          while(rs.next()){
              e.setEmpid(eid);
              e.seteName(rs.getString(2));
              e.setJob(rs.getString(3));
              e.setSal(rs.getDouble(4));
              return e;
          }
          return null;
    }
    public static ArrayList<Employee> getAllData()throws SQLException{
         Connection conn=DBConnection.getConnection();
         Statement st=conn.createStatement();
         ArrayList<Employee>list=new ArrayList<>();
         ResultSet rs=st.executeQuery("select * from employee");
         while(rs.next()){
             Employee e=new Employee();
             e.setEmpid(rs.getString(1));
             e.seteName(rs.getString(2));
             e.setJob(rs.getString(3));
             e.setSal(rs.getDouble(4));
             list.add(e);
         }
         return list;
    }
     public static ArrayList<String> getIdCashier()throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement st=conn.prepareStatement("select empid from employee where job=?");
        st.setString(1,"Cashier");
        ResultSet rs=st.executeQuery();
        ArrayList<String>list=new ArrayList<>();
        while(rs.next()){
            list.add(rs.getString(1));
        }
        return list;
    }
}
