package com.crexos.main;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crexos.model.beans.Author;
import com.crexos.model.beans.Book;
import com.crexos.model.dao.DAOFactory;
import com.crexos.model.utils.Country;

/**
 * Servlet implementation class FrontServlet
 */
@WebServlet(
		name="FrontServlet",
		value = {"/home", "/books", "/books/add", "/books/delete", "/books/edit"}
		)
public class FrontServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static final String HOME = "/WEB-INF/home.jsp";   

	public static final String ACTION_ADD = "add";
	public static final String ACTION_DELETE = "delete";
	public static final String ACTION_EDIT = "edit";
	public static final String ACTION_LIST_BOOKS = "books";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontServlet()
	{super();}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//		PrintWriter out = response.getWriter();
		//		
		//		try {
		//			Context ctx = new InitialContext();
		//			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/library");//Utilisation de la JNDI
		//			
		//			try(Connection cnx = ds.getConnection())//Equivalent du Using de ASP.NET il faut que la classe herite de AutoCloseable
		//			{
		//				out.println("Connexion " + (cnx.isClosed()? "fermée" : "établie"));
		//			}
		//		}
		//		catch (NamingException|SQLException e)
		//		{
		//			e.printStackTrace();
		//		}
		//		out.close();	


//		Book book = new Book("Mon livre", "résumé absent", true, 34f, new HashSet<Author>());
//		
//		System.out.println("Action AddBook");
//		
//		book.getAuthors().add(new Author(
//				"Alexis",
//				"Cretinoir",
//				Country.FRANCE
//				));
//
//		DAOFactory.getInstance().getBookDAO().create(book);
//		
//		
		String actionName = getActionName(request);

		if(!actionName.equals("home"))
			ActionManager.getAction(actionName).executeAction(request);


		//this.getServletContext().setAttribute("books", DAOFactory.getInstance().getBookDAO().getAll());	
		this.getServletContext().getRequestDispatcher(HOME).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String actionName = getActionName(request);
		if(!actionName.equals("home"))
			ActionManager.getAction(actionName).executeAction(request);
		response.sendRedirect(request.getContextPath()+ "/books");

	}

	private String getActionName(HttpServletRequest request)
	{
		String uri = request.getRequestURI();
		return request.getRequestURI().substring(uri.lastIndexOf("/")+1);
	}
}
