package com.crexos.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.crexos.model.beans.User;
import com.crexos.model.utils.SHA1;

public class UserDAOImpl extends AbstractDAO implements UserDAO
{	
	private final String COLUMN_ID = "id";
	private final String COLUMN_PSEUDO = "pseudo";
	private final String COLUMN_PASSWORD = "passwd";
	private final String COLUMN_FIRSTNAME = "firstname";
	private final String COLUMN_LASTNAME = "lastname";
	private final String COLUMN_NATIVE_EMAIL = "email";
	private final String COLUMN_NATIVE_ROLE = "role";


	public UserDAOImpl(){}

	@Override
	public User getById(int id)
	{
		User user = new User();
		String query = "SELECT * FROM User WHERE id=" + id;

		try
		{
			ResultSet result = executeQuery(query, "Impossible de récupéter un utilisateur par ID");

			if(result.next())
			{
				user.setId(result.getInt(COLUMN_ID));
				user.setPseudo(result.getString(COLUMN_PSEUDO));
				user.setPassword(result.getString(COLUMN_PASSWORD));
				user.setFirstname(result.getString(COLUMN_FIRSTNAME));
				user.setLastname(result.getString(COLUMN_LASTNAME));
				user.setEmail(result.getString(COLUMN_NATIVE_EMAIL));
				user.setRole(result.getString(COLUMN_NATIVE_ROLE));

			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DAOFactory.getInstance().close();
		}

		return user;
	}

	@Override
	public User getByPseudoPass(String pseudo, String password)
	{
		String query = "SELECT * FROM User WHERE pseudo=? AND passwd=?";
		
		User user = null;
		PreparedStatement ps = null;
		ResultSet resultData = null;
		try
		{
			ps = DAOFactory.getInstance().getPreparedStatement(query);
			ps.setString(1, pseudo);
			ps.setString(2, SHA1.encryptPassword(password));
			
			resultData = executeQuery(ps, "Impossible de récupéter un utilisateur par ID");

			if(resultData != null && resultData.next())
			{
				user = new User();
				user.setId(resultData.getInt(COLUMN_ID));
				user.setPseudo(resultData.getString(COLUMN_PSEUDO));
				user.setPassword(resultData.getString(COLUMN_PASSWORD));
				user.setFirstname(resultData.getString(COLUMN_FIRSTNAME));
				user.setLastname(resultData.getString(COLUMN_LASTNAME));
				user.setEmail(resultData.getString(COLUMN_NATIVE_EMAIL));
				user.setRole(resultData.getString(COLUMN_NATIVE_ROLE));

			}
		}
		catch(SQLException e)
		{
			System.err.println("Impossible de préparer la requête getByPseudoPass utilisateur");
			e.printStackTrace();
		}
		finally
		{
			DAOFactory.getInstance().close(resultData, ps);
		}

		return user;
	}
	
	
	@Override
	public List<User> getAll()
	{
		List<User> users = new ArrayList<User>();

		String query = "SELECT * FROM User";

		try
		{			
			ResultSet result = executeQuery(query, "Impossible de récupéter liste des utilisateurs");

			while (result.next())
			{
				User user = new User();
				user.setId(result.getInt(COLUMN_ID));
				user.setPseudo(result.getString(COLUMN_PSEUDO));
				user.setPassword(result.getString(COLUMN_PASSWORD));
				user.setFirstname(result.getString(COLUMN_FIRSTNAME));
				user.setLastname(result.getString(COLUMN_LASTNAME));
				user.setEmail(result.getString(COLUMN_NATIVE_EMAIL));
				user.setRole(result.getString(COLUMN_NATIVE_ROLE));

				users.add(user);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DAOFactory.getInstance().close();
		}

		return users;
	}

	@Override
	public int create(User user)
	{	
		String query = "INSERT INTO User (pseudo, passwd, firstname, lastname, email) VALUES (?, ?, ?, ?, ?)" ;
		
		PreparedStatement ps = null;
		int userId = 0;
		try
		{
			ps = DAOFactory.getInstance().getPreparedStatement(query);
			ps.setString(1, user.getPseudo());
			ps.setString(2, SHA1.encryptPassword(user.getPassword()));
			ps.setString(3, user.getFirstname());
			ps.setString(4, user.getLastname());
			ps.setString(5, user.getEmail());
			
			userId = executeUpdate(ps, "Aucun utilisateur créé");
		}
		catch(SQLException e)
		{
			System.err.println("Impossible de préparer la requête create utilisateur");
			e.printStackTrace();
		}
		finally
		{
			DAOFactory.getInstance().close(ps);
		}

		return userId;
	}

	@Override
	public void update(User user)
	{
		String query = "UPDATE USER " +
				"SET pseudo = '" + user.getPseudo() + "', " +
				"password = " + user.getPassword() + ", " +
				"firstname = " + user.getFirstname() + ", " +
				"lastname = " + user.getLastname() + ", " +
				"email = " + user.getEmail() + ", " +
				"role = " + user.getRole() + ", " +
				"WHERE id = " + user.getId() + " ";

		executeUpdate(query, "Aucune MAJ utilisateur effectuée");
	}

	@Override
	public void delete(int id)
	{
		String query = "DELETE FROM User WHERE id=" + id;
		
		executeUpdate(query, "Aucun utilisateur a été supprimé");
	}
}
