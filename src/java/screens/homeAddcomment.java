/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import database.addComment;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Naveen Prasad
 */
@WebServlet(name = "homeAddcomment", urlPatterns = {"/homeAddcomment"})
public class homeAddcomment extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commentername = request.getParameter("commentername");
        String authorname = request.getParameter("authorname");
        int postno =Integer.parseInt(request.getParameter("postno"));
        System.out.println("POST NO"+postno);
        String comment = request.getParameter("comment");
        String commentTablename = request.getParameter("commentTablename");
        int status = new addComment().addcomment(postno, commentTablename, commentername, authorname, comment);
        System.out.println("COMMENT ADDING STATUS"+status);
        response.sendRedirect("./profile?username="+authorname);
    }

    

}
