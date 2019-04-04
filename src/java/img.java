/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import MyPkg.DBConnect;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 *
 * @author DELL
 */
public class img extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
           
             Class.forName("com.mysql.jdbc.Driver");
             DBConnect connect=new DBConnect();
             Connection con=connect.connect();
	//java.sql.Connection con=java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","");
            String s1=request.getParameter("id");
            String qr="select * from items where itemid=?";
            java.sql.PreparedStatement ps=con.prepareStatement(qr);
             ps.setInt(1,Integer.parseInt(s1));
         
             java.sql.ResultSet rs= ps.executeQuery();
             while(rs.next())
             {
                  byte b[]=rs.getBytes(7);
           ServletOutputStream sos= response.getOutputStream();
          sos.write(b);
             }
            
             
        }
        catch(Exception e)
        {
            System.out.print(e);
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
