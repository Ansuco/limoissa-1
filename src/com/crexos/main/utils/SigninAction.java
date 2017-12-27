package com.crexos.main.utils;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.beans.User;
import com.crexos.model.dao.DAOFactory;
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
				int userId = DAOFactory.getInstance().getUserDAO().create(user);

				if(userId > 0)
				{	
					request.getSession().setAttribute("user", user);
					redirect = new Redirect(true, "books");
				}
				else
					redirect = new Redirect(true, "signin");
			}
		}

		return redirect;
	}
}
