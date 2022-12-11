package unsa.etf.rpr.dao;

import unsa.etf.rpr.domain.Person;
import unsa.etf.rpr.exception.DBHandleException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoSQLImpl implements PersonDao {

    private Connection connection;

    /**
     * Establishes connection to the DB
     *
     */
    public PersonDaoSQLImpl() throws DBHandleException {
        connection = MyConnection.EstablishConnection();
    }

    /**
     * Get entity from database coresponding to its primary key
     *
     * @param id primary key of entity
     * @return corresponding entity
     */
    @Override
    public Person getById(int id) throws DBHandleException {
        String query = "SELECT * FROM person WHERE person_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Person person = new Person(
                        resultSet.getInt("person_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")
                );

                resultSet.close();

                return person;
            }

        } catch (SQLException e) {
            throw new DBHandleException(e);
        }

        return null;
    }

    /**
     * Saves entity into database
     *
     * @param item bean for saving into database
     * @return updated version of the bean
     */
    @Override
    public Person add(Person item) throws DBHandleException {
        String insert = "INSERT INTO person(first_name, last_name) VALUES(?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, item.getFirstName());
            preparedStatement.setString(2, item.getLastName());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.next();

            item.setPersonId(resultSet.getInt(1));

            resultSet.close();

            return item;

        } catch (SQLException e) {
            throw new DBHandleException(e);
        }
    }

    /**
     * Updates entity from database based on its primary key
     *
     * @param item bean which we will update (id must be populated)
     * @return updated version of the bean
     */
    @Override
    public Person update(Person item) throws DBHandleException {
        String update = "UPDATE person SET first_name = ?, last_name = ? WHERE person_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);

            preparedStatement.setInt(3, item.getPersonId());
            preparedStatement.setString(1, item.getFirstName());
            preparedStatement.setString(2, item.getLastName());

            preparedStatement.executeUpdate();

            preparedStatement.close();

            return item;

        } catch (SQLException e) {
            throw new DBHandleException(e);
        }
    }

    /**
     * Hard delete of entity with the corseponding primary key
     *
     * @param id primary key of the entity
     */
    @Override
    public void delete(int id) throws DBHandleException {
        String delete = "DELETE FROM person WHERE person_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException e) {
            throw new DBHandleException(e);
        }
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
    public List<Person> getByFirstName(String firstName) throws DBHandleException {
        List<Person> personList = new ArrayList<>();

        String query = "SELECT * FROM person WHERE first_name = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, firstName);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                personList.add(new Person(
                        resultSet.getInt("person_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"))
                );
            }

            resultSet.close();

        } catch (SQLException e) {
            throw new DBHandleException(e);
        }

        return personList;
    }

    /**
     * Search people in DB based on their last name
     *
     * @param lastName Last name of a person
     * @return List of people
     */
    @Override
    public List<Person> getByLastName(String lastName) throws DBHandleException {
        List<Person> personList = new ArrayList<>();

        String query = "SELECT * FROM person WHERE last_name = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, lastName);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                personList.add(new Person(
                        resultSet.getInt("person_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"))
                );
            }

            resultSet.close();

        } catch (SQLException e) {
            throw new DBHandleException(e);
        }

        return personList;
    }

}
