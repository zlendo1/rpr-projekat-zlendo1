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

}
