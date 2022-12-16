package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exception.DBHandleException;

import java.util.List;

/**
 * Root intefrace for all DAO classes
 *
 * @param <T> Bean class
 */
public interface Dao<T> {

    /**
     * Get entity from database coresponding to its primary key
     *
     * @param id primary key of entity
     * @return corresponding entity
     * @throws DBHandleException In case of any DB handling error
     */
    T getById(int id) throws DBHandleException;

    /**
     * Saves entity into database
     *
     * @param item bean for saving into database
     * @return updated version of the bean
     * @throws DBHandleException In case of any DB handling error
     */
    T add(T item) throws DBHandleException;

    /**
     * Updates entity from database based on its primary key
     *
     * @param item bean which we will update (id must be populated)
     * @return updated version of the bean
     * @throws DBHandleException In case of any DB handling error
     */
    T update(T item) throws DBHandleException;

    /**
     * Hard delete of entity with the corseponding primary key
     *
     * @param id primary key of the entity
     * @throws DBHandleException In case of any DB handling error
     */
    void delete(int id) throws DBHandleException;

    /**
     * Lists all entites from the database
     *
     * @return list of entities from the database
     * @throws DBHandleException In case of any DB handling error
     */
    List<T> getAll() throws DBHandleException;

}
