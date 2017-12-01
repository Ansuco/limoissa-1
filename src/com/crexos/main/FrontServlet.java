package com.crexos.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crexos.model.beans.Author;
import com.crexos.model.beans.Book;
import com.crexos.model.utils.Country;

/**
 * Servlet implementation class FrontServlet
 */
@WebServlet(
		name="FrontServlet",
		value="/home"
		)
public class FrontServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static final String HOME = "/WEB-INF/home.jsp";   

	private final String URL = "jdbc:mariadb://localhost:3307/library";
	private final String USER = "alexis";
	private final String PASSWORD = "eureka";
	private Connection connexion = null;

	private PreparedStatement queryBooks;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontServlet()
	{super();}


	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);

		String queryBookStr = "SELECT * FROM book";//Lister tout les livres
		String queryBooksAndAuthors = "SELECT b.id as idbook, a.id as idauthor, b.title, b.availability, b.overview, b.price, a.firstname, a.lastname, a.native_country FROM Book b INNER JOIN Authors_books ab ON ab.book_id = b.id JOIN Author a ON ab.author_id = a.id";

		try
		{
			Class.forName("org.mariadb.jdbc.Driver");
			connexion = DriverManager.getConnection(URL, USER, PASSWORD);
			
			queryBooks = connexion.prepareStatement(queryBooksAndAuthors);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Set<Author> authors = new HashSet<Author>();
		Set<Book> books = new HashSet<Book>();

		try
		{			
			/* Exécution d'une requête de lecture */
			ResultSet resultat = queryBooks.executeQuery();//Lister tout les auteurs.

			/* Récupération des données du résultat de la requête de lecture */
			while (resultat.next())
			{
				int idbook = resultat.getInt("idbook");
				
				if(!books.stream().anyMatch(b -> b.getId() == idbook))
				{
					Book book = new Book();
					book.setId(idbook);
					book.setTitle(resultat.getString("title"));
					book.setAvailability(resultat.getInt("availability"));
					book.setOverview(resultat.getString("overview"));
					book.setPrice(resultat.getFloat("price"));
					
					Author author = new Author();
					author.setId(resultat.getInt("idauthor"));
					author.setFirstname(resultat.getString("firstname"));
					author.setLastName(resultat.getString("lastname"));
					author.setNativeCountry(Country.valueOf(resultat.getString("native_country")));
					
					book.addAuthor(author);
					books.add(book);
				}
				else
				{
					Author author = new Author();
					author.setId(resultat.getInt("idauthor"));
					author.setFirstname(resultat.getString("firstname"));
					author.setLastName(resultat.getString("lastname"));
					author.setNativeCountry(Country.valueOf(resultat.getString("native_country")));
					
					books.stream().filter(b -> b.getId() == idbook).findFirst().get().addAuthor(author);
				}
			}


		}
		catch (SQLException e)
		{
			System.err.println(e.toString());
		}

		this.getServletContext().setAttribute("books", books);
		this.getServletContext().getRequestDispatcher(HOME).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}


	public void destroy()
	{
		if (connexion != null)
			try
		{
				connexion.close();
		}
		catch (SQLException ignore)
		{
			System.err.println(ignore.toString());
		}
	}
}
