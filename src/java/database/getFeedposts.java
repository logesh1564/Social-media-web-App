/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import models.Comment;
import models.Post;

/**
 *
 * @author Naveen Prasad
 */
public class getFeedposts {
    Connection conn = connect.getCon();
    Post feedArray[] = new Post[4];
    int upperBound = getNoUsers(conn);
    
    public Post[] getFeedfromDB(){
        for(int i=0;i<4;i++){
            int user = new Random().nextInt(upperBound)+1;
           // int user = 1;
            
            String posttablename = getUserpostname(conn, user);
            int num = getlastestpostno(conn, posttablename);
            while(num == 0){
                user = user-1;
                posttablename = getUserpostname(conn, user);
                num = getlastestpostno(conn, posttablename);
            }
            
            System.out.println("USER--------------------"+user);
            Post post = getpost(conn, posttablename);
            feedArray[i] = post;
            System.out.println("POST CAPTION"+i+"th post "+post.getCaption());
            System.out.println("length"+feedArray.length);
            
        }
        return feedArray;
    }
    
    public static Post getpost(Connection conn,String posttablename){
        int upperBound = getlastestpostno(conn, posttablename);
        System.out.println("UPPER BOUND "+upperBound);
      //  int postno = 0;
      //  while(postno <= 0){
         int postno = ThreadLocalRandom.current().nextInt(0,upperBound)+1;
         System.out.println("WHile loop post"+postno);
       // }
        System.out.print("POST NO "+postno);
        String querry = "SELECT * FROM "+posttablename+" where postno = (?)";
        Post post = new Post();
        try{
            PreparedStatement pstmt = conn.prepareStatement(querry);
          //  pstmt.setString(1, posttablename);
            pstmt.setInt(1, postno);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                String uname = rs.getString("username");
                String caption = rs.getString("caption");
                int likes = rs.getInt("likes");
                String commenttablename = rs.getString("comment_table");
                Blob blob = rs.getBlob("image");
                byte imageArray[] = blob.getBytes(1,(int)blob.length());
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                Comment commentArray[] = new getComment().Comment(commenttablename);
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);                  
                }
                 
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                String profile = new getuserDP().getDP(conn, uname);
                inputStream.close();
                outputStream.close();
                post.setPostNo(postno);
                post.setUsername(uname);
                post.setCaption(caption);
                post.setLikes(likes);
                post.setByteArray(imageArray);
                post.setBase64Image(base64Image);
                post.setProfilepic(profile);
                post.setCommentTableName(commenttablename);
                post.setComments(commentArray);
            }
        }catch(Exception ee){
            System.out.println("Error blab blah"+ee);
        }
        return post;
    }
    
    
    public static String getUserpostname(Connection conn,int usersno){
        String querry = "SELECT posts from userdata where sno =?";
        String post = "";
        try{
            PreparedStatement pstmt = conn.prepareStatement(querry);
            pstmt.setInt(1, usersno);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                post = rs.getString("posts");
            }
        }catch(Exception ee){
            System.out.println(ee);
        }
        return post;
    }
    
    public static int getNoUsers(Connection conn){
        int num = 0;
        String querry = "SELECT MAX(sno)  FROM userdata";
        try{
        PreparedStatement pstmt = conn.prepareStatement(querry);
        ResultSet ra = pstmt.executeQuery();
        if(ra.next()){
               num = ra.getInt("MAX(sno)");
               System.out.println(num);
           }
        }catch(Exception ee){
            System.out.println(ee);
        }
        return num;
    }
    
    public static int getlastestpostno(Connection conn,String tablename){
        int num = 0;
        String querry = "SELECT MAX(postno)  FROM "+tablename;
        try{
        PreparedStatement pstmt = conn.prepareStatement(querry);
    //    pstmt.setString(1, tablename);
        ResultSet ra = pstmt.executeQuery();
        if(ra.next()){
               num = ra.getInt("MAX(postno)");
               System.out.println("MAXIMUM POSTS AT NAVEEN@GMAIL.COM"+num);
           }
        }catch(Exception ee){
            System.out.println(ee);
        }
        return num;
    }
}
