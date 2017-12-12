package com.crexos.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.crexos.model.beans.Author;
import com.crexos.model.utils.Country;

public class AuthorDAOImpl extends AbstractDAO implements AuthorDAO
{
	private final String COLUMN_ID = "id";
	private final String COLUMN_FIRSTNAME = "firstname";
	private final String COLUMN_LASTNAME = "lastname";
	private final String COLUMN_NATIVE_COUNTRY = "native_country";

	public AuthorDAOImpl(){}

	@Override
	public Author getById(int id)
	{
		Author author = new Author();
		String query = "SELECT * FROM Author WHERE id=" + id;

		try
		{
			ResultSet result = executeQuery(query, "Impossible de récupéter un auteur par ID");

			if(result.next())
			{
				author.setId(result.getInt(COLUMN_ID));
				author.setFirstname(result.getString(COLUMN_FIRSTNAME));
				author.setLastName(result.getString(COLUMN_LASTNAME));
				author.setNativeCountry(Country.valueOf(result.getString(COLUMN_NATIVE_COUNTRY)));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		return author;
	}

	@Override
	public List<Author> getAll()
	{
		List<Author> authors = new ArrayList<Author>();

		String query = "SELECT * FROM Author";

		try
		{			
			ResultSet result = executeQuery(query, "Impossible de récupéter liste des auteurs");

			while (result.next())
			{

				Author author = new Author();
				author.setId(result.getInt(COLUMN_ID));
				author.setFirstname(result.getString(COLUMN_FIRSTNAME));
				author.setLastName(result.getString(COLUMN_LASTNAME));
				author.setNativeCountry(Country.valueOf(result.getString(COLUMN_NATIVE_COUNTRY)));

				authors.add(author);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return authors;
	}

	@Override
	public void create(Author author)
	{
		String query = "INSERT INTO Author (firstname, lastname, native_country) VALUES (" +
				"'" + author.getFirstName() + "', " +
				author.getLastName() + ", " +
				author.getNativeCountry() + ", " + "') " ;


		executeUpdate(query, "Aucune auteur créé");
	}

	@Override
	public void update(Author author)
	{
		String query = "UPDATE Author " +
				"SET firstname = '" + author.getFirstName()+ "', " +
				"lastname = " + author.getLastName() + ", " +
				"native_country = " + author.getNativeCountry() + ", " +
				"WHERE id = " + author.getId() + " ";

		executeUpdate(query, "Aucune MAJ auteur effectuée");
	}

	@Override
	public void delete(int id)
	{
		String query = "DELETE FROM Author WHERE id=" + id;
		
		executeUpdate(query, "Aucun auteur a été supprimé");
	}
}
