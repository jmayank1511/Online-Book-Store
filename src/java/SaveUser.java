/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import MyPkg.DBConnect;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 *
 * @author DELL
 */
public class SaveUser extends HttpServlet {
    
    Connection con;   PreparedStatement ps;
     ResultSet rs;
      public void init()
    {
	try
	{
            DBConnect  connect= new DBConnect();
            con= connect.connect();
	//Class.forName("com.mysql.jdbc.Driver");
          //  java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost/new_schema","root","root");
	String qr="insert into user (name,password,email,address,contact) values(?,?,?,?,?,?)";
      
	ps=con.prepareStatement(qr);
       
        
        
	}
	catch(Exception ex){}
    }
      public void destroy()
    {
	try{
	    con.close();
	}
        catch(Exception e){}
    }
    
    
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
	String s1=request.getParameter("t1");
	String s2=request.getParameter("t2");
	String s3=request.getParameter("t3");
	String s4=request.getParameter("t4");
	String s5=request.getParameter("t5");
	
          
        try
	{
           
            ps.setString(1, s1);    
	    ps.setString(2, s2);
	    ps.setString(3, s3);
	    ps.setString(4, s4);
	    ps.setString(5, s5);
	  
	    ps.executeUpdate();
            
	   out.println("<html>");
            out.println("<body>");
	    out.println("<h2><i>REGISTRATION COMPLETED</i><h2>");
            out.println("<a href=\"index.jsp\">Home..</a>");
            out.println("</body>");
            out.println("</html>");
            
            
            
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
