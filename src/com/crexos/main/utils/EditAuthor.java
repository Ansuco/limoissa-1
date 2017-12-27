package com.crexos.main.utils;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.beans.Author;
import com.crexos.model.dao.DAOFactory;
import com.crexos.model.utils.Country;
import com.crexos.model.utils.Redirect;

public class EditAuthor extends AbstractAction
{
	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		Author author = DAOFactory.getInstance().getAuthorDAO().getById(Integer.parseInt(request.getParameter("author-id")));
		Redirect redirect = new Redirect(false, "authors:edit");
		request.setAttribute("title", "Editer un auteur");
		
		if(request.getMethod().equals("POST"))
		{
			author.setFirstname(request.getParameter("author-firstName"));
			author.setLastName(request.getParameter("author-lastName"));
			author.setNativeCountry(Country.valueOf(request.getParameter("author-nativeCountry")));

			DAOFactory.getInstance().getAuthorDAO().update(author);
			redirect = new Redirect(true, "authors");
		}
		
		
		request.setAttribute("countries", Country.values());
		request.setAttribute("author", author);
		
		return redirect;
	}

}
