package com.crexos.main.utils;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.dao.DAOFactory;
import com.crexos.model.utils.Redirect;

public class DeleteBook extends AbstractAction
{
	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		DAOFactory.getInstance().getBookDAO().delete(Integer.parseInt(request.getParameter("book-id")));
		
		return new Redirect(true, "books");
	}
}
