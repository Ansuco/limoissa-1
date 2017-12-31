package com.crexos.main.utils;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.utils.Redirect;

public class LogoutAction extends AbstractAction
{
	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		request.getSession().setAttribute("user", null);
		request.getSession().invalidate();//Redondant?
		return new Redirect(true, "home");
	}

}
