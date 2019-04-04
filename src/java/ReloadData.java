/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
public class ReloadData extends HttpServlet {

   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String owner=request.getParameter("owner");
        if(!(owner.equals(null)||owner.equals("")))
        {
        try {
            HttpSession hs=request.getSession();
            String usr = (String)hs.getAttribute("username");
            PrintWriter out=response.getWriter();
            response.setContentType("text/html");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/new_schema?zeroDateTimeBehavior=convertToNull","root","root");
            //java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost/chat","root","root");
           
        String colName;
        String invert;
         String identify;
        if(owner.compareToIgnoreCase(usr)<0)
        {
             identify=owner.concat("&"+usr);
             colName="utwo";
             invert="uone";
        }
        else
        {
            identify = usr.concat("&"+owner);
            colName = "uone";
            invert="utwo";
        }
        System.out.println(identify+"     identify");
            String qr="select* from chatting_data where identify='"+identify+"'";
           Statement statement=con.createStatement(java.sql.ResultSet.TYPE_SCROLL_SENSITIVE,java.sql.ResultSet.CONCUR_UPDATABLE);
          ResultSet rs= statement.executeQuery(qr);
          
        
          
          //  System.out.println(request.getParameter("owner")+"fffffffffffffffffffffffffffffff");
           while(rs.next())
           {
               rs.updateInt(colName, 1);
                rs.updateRow();
               String uname=rs.getString(1);
               String msg=rs.getString(2);
               String pdate=rs.getString(3);
               String ptime=rs.getString(4);
               ptime=ptime.substring(0,5);
               int read=rs.getInt(invert);
               PrintWriter pw=response.getWriter();
               String s="<p align='right'><small>"+ptime+"</small></p>";
               String t=s;
               if(read==1)
                   s="<p align='right'><small>"+ptime+"</small> "+"<img src='tick.png' width =15 height =10></p>";
               if(usr.equals(uname))
               {
              pw.print("<div class='chat-box-right'>"+msg+""+s+"</div><div class='chat-box-name-right'></g><br></p></div>");
               }
               else if(owner.equals(uname))
               {    
                   pw.print("<div class='chat-box-left'>"+msg+""+t+"</div><div class='chat-box-name-left'></g><br></p></div>");
                   
               }
               else
               {
                  // pw.print("<div class='chat-box-left'>"+msg+"</div><div class='chat-box-name-left'><img src='assets/img/user.png' alt='bootstrap Chat box user image' class='img-circle' /> "+uname+"</g><br><small>"+pdate+" "+ptime+"</small></p></div><hr class='hr-clas' />");
               }
                   //   pw.print("<p>"+uname+"<g>"+msg+"</g><br><small>"+pdate+" "+ptime+"</small></p>");
           }
           con.close();
           
           
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Chatstore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Chatstore.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
       
    }
    }  
}