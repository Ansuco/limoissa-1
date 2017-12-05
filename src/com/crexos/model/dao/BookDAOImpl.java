package com.crexos.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.crexos.model.beans.Author;
import com.crexos.model.beans.Book;
import com.crexos.model.utils.Country;

public class BookDAOImpl implements BookDAO
{
	private final String COLUMN_ID = "id";
	private final String COLUMN_TITLE = "title";
	private final String COLUMN_AVAILABILITY = "availability";
	private final String COLUMN_PRICE = "price";
	private final String COLUMN_OVERVIEW = "overview";
	
	public BookDAOImpl(){}
	
	@Override
	public Book getById(int id)
	{
		Book book = new Book();
		Connection cnx = null;
		String query = "SELECT * FROM Book WHERE id=" + id;
		
		try
		{
			cnx = DAOFactory.getInstance().getConnection();
			Statement st = cnx.createStatement();
			st.executeQuery(query);
			
			ResultSet result = st.getResultSet();
			
			if(result.next())
			{
				book.setId(result.getInt(COLUMN_ID));
				book.setTitle(result.getString(COLUMN_TITLE));
				book.setAvailability(result.getBoolean(COLUMN_AVAILABILITY));
				book.setPrice(result.getFloat(COLUMN_PRICE));
				book.setOverview(result.getString(COLUMN_OVERVIEW));
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
		
		return book;
	}

	@Override
	public List<Book> getAll()
	{
		Connection cnx = null;
		Set<Author> authors = new HashSet<Author>();
		List<Book> books = new ArrayList<Book>();
		
		String query = "SELECT b.id as idbook, a.id as idauthor, b.title, b.availability, b.overview, b.price, a.firstname, a.lastname, a.native_country FROM Book b INNER JOIN Authors_books ab ON ab.book_id = b.id JOIN Author a ON ab.author_id = a.id";

		try
		{			
			cnx = DAOFactory.getInstance().getConnection();
			Statement st = cnx.createStatement();
			st.executeQuery(query);
			
			ResultSet result = st.getResultSet();

			while (result.next())
			{
				int idbook = result.getInt("idbook");
				
				if(!books.stream().anyMatch(b -> b.getId() == idbook))
				{
					Book book = new Book();
					book.setId(idbook);
					book.setTitle(result.getString(COLUMN_TITLE));
					book.setAvailability(result.getBoolean(COLUMN_AVAILABILITY));
					book.setOverview(result.getString(COLUMN_OVERVIEW));
					book.setPrice(result.getFloat(COLUMN_PRICE));
					
					Author author = new Author();
					author.setId(result.getInt("idauthor"));
					author.setFirstname(result.getString("firstname"));
					author.setLastName(result.getString("lastname"));
					author.setNativeCountry(Country.valueOf(result.getString("native_country")));
					
					book.addAuthor(author);
					books.add(book);
				}
				else
				{
					Author author = new Author();
					author.setId(result.getInt("idauthor"));
					author.setFirstname(result.getString("firstname"));
					author.setLastName(result.getString("lastname"));
					author.setNativeCountry(Country.valueOf(result.getString("native_country")));
					
					books.stream().filter(b -> b.getId() == idbook).findFirst().get().addAuthor(author);
				}
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
		
		return books;
	}

	@Override
	public void create(Book book)
	{
		Connection cnx = null;
		String query = "INSERT INTO Book (title, availability, price, overview) VALUES (" +
	    "'" + book.getTitle() + "', " +
	    book.getAvailability() + ", " +
	    book.getPrice() + ", " +
	    "'" + book.getOverview() + "') " ;
		
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
				System.err.println("Aucune livre créé");
			e.printStackTrace();
		}
		finally
		{
			DAOFactory.getInstance().close();
		}
	}

	@Override
	public void update(Book book)
	{
		Connection cnx = null;
		String query = "UPDATE Book " +
	    "SET title = '" + book.getTitle() + "', " +
	    "availability = " + book.getAvailability() + ", " +
	    "price = " + book.getPrice() + ", " +
	    "overview = '" + book.getOverview() + "' " +
	    "WHERE id = " + book.getId() + " ";
		
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
				System.err.println("Aucune MAJ livre effectuée");
			e.printStackTrace();
		}
		finally
		{
			DAOFactory.getInstance().close();
		}
	}

	@Override
	public void delete(int id)
	{
		Connection cnx = null;
		String query = "DELETE FROM Book WHERE id=" + id;
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
				System.err.println("Aucun livre a été supprimé");
			e.printStackTrace();
		}
		finally
		{
			DAOFactory.getInstance().close();
		}
	}

}
