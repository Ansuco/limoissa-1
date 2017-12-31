package com.crexos.main.utils;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.dao.BookDAO;
import com.crexos.model.dao.DAOFactory;
import com.crexos.model.utils.Redirect;

public class CatalogAction extends AbstractAction
{
	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		Redirect redirect = new Redirect(false, "catalog");
		request.setAttribute("title", "Librairie Limoissa");
		
		if(request.getMethod().equals("GET"))
		{
			if(request.getParameter("sort") != null && !request.getParameter("sort").isEmpty() && request.getParameter("mode") != null && !request.getParameter("mode").isEmpty())
			{
				String sort = request.getParameter("sort");
				String mode= request.getParameter("mode");
				request.setAttribute("books", ((BookDAO)DAOFactory.getInstance().getBookDAO()).getAllSortedBy(sort, mode));
			}
			else
			{
				request.setAttribute("books", DAOFactory.getInstance().getBookDAO().getAll());
			}
		}

		return redirect;
	}
}
