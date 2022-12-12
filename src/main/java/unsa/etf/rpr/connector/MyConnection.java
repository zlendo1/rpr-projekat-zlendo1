package unsa.etf.rpr.connector;

import unsa.etf.rpr.exception.DBHandleException;

import java.io.InputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * The class that lets us establish only one connection to the database at a time
 *
 */
public class MyConnection {

    private static Connection connection = null;    // The observant viewer will recognise that this is,
                                                    // infact, implemented with the singleton design pattern in mind.

    /**
     * Static method that establishes a connection to the database only if none is established beforehand
     * and promply returns the same connection.
     * If a connection was established beforehand, returns the older connection.
     *
     * @return Connection to our database
     * @throws DBHandleException In case of file reading error or connection establishment
     */
    public static Connection EstablishConnection() throws DBHandleException {
        if (connection != null) {
            return connection;
        }

        try (InputStream inputStream = new FileInputStream("config.properties")) {
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

        return connection;
    }

}