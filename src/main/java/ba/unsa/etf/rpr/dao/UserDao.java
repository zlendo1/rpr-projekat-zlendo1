package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.DBHandleException;

import java.util.List;

/**
 * Dao interface for User bean.
 *
 */
public interface UserDao extends Dao<User> {

    /**
     * Search users in DB based on their first names.
     *
     * @param firstName First name of a user
     * @return List of users
     * @throws DBHandleException In case of any DB handling error
     */
    List<User> getByFirstName(String firstName) throws DBHandleException;

    /**
     * Search users in DB based on their last name.
     *
     * @param lastName Last name of a user
     * @return List of users
     * @throws DBHandleException In case of any DB handling error
     */
    List<User> getByLastName(String lastName) throws DBHandleException;

    /**
     * Search users in DB based on their username.
     *
     * @param username Username of a user
     * @return A user object
     * @throws DBHandleException In case of any DB handling error
     */
    User getByUsername(String username) throws DBHandleException;

    /**
     * Search user in DB based on their password.
     *
     * @param password Password of a user
     * @return List of users
     * @throws DBHandleException In case of any DB handling error
     */
    List<User> getByPassword(String password) throws DBHandleException;

}
