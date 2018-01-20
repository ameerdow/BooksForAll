package booksforall.db;

import booksforall.utils.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import static booksforall.utils.Constants.*;

public class InitDatabase {
    public static void createTable(String createStatement) {
        try {
            Connection connection = new DBConnection().getConnection();
            PreparedStatement statement = connection.prepareStatement(createStatement);
            statement.executeUpdate(createStatement, Statement.RETURN_GENERATED_KEYS);
            connection.commit();
            connection.close();
        } catch (Exception e) {
            SQLException ex = (SQLException) e;
            if (ex.getSQLState().equals("X0Y32")) {
                Log.e("InitDatabase", "createTable", "Table already exists, " + createStatement, ex);
            } else
                Log.e("InitDatabase", "createTable", e.getMessage(), e);
        }
    }

    public static void initTables() {
        Log.l("InitDatabase", "initTables", "Initializing tables");

        /*
        dropTable(USERS);
        dropTable(USERS_BOOKS_LIKE);
        dropTable(USERS_BOOKS_POSITION);
        dropTable(USERS_BOOKS_PURCHASE);
        dropTable(USERS_BOOKS_REVIEW);
        dropTable(BOOKS);*/

        createTable(CREATE_USERS_STATEMENT);
        createTable(CREATE_BOOKS_STATEMENT);
        createTable(CREATE_USERS_BOOKS_LIKE_STATEMENT);
        createTable(CREATE_USERS_BOOKS_POSITION_STATEMENT);
        createTable(CREATE_USERS_BOOKS_PURCHASE_STATEMENT);
        createTable(CREATE_USERS_BOOKS_REVIEW_STATEMENT);
    }


    public static void dropTable(String tableName) {
        Log.l("InitDatabase", "dropTable", "Dropping table \"" + tableName + "\"");
        try (Connection connection = new DBConnection().getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("DROP TABLE %s", tableName));
            connection.commit();
            Log.l("InitDatabase", "dropTable", "Table \"" + tableName + "\" dropped successfully!");
        } catch (Exception ignore) {
        }
    }
}
