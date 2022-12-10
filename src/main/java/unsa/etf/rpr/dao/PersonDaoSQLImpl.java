package unsa.etf.rpr.dao;

import unsa.etf.rpr.domain.Person;

import java.sql.Connection;
import java.util.List;

public class PersonDaoSQLImpl implements PersonDao {

    private Connection connection;

    public PersonDaoSQLImpl() {
    }

    @Override
    public Person getById(int id) {
        return null;
    }

    @Override
    public Person add(Person item) {
        return null;
    }

    @Override
    public Person update(Person item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Person> getAll() {
        return null;
    }

    @Override
    public List<Person> searchByFirstName(String firstName) {
        return null;
    }

    @Override
    public List<Person> searchByLastName(String lastName) {
        return null;
    }

}
