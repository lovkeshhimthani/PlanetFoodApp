/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetfoodapp.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class DBConnection {
    private static Connection conn;
    static{
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-CQC2AA0:1521/XE","planetfood","cashier");
             JOptionPane.showMessageDialog(null,"Connected successfully to the db");
        }
         catch(Exception ex){
            JOptionPane.showMessageDialog(null,"cannot connect to the db");
            ex.printStackTrace();
            System.exit(1);
        }
    }
    public static Connection getConnection(){
        return conn;
    }
    public static void closeConnection(){
        try{
        conn.close();
        JOptionPane.showMessageDialog(null,"Connection close to  the db");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,"cannot disconnect with  the db");
            ex.printStackTrace();
  
        }
    }
}
