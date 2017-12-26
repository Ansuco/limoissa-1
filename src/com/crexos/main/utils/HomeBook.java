package com.crexos.main.utils;

import javax.servlet.http.HttpServletRequest;

import com.crexos.main.ActionManager;
import com.crexos.model.utils.Redirect;

public class HomeBook extends AbstractAction
{
	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		request.setAttribute("title", "Page d'accueil");
		return new Redirect(true, ActionManager.ACTION_LIST_BOOKS);
	}

}
