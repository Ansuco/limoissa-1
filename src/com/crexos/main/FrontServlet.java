package com.crexos.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crexos.model.dao.DAOFactory;

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
		this.getServletContext().setAttribute("books", DAOFactory.getInstance().getBookDAO().getAll());	
		this.getServletContext().getRequestDispatcher(HOME).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
