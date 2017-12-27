package com.crexos.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crexos.model.utils.Redirect;

/**
 * Servlet implementation class FrontServlet
 */
@WebServlet(
		name="FrontServlet",
		value = {"/home", "/logout", "/login", "/signin", 
				"/books", "/books/add", "/books/delete", "/books/edit",
				"/authors", "/authors/edit", "/authors/add", "/authors/delete"}
		)
public class FrontServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static final String HOME = "/WEB-INF/index.jsp";   

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontServlet(){super();}

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
		
//		
//		if(!actionName.equals("home"))
//			ActionManager.getAction(actionName).executeAction(request);

		String actionName = getActionName(request);
		Redirect redirect = ActionManager.getAction(actionName).executeAction(request);
		request.setAttribute("actionName", redirect.getAction());
		
		if(redirect.isRedirection())
			response.sendRedirect(request.getContextPath() + "/" + redirect.getAction());
		else	
			this.getServletContext().getRequestDispatcher(HOME).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String actionName = getActionName(request);
		Redirect redirect = ActionManager.getAction(actionName).executeAction(request);
		
		if(redirect.isRedirection())
		{
			request.setAttribute("actionName", redirect.getAction());
			response.sendRedirect(request.getContextPath() + "/" + redirect.getAction());
		}
		else
			response.sendRedirect(request.getContextPath() + "/home");
	}

	private String getActionName(HttpServletRequest request)
	{
		return request.getRequestURI().replaceAll(request.getContextPath(), "").substring(1).replace("/", ":");
	}
}
