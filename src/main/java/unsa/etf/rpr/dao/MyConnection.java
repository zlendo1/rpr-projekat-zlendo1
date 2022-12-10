package unsa.etf.rpr.dao;

import unsa.etf.rpr.exception.DBHandleException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    public static Connection EstablishConnection() throws DBHandleException {
        String info;

        try {
            info = Files.readString(Path.of("C:\\Users\\User\\OneDrive\\Desktop\\Fakultet\\III\\RPR\\Projekat\\rpr-projekat-zlendo1\\dbinfo\\info.txt"));
        } catch (IOException e) {
            throw new DBHandleException(e);
        }

        String[] infoArray = info.split(" ");

        try {
            return DriverManager.getConnection(infoArray[0], infoArray[1], infoArray[2]);
        } catch (SQLException e) {
            throw new DBHandleException(e);
        }
    }

}
