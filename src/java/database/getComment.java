/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.Comment;

/**
 *
 * @author Naveen Prasad
 */
public class getComment {
    
    public Comment[] Comment(String tablename){
        Connection conn = connect.getCon();
        int NoOfComments = totalComments(tablename, conn) ;
        System.out.println("TOTAL COMMENTS"+NoOfComments);
        Comment commentsArray[] = new Comment[NoOfComments];
        if(NoOfComments > 0){
        String querry = "SELECT * FROM "+tablename;
        try{
            PreparedStatement pstmt = conn.prepareStatement(querry);
            ResultSet rs = pstmt.executeQuery();
            int i = 0;
            while(rs.next()){
                String commentUser = rs.getString("uname");
                String profileimg = new getuserDP().getDP(conn, commentUser);
                String comment = rs.getString("comment");
                int postno = rs.getInt("postno");
                String authorname = rs.getString("authorname");
                Comment c = new Comment();
                c.setComment(comment);
                c.setCommentedUser(commentUser);
                c.setAuthorname(authorname);
                c.setPostno(postno);
                c.setProfileimg(profileimg);
                System.out.println("LOOP EXCECUTED");
                commentsArray[i] = c;
                i = i + 1;
                
             //   i++;
            }
        }catch(Exception ee){
            System.out.println("COMMENTS ERROR "+ee);
        }
        }
//        else{
//            String commentUser = "";
//            String comment = "NO COMMENTS AVAILABLE";
//            int postno = 0;
//            String authorname="";
//            Comment c = new Comment();
//                c.setComment(comment);
//                c.setCommentedUser(commentUser);
//                c.setAuthorname(authorname);
//                c.setPostno(postno);
//             commentsArray[0] = c;
//            
//        }
        System.out.println("COMMENTS SIZE 1 "+commentsArray.length);
       return commentsArray;    
    }
    
    public static int totalComments(String tablename,Connection conn){
        String querry = "SELECT COUNT(*) FROM "+tablename;
        int result = 0;
        try{
            PreparedStatement pstmt = conn.prepareStatement(querry);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                result = rs.getInt("COUNT(*)");
            }
           
        }catch(Exception ee){
            System.out.println(ee);
        }
        return result;
    }
}
