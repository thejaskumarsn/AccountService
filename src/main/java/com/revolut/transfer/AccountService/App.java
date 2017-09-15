package com.revolut.transfer.AccountService;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.revolut.controller.TransactionManager;
import com.revoult.transfer.service.AccountService;

/**
 * Hello world!
 *
 */
public class App 
{
public static TransactionManager manager;
	
	public static void main(String[] args) throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(8080);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
             org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
         manager = new TransactionManager();
        jerseyServlet.setInitParameter(
           "jersey.config.server.provider.classnames",
           AccountService.class.getCanonicalName());

        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }
    }
}
