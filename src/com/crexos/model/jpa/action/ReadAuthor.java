package com.crexos.model.jpa.action;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import com.crexos.main.utils.AbstractAction;
import com.crexos.model.beans.Author;
import com.crexos.model.beans.Book;
import com.crexos.model.dao.DAOFactory;
import com.crexos.model.jpa.JpaUtil;
import com.crexos.model.utils.Country;
import com.crexos.model.utils.Redirect;

public class ReadAuthor extends AbstractAction
{

	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		//Author author = DAOFactory.getInstance().getAuthorDAO().getById(Integer.parseInt(request.getParameter("author-id")));
		Redirect redirect = new Redirect(false, "authors:read");
		request.setAttribute("title", "Afficher un auteur");
		
		EntityManager em = JpaUtil.getEntityManager();

		Query query= em.createQuery("SELECT a FROM Author a WHERE a.id=:idAuthor");
		query.setParameter("idAuthor", Integer.parseInt(request.getParameter("author-id")));
		Author author =(Author)query.getSingleResult();
		
		request.setAttribute("countries", Country.values());
		request.setAttribute("author", author);
		
		return redirect;
	}

}
