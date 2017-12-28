package com.crexos.model.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class AppListener
 *
 */
@WebListener
public class AppListener implements ServletContextListener
{
	private static EntityManagerFactory emf;

	public static EntityManagerFactory getEmf()
	{
		return emf;
	}
    
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)
    { 
//		if(emf != null && emf.isOpen())
//			emf.close();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) 
    { 
		//emf = Persistence.createEntityManagerFactory("Limoissa");
    }
}
