package booksforall.main;

import booksforall.db.InitDatabase;
import booksforall.models.Address;
import booksforall.services.UserService;

import java.sql.SQLException;

public class BooksForAll {

    private static final String classFunc = BooksForAll.class.getSimpleName();
    private static BooksForAll instance = null;

    /**
     * Singleton WebProjectApp, init the DB connections
     */
    public static BooksForAll getInstance() throws SQLException {
        if (instance == null)
            instance = new BooksForAll();
        return instance;
    }

    private BooksForAll() {
        InitDatabase.initTables();
        UserService service = new UserService();
        Address address = new Address("admin",1,"admin","1234567","admin");
        service.addUser("admin", "admin@email.com", "passw0rd", address, "0123456789", "nickname", "description", "photoUrl");
    }

}
