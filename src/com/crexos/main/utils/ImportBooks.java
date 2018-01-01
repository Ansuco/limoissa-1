package com.crexos.main.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.crexos.model.beans.Author;
import com.crexos.model.beans.Book;
import com.crexos.model.dao.DAOFactory;
import com.crexos.model.utils.Country;
import com.crexos.model.utils.Redirect;

public class ImportBooks extends AbstractAction
{
	private List<Book> books;


	@Override
	public Redirect executeAction(HttpServletRequest request)
	{
		Redirect redirect = new Redirect(false, "importbooks");

		if(!isAdmin(request))
			return new Redirect(true, "logout");
		
		if(request.getMethod().equals("POST"))
		{
			File file = null;
			int maxFileSize = 5000 * 1024;
			int maxMemSize = 5000 * 1024;
			ServletContext context = request.getServletContext();
			String filePath = context.getInitParameter("file-upload");

			// Verify the content type
			String contentType = request.getContentType();

			if ((contentType.indexOf("multipart/form-data") >= 0)) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// maximum size that will be stored in memory
				factory.setSizeThreshold(maxMemSize);

				// Location to save data that is larger than maxMemSize.
				factory.setRepository(new File("c:\\temp"));

				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);

				// maximum file size to be uploaded.
				upload.setSizeMax( maxFileSize );

				try { 
					// Parse the request to get file items.
					List<FileItem> fileItems = upload.parseRequest(request);

					// Process the uploaded file items
					Iterator<FileItem> i = fileItems.iterator();

					while ( i.hasNext () ) {
						FileItem fi = (FileItem)i.next();
						if ( !fi.isFormField () ) {
							// Get the uploaded file parameters
							String fileName = fi.getName();

							// Write the file
							if( fileName.lastIndexOf("\\") >= 0 ) {
								file = new File( filePath + 
										fileName.substring( fileName.lastIndexOf("\\"))) ;
							} else {
								file = new File( filePath + 
										fileName.substring(fileName.lastIndexOf("\\")+1)) ;
							}
							fi.write(file) ;

						}
					}
				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}
			}

			parseDocument(file);

			if(books != null)
			{
				for(Book book : books)
				{
					DAOFactory.getInstance().getBookDAO().create(book);
				}
			}

			redirect = new Redirect(true, "books");
			file.deleteOnExit();
		}

		return redirect;
	}


	private void parseDocument(File file)
	{
		if(file != null)
		{
			try
			{
				books = new ArrayList<Book>();

				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();

				Document doc = builder.parse(file);

				doc.getDocumentElement().normalize();

				NodeList nodeList = doc.getElementsByTagName("book");

				for (int i = 0; i < nodeList.getLength(); i++)
				{
					Node node = nodeList.item(i);

					if (node.getNodeType() == Node.ELEMENT_NODE)
					{
						Element element = (Element) node;

						Book book = new Book();

						if(element.getElementsByTagName("title").item(0) != null && !element.getElementsByTagName("title").item(0).getTextContent().equals(""))
							book.setTitle(element.getElementsByTagName("title").item(0).getTextContent());

						if(element.getElementsByTagName("overview").item(0) != null && !element.getElementsByTagName("overview").item(0).getTextContent().equals(""))
							book.setOverview(element.getElementsByTagName("overview").item(0).getTextContent());

						if(element.getElementsByTagName("price").item(0) != null && !element.getElementsByTagName("price").item(0).getTextContent().equals(""))
							book.setPrice(Float.parseFloat(element.getElementsByTagName("price").item(0).getTextContent()));

						if(element.getElementsByTagName("availability").item(0) != null && !element.getElementsByTagName("availability").item(0).getTextContent().equals(""))
							book.setAvailability(Boolean.parseBoolean(element.getElementsByTagName("availability").item(0).getTextContent()));

						NodeList authorList = ((Element)node).getElementsByTagName("author");//TODO affiner le résultat que ça prenne par book

						for (int j = 0; j < authorList.getLength(); j++)
						{
							Node nodej = authorList.item(j);

							if (nodej.getNodeType() == Node.ELEMENT_NODE)
							{

								Element elementj = (Element) nodej;

								Author author = new Author();

								if(elementj.getElementsByTagName("firstname").item(0) != null && !element.getElementsByTagName("firstname").item(0).getTextContent().equals(""))
									author.setFirstname(elementj.getElementsByTagName("firstname").item(0).getTextContent());

								if(elementj.getElementsByTagName("lastname").item(0) != null && !element.getElementsByTagName("lastname").item(0).getTextContent().equals(""))
									author.setLastName(elementj.getElementsByTagName("lastname").item(0).getTextContent());

								try
								{
									if(element.getElementsByTagName("native-country").item(0) != null && !element.getElementsByTagName("native-country").item(0).getTextContent().equals(""))
										author.setNativeCountry(Country.valueOf(elementj.getElementsByTagName("native-country").item(0).getTextContent()));
								}
								catch(IllegalArgumentException e)
								{
									author.setNativeCountry(Country.FRANCE);
								}

								book.addAuthor(author);
							}
						}

						books.add(book);
					}                          
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
