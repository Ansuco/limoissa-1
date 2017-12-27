package com.crexos.main.utils;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.dao.DAOFactory;
import com.crexos.model.utils.Redirect;

public class ListBooks extends AbstractAction
{

	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
//		EntityManager em = JpaUtil.getEntityManager();
//		
//		//javax.persistence.query
//		Query q = em.createQuery("SELECT b FROM Book b");
//		List<Book> books = q.get
//		
		//Voir JPQl
		
		request.setAttribute("title", "Catalogue");
		request.setAttribute("books", DAOFactory.getInstance().getBookDAO().getAll());
		
		return new Redirect(false, "books");
	}
}
