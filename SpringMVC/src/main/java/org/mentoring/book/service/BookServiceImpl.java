package org.mentoring.book.service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.mentoring.book.dao.BookDAO;
import org.mentoring.book.form.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class BookServiceImpl implements BookService {
 
	   @Autowired
	    private BookDAO bookDAO;
	     
	    @Transactional
	    public void addBook(Book book) {
	        bookDAO.addBook(book);
	    }
	 
	    @Transactional
	    public List<Book> listBook() {
	 
	        return bookDAO.listBook();
	    }
	 
	    @Transactional
	    public void removeBook(Integer id) {
	        bookDAO.removeBook(id);
	    }
}