package org.mentoring.book.dao;

import java.util.List;

import org.mentoring.book.form.Book;

public interface BookDAO {

	void addBook(Book book);

	List<Book> listBook();

	void removeBook(Integer id);
}