package unsa.etf.rpr.connector;

import unsa.etf.rpr.exception.DBHandleException;

import java.io.InputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class MyConnection {

    public static Connection EstablishConnection() throws DBHandleException {
        try (InputStream inputStream = new FileInputStream("config.properties")) {
            Properties properties = new Properties();

            properties.load(inputStream);

            return DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password")
            );

        } catch (Exception e) {
            throw new DBHandleException(e);
        }
    }

}
