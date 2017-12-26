package com.crexos.main.utils;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.dao.DAOFactory;

public class DeleteBook extends AbstractAction
{
	@Override
	public boolean executeAction(HttpServletRequest request)
	{
		DAOFactory.getInstance().getBookDAO().delete(Integer.parseInt(request.getParameter("book-id")));
		
		return true;
	}
}
