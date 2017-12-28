package com.crexos.model.jpa.action;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import com.crexos.main.utils.AbstractAction;
import com.crexos.model.beans.User;
import com.crexos.model.jpa.JpaUtil;
import com.crexos.model.utils.Redirect;

public class LoginAction extends AbstractAction
{
	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		request.setAttribute("title", "Page de connexion");
		Redirect redirect = new Redirect(false, "login");

		if(request.getMethod().equals("POST"))
		{
			String pseudo = request.getParameter("pseudo");
			String password = request.getParameter("password");

			if(pseudo != null && password != null)
			{
				EntityManager em = JpaUtil.getEntityManager();

				Query q = em.createQuery("SELECT u FROM User u WHERE pseudo=:ps AND password=:pwd");
				q.setParameter("ps", pseudo);
				q.setParameter("pwd", password);
				try
				{
					User user =(User)q.getSingleResult();
					request.getSession().setAttribute("user", user);
					redirect = new Redirect(true, "books");
				}
				catch(NoResultException e)
				{
					redirect = new Redirect(true, "login");
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
		}

		return redirect;
	}

}
