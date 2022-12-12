package unsa.etf.rpr.connector;

import unsa.etf.rpr.exception.DBHandleException;

import java.io.InputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * The class that lets us establish only one connection to the database at a time.
 *
 */
public class MyConnection {

    private final Connection connection;

    private static MyConnection instance = null;    // The observant viewer will notice that this was
                                                    // implemented using the singleton design pattern.

    /**
     * Constructor that establishes a connection to my database.
     *
     * @throws DBHandleException In case of file reading error or connection establishment
     */
    private MyConnection() throws DBHandleException {
        try {
            InputStream inputStream = new FileInputStream("config.properties");

            Properties properties = new Properties();

            properties.load(inputStream);

            connection = DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password")
            );

        } catch (Exception e) {
            throw new DBHandleException(e);
        }
    }

    /**
     * Static method that returns an instance of the class in accordance to the singleton design pattern.
     *
     * @return Connection to our database
     * @throws DBHandleException In case of file reading error or connection establishment
     */
    public static MyConnection getInstance() throws DBHandleException {
        if (instance != null) {
            instance = new MyConnection();
        }

        return instance;
    }

    /**
     * Getter for the Connection object.
     *
     * @return Connection object to the database
     * @throws DBHandleException In case of file reading error or connection establishment
     */
    public Connection getConnection() throws DBHandleException {
        return connection;
    }

}