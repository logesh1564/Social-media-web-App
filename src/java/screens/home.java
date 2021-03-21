/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import database.addPost;
import database.connect;
import database.getFeedposts;
import database.getProfilefromDB;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import models.Post;
import models.user;

/**
 *
 * @author Naveen Prasad
 */
@MultipartConfig
@WebServlet(name = "home", urlPatterns = {"/home"})
public class home extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     Post feedArray[] = new getFeedposts().getFeedfromDB();
     String username = getUsername(request);
     user userprofile = new getProfilefromDB().profile(username);
     request.setAttribute("profile", userprofile);
     System.out.println(feedArray.length);
     request.setAttribute("feed", feedArray);
     RequestDispatcher rd =  request.getRequestDispatcher("home.jsp");
     rd.forward(request, response);
   //  response.sendRedirect("home.jsp");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       Part part = request.getPart("img");
       InputStream is = part.getInputStream();
       Connection c = new connect().getCon();
       String username = getUsername(request);
       String tablename = username.substring(0,username.indexOf('@'))+"post";
       String caption = request.getParameter("caption");
       int likes = 0;
       String commentTablename = username.substring(0,username.indexOf('@'))+"_"+String.valueOf(getNoofPosts(c,tablename));
        System.out.println("post no"+getNoofPosts(c,tablename));
       new addPost().addpost(c, tablename, username, is, caption, likes, commentTablename);
    //   System.out.println("File name"+part.getSubmittedFileName());
       response.sendRedirect("./home");
    }
    
    
    public static int getNoofPosts(Connection conn,String tablename){
        String qry = "SELECT postno from "+tablename;
        int result = 1;
        try{
            PreparedStatement pstmt = conn.prepareStatement(qry);
            ResultSet rs =  pstmt.executeQuery();
            int count = 0;
            while(rs.next()){
               count = rs.getInt("postno");
            }
            if(count>0){
                result = count+1;
            }
        }
        catch(SQLException ee){
            System.out.println("Error"+ee);
        }
        return result;
    }
   
    public static String getUsername(HttpServletRequest request){
        Cookie[] cookie = request.getCookies();
        String username = "";
        if(cookie != null){
              for(int i=0;i<cookie.length;i++){
                  String cName = cookie[i].getName();
                  String cValue = cookie[i].getValue();
                  if(cName.equals("username")){
                      username = cValue;
                      break;
              }
          }  
    } 
        return username;
    }

}
