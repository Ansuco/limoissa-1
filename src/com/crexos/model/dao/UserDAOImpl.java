package com.crexos.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.crexos.model.beans.User;
import com.crexos.model.utils.SHA1;

/*
 *  L'implémentation concrètre de User pour la persistence en base de donnée
 */
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

	/*
	 * @see com.crexos.model.dao.InterfaceDAO#getById(int)
	 */
	@Override
	public User getById(int id)
	{
		String query = "SELECT * FROM User WHERE id=?";

		User user = null;
		PreparedStatement ps = null;
		ResultSet resultData = null;
		try
		{
			ps = DAOFactory.getInstance().getPreparedStatement(query);
			ps.setInt(1, id);

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
			System.err.println("Impossible de préparer la requête getById utilisateur");
			e.printStackTrace();
		}
		finally
		{
			DAOFactory.getInstance().close(resultData, ps);
		}


		return user;
	}

	/*
	 * @see com.crexos.model.dao.UserDAO#getByPseudoPass(java.lang.String, java.lang.String)
	 */
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

			resultData = executeQuery(ps, "Impossible de récupéter un utilisateur par Pseudo&Password");

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

/*
 * @see com.crexos.model.dao.InterfaceDAO#getAll()
 */
	@Override
	public List<User> getAll()
	{
		List<User> users = new ArrayList<User>();

		String query = "SELECT * FROM User";

		PreparedStatement ps = null;
		ResultSet resultData = null;

		try
		{			
			ps = DAOFactory.getInstance().getPreparedStatement(query);
			resultData = executeQuery(ps, "Impossible de récupéter la liste des utilisateurs");

			if(resultData!= null)
				while (resultData.next())
				{
					User user = new User();
					user.setId(resultData.getInt(COLUMN_ID));
					user.setPseudo(resultData.getString(COLUMN_PSEUDO));
					user.setPassword(resultData.getString(COLUMN_PASSWORD));
					user.setFirstname(resultData.getString(COLUMN_FIRSTNAME));
					user.setLastname(resultData.getString(COLUMN_LASTNAME));
					user.setEmail(resultData.getString(COLUMN_NATIVE_EMAIL));
					user.setRole(resultData.getString(COLUMN_NATIVE_ROLE));

					users.add(user);
				}
		}
		catch (SQLException e)
		{
			System.err.println("Impossible de préparer la requête getAll utilisateur");
			e.printStackTrace();
		}
		finally
		{
			DAOFactory.getInstance().close(resultData, ps);
		}

		return users;
	}

	/*
	 * @see com.crexos.model.dao.InterfaceDAO#create(java.lang.Object)
	 */
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

	/*
	 * @see com.crexos.model.dao.InterfaceDAO#update(java.lang.Object)
	 */
	@Override
	public void update(User user)
	{
		String query = "UPDATE USER SET pseudo = ?, firstname = ?, lastname = ?, email = ? WHERE id = ?";	
		
		PreparedStatement ps = null;
		try
		{
			ps = DAOFactory.getInstance().getPreparedStatement(query);
			ps.setString(1, user.getPseudo());
			ps.setString(2, user.getFirstname());
			ps.setString(3, user.getLastname());
			ps.setString(4, user.getEmail());
			ps.setInt(5, user.getId());
			
			executeUpdate(ps, "Aucune MAJ utilisateur effectuée");
		}
		catch(SQLException e)
		{
			System.err.println("Impossible de préparer la requête MAJ utilisateur");
			e.printStackTrace();
		}
		finally
		{
			DAOFactory.getInstance().close(ps);
		}
	}

	/*
	 * @see com.crexos.model.dao.InterfaceDAO#delete(int)
	 */
	@Override
	public void delete(int id)
	{
		String query = "DELETE FROM User WHERE id=?";

		PreparedStatement ps = null;
		try
		{
			ps = DAOFactory.getInstance().getPreparedStatement(query);
			ps.setInt(1, id);
			
			executeUpdate(ps, "Aucun utilisateur supprimé");
		}
		catch(SQLException e)
		{
			System.err.println("Impossible de préparer la requête delete utilisateur");
			e.printStackTrace();
		}
		finally
		{
			DAOFactory.getInstance().close(ps);
		}
	}

	/*
	 * @see com.crexos.model.dao.InterfaceDAO#exist(java.lang.Object)
	 */
	@Override
	public int exist(User obj) {return 0;}
}
