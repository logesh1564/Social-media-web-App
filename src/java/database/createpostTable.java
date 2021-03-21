/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author Naveen Prasad
 */
public class createpostTable {
    public void createtable(Connection conn,String tablename){
        try{
            String query = "CREATE TABLE "+tablename+"("
                    + "postno INT NOT NULL AUTO_INCREMENT ,"
                    + "username TEXT,"
                    + "image longblob,"
                    + "caption TEXT,"
                    + "likes int,"
                    + "comment_table TEXT,"
                    + "PRIMARY KEY (postno))";
            Statement stmt = conn.createStatement();
            stmt.execute(query);
       //  PreparedStatement pstmt = conn.prepareStatement(query);
        // int result1 = pstmt.executeUpdate();
         conn.setAutoCommit(true);
         //System.out.println("Success in insertion"+" "+result1);
        }catch(Exception ee){
            System.out.println("ERROR"+ee);
        }
         
    }
}
