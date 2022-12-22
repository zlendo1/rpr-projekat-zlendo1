package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exception.DBHandleException;

import java.sql.Connection;
import java.util.List;

public class AbstractDao <T extends Idable> implements Dao<T> {
    private Connection connection;
    private String tableName;

    /**
     * Get entity from database coresponding to its primary key
     *
     * @param id primary key of entity
     * @return corresponding entity
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public T getById(int id) throws DBHandleException {
        return null;
    }

    /**
     * Saves entity into database
     *
     * @param item bean for saving into database
     * @return updated version of the bean
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public T add(T item) throws DBHandleException {
        return null;
    }

    /**
     * Updates entity from database based on its primary key
     *
     * @param item bean which we will update (id must be populated)
     * @return updated version of the bean
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public T update(T item) throws DBHandleException {
        return null;
    }

    /**
     * Hard delete of entity with the corseponding primary key
     *
     * @param id primary key of the entity
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public void delete(int id) throws DBHandleException {

    }

    /**
     * Lists all entites from the database
     *
     * @return list of entities from the database
     * @throws DBHandleException In case of any DB handling error
     */
    @Override
    public List<T> getAll() throws DBHandleException {
        return null;
    }

}
