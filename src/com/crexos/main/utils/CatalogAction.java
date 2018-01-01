package com.crexos.main.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.beans.Book;
import com.crexos.model.dao.BookDAO;
import com.crexos.model.dao.DAOFactory;
import com.crexos.model.utils.Redirect;

public class CatalogAction extends AbstractAction
{
	private final int MAX_PER_PAGE = 10;
	
	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		Redirect redirect = new Redirect(false, "catalog");
		request.setAttribute("title", "Librairie Limoissa");
		List<Book> books = null;
		String sort = "";
		String mode = "";
		int recordsPerPage = MAX_PER_PAGE;
		int page = 1;
		try
		{page = (request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1);}
		catch(NumberFormatException e){}
		
		if(request.getMethod().equals("GET"))
		{
			if(request.getParameter("sort") != null && !request.getParameter("sort").isEmpty() && request.getParameter("mode") != null && !request.getParameter("mode").isEmpty())
			{
				sort = request.getParameter("sort");
				mode = request.getParameter("mode");
				books = ((BookDAO)DAOFactory.getInstance().getBookDAO()).getAllSortedBy(sort, mode, (page - 1) * recordsPerPage, recordsPerPage);
			}
			else
			{
				books = ((BookDAO)DAOFactory.getInstance().getBookDAO()).getAll((page - 1) * recordsPerPage, recordsPerPage);
			}
		}
		request.setAttribute("books", books);
		
		int noOfRecords = 1;
		int noOfPages = 1;		
		if(books != null)
		{
			noOfRecords = ((BookDAO)DAOFactory.getInstance().getBookDAO()).getSize();
			noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		}

		request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
		
        request.setAttribute("sort", sort);
        request.setAttribute("mode", mode);
        
		return redirect;
	}
}
