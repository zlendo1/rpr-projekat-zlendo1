package unsa.etf.rpr.dao;

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
    T getById(int id);

    /**
     * Saves entity into database
     * @param item bean for saving into database
     * @return updated version of the bean
     */
    T add(T item);

    /**
     * Updates entity from database based on its primary key
     * @param item bean which we will update (id must be populated)
     * @return updated version of the bean
     */
    T update(T item);

    /**
     * Hard delete of entity with the corseponding primary key
     * @param id primary key of the entity
     */
    void delete(int id);

    /**
     * Lists all entites from the database
     * @return list of entities from the database
     */
    List<T> getAll();

}
