package unsa.etf.rpr.dao;

import unsa.etf.rpr.domain.Person;

import java.sql.Connection;
import java.util.List;

public class PersonDaoSQLImpl implements PersonDao {

    private Connection connection;

    /**
     * Establishes connection to the DB
     *
     */
    public PersonDaoSQLImpl() {
    }

    /**
     * Get entity from database coresponding to its primary key
     *
     * @param id primary key of entity
     * @return corresponding entity
     */
    @Override
    public Person getById(int id) {
        return null;
    }

    /**
     * Saves entity into database
     *
     * @param item bean for saving into database
     * @return updated version of the bean
     */
    @Override
    public Person add(Person item) {
        return null;
    }

    /**
     * Updates entity from database based on its primary key
     *
     * @param item bean which we will update (id must be populated)
     * @return updated version of the bean
     */
    @Override
    public Person update(Person item) {
        return null;
    }

    /**
     * Hard delete of entity with the corseponding primary key
     *
     * @param id primary key of the entity
     */
    @Override
    public void delete(int id) {

    }

    /**
     * Lists all entites from the database
     *
     * @return list of entities from the database
     */
    @Override
    public List<Person> getAll() {
        return null;
    }

    /**
     * Search people in DB based on their first names
     *
     * @param firstName First name of a person
     * @return List of people
     */
    @Override
    public List<Person> searchByFirstName(String firstName) {
        return null;
    }

    /**
     * Search people in DB based on their last name
     *
     * @param lastName Last name of a person
     * @return List of people
     */
    @Override
    public List<Person> searchByLastName(String lastName) {
        return null;
    }

}
