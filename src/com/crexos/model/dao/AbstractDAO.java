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
		finally
		{
			DAOFactory.getInstance().close();
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
			result = st.executeUpdate(query);
			
			if(result == 0)
				throw new SQLException();
		}
		catch(SQLException e)
		{
			if(result == 0)
				System.err.println(exceptionMessage);
			e.printStackTrace();
		}
		finally
		{
			DAOFactory.getInstance().close();
		}
		
		return result;
	}
}
