package com.crexos.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crexos.model.beans.Author;
import com.crexos.model.beans.Book;

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
		String queryBooksAndAuthors = "SELECT * FROM Book b INNER JOIN Authors_books ab ON b.id = ab.author_id INNER JOIN Author a ON a.id = a.book_id";

		try
		{
			Class.forName("org.mariadb.jdbc.Driver");
			connexion = DriverManager.getConnection(URL, USER, PASSWORD);
			
			queryBooks = connexion.prepareStatement(queryBookStr);
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
				Book book = new Book(
						resultat.getInt("id"),
						resultat.getString("title"),
						resultat.getString("overview"),
						resultat.getInt("availability"),
						resultat.getFloat("price"),
						null
						);

				books.add(book);
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
