package com.crexos.main.utils;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.dao.BookDAO;
import com.crexos.model.dao.DAOFactory;
import com.crexos.model.utils.Redirect;

public class DeleteJoinAuthorBook extends AbstractAction
{
	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		String authorId = request.getParameter("author-id");
		String bookId = request.getParameter("book-id");

		if(request.getMethod().equals("POST"))
		{
			((BookDAO)DAOFactory.getInstance().getBookDAO()).deleteJoinAuthorBook(Integer.parseInt(authorId), Integer.parseInt(bookId));
		}

		return new Redirect(true,"books/edit?book-id=" + Integer.parseInt(bookId));
	}
}
