package com.crexos.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Methode commune à nos AO pour executer des requêtes en base de donnée
 */
public abstract class AbstractDAO
{
	/*
	 * Execute une requête par exemple SELECT
	 * @param query la requête SQL que l'on souhaite executer
	 * @param exceptionMessage un message à afficher en console lorsqu'une exception est déclenché
	 * @return le resultat de la requete
	 */
	@Deprecated
	public ResultSet executeQuery(String query, String exceptionMessage)
	{
		Connection cnx = null;
		ResultSet result = null;
		
		try
		{			
			cnx = DAOFactory.getInstance().getConnection();
			Statement st = cnx.createStatement();
			st.executeQuery(query);
			
			result = st.getResultSet();
		}
		catch (SQLException e)
		{
			System.err.println(exceptionMessage);
			e.printStackTrace();
		}
		
		return result;
	}
	
	/*
	 * Execute une requête paramétré par exemple SELECT
	 * @param ps la requête paramétré SQL que l'on souhaite executer
	 * @param exceptionMessage un message à afficher en console lorsqu'une exception est déclenché
	 * @return le resultat de la requete
	 */
	public ResultSet executeQuery(PreparedStatement ps, String exceptionMessage)
	{
		ResultSet result = null;
		try
		{	
			result = ps.executeQuery();
		}
		catch (SQLException e)
		{
			System.err.println(exceptionMessage);
			e.printStackTrace();
		}
		
		return result;
	}

	/*
	 * Execute une requête par exemple UPDATE
	 * @param query la requête SQL que l'on souhaite executer
	 * @param exceptionMessage un message à afficher en console lorsqu'une exception est déclenché
	 * @return le nombre de résultat affecté par la requête ou un identifiant si la requête c'est INSERT INTO
	 */
	@Deprecated
	public int executeUpdate(String query, String exceptionMessage)
	{
		Connection cnx = null;
		int result = 0;
		
		try
		{
			cnx = DAOFactory.getInstance().getConnection();
			Statement st = cnx.createStatement();
			result = st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet resultId = st.getGeneratedKeys(); 
			if(result == 0)
				throw new SQLException();
			else
			{
				if(resultId.next())
				{
					result = resultId.getInt(1);
				}
			}
		}
		catch(SQLException e)
		{
			if(result == 0)
				System.err.println(exceptionMessage);
			e.printStackTrace();
		}
		
		return result;
	}
	
	/*
	 * Execute une requête paramètré par exemple UPDATE
	 * @param query la requête paramétré SQL que l'on souhaite executer
	 * @param exceptionMessage un message à afficher en console lorsqu'une exception est déclenché
	 * @return le nombre de résultat affecté par la requête ou un identifiant si la requête c'est INSERT INTO
	 */
	public int executeUpdate(PreparedStatement ps, String exceptionMessage)
	{
		int result = 0;
		ResultSet resultId = null;
		try
		{
			result = ps.executeUpdate();
			
			resultId = ps.getGeneratedKeys(); 
			if(result == 0)
				throw new SQLException();
			else
			{
				if(resultId.next())
				{
					result = resultId.getInt(1);
				}
			}
		}
		catch(SQLException e)
		{
			if(result == 0)
				System.err.println(exceptionMessage);
			e.printStackTrace();
		}
		finally
		{
			DAOFactory.getInstance().close(resultId);
		}
		
		return result;
	}
	
	/*
	 * ATTENTION : Ne fonctionne pas, ne surtout pas utiliser, sinon bug assuré
	 * Il faut supprimer cette méthode qui ne sert strictement à rien :p
	 * L'ID de l'insert into est récupéré à la volé par la méthode executeUpdate 
	 */
	@Deprecated
	public int lastID()
	{
		String query = "SELECT LAST_INSERT_ID() as ID;";
		
		ResultSet result = executeQuery(query, "LASTID");
		int lastID = 0;
		
		try
		{
			if(result.next())
			{
				lastID = result.getInt("ID");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		System.out.println(">>>>>>>>lastID<<<<<<<<<<< " + lastID);
		
		
		return lastID;
	}
}
