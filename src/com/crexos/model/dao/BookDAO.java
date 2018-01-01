package com.crexos.model.dao;

import java.util.List;

import com.crexos.model.beans.Book;

public interface BookDAO extends InterfaceDAO<Book>
{
	Book getById(int id);
	List<Book> getAll();
	List<Book> getAll(int offset, int noOfRecords);
	List<Book> getAllSortedBy(String column, String mode);
	List<Book> getAllSortedBy(String column, String mode, int offset, int noOfRecords);
	int create(Book book);
	void update(Book book);
	void delete(int id);
	boolean deleteJoinAuthorBook(int authorId, int bookId);
	boolean joinAuthor(int bookId, int authorId);
	boolean existJoin(int bookId, int authorId);
	int getSize();
}
