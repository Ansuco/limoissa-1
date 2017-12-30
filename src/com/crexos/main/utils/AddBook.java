package com.crexos.main.utils;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.beans.Author;
import com.crexos.model.beans.Book;
import com.crexos.model.dao.DAOFactory;
import com.crexos.model.utils.Country;
import com.crexos.model.utils.Redirect;

public class AddBook extends AbstractAction
{

	@SuppressWarnings("unchecked")
	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		Redirect redirect = new Redirect(false, "books:add");

		request.setAttribute("title", "Ajouter un livre");

		if(request.getMethod().equals("POST"))
		{
			if(request.getParameter("mode") != null && request.getParameter("mode").equals("tmpauthors"))
			{
				Set<Author> tmpauthors = null;
				if(request.getSession().getAttribute("tmpauthorsforbook") == null)
				{
					tmpauthors = new HashSet<Author>();
					request.getSession().setAttribute("tmpauthorsforbook", tmpauthors);
				}
				else
				{
					try
					{					
						tmpauthors = (Set<Author>)request.getSession().getAttribute("tmpauthorsforbook");
					}
					catch(ClassCastException e)
					{
						tmpauthors = new HashSet<Author>();
						request.getSession().setAttribute("tmpauthorsforbook", tmpauthors);
					}
				}

				String[] tmpAuthors = request.getParameterValues("book-add-authors");

				for(String strAuthorID : tmpAuthors)
				{
					Author tmpA = DAOFactory.getInstance().getAuthorDAO().getById(Integer.parseInt(strAuthorID));
					tmpauthors.add(tmpA);
				}
				
				redirect = new Redirect(true, "books/add");
			}
			else
			{
				Book book = new Book(request.getParameter("book-title"), "résumé absent", true, Float.parseFloat(request.getParameter("book-price")), 
						(request.getSession().getAttribute("tmpauthorsforbook") == null ? new HashSet<Author>(): (Set<Author>)request.getSession().getAttribute("tmpauthorsforbook"))
						);


				DAOFactory.getInstance().getBookDAO().create(book);
				request.getSession().setAttribute("tmpauthorsforbook", null);
				redirect = new Redirect(true, "books");
			}
		}

		request.setAttribute("authors", DAOFactory.getInstance().getAuthorDAO().getAll());

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
