package com.crexos.model.jpa.action;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import com.crexos.main.utils.AbstractAction;
import com.crexos.model.beans.User;
import com.crexos.model.jpa.JpaUtil;
import com.crexos.model.utils.Redirect;

public class SigninAction extends AbstractAction
{
	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		request.setAttribute("title", "Page d'inscription");
		
		Redirect redirect = new Redirect(false, "signin");

		if(request.getMethod().equals("POST"))
		{
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String pseudo = request.getParameter("pseudo");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			
			if(firstname != null && lastname != null && pseudo != null && password != null && email != null &&
					!firstname.isEmpty() && !lastname.isEmpty() && !pseudo.isEmpty() && !password.isEmpty() && !email.isEmpty())
			{
				User user = new User(firstname, lastname, pseudo, email, password);
				
				EntityManager em = JpaUtil.getEntityManager();
				
				try
				{
					em.getTransaction().begin();
					em.persist(user);
					em.getTransaction().commit();
					
					request.getSession().setAttribute("user", user);
					
					redirect = new Redirect(true, "books");
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
			}
		}
		
		
		
		return redirect;
	}

}
