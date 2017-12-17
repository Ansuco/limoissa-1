package com.crexos.main.utils;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.beans.Author;
import com.crexos.model.beans.Book;
import com.crexos.model.dao.DAOFactory;
import com.crexos.model.utils.Country;

public class AddBook extends AbstractAction
{

	@Override
	public void executeAction(HttpServletRequest request)
	{
		Book book = new Book(request.getParameter("book-title"), "résumé absent", true, Float.parseFloat(request.getParameter("book-price")), new HashSet<Author>());
		
		book.getAuthors().add(new Author(
				request.getParameter("author-firstname"),
				"",
				Country.FRANCE
				));
		
		DAOFactory.getInstance().getBookDAO().create(book);
	}

}
