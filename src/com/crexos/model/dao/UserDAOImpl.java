package com.crexos.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.crexos.model.beans.User;

public class UserDAOImpl extends AbstractDAO implements UserDAO
{	
	private final String COLUMN_ID = "id";
	private final String COLUMN_PSEUDO = "pseudo";
	private final String COLUMN_PASSWORD = "password";
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

		return users;
	}

	@Override
	public void create(User user)
	{	
		String query = "INSERT INTO User (pseudo, password, firstname, lastname, email, role) VALUES (" +
				"'" + user.getPseudo() + "', " +
				"'" + user.getPassword() + "', " +
				"'" + user.getFirstname() + "', " +
				"'" + user.getLastname() + "', " +
				"'" + user.getEmail() + "', " +
				"'" + user.getRole() + ", " + "') " ;
		
		executeUpdate(query, "Aucune auteur créé");
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
