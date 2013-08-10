package br.com.softal.pfc;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

public class SystemPropertiesHelper implements javax.servlet.ServletContextListener {
    private ServletContext context = null;

    public void contextInitialized(ServletContextEvent event) {
        context = event.getServletContext();
        Enumeration<String> params = context.getInitParameterNames();

        while (params.hasMoreElements()) {
          String param = (String) params.nextElement();
          String value = 
            context.getInitParameter(param);
          if (param.startsWith("customPrefix.")) {
              System.setProperty(param, value);
          }
        }
    }

    public void contextDestroyed(ServletContextEvent event) {
    }
}

