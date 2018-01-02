package com.crexos.model.jpa.action;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import com.crexos.main.utils.AbstractAction;
import com.crexos.model.beans.Author;
import com.crexos.model.jpa.JpaUtil;
import com.crexos.model.utils.Country;
import com.crexos.model.utils.Redirect;

public class AddAuthor extends AbstractAction
{
	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		Redirect redirect = new Redirect(false, "authors:add");
		request.setAttribute("title", "Ajouter un auteur");
		
		if(!isAdmin(request))
			return new Redirect(true, "logout");
		
		if(request.getMethod().equals("POST"))
		{
			Author author = new Author();
			author.setFirstname(request.getParameter("author-firstName"));
			author.setLastName(request.getParameter("author-lastName"));
			author.setNativeCountry(Country.valueOf(request.getParameter("author-nativeCountry")));

			request.setAttribute("author", author);
			
			
			EntityManager em = JpaUtil.getEntityManager();
			
			try
			{
				em.getTransaction().begin();
				em.persist(author);
				em.getTransaction().commit();
				
				redirect = new Redirect(true, "authors");
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
		
		request.setAttribute("countries", Country.values());
		
		return redirect;
	}
}
