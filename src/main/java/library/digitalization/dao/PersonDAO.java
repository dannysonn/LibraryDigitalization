package library.digitalization.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import library.digitalization.models.Person;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PersonDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> findAll() {
//        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));

        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("from Person", Person.class).list();
    }

    ;

    public void save(Person person) {
//        jdbcTemplate.update("INSERT INTO Person(fullName, yearOfBirth) VALUES (?, ?)", person.getFullName(), person.getYearOfBirth());

    }

    public void update(int id, Person person) {
//        jdbcTemplate.update("UPDATE Person SET fullName=?, yearOfBirth=? WHERE id=?", person.getFullName(), person.getYearOfBirth(), id);
    }

    public Person findById(int id) {
//        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);

        return null;
    }

    public void deletePerson(int id) {
//        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}
