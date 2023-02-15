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

    public User findUser(String username, String password) throws DBHandleException {
        User user = DaoFactory.userDao().getByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    public boolean existsUser(String username) throws DBHandleException {
        User user = DaoFactory.userDao().getByUsername(username);

        return user != null;
    }

    public User createUser(String username, String password, String firstName, String lastName) throws DBHandleException {
        if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            return null;
        }

        if (existsUser(username)) {
            new Alert(Alert.AlertType.ERROR, "Username occupied", ButtonType.OK);

            return null;
        }

        User newUser = new User();

        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);

        return DaoFactory.userDao().add(newUser);
    }

}
