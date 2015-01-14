package org.mentoring.book.service;

import java.util.List;

import org.mentoring.book.form.Book;

public interface BookService {

	void addBook(Book book);

	List<Book> listBook();

	void removeBook(Integer id);
}