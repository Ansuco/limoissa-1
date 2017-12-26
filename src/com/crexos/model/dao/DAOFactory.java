package com.crexos.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DAOFactory
{
	private static DAOFactory instance;
	
	private Connection cnx = null;
	
	private DAOFactory(){}
	
	public static DAOFactory getInstance()
	{
		if(instance == null)
		{
			instance = new DAOFactory();
		}
		return instance;
	}
	
	public Connection getConnection()
	{
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/library");//Utilisation de la JNDI
			cnx = ds.getConnection();
		}
		catch (NamingException|SQLException e)
		{
			e.printStackTrace();
		}

		return cnx;
	}
	
	public PreparedStatement getPreparedStatement(String query) throws SQLException
	{
		return getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	}
		
	private void closeResultSet(ResultSet resultSet)
	{
	        try
	        {
	        	if(resultSet != null && !resultSet.isClosed())
	        		resultSet.close();
	        }
	        catch (SQLException e)
	        {
	        	System.err.println("Échec de la fermeture du ResultSet : ");
	            e.printStackTrace();
	        }
	}

	/* Fermeture silencieuse du statement */
	private void closeStatement(Statement statement)
	{
	        try
	        {
	        	if(statement != null && !statement.isClosed())
	            statement.close();
	        }
	        catch (SQLException e)
	        {
	        	System.err.println("Échec de la fermeture du Statement : ");
	        	e.printStackTrace();
	        }
	}

	/* Fermetures silencieuses du statement et de la connexion */
	public void close(Statement statement)
	{
	    closeStatement(statement);
	    close();
	}
	
	/* Fermetures silencieuses du statement et de la connexion */
	public void close(ResultSet statement)
	{
		closeResultSet(statement);
	    close();
	}

	/* Fermetures silencieuses du statement et de la connexion */
	public void close(ResultSet resultSet, Statement statement)
	{
		closeResultSet(resultSet);
		closeStatement(statement);
	    close();
	}
	
	public void close()
	{
		try
		{
			if(cnx != null && !cnx.isClosed())
				cnx.close();
		}
		catch(SQLException e)
		{
			System.err.println("Échec de la fermeture de la connexion : ");
			e.printStackTrace();
		}
	}
	
	public BookDAO getBookDAO()
	{
		return new BookDAOImpl();
	}
	
	public AuthorDAO getAuthorDAO()
	{
		return new AuthorDAOImpl();
	}
	
	public UserDAO getUserDAO()
	{
		return new UserDAOImpl();
	}
}
