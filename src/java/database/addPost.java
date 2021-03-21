/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author Naveen Prasad
 */
public class addPost {
    public void addpost(Connection conn,String tablename,String username,InputStream is,String caption,int likes,String commentTablename){
        try{
           String query = "INSERT INTO "+tablename+"(username,image,caption,likes,comment_table) values(?,?,?,?,?)";
           PreparedStatement pstmt = conn.prepareStatement(query);
           pstmt.setString(1, username);
           pstmt.setBlob(2, is);
           pstmt.setString(3, caption);
           pstmt.setInt(4, likes);
           pstmt.setString(5,commentTablename);
           int result1 = pstmt.executeUpdate();
           conn.setAutoCommit(true);
           System.out.println("Post added succesfully"+" "+result1);
           String query2 = "CREATE TABLE "+commentTablename+"("
                   + "uname TEXT,"
                   + "comment TEXT,"
                   + "postno INT,"
                   + "authorname TEXT)";
           Statement stmt = conn.createStatement();
           stmt.execute(query2);
           conn.setAutoCommit(true);  
           System.out.println("Comment table created");
        }
         catch(Exception ee){
            System.out.println(ee);
        }
        
    }
}
