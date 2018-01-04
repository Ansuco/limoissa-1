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

/*
 * Pattern DAO permet de s'abstraire de la fa�on dont les donn�es sont stock�es au niveau des objets m�tier
 */
public class DAOFactory
{
	private static DAOFactory instance;
	
	private Connection cnx = null;
	
	private DAOFactory(){}
	
	/*
	 * Retourne une instance du pattern DAO
	 * @return Une instance du pattern unique
	 */
	public static DAOFactory getInstance()
	{
		if(instance == null)
		{
			instance = new DAOFactory();
		}
		return instance;
	}
	
	/*
	 * Obtenir une connexion de base de donn�e
	 * @return la connexion de la base de donn�e
	 */
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
	
	/*
	 * Retourne un requ�te pr�par�
	 * @param query la requ�te SQL
	 * @return la requ�te pr�par� en base de donn�e
	 */
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
	        	System.err.println("�chec de la fermeture du ResultSet : ");
	            e.printStackTrace();
	        }
	}

	private void closeStatement(Statement statement)
	{
	        try
	        {
	        	if(statement != null && !statement.isClosed())
	            statement.close();
	        }
	        catch (SQLException e)
	        {
	        	System.err.println("�chec de la fermeture du Statement : ");
	        	e.printStackTrace();
	        }
	}

	/*
	 * Ferme la connexion ainsi que celle de la requ�te pr�par�
	 * @param statement en g�n�rale c'est la requete pr�par�
	 */
	public void close(Statement statement)
	{
	    closeStatement(statement);
	    close();
	}
	
	/*
	 * Ferme la connexion ainsi que le le R�sultSet g�n�r� par la requ�te
	 * @param statement C'est le ResultSet que l'on veut la fermeture
	 */
	public void close(ResultSet statement)
	{
		closeResultSet(statement);
	    close();
	}

	/*
	 * Ferme la connexion, le resulset et la requete pr�parer
	 * @param resultSet le resultset g�n�r� par la requ�te
	 * @param statement La requ�te pr�par�
	 */
	public void close(ResultSet resultSet, Statement statement)
	{
		closeResultSet(resultSet);
		closeStatement(statement);
	    close();
	}
	
	/*
	 * Ferme la connexion de base de donn�e
	 */
	public void close()
	{
		try
		{
			if(cnx != null && !cnx.isClosed())
				cnx.close();
		}
		catch(SQLException e)
		{
			System.err.println("�chec de la fermeture de la connexion : ");
			e.printStackTrace();
		}
	}
	
	/*
	 * Obtenir une DAO de Book pour la persistence en base de donn�e
	 * @return un BookDAO
	 */
	public BookDAO getBookDAO()
	{
		return new BookDAOImpl();
	}
	
	/*
	 * Obtenir une DAO de Author pour la persistence en base de donn�e
	 * @return un AuthorDAO
	 */
	public AuthorDAO getAuthorDAO()
	{
		return new AuthorDAOImpl();
	}
	
	/*
	 * Obtenir une DAO de User pour la persistence en base de donn�e
	 * @return un UserDAO
	 */
	public UserDAO getUserDAO()
	{
		return new UserDAOImpl();
	}
}
