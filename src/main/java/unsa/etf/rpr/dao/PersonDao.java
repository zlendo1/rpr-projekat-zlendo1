package unsa.etf.rpr.dao;

import unsa.etf.rpr.domain.Person;

import java.util.List;

/**
 * Dao interface for Person bean
 */
public interface PersonDao extends Dao<Person> {

    /**
     * Search people in DB based on their first names
     * @param firstName First name of a person
     * @return List of people
     */
    List<Person> searchByFirstName(String firstName);

    /**
     * Search people in DB based on their last name
     * @param lastName Last name of a person
     * @return List of people
     */
    List<Person> searchByLastName(String lastName);

}
