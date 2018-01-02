package com.crexos.model.jpa.action;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import com.crexos.main.utils.AbstractAction;
import com.crexos.model.beans.Author;
import com.crexos.model.beans.Book;
import com.crexos.model.dao.DAOFactory;
import com.crexos.model.jpa.JpaUtil;
import com.crexos.model.utils.Redirect;

public class ListAuthors extends AbstractAction
{
	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		if(!isAdmin(request))
			return new Redirect(true, "logout");
		request.setAttribute("title", "Catalogue Auteur");
		
		EntityManager em = JpaUtil.getEntityManager();

		Query query= em.createQuery("SELECT a FROM Author a");
		List<Author> authors = new ArrayList<Author>();

		authors = query.getResultList();
		
		request.setAttribute("authors", authors);
		
		
		return new Redirect(false, "authors");
	}
}
