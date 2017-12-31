package com.crexos.main.utils;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.beans.User;
import com.crexos.model.dao.DAOFactory;
import com.crexos.model.dao.UserDAO;
import com.crexos.model.utils.Redirect;

public class LoginAction extends AbstractAction
{
	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		//SecurityConstraint for configure for admin or user role
		request.setAttribute("title", "Page de connexion");
		//TODO : encrypter mot de passe en SHA1
		Redirect redirect = new Redirect(false, "login");
		if(request.getMethod().equals("POST"))
		{
			String pseudo = request.getParameter("pseudo");
			String password = request.getParameter("password");
			
			if(pseudo != null && password != null)
			{
				User user = ((UserDAO)DAOFactory.getInstance().getUserDAO()).getByPseudoPass(pseudo, password);
				
				request.getSession().setAttribute("user", user);
				
				if(user != null)
					redirect = new Redirect(true, "catalog");
				else
					redirect = new Redirect(true, "login");
			}
		}
			
		return redirect;
	}
}
