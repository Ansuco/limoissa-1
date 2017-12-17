package com.crexos.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.crexos.model.beans.Author;
import com.crexos.model.beans.Book;

public class BookDAOImpl extends AbstractDAO implements BookDAO
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
		String query = "SELECT * FROM Book b JOIN Authors_Books ab ON ab.book_id = b.id WHERE id=" + id;

		try
		{
			ResultSet result = executeQuery(query, "Impossible de récupéter un livre par ID");

			while(result.next())
			{
				Author author = DAOFactory.getInstance().getAuthorDAO().getById(result.getInt("author_id"));

				if(result.isFirst())
				{
					book.setId(result.getInt(COLUMN_ID));
					book.setTitle(result.getString(COLUMN_TITLE));
					book.setAvailability(result.getBoolean(COLUMN_AVAILABILITY));
					book.setPrice(result.getFloat(COLUMN_PRICE));
					book.setOverview(result.getString(COLUMN_OVERVIEW));
				}

				book.addAuthor(author);
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
	
	public int exist(Book book)
	{
		String query = "SELECT id FROM Book WHERE title = '" + book.getTitle() + "'";
		ResultSet result = executeQuery(query, "Impossible de vrifier si un livre existe");
		
		int bookID = 0;
		
		try
		{
			if(result.next())
				bookID = result.getInt(1);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return bookID;
	}
	

	@Override
	public List<Book> getAll()
	{
		List<Book> books = new ArrayList<Book>();

		String query = "SELECT * FROM Book b INNER JOIN Authors_books ab ON ab.book_id = b.id";

		try
		{			
			ResultSet result = executeQuery(query, "Impossible de récupéter liste de livre");
			Book book = null;
			while (result.next())
			{
				int idbook = result.getInt(COLUMN_ID);
				Author author = DAOFactory.getInstance().getAuthorDAO().getById(result.getInt("author_id"));

				if(!books.stream().anyMatch(b -> b.getId() == idbook))
				{
					book = new Book();
					book.setId(idbook);
					book.setTitle(result.getString(COLUMN_TITLE));
					book.setAvailability(result.getBoolean(COLUMN_AVAILABILITY));
					book.setOverview(result.getString(COLUMN_OVERVIEW));
					book.setPrice(result.getFloat(COLUMN_PRICE));

					book.addAuthor(author);
					books.add(book);
				}
				else
					books.stream().filter(b -> b.getId() == idbook).findFirst().get().addAuthor(author);
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
	public int create(Book book)
	{
		
		String query = "INSERT INTO Book (title, availability, price, overview) VALUES (" +
				"'" + book.getTitle() + "', " +
				book.getAvailability() + ", " +
				book.getPrice() + ", " +
				"'" + book.getOverview() + "') " ;
		
		int bookID = exist(book);
		
		if(bookID <= 0)
			bookID = executeUpdate(query, "Aucune livre créé");
		
		if(bookID != 0)
		{
			if(book.getAuthors().size() > 0)
			{
				for(Author author : book.getAuthors())
				{
					int authorID;
					//Vérifie si un auteur n'existe pas dans la BDD avant de le crer, si il existe on récupère son ID pour la jointure avec le livre
					
					if(DAOFactory.getInstance().getAuthorDAO().getAll().stream().anyMatch(a -> (a.getFirstName().equals(author.getFirstName()) && a.getLastName().equals(author.getLastName()) && a.getNativeCountry().name().equals(author.getNativeCountry().name()))))
					{
						authorID = DAOFactory.getInstance().getAuthorDAO().getAll().stream().filter(a -> (a.getFirstName().equals(author.getFirstName()) && a.getLastName().equals(author.getLastName()) && a.getNativeCountry().name().equals(author.getNativeCountry().name()))).findFirst().get().getId();
					}
					else
						authorID = DAOFactory.getInstance().getAuthorDAO().create(author);
					joinAuthor(bookID, authorID);
				}
			}
		}
		
		return bookID;
	}

	@Override
	public void update(Book book)
	{
		String query = "UPDATE Book " +
				"SET title = '" + book.getTitle() + "', " +
				"availability = " + book.getAvailability() + ", " +
				"price = " + book.getPrice() + ", " +
				"overview = '" + book.getOverview() + "' " +
				"WHERE id = " + book.getId() + " ";

		executeUpdate(query, "Aucune MAJ livre effectuée");
	}

	@Override
	public void delete(int id)
	{
		String query = "DELETE FROM Book WHERE id=" + id;

		executeUpdate(query, "Aucun livre a été supprimé");
	}

	public boolean joinAuthor(int book, int author)
	{
		String query = "INSERT INTO authors_books (author_id, book_id) VALUES (" +
				+ author + ", " +
				book + ") " ;
		return (executeUpdate(query, "Aucune Jointure de livre-auteur créé") == 0 ? false : true);
	}
}
