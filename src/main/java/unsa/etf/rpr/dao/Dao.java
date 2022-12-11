package unsa.etf.rpr.dao;

import unsa.etf.rpr.exception.DBHandleException;

import java.util.List;

/**
 * Root intefrace for all DAO classes
 * @param <T> Bean class
 */
public interface Dao<T> {

    /**
     * Get entity from database coresponding to its primary key
     * @param id primary key of entity
     * @return corresponding entity
     */
    T getById(int id) throws DBHandleException;

    /**
     * Saves entity into database
     * @param item bean for saving into database
     * @return updated version of the bean
     */
    T add(T item) throws DBHandleException;

    /**
     * Updates entity from database based on its primary key
     * @param item bean which we will update (id must be populated)
     * @return updated version of the bean
     */
    T update(T item) throws DBHandleException;

    /**
     * Hard delete of entity with the corseponding primary key
     * @param id primary key of the entity
     */
    void delete(int id) throws DBHandleException;

    /**
     * Lists all entites from the database
     * @return list of entities from the database
     */
    List<T> getAll();

}
