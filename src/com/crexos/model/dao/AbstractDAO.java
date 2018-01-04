package com.crexos.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Methode commune � nos AO pour executer des requ�tes en base de donn�e
 */
public abstract class AbstractDAO
{
	/*
	 * Execute une requ�te par exemple SELECT
	 * @param query la requ�te SQL que l'on souhaite executer
	 * @param exceptionMessage un message � afficher en console lorsqu'une exception est d�clench�
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
	 * Execute une requ�te param�tr� par exemple SELECT
	 * @param ps la requ�te param�tr� SQL que l'on souhaite executer
	 * @param exceptionMessage un message � afficher en console lorsqu'une exception est d�clench�
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
	 * Execute une requ�te par exemple UPDATE
	 * @param query la requ�te SQL que l'on souhaite executer
	 * @param exceptionMessage un message � afficher en console lorsqu'une exception est d�clench�
	 * @return le nombre de r�sultat affect� par la requ�te ou un identifiant si la requ�te c'est INSERT INTO
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
	 * Execute une requ�te param�tr� par exemple UPDATE
	 * @param query la requ�te param�tr� SQL que l'on souhaite executer
	 * @param exceptionMessage un message � afficher en console lorsqu'une exception est d�clench�
	 * @return le nombre de r�sultat affect� par la requ�te ou un identifiant si la requ�te c'est INSERT INTO
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
	 * ATTENTION : Ne fonctionne pas, ne surtout pas utiliser, sinon bug assur�
	 * Il faut supprimer cette m�thode qui ne sert strictement � rien :p
	 * L'ID de l'insert into est r�cup�r� � la vol� par la m�thode executeUpdate 
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
