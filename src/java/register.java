
import database.connect;
import database.insert;
import database.createpostTable;
import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
@WebServlet(urlPatterns = {"/register"})
public class register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.sendRedirect("./login");
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String userName = request.getParameter("email");
       String userPass = getHash(request.getParameter("password").getBytes(),"MD5");
       int userAge = Integer.parseInt(request.getParameter("age"));
       String postTable = userName.substring(0,userName.indexOf('@'))+"post";
       System.out.println(userPass);
       Connection c = new connect().getCon();
       int lastuserno = getNoUsers(c);
       System.out.println("NO OF USERS"+lastuserno);
       new insert().register(lastuserno,userName,userPass,userAge,postTable,c,"userdata");
       new createpostTable().createtable(c, postTable);
       Cookie cookie = new Cookie("username",userName);
         //  cookie.setPath("/boothathon-2");
           cookie.setMaxAge(24*60*60);
           response.addCookie(cookie);
       response.sendRedirect("./home");
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
    
    public static int getNoUsers(Connection conn){
        int num = 0;
        String querry = "SELECT MAX(sno)  FROM userdata";
        try{
        PreparedStatement pstmt = conn.prepareStatement(querry);
        ResultSet ra = pstmt.executeQuery();
        while(ra.next()){
               num = ra.getInt("MAX(sno)");
               System.out.println(num);
           }
        }catch(Exception ee){
            System.out.println(ee);
        }
        return num;
    }
}
