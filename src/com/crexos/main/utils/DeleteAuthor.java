package com.crexos.main.utils;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.dao.DAOFactory;
import com.crexos.model.utils.Redirect;

public class DeleteAuthor extends AbstractAction
{

	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		DAOFactory.getInstance().getAuthorDAO().delete(Integer.parseInt(request.getParameter("author-id")));
		
		return new Redirect(true, "authors");
	}

}
