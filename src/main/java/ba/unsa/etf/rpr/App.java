package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
import javafx.application.Application;
import javafx.stage.Stage;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.DBHandleException;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App extends Application {

    public static void main( String[] args ) {
        List<User> userList = null;

        try {
            userList = new UserDaoSQLImpl().getAll();

            System.out.println(userList.size());
        } catch (DBHandleException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}