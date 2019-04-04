/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyPkg;

import java.util.ArrayList;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import sun.security.pkcs11.wrapper.Functions;

/**
 * Web application lifecycle listener.
 *
 * @author Mayank
 */
public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ArrayList list = new ArrayList();
        list.add("zdjsdjfsj");
        javax.servlet.ServletContext context=sce.getServletContext();
        context.setAttribute("list", list);
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
