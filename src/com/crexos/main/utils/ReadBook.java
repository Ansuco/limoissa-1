package com.crexos.main.utils;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.beans.Book;
import com.crexos.model.dao.DAOFactory;
import com.crexos.model.utils.Redirect;

public class ReadBook extends AbstractAction
{
	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		Book book = DAOFactory.getInstance().getBookDAO().getById(Integer.parseInt(request.getParameter("book-id")));
		Redirect redirect = new Redirect(false, "books:read");
		request.setAttribute("title", "Afficher un livre");

		request.setAttribute("book", book);
		
		return redirect;
	}
}
