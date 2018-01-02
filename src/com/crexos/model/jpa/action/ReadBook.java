package com.crexos.model.jpa.action;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import com.crexos.main.utils.AbstractAction;
import com.crexos.model.beans.Book;
import com.crexos.model.jpa.JpaUtil;
import com.crexos.model.utils.Redirect;

public class ReadBook extends AbstractAction
{
	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		Redirect redirect = new Redirect(false, "books:read");
		EntityManager em = JpaUtil.getEntityManager();

		Query query= em.createQuery("SELECT b FROM Book b WHERE b.id=:idBook");
		query.setParameter("idBook", Integer.parseInt(request.getParameter("book-id")));
		Book book =(Book)query.getSingleResult();

		request.setAttribute("title", "Afficher un livre");

		request.setAttribute("book", book);

		return redirect;
	}
}
