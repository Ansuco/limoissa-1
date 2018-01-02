package com.crexos.model.jpa.action;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import com.crexos.main.utils.AbstractAction;
import com.crexos.model.beans.Author;
import com.crexos.model.jpa.JpaUtil;
import com.crexos.model.utils.Country;
import com.crexos.model.utils.Redirect;

public class EditAuthor extends AbstractAction
{
	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		EntityManager em = JpaUtil.getEntityManager();
		Author author = em.find(Author.class, Integer.parseInt(request.getParameter("author-id")));
		Redirect redirect = new Redirect(false, "authors:edit");
		request.setAttribute("title", "Editer un auteur");
		
		if(!isAdmin(request))
			return new Redirect(true, "logout");
		
		if(request.getMethod().equals("POST"))
		{
			try
			{
				
				
				em.getTransaction().begin();
				author.setFirstname(request.getParameter("author-firstName"));
				author.setLastName(request.getParameter("author-lastName"));
				author.setNativeCountry(Country.valueOf(request.getParameter("author-nativeCountry")));
				em.getTransaction().commit();
			
				redirect = new Redirect(true, "authors/read?author-id=" + author.getId());
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
		request.setAttribute("author", author);
		
		return redirect;
	}

}
