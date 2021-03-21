/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.*; 
/**
 *
 * @author Naveen Prasad
 */
@WebServlet(urlPatterns = {"/passValidate"})
public class passValidate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%_]).{8,20}$";
        Pattern p = Pattern.compile(regex); 
        Matcher m = p.matcher(request.getParameter("pass"));
        if(!m.matches()){
            out.println("<p>Password must contain atleast a digit,lowercase,uppercase and special character</p>");
        }
        else if(request.getParameter("pass").length() <=8){
            out.println("<p><center>Password must contain  atleast 8 characters<center> </p>");
        }
        else{
            out.println("");
        }
        out.close();
    }
   

}

