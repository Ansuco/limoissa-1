package com.crexos.main.utils;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.dao.DAOFactory;
import com.crexos.model.utils.Redirect;

public class DeleteAuthor extends AbstractAction
{

	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		if(!isAdmin(request))
			return new Redirect(true, "logout");
		
		if(request.getMethod().equals("POST"))
		{
			DAOFactory.getInstance().getAuthorDAO().delete(Integer.parseInt(request.getParameter("author-id")));
		}
		return new Redirect(true, "authors");
	}

}
