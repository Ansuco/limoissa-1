package com.crexos.model.jpa.action;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import com.crexos.main.utils.AbstractAction;
import com.crexos.model.beans.Book;
import com.crexos.model.jpa.JpaUtil;
import com.crexos.model.utils.Redirect;

public class ListBooks extends AbstractAction
{
	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		request.setAttribute("title", "Catalogue Livre");
		if(!isAdmin(request))
			return new Redirect(true, "logout");

		EntityManager em = JpaUtil.getEntityManager();

		Query query= em.createQuery("SELECT b FROM Book b");
		List<Book> books = new ArrayList<Book>();

		books = query.getResultList();

		request.setAttribute("books", books);

		return new Redirect(false, "books");
	}

}
