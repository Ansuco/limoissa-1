package com.crexos.model.jpa.action;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import com.crexos.main.utils.AbstractAction;
import com.crexos.model.beans.Book;
import com.crexos.model.dao.DAOFactory;
import com.crexos.model.jpa.JpaUtil;
import com.crexos.model.utils.Redirect;

public class EditBook extends AbstractAction
{
	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		if(!isAdmin(request))
			return new Redirect(true, "logout");


		EntityManager em = JpaUtil.getEntityManager();
		Book book = em.find(Book.class, Integer.parseInt(request.getParameter("book-id")));

		Redirect redirect = new Redirect(false, "books:edit");
		request.setAttribute("title", "Editer un livre");


		if(request.getMethod().equals("POST"))
		{
			try
			{
				em.getTransaction().begin();
				book.setTitle(request.getParameter("book-title"));
				book.setPrice(Float.parseFloat(request.getParameter("book-price")));
				book.setOverview(request.getParameter("book-overview"));			
				book.setAvailability((request.getParameter("book-availability") == null ? false: true));
				em.getTransaction().commit();


				//DAOFactory.getInstance().getBookDAO().update(book);
				redirect = new Redirect(true, "books/read?book-id=" + book.getId());
			}
			catch(Exception e)
			{
				em.getTransaction().rollback();
				e.printStackTrace();
			}
			finally
			{					
				em.close();
			}
		}

		request.setAttribute("book", book);
		request.setAttribute("authors", DAOFactory.getInstance().getAuthorDAO().getAll());

		return redirect;
	}
}
