package ba.unsa.etf.rpr.connector;

import ba.unsa.etf.rpr.exception.DBHandleException;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton wrapper for Connection.
 * Basically limits us to opening up only one connection to the DB at a time.
 *
 */
public class MyConnection {

    private static Connection connection = null;

    /**
     * Getter for the Connection object.
     * Will only construct a new object if one does not already exist.
     *
     * @return Connection object to the database
     */
    public static Connection getConnection() throws DBHandleException {
        if (connection != null) {
            return connection;
        }

        try {
            Properties properties = new Properties();
            properties.load(ClassLoader.getSystemResource("db_properties/config.properties").openStream());

            connection = DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password")
            );

        } catch (SQLException e) {
            throw new DBHandleException(e);
        } catch (IOException e) {
            throw new DBHandleException("Necessary properties file not found", e);
        }

        return connection;
    }

}