package com.crexos.main.utils;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.beans.Author;
import com.crexos.model.dao.DAOFactory;
import com.crexos.model.utils.Country;
import com.crexos.model.utils.Redirect;

public class ReadAuthor extends AbstractAction {

	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		Author author = DAOFactory.getInstance().getAuthorDAO().getById(Integer.parseInt(request.getParameter("author-id")));
		Redirect redirect = new Redirect(false, "authors:read");
		request.setAttribute("title", "Afficher un auteur");
		
		request.setAttribute("countries", Country.values());
		request.setAttribute("author", author);
		
		return redirect;
	}

}
