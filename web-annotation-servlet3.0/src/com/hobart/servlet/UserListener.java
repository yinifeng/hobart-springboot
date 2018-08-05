package com.hobart.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class UserListener
 *
 */
public class UserListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public UserListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         System.out.println("web应用销毁监听....");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	 System.out.println("web应用启动监听....");
    }
	
}
