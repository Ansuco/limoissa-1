package com.crexos.main.utils;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.beans.User;
import com.crexos.model.utils.Redirect;

public abstract class AbstractAction
{
	public abstract Redirect executeAction(HttpServletRequest request);
	
	public boolean isAdmin(HttpServletRequest request)
	{
		if(request.getSession().getAttribute("user") == null)
			return false;
		
		User user = null;
		try
		{
			user = (User)request.getSession().getAttribute("user");
		}
		catch(ClassCastException e)
		{
			return false;
		}
		
		if(user != null && user.getRole().equals("ADMIN"))
			return true;
		else
			return false;
	}
}
