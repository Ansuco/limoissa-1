package com.crexos.main.utils;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.beans.Book;
import com.crexos.model.dao.DAOFactory;
import com.crexos.model.utils.Redirect;

public class EditBook extends AbstractAction
{
	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		Book book = DAOFactory.getInstance().getBookDAO().getById(Integer.parseInt(request.getParameter("book-id")));
		Redirect redirect = new Redirect(false, "books:edit");
		request.setAttribute("title", "Editer un livre");
		
		if(request.getMethod().equals("POST"))
		{
			book.setTitle(request.getParameter("book-title"));
			book.setPrice(Float.parseFloat(request.getParameter("book-price")));
			book.setOverview(request.getParameter("book-overview"));			
			book.setAvailability((request.getParameter("book-availability") == null ? false: true));
			
			DAOFactory.getInstance().getBookDAO().update(book);
			redirect = new Redirect(true, "books/read?book-id=" + book.getId());
		}
		
		request.setAttribute("book", book);
		
		return redirect;
	}
}
