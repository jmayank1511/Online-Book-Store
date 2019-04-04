
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import MyPkg.DBConnect;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@MultipartConfig(maxFileSize = 166454)
public class UploadItem1 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        try{
           
            DBConnect db=new DBConnect();
           Connection con=db.connect();
           DiskFileItemFactory df= new DiskFileItemFactory();
            ServletFileUpload upload=new ServletFileUpload(df);
            List<FileItem> items = upload.parseRequest(request);
            String name="";
             String dept="";
             String year="";
             int price=0;
             String desc = "";
             String cat="";
             byte pic[]=null;
             for(int i=0;i<items.size();i++)
             {
                 FileItem it = items.get(i);
                 String fname = it.getFieldName();
                 if(fname.equals("name"))
                 {
                     name=it.getString();
                 }
                 else if(fname.equals("desc"))
                 {
                     desc=it.getString();
                 }
                 else if(fname.equals("category"))
                 {
                     cat=it.getString();
                 }
                 else if(fname.equals("price"))
                 {
                     price=Integer.parseInt(it.getString());
                 }
                 else if(fname.equals("yr"))
                 {
                     year =it.getString();
                 }
                 else if(fname.equals("branch"))
                 {
                     dept=it.getString();
                 }
                 else if(fname.equals("photo"))
                 {
                     pic=it.get();
                 }
             } 
              String qr2="insert into items (item_name,dept,year,price,descrip,image,category,owner) values(?,?,?,?,?,?,?,?)";
           PreparedStatement ps=con.prepareStatement(qr2);
           HttpSession hs=request.getSession();
          String owner =(String) hs.getAttribute("username");
         //  File f= new File(s6);
         //  FileInputStream fin=new FileInputStream(f);         
	    ps.setString(1, name);
	    ps.setString(2, dept);
	    ps.setString(3, year);
	    ps.setInt(4, price);
            ps.setString(5, desc);
            if(pic!=null)
            ps.setBytes(6, pic);
            ps.setString(7, cat);
            ps.setString(8, owner);
	    ps.executeUpdate();
             
                      
           
            
            System.out.println("asjfgauzsjgfvhlaizusdhxgsulvzhsdxlgikjabhszlxkhj");
            
	
           
        }
        catch(Exception e)
        {
            out.println(e+"asjfgauzsjgfvhlaizusdhxgsulvzhsdxlgikjabhszlxkhj");
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





























/*


String s1=request.getParameter("name");
           String s2=request.getParameter("desc");
           String s3=request.getParameter("category");
           int s4=Integer.parseInt(request.getParameter("price"));
           String s5=request.getParameter("yr");
           String s6=request.getParameter("branch");
          // String s6=request.getParameter("t6");
           
           InputStream is=null;
           Part filePart = request.getPart("photo");
           if(filePart!=null)
           {
               out.println(filePart.getName());
               out.println(filePart.getSize());
               out.println(filePart.getContentType());
              is=filePart.getInputStream();
           }
           
           
           
           
           
           
           //out.println(s6);
           String qr2="insert into items (item_name,dept,year,price,descrip,image,category) values(?,?,?,?,?,?,?)";
           PreparedStatement ps=con.prepareStatement(qr2);
         //  File f= new File(s6);
         //  FileInputStream fin=new FileInputStream(f);         
	    ps.setString(1, s1);
	    ps.setString(2, s6);
	    ps.setString(3, s5);
	    ps.setInt(4, s4);
            ps.setString(5, s2);
            if(is!=null)
            ps.setBlob(6, is);
            ps.setString(3, s3);
	    ps.executeUpdate();
            out.println("<html>");
            
            out.println("<a href=\"LogoutServlet\">Logout</a>");
	    out.println("<h2><i>ITEM UPLOADED</i><h2>");
          
            out.println("</body>");
            out.println("</html>");


*/