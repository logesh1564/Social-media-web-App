
package database;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Naveen Prasad
 */
public class connect {
     private static Connection con;
    public static Connection getCon() {
          try
          {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/bootathon-2", "naveen","malarmay28");
          }
          catch(Exception e)
          {
               System.out.println("Error : "+e);
          }

        return con;
    }
}
