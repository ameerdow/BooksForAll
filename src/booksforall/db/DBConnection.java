package booksforall.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static booksforall.utils.Constants.*;

public class DBConnection {

    public Connection getConnection() throws SQLException {
        try {

            // Load database driver if not already loaded
            Class.forName(DRIVER).newInstance();
            // Establish network connection to database

            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException ignore) {
            throw new SQLException("Can't find class for driver: " + DRIVER);
        } catch (IllegalAccessException ignore) {
            throw new SQLException("Can't access DB with username: " + USERNAME + " password: " + PASSWORD);
        } catch (InstantiationException ignore) {
            throw new SQLException("Can't access DB with url: " + URL);
        }
    }

    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (Exception ignore) {
        }
    }
}
