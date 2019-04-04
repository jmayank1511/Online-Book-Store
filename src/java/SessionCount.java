/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mayank
 */
public class SessionCount extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String owner=request.getParameter("owner");
            javax.servlet.ServletContext context = getServletContext();
            ArrayList<String> list = (ArrayList < String >)context.getAttribute("list");
            
           
            if(list!=null)
            {
               //  out.print("Onc");
                boolean isOnline =false;
            for(int i=0;i<list.size();i++)
            {
                String id=(String)list.get(i);
                 
                if(owner.equals(id))
                { isOnline=true;
                    break;
                }
            }
            if(isOnline==true)
            { out.print("<p align=\"left\"><img src=\"assets/img/user.png\" width = '30' alt=\"bootstrap Chat box user image\" class=\"img-circle\" />"+" "+owner+"<small>"+" "+"Online</small></p> ");
               // out.print("<em align='right'>Online</em>");
             System.out.print("<p align=\"left\"><img src=\"assets/img/user.png\" width = '30' alt=\"bootstrap Chat box user image\" class=\"img-circle\" />"+" "+owner+"<small>"+" "+"Online</small></p> ");
            }
             else
            {
               
                 out.print("<p align=\"left\"><img src=\"assets/img/user.png\" width = '30' alt=\"bootstrap Chat box user image\" class=\"img-circle\" />"+" "+owner+"<small>"+" "+"Offline</small></p> ");
            }
            }
            else{System.out.print("Null List");}
                    
                    
            
           // out.print("Online");
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
