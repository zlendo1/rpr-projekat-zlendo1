package unsa.etf.rpr.dao;

import unsa.etf.rpr.domain.Person;
import unsa.etf.rpr.exception.DBHandleException;

import java.util.List;

/**
 * Dao interface for Person bean
 *
 */
public interface PersonDao extends Dao<Person> {

    /**
     * Search people in DB based on their first names
     *
     * @param firstName First name of a person
     * @return List of people
     * @throws DBHandleException In case of any DB handling error
     */
    List<Person> getByFirstName(String firstName) throws DBHandleException;

    /**
     * Search people in DB based on their last name
     *
     * @param lastName Last name of a person
     * @return List of people
     * @throws DBHandleException In case of any DB handling error
     */
    List<Person> getByLastName(String lastName) throws DBHandleException;

    /**
     * Search people in DB based on their username
     *
     * @param username Username of a user
     * @return A user object
     * @throws DBHandleException In case of any DB handling error
     */
    Person getByUsername(String username) throws DBHandleException;

    /**
     * Search user in DB based on their password
     *
     * @param password Password of a user
     * @return List of users
     * @throws DBHandleException In case of any DB handling error
     */
    List<Person> getByPassword(String password) throws DBHandleException;

}
