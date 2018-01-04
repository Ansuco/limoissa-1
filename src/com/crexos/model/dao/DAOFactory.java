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
 * Pattern DAO permet de s'abstraire de la façon dont les données sont stockées au niveau des objets métier
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
	 * Obtenir une connexion de base de donnée
	 * @return la connexion de la base de donnée
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
	 * Retourne un requète préparé
	 * @param query la requête SQL
	 * @return la requête préparé en base de donnée
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
	        	System.err.println("Échec de la fermeture du ResultSet : ");
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
	        	System.err.println("Échec de la fermeture du Statement : ");
	        	e.printStackTrace();
	        }
	}

	/*
	 * Ferme la connexion ainsi que celle de la requête préparé
	 * @param statement en générale c'est la requete préparé
	 */
	public void close(Statement statement)
	{
	    closeStatement(statement);
	    close();
	}
	
	/*
	 * Ferme la connexion ainsi que le le RésultSet généré par la requête
	 * @param statement C'est le ResultSet que l'on veut la fermeture
	 */
	public void close(ResultSet statement)
	{
		closeResultSet(statement);
	    close();
	}

	/*
	 * Ferme la connexion, le resulset et la requete préparer
	 * @param resultSet le resultset généré par la requête
	 * @param statement La requête préparé
	 */
	public void close(ResultSet resultSet, Statement statement)
	{
		closeResultSet(resultSet);
		closeStatement(statement);
	    close();
	}
	
	/*
	 * Ferme la connexion de base de donnée
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
			System.err.println("Échec de la fermeture de la connexion : ");
			e.printStackTrace();
		}
	}
	
	/*
	 * Obtenir une DAO de Book pour la persistence en base de donnée
	 * @return un BookDAO
	 */
	public BookDAO getBookDAO()
	{
		return new BookDAOImpl();
	}
	
	/*
	 * Obtenir une DAO de Author pour la persistence en base de donnée
	 * @return un AuthorDAO
	 */
	public AuthorDAO getAuthorDAO()
	{
		return new AuthorDAOImpl();
	}
	
	/*
	 * Obtenir une DAO de User pour la persistence en base de donnée
	 * @return un UserDAO
	 */
	public UserDAO getUserDAO()
	{
		return new UserDAOImpl();
	}
}
