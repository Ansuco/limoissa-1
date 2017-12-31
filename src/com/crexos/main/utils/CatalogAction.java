package com.crexos.main.utils;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.dao.DAOFactory;
import com.crexos.model.utils.Redirect;

public class CatalogAction extends AbstractAction
{
	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		request.setAttribute("title", "Librairie Limoissa");
		request.setAttribute("books", DAOFactory.getInstance().getBookDAO().getAll());
		
		return new Redirect(false, "catalog");
	}
}
