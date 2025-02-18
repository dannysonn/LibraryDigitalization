package library.digitalization.dao;

import library.digitalization.models.Book;
import library.digitalization.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BookDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public BookDAO(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Book", Book.class).list();
    }

    @Transactional
    public void addBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.save(book);
    }

    @Transactional(readOnly = true)
    public Book findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, id);
    }

    @Transactional
    public void updateBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.update(book);
    }

    @Transactional
    public void assignPerson(int personId, int bookId) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, bookId);
        Person person = session.get(Person.class, personId);
        book.setOwner(person);
        person.getBookList().add(book);
    }

    @Transactional
    public void deleteBook(int bookId) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("delete from Book WHERE id = :bookId").setParameter("bookId", bookId).executeUpdate();
    }

    @Transactional
    public void freeBook(int bookId) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, bookId);
        Person person = session.get(Person.class, book.getOwner().getId());
        person.getBookList().remove(book);
        book.setOwner(null);
    }
}
