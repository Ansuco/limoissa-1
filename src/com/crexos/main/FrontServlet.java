package com.crexos.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

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
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Set<Author> authors = new HashSet<Author>();
		Set<Book> books = new HashSet<Book>();
		
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
					
			connexion = DriverManager.getConnection(URL, USER, PASSWORD);
			
			Statement statement = connexion.createStatement();
			/* Ici, nous placerons nos requêtes vers la BDD */

			
			/* Exécution d'une requête de lecture */
			ResultSet resultat = statement.executeQuery("SELECT * FROM book;");//Lister tout les auteurs.

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
		catch (SQLException | ClassNotFoundException e)
		{
			System.err.println(e.toString());
		}
		finally
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
		
		this.getServletContext().setAttribute("books", books);
		this.getServletContext().getRequestDispatcher(HOME).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
