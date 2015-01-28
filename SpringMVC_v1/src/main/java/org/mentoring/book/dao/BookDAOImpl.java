package org.mentoring.book.dao;

import java.util.List;

 
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.mentoring.book.form.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 
@Repository
public class BookDAOImpl implements BookDAO {
 
	static final Logger logger = Logger.getLogger(BookDAOImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
 
	public void addBook(Book book) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(book);
			session.getTransaction().commit();
			logger.info("book " + book.getTitle() + " has been saved");
		} catch (Exception e) {
			logger.error("Exception :" + e.getCause());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
		}
	}
 
	@SuppressWarnings("unchecked")
	public List<Book> listBook() {

		Session session = null;
		try {
			session = sessionFactory.openSession();
			return session.createQuery("from Book").list();
		} catch (Exception e) {
			logger.error("Exception :" + e.getCause());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
		}
		return null;
	}
 
	public void removeBook(Integer id) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Book book = (Book) session.load(Book.class, id);
			if (null != book) {
				session.delete(book);
			}
			session.getTransaction().commit();
			logger.info("book " + book.getTitle() + " has been deleted");
		} catch (Exception e) {
			logger.error("Exception :" + e.getCause());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
		}

	}
}