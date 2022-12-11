package unsa.etf.rpr.dao;

import unsa.etf.rpr.connector.MyConnection;
import unsa.etf.rpr.domain.Subscriber;
import unsa.etf.rpr.exception.DBHandleException;

import java.sql.Connection;
import java.util.List;

public class SubscriberDaoSQLImpl implements SubscriberDao {

    private Connection connection;

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
    public Subscriber getById(int id) {
        return null;
    }

    /**
     * Saves entity into database
     *
     * @param item bean for saving into database
     * @return updated version of the bean
     */
    @Override
    public Subscriber add(Subscriber item) {
        return null;
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
