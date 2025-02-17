package library.digitalization.dao;

import library.digitalization.models.Book;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public BookDAO(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Book> getAllBooks() {
//        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
        return null;
    }

    public void addBook(Book book) {
//        jdbcTemplate.update("INSERT INTO Book (title, author, publicationYear) VALUES (?, ?, ?)", book.getTitle(), book.getAuthor(), book.getPublicationYear());
    }

    public Book findById(int id) {
//        return jdbcTemplate.queryForObject("SELECT * FROM Book WHERE id = ?", new BeanPropertyRowMapper<>(Book.class), id);
        return null;
    }

    public void updateBook(Book book, int id) {
//        jdbcTemplate.update("UPDATE Book SET title=?, author=?, publicationYear=? WHERE id=?", book.getTitle(), book.getAuthor(), book.getPublicationYear(), id);
    }

    public void assignPerson(int personId, int bookId) {
//        jdbcTemplate.update("UPDATE Book SET person_id = ? WHERE id = ?", personId, bookId);
    }

    public String getOwner(int bookId) {
        String sql = "SELECT Person.fullName FROM Book JOIN Person ON Book.person_id = Person.id WHERE Book.id = ?";

        try {
            return null;
//            return jdbcTemplate.queryForObject(sql, String.class, bookId);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void deleteBook(int bookId) {
        String sql = "DELETE FROM Book WHERE id = ?";

//        jdbcTemplate.update(sql, bookId);
    }

    public void freeBook(int bookId) {
//        jdbcTemplate.update("UPDATE Book SET person_id = null WHERE id = ?", bookId);
    }

    public List<Book> getBooksByPersonId(int personId) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id = ?", new BeanPropertyRowMapper<>(Book.class), personId);
        return null;
    }
}
