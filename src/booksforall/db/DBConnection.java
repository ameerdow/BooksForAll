package booksforall.db;

import booksforall.utils.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static booksforall.utils.Constants.*;

public class DBConnection {

    /**
     * Create connection with derby database
     * @return Connection to the /db
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        try {
            Log.l("DBConnection", "getConnection","getting connection to DB");
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

    /**
     * Closes the connection to DB.
     * @param connection the connection to close.
     */
    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (Exception ignore) {
        }
    }
}
