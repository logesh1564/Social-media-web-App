/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Naveen Prasad
 */
public class addComment {
    public int addcomment(int postno,String tablename,String commentedUser,String author,String comment){
        Connection conn = connect.getCon();
        String querry = "INSERT INTO "+tablename+"(uname,comment,postno,authorname) VALUES(?,?,?,?)";
        int status = 0;
        try{
            PreparedStatement pstmt = conn.prepareStatement(querry);
            pstmt.setString(1,commentedUser);
            pstmt.setString(2, comment);
            pstmt.setInt(3,postno);
            pstmt.setString(4,author);
            int result = pstmt.executeUpdate();
            conn.setAutoCommit(true);
            status = result;
            System.out.println("Success in insertion"+" "+result);
        }catch(Exception ee){
            System.out.println(ee);
        }
        return status;
        
    }
}
