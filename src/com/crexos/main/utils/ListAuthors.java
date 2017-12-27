package com.crexos.main.utils;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.dao.DAOFactory;
import com.crexos.model.utils.Redirect;

public class ListAuthors extends AbstractAction {

	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		request.setAttribute("title", "Catalogue Auteur");
		request.setAttribute("authors", DAOFactory.getInstance().getAuthorDAO().getAll());
		
		return new Redirect(false, "authors");
	}

}
