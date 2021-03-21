
import database.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
/**
 *
 * @author Naveen Prasad
 */
@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
      request.setAttribute("Validate", " ");
      RequestDispatcher rd =  request.getRequestDispatcher("login.jsp");
      rd.forward(request, response);
  //    response.sendRedirect("./login");
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String userName = request.getParameter("email");
       String userPass = getHash(request.getParameter("password").getBytes(),"MD5");
       String query = "select * from userdata where username=?";//+userName;
       String dataPass = new search().search(new connect().getCon(), query,userName);
       if(userPass.equals(dataPass)){
           Cookie cookie = new Cookie("username",userName);
           cookie.setMaxAge(24*60*60);
           response.addCookie(cookie);
           response.sendRedirect("./home");
       }else{
       //    PrintWriter out=response.getWriter();
       //    out.println("<font color=red>Either user name or password is wrong.</font>");
       //    request.getRequestDispatcher("login.jsp").include(request,response);
           request.setAttribute("Validate", "Wrong username or password");
           RequestDispatcher rd =  request.getRequestDispatcher("login.jsp");
           rd.forward(request, response);
         //  response.sendRedirect("./login");
       }       
    }
    
    public static String getHash(byte[] inputBytes,String algorithm){
      String hashValue = "";
      try{
          MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
          messageDigest.update(inputBytes);
          byte[] digestedBytes = messageDigest.digest();
          hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
          
      }catch(Exception e){
          
      }
      return hashValue;
  }
    
}
 