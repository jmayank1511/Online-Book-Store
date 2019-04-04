package MyPkg;

import java.sql.*;

public class DBConnect {
 public Connection con;
 public Connection connect() throws Exception
         { 
              Class.forName("com.mysql.jdbc.Driver");
           Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/new_schema?zeroDateTimeBehavior=convertToNull","root","root");
// java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost/bootstore","root","");
         return con;   
         }
 public void queryExecuter(String sql) throws Exception
 {
        Statement stmt=con.createStatement();
        stmt.execute(sql);
        stmt.close();
        con.close();
 }
 public ResultSet queryReturner(String sql) throws Exception
 {
     Statement stmt =con.createStatement();
     ResultSet rs=stmt.executeQuery(sql);
     return rs;
 }
}
