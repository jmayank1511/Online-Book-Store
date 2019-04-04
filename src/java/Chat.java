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
import java.util.ArrayList;
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
public class Chat extends HttpServlet {

   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                PrintWriter out=response.getWriter();
        try {
    
            response.setContentType("text/html");
           Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost/new_schema","root","root");
           
         String uname=request.getParameter("uname");
        
            System.out.println(uname);
            String pw=request.getParameter("pw");
            System.out.println(pw);
           // Statement statement=con.createStatement();
            String qr="select * from chatting where username=? and password =?";
            PreparedStatement ps=con.prepareStatement(qr);
           // String qr="select * from chatting where username="+uname+"and password ="+pw+"";
            
            ps.setString(1, uname);
            ps.setString(2, pw);
            ResultSet rs=ps.executeQuery();
            //ResultSet  rs=statement.executeQuery(qr);
            
            if(rs.next())
            {
            String username=rs.getString("username");
            javax.servlet.ServletContext context=getServletContext();
                ArrayList<String> list = (ArrayList < String >)context.getAttribute("list");
                list.add(username);
            HttpSession hs=request.getSession();
            hs.setAttribute("username", username);
            out.println("Welcome"+username.toUpperCase());
            out.println("<a href='index1.jsp?owner=%27ankita%27'>StartChat</a>");
            out.print("<a href='logout.jsp' class='logout-chat'>Logout</a>");
            
            }
            else
            out.println("Incorrect username and password");
           con.close();
           
           
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Chatstore.class.getName()).log(Level.SEVERE, null, ex);
            out.println(ex);
        }
       
        
        catch (SQLException ex) {
            Logger.getLogger(Chatstore.class.getName()).log(Level.SEVERE, null, ex);
                        out.println("cdddddddddddddddd");

        }
        
        
        
    }}