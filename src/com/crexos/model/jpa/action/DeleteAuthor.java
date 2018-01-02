package com.crexos.model.jpa.action;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import com.crexos.main.utils.AbstractAction;
import com.crexos.model.beans.Author;
import com.crexos.model.jpa.JpaUtil;
import com.crexos.model.utils.Redirect;

public class DeleteAuthor extends AbstractAction
{
	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		if(!isAdmin(request))
			return new Redirect(true, "logout");
		
		if(request.getMethod().equals("POST"))
		{
			EntityManager em = JpaUtil.getEntityManager();
			
			Author book = em.find(Author.class, Integer.parseInt(request.getParameter("author-id")));
			
			em.getTransaction().begin();
			em.remove(book);
			em.getTransaction().commit();
		}
		return new Redirect(true, "authors");
	}
}
