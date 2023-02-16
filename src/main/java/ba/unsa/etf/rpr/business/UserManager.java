package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.auxiliary.AlertThrower;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.DBHandleException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Business layer manager for User.
 *
 */
public class UserManager {

    /**
     * Searches the DB for the user with the given information.
     *
     * @param username Username handle of the user.
     * @param password Unencrypted password of the user.
     * @return Person bean with the specified information. Null if one does not exist.
     * @throws DBHandleException In case of any DB errors.
     */
    public User findUser(String username, String password) throws DBHandleException {
        User user = DaoFactory.userDao().getByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    /**
     * Checks for the existence of a user in the DB.
     *
     * @param username Username handle of the user.
     * @return True if the user exists, false otherwise.
     * @throws DBHandleException In case of any DB errors.
     */
    public boolean existsUser(String username) throws DBHandleException {
        User user = DaoFactory.userDao().getByUsername(username);

        return user != null;
    }

    /**
     * Creates a new User and inserts them into the DB.
     *
     * @param username Username handle of the user.
     * @param password Unencrypted password of the user.
     * @param firstName First name of the user.
     * @param lastName Last name of the user.
     * @return Freshly created User bean with the specified information.
     * @throws DBHandleException In case of any DB errors.
     */
    public User createUser(String username, String password, String firstName, String lastName) throws DBHandleException {
        if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            return null;
        }

        if (existsUser(username)) {
            throw new DBHandleException("Username occupied");
        }

        User newUser = new User();

        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);

        return DaoFactory.userDao().add(newUser);
    }

}
