package unsa.etf.rpr.connector;

import unsa.etf.rpr.exception.DBHandleException;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
        File myFile = new File("config.properties");

        try {
            myFile.createNewFile(); // Only creates a new file if one does not exist (exception security reasons)

            InputStream inputStream = new FileInputStream(myFile);

            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();

            connection = DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password")
            );

        } catch (SQLException | IOException e) {
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
        if (instance == null) {
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