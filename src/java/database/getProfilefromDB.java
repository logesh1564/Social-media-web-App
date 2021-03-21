/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static database.getFeedposts.getNoUsers;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;
import models.Comment;
import models.Post;
import models.user;

/**
 *
 * @author Naveen Prasad
 */
public class getProfilefromDB {
    Connection conn = connect.getCon();
    
    int upperBound = getNoUsers(conn);
    
   public user profile(String username){
        String postTablename = getUserpostname(conn, username);
        int numOfPosts = getlastestpostno(conn, postTablename);
        Post profileArray[] = new Post[numOfPosts];
        for(int i=1;i<=numOfPosts;i++){
            Post post = getpost(conn, postTablename,i);
            profileArray[i-1] = post;
        }
        user pr = new user();
        pr.setPosts(profileArray);
        pr.setUsername(username);
        pr.setUserage(getAge(conn, username));
        pr.setProfileimage(new getuserDP().getDP(conn, username));
        String cover = getCoverpic(conn, username);
        String arr[] = getdata(conn, username);
        pr.setPhone(arr[0]);
        pr.setAddress(arr[1]);
        pr.setAbout(arr[2]);
        pr.setPlayerlevel(arr[3]);
        pr.setGamelevel(arr[4]);
        pr.setCoverimage(cover);
        return pr;
    }
    
    public static int getAge(Connection conn,String username){
        String querry = "SELECT age from userdata where username = ?";
        int age = 0;
        try{
            PreparedStatement pstmt = conn.prepareStatement(querry);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                age = rs.getInt("age");
            }
        }catch(Exception ee){
            System.out.println(ee);
        }
        return age;
    }
    
    
    public static Post getpost(Connection conn,String posttablename,int pno){
        int postno = pno;
        String querry = "SELECT * FROM "+posttablename+" where postno = (?)";
        Post post = new Post();
        try{
            PreparedStatement pstmt = conn.prepareStatement(querry);
          //  pstmt.setString(1, posttablename);
            pstmt.setInt(1, postno);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
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
            System.out.println("Error"+ee);
        }
        return post;
    }
    
    
    
    public static String getUserpostname(Connection conn,String username){
        String querry = "SELECT posts from userdata where username = ?";
        String post = "";
        try{
            PreparedStatement pstmt = conn.prepareStatement(querry);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                post = rs.getString("posts");
            }
        }catch(Exception ee){
            System.out.println(ee);
        }
        return post;
    }
    
    public static int getlastestpostno(Connection conn,String tablename){
        int num = 0;
        String querry = "SELECT MAX(postno)  FROM "+tablename;
        try{
        PreparedStatement pstmt = conn.prepareStatement(querry);
    //    pstmt.setString(1, tablename);
        ResultSet ra = pstmt.executeQuery();
        while(ra.next()){
               num = ra.getInt("MAX(postno)");
               System.out.println("MAXIMUM POSTS AT NAVEEN@GMAIL.COM"+num);
           }
        }catch(Exception ee){
            System.out.println(ee);
        }
        return num;
    }
    
    public static String getCoverpic(Connection conn,String username){
        String querry = "Select coverimage from userdata where username=?";
        String cover = "";
        try {
            PreparedStatement pstmt = conn.prepareStatement(querry);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                Blob blob = rs.getBlob("coverimage");
                if(blob != null){
                byte imageArray[] = blob.getBytes(1,(int)blob.length());
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);                  
                }
                 
                byte[] imageBytes = outputStream.toByteArray();
                cover = "data:image/jpg;base64,"+Base64.getEncoder().encodeToString(imageBytes);
                
                inputStream.close();
                outputStream.close();
                }else{
                    cover = "assets/images/bf.jpg";
                }
            }
            if(cover.equals("")){
                System.out.println("ASsigning default cover");
                cover = "assets/images/bf.jpg";
            }
        } catch (Exception e) {
            System.out.println("Error "+e);
        }
        
        return cover;
    }
    
    public static String[] getdata(Connection conn,String username){
        String array[] = new String[5];
        String querry = "Select * from userdata where username=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(querry);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                
                array[0] = rs.getString("phone")!=null?rs.getString("phone"):"(+44) 123 456 789";
                array[1] = rs.getString("address")!=null?rs.getString("address"):"Dubai main road , Dubai.";
                array[2] = rs.getString("about")!=null?rs.getString("about"):"I am a full time Gamer.I play all types of games";
                array[3] = rs.getString("playerlevel")!=null?rs.getString("playerlevel"):"Pro";
                array[4] = rs.getString("games")!=null?rs.getString("games"):"COD"+"\n"+"PUBG"+"\n"+"COC";
               
            }
            else{
                array[0] = "(+44) 123 456 789";
                array[1] = "Dubai main road , Dubai.";
                array[2] = "I am a full time Gamer.I play all types of games";
                array[3] = "Pro";
                array[4] = "COD"+"\n"+"PUBG"+"\n"+"COC";
            }
        } catch (Exception e) {
            System.out.println("Error"+e);
        }
        return array;
    }
    
}
