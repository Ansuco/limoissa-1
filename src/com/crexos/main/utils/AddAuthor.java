package com.crexos.main.utils;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.beans.Author;
import com.crexos.model.dao.DAOFactory;
import com.crexos.model.utils.Country;
import com.crexos.model.utils.Redirect;

public class AddAuthor extends AbstractAction
{
	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		Redirect redirect = new Redirect(false, "authors:add");
		request.setAttribute("title", "Ajouter un auteur");
		
		if(!isAdmin(request))
			return new Redirect(true, "logout");
		
		if(request.getMethod().equals("POST"))
		{
			Author author = new Author();
			author.setFirstname(request.getParameter("author-firstName"));
			author.setLastName(request.getParameter("author-lastName"));
			author.setNativeCountry(Country.valueOf(request.getParameter("author-nativeCountry")));

			request.setAttribute("author", author);
			
			if(DAOFactory.getInstance().getAuthorDAO().create(author) > 0)
				redirect = new Redirect(true, "authors");
		}
		
		request.setAttribute("countries", Country.values());
		
		return redirect;
	}
}
