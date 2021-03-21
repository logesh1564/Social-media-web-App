/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import database.getProfilefromDB;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.user;

/**
 *
 * @author Naveen Prasad
 */
@WebServlet(name = "profile", urlPatterns = {"/profile"})
public class profile extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
    //    String username = "naveen@gmail.com";
        user userprofile = new getProfilefromDB().profile(username);
        request.setAttribute("profile", userprofile);
        RequestDispatcher rd =  request.getRequestDispatcher("profile.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


}
