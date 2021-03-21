
package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author Naveen Prasad
 */
public class insert {
    public void register(int lastuserno,String username,String password,int age,String postTable,Connection conn,String tableName){
        try{
            int num = lastuserno+1;
           String querry ="insert into "+tableName+"(username,password,age,posts,sno) values(?,?,?,?,?)";
           PreparedStatement pstmt = conn.prepareStatement(querry);
           pstmt.setString(1,username);
           pstmt.setString(2, password);
           pstmt.setInt(3,age);
           pstmt.setString(4, postTable);
           pstmt.setInt(5, num);
           int result1 = pstmt.executeUpdate();
           conn.setAutoCommit(true);
            System.out.println("Success in insertion"+" "+result1);
        }
        catch(Exception ee){
            System.out.println(ee);
        }
    }
}
