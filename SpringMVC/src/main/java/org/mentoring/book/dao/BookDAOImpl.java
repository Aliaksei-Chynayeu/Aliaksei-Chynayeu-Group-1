package org.mentoring.book.dao;

import java.util.List;

 
import org.hibernate.SessionFactory;
import org.mentoring.book.form.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 
@Repository
public class BookDAOImpl implements BookDAO {
 
    @Autowired
    private SessionFactory sessionFactory;
 
    public void addBook(Book book) {
        sessionFactory.getCurrentSession().save(book);
    }
 
    public List<Book> listBook() {
 
        return sessionFactory.getCurrentSession().createQuery("from Book")
                .list();
    }
 
    public void removeBook(Integer id) {
        Book book = (Book) sessionFactory.getCurrentSession().load(
                Book.class, id);
        if (null != book) {
            sessionFactory.getCurrentSession().delete(book);
        }
 
    }
}