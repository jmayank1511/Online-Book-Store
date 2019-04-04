import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Chatstore extends HttpServlet {

    
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println("ttttttttttttttttthhhddfffddf--------------------------------------");
            PrintWriter out=response.getWriter();
            response.setContentType("text/html");
             Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost/new_schema","root","root");
          System.out.println("------------------------ttttttttttttttttthhhddfffddf------------------------");

            String uname=request.getParameter("uname");
            String msg=request.getParameter("msg");
            String owner=request.getParameter("owner");
            SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat sdf1=new SimpleDateFormat("HH:mm:ss");
            String identify;
             if(owner.compareToIgnoreCase(uname)<0)
        {
             identify=owner.concat("&"+uname);
        }
        else
        {
            identify = uname.concat("&"+owner);
        }
            
            Date date=new Date();
            String cmtDate=sdf.format(date);
            String cmttime =sdf1.format(date);
              
            System.out.println(uname);
            System.out.println(msg);
            
            String qr="insert into chatting_data(username,msg,date,time,identify) values(?,?,?,?,?)";
            
            PreparedStatement ps=con.prepareStatement(qr);
            ps.setString(1, uname);
            ps.setString(2, msg);
            ps.setString(3, cmtDate);
            ps.setString(4, cmttime);
            ps.setString(5, identify);
            
            
            ps.executeUpdate();
            
            con.close();
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Chatstore.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("d");
        } catch (SQLException ex) {
            Logger.getLogger(Chatstore.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        }
        
        
        
       
    }

 
}