package com.crexos.main.utils;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.utils.Redirect;

public class LogoutAction extends AbstractAction
{
	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		request.getSession().invalidate();
		return new Redirect(true, "home");
	}

}
