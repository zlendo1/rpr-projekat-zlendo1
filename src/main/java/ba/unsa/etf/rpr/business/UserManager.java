package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.auxiliary.AlertThrower;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.DBHandleException;
import javafx.scene.control.Alert;

/**
 * Business layer manager for User.
 *
 */
public class UserManager {

    public User findUser(String username, String password) {
        try {
            User user = DaoFactory.userDao().getByUsername(username);

            if (user != null && user.getPassword().equals(password)) {
                return user;
            }
        } catch (DBHandleException e) {
            AlertThrower.throwAlert(e, Alert.AlertType.ERROR);
        }

        return null;
    }

    public boolean existsUser(String username) {
        try {
            User user = DaoFactory.userDao().getByUsername(username);

            return user != null;
        } catch (DBHandleException e) {
            AlertThrower.throwAlert(e, Alert.AlertType.ERROR);
        }

        return false;
    }

    public User createUser(String username, String password, String firstName, String lastName) {
        User newUser = new User();

        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);

        try {
            return DaoFactory.userDao().add(newUser);
        } catch (DBHandleException e) {
            AlertThrower.throwAlert(e, Alert.AlertType.ERROR);
        }

        return null;
    }

}
