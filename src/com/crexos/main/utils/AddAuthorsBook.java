package com.crexos.main.utils;

import javax.servlet.http.HttpServletRequest;

import com.crexos.model.dao.BookDAO;
import com.crexos.model.dao.DAOFactory;
import com.crexos.model.utils.Redirect;

public class AddAuthorsBook extends AbstractAction
{
	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		String[] joinAuthorsStr = request.getParameterValues("book-add-authors");
		int bookID = 0;
		
		if(!isAdmin(request))
			return new Redirect(true, "logout");
		
		for(String author : joinAuthorsStr)
		{
			String[] authorSplit = author.split("#");
			if(bookID == 0)
				bookID = Integer.parseInt(authorSplit[1]);
			if(!((BookDAO)DAOFactory.getInstance().getBookDAO()).existJoin(bookID, Integer.parseInt(authorSplit[0])))
				((BookDAO)DAOFactory.getInstance().getBookDAO()).joinAuthor(bookID, Integer.parseInt(authorSplit[0]));
		}
		
		return new Redirect(true, "books/edit?book-id=" + bookID);
	}

}
