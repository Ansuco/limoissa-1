package com.crexos.main.utils;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.beans.Author;
import com.crexos.model.beans.Book;
import com.crexos.model.dao.DAOFactory;
import com.crexos.model.utils.Country;
import com.crexos.model.utils.Redirect;

public class AddBook extends AbstractAction
{

	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		Redirect redirect = new Redirect();
		
		request.setAttribute("title", "Ajouter un livre");
		
		if(request.getMethod().equals("POST"))
		{
			Book book = new Book(request.getParameter("book-title"), "résumé absent", true, Float.parseFloat(request.getParameter("book-price")), new HashSet<Author>());

			book.getAuthors().add(new Author(
					request.getParameter("author-firstname"),
					"",
					Country.FRANCE
					));

			DAOFactory.getInstance().getBookDAO().create(book);
		}
//		EntityManager em = JpaUtil.getEntityManager();
//		EntityTransaction transaction = em.getTransaction();
//		
//		try
//		{
//			transaction.begin();
//			em.persist(book);
//			transaction.commit();
//		}
//		catch(RollBackException e)
//		{
//			transaction.rollback();
//		}
		
		return redirect;
	}

}
