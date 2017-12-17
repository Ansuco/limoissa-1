package com.crexos.main.utils;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.dao.DAOFactory;

public class ListBooks extends AbstractAction
{

	@Override
	public void executeAction(HttpServletRequest request)
	{
		request.setAttribute("books", DAOFactory.getInstance().getBookDAO().getAll());
	}
}
