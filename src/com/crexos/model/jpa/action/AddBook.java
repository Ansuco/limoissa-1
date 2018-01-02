package com.crexos.model.jpa.action;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import com.crexos.main.utils.AbstractAction;
import com.crexos.model.beans.Author;
import com.crexos.model.beans.Book;
import com.crexos.model.dao.DAOFactory;
import com.crexos.model.jpa.JpaUtil;
import com.crexos.model.utils.Redirect;

public class AddBook extends AbstractAction
{
	@SuppressWarnings("unchecked")
	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		Redirect redirect = new Redirect(false, "books:add");
		request.setAttribute("title", "Ajouter un livre");

		if(!isAdmin(request))
			return new Redirect(true, "logout");

		if(request.getMethod().equals("POST"))
		{
			if(request.getParameter("mode") != null && request.getParameter("mode").equals("tmpauthors"))
			{
				List<Author> tmpauthors = null;
				if(request.getSession().getAttribute("tmpauthorsforbook") == null)
				{
					tmpauthors = new ArrayList<Author>();
					request.getSession().setAttribute("tmpauthorsforbook", tmpauthors);
				}
				else
				{
					try
					{					
						tmpauthors = (List<Author>)request.getSession().getAttribute("tmpauthorsforbook");
					}
					catch(ClassCastException e)
					{
						tmpauthors = new ArrayList<Author>();
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
						(request.getSession().getAttribute("tmpauthorsforbook") == null ? new ArrayList<Author>(): (List<Author>)request.getSession().getAttribute("tmpauthorsforbook"))
						);

				EntityManager em = JpaUtil.getEntityManager();

				try
				{
					em.getTransaction().begin();
					em.persist(book);
					em.getTransaction().commit();

					redirect = new Redirect(true, "catalog");
				}catch(Exception e)
				{
					redirect = new Redirect(true, "signin");
					em.getTransaction().rollback();
					e.printStackTrace();
				}
				finally
				{					
					em.close();
				}

				request.getSession().setAttribute("tmpauthorsforbook", null);
				redirect = new Redirect(true, "books");
			}
		}

		request.setAttribute("authors", DAOFactory.getInstance().getAuthorDAO().getAll());
		return redirect;
	}
}
