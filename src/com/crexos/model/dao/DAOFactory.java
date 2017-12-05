package com.crexos.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory
{
	private static DAOFactory instance;
	
	private Connection cnx = null;
	private String url;
	private String user;
	private String password;
	
	private DAOFactory(String url, String user, String password)
	{
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	public static DAOFactory getInstance()
	{
		if(instance == null)
		{
			instance = new DAOFactory(
					"jdbc:mariadb://localhost:3307/library",
					"alexis",
					"eureka"
					);
			
			try
			{
				DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	public Connection getConnection()
	{
		try
		{
			cnx = DriverManager.getConnection(url, user, password);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return cnx;
	}
	
	public void close()
	{
		try
		{
			if(cnx != null & !cnx.isClosed())
				cnx.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	BookDAO getBookDAO()
	{
		return new BookDAOImpl();
	}
}
