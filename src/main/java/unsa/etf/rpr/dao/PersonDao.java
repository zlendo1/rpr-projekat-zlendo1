package unsa.etf.rpr.dao;

import unsa.etf.rpr.domain.Person;

import java.util.List;

/**
 * Dao interface for Person bean
 */
public interface PersonDao extends Dao<Person> {

    /**
     * @param firstName First name of a person
     * @return List of people with the same first name
     */
    List<Person> searchByFirstName(String firstName);

    /**
     * @param lastName Last name of a person
     * @return List of people with the same last name
     */
    List<Person> searchByLastName(String lastName);

}
