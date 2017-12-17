package com.crexos.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDAO
{
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
