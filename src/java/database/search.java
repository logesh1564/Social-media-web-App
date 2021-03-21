/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Naveen Prasad
 */
public class search {
    public String search(Connection conn,String query,String uName){
        String pass = "";
       try{
           PreparedStatement pstmt = conn.prepareStatement(query);
        //  String city = "12345";
           pstmt.setString(1, uName);
            ResultSet ra = pstmt.executeQuery();
           while(ra.next()){
               pass = ra.getString("password");
               System.out.println(pass);
           }
           
       }
       catch(Exception ee){
           System.out.println(ee);
       }
       return pass;
   }
}
