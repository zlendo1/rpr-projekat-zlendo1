package unsa.etf.rpr.dao;

import unsa.etf.rpr.connector.MyConnection;
import unsa.etf.rpr.domain.Subscriber;
import unsa.etf.rpr.exception.DBHandleException;

import java.sql.*;
import java.util.List;

public class SubscriberDaoSQLImpl implements SubscriberDao {

    private final Connection connection;

    /**
     * Establishes connection to the DB
     *
     */
    public SubscriberDaoSQLImpl() throws DBHandleException {
        connection = MyConnection.EstablishConnection();
    }

    /**
     * Get entity from database coresponding to its primary key
     *
     * @param id primary key of entity
     * @return corresponding entity
     */
    @Override
    public Subscriber getById(int id) throws DBHandleException {
        String query = "SELECT * FROM subscriber WHERE subscriber_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Subscriber subscriber = new Subscriber(
                        new PersonDaoSQLImpl().getById(resultSet.getInt("subscriber_id")),
                        resultSet.getString("preferences")
                );

                resultSet.close();

                return subscriber;
            }

        } catch (Exception e) {
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
    public Subscriber add(Subscriber item) throws DBHandleException {
        String insert = "INSERT INTO subscriber(preferences) VALUES(?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, item.getPreferences());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.next();

            item.setPerson(new PersonDaoSQLImpl().getById(resultSet.getInt("provider_id")));

            resultSet.close();

            return item;

        } catch (Exception e) {
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
    public Subscriber update(Subscriber item) throws DBHandleException {
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
    public List<Subscriber> getAll() {
        return null;
    }

    /**
     * Search subscribers in DB by their exam preferences
     *
     * @param preference Exam preference (only one)
     * @return List of subscribers
     */
    @Override
    public List<Subscriber> getByPreference(String preference) {
        return null;
    }
}
