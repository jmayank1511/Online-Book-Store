/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import MyPkg.DBConnect;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class VerifyUser extends HttpServlet {
       ResultSet rs;
    Connection con;
    PreparedStatement ps;
   public void init()
   {
       try
       {
           
             //DBConnect  connect= new DBConnect();
           // con= connect.connect();
          Class.forName("com.mysql.jdbc.Driver");
          DBConnect connect=new DBConnect();
                                       java.sql.Connection con=connect.connect();
        
        String qr="select * from chatting where username=? and password=?";
        ps=con.prepareStatement(qr);
             }
       catch(Exception e)
       {
         
       }
       
   }
   public void destory()
   {
       try
       {
           con.close();
       }
       catch(Exception e)
       {
           
       }
   }

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String s1=request.getParameter("t1");
        String s2=request.getParameter("t2");
        out.println(s1);
        out.println(s2);
        
        try{
                
            ps.setString(1,s1);
            ps.setString(2,s2);
            rs= ps.executeQuery();
            boolean found =rs.next();
            if(found)
            {
              //  String username=rs.getString(1); 
                String username=rs.getString("username");
            javax.servlet.ServletContext context=getServletContext();
                ArrayList<String> list = (ArrayList < String >)context.getAttribute("list");
                list.add(username);
            HttpSession hs=request.getSession();
            hs.setAttribute("username", username);
                response.sendRedirect("categoryHome.jsp");
            
            }
            else
            {
                out.println("INVALID USREID OR PASSWORD");
            }
        }
        
        catch(Exception e)
        {
            out.println(e);
        }
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
