package booksforall.utils;

public class Constants {

    /************************************
     * Database params.
     **************************************/

    public static final String DBName = "WebProjectDB";
    public static final String PROTOCOL = "jdbc:derby:";
    public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String URL = PROTOCOL + DBName + ";create=true";
    public static final String SESSION_USER_ID = "userId";

    /************************************
     * Tables names.
     **************************************/

    public static final String USERS = "USERS";
    public static final String BOOKS = "BOOKS";
    public static final String USERS_BOOKS_PURCHASE = "USERS_BOOKS_PURCHASE";
    public static final String USERS_BOOKS_POSITION = "USERS_BOOKS_POSITION";
    public static final String USERS_BOOKS_REVIEW = "USERS_BOOKS_REVIEW";
    public static final String USERS_BOOKS_LIKE = "USERS_BOOKS_LIKE";


    /************************************
     * Create Statements to create tables.
     **************************************/

    public static final String CREATE_USERS_STATEMENT = "CREATE TABLE " + USERS + " (" +
            "USERNAME VARCHAR2(10) NOT NULL UNIQUE, " +
            "EMAIL VARCHAR2(30) NOT NULL, " +
            "PASSWORD VARCHAR2(8) NOT NULL, " +
            "ADDRESS VARCHAR2(50) NOT NULL, " +
            "PHONE_NUMBER INTEGER NOT NULL, " +
            "NICKNAME VARCHAR2(20) NOT NULL, " +
            "DESCRIPTION VARCHAR2(50) NOT NULL, " +
            "PHOTO VARCHAR2(255), " +
            "ROLE VARCHAR2(10), " +
            "DELETED VARCHAR2(1), " +
            "SYS_CREATION_DATE DATE NOT NULL, " +
            "SYS_UPDATE_DATE DATE";

    private static final String INC_ID = "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)";

    public static final String CREATE_BOOKS_STATEMENT = "CREATE TABLE " + BOOKS + " (" +
            INC_ID +
            "NAME VARCHAR22(10) NOT NULL UNIQUE, " +
            "PHOTO VARCHAR2(255) NOT NULL, " +
            "PRICE DOUBLE NOT NULL, " +
            "DESCRIPTION VARCHAR2(50) NOT NULL, " +
            "LIKES_NUM INTEGER, " +
            "REVIEWS_NUM INTEGER, " +
            "DELETED VARCHAR2(1), " +
            "SYS_CREATION_DATE DATE NOT NULL, " +
            "SYS_UPDATE_DATE DATE," +
            "FILE_PATH VARCHAR2(500) NOT NULL";

    public static final String CREATE_USERS_BOOKS_PURCHASE_STATEMENT = "CREATE TABLE " + USERS_BOOKS_PURCHASE + " (" +
            "USERNAME VARCHAR2(10) NOT NULL," +
            "BOOK_ID INTEGER NOT NULL," +
            "PRICE DOUBLE NOT NULL," +
            "SYS_CREATION_DATE DATE NOT NULL";

    public static final String CREATE_USERS_BOOKS_POSITION_STATEMENT = "CREATE TABLE " + USERS_BOOKS_POSITION + " (" +
            "USERNAME VARCHAR2(10) NOT NULL," +
            "BOOK_ID INTEGER NOT NULL," +
            "POSITION FLOAT NOT NULL," +
            "SYS_CREATION_DATE DATE NOT NULL";

    public static final String CREATE_USERS_BOOKS_REVIEW_STATEMENT = "CREATE TABLE " + USERS_BOOKS_REVIEW + " (" +
            "USERNAME VARCHAR2(10) NOT NULL," +
            "BOOK_ID INTEGER NOT NULL," +
            "REVIEW VARCHAR2(500) NOT NULL," +
            "APPROVED VARCHAR2(1) NOT NULL," +
            "SYS_CREATION_DATE DATE NOT NULL";

    public static final String CREATE_USERS_BOOKS_LIKE_STATEMENT = "CREATE TABLE " + USERS_BOOKS_LIKE + " (" +
            "USERNAME VARCHAR2(10) NOT NULL," +
            "BOOK_ID INTEGER NOT NULL";




    /************************************
     * User Statements.
     **************************************/

    public static final String SELECT_ALL_USERS = " SELECT * FROM " + USERS;
    public static final String SELECT_USER_BY_USERNAME = " SELECT * FROM " + USERS + " WHERE USERNAME=?";
    public static final String INSERT_NEW_USER = "INSERT INTO " + USERS +
            " (USERNAME,EMAIL,PASSWORD,ADDRESS,PHONE_NUMBER,NICKNAME,DESCRIPTION,PHOTO,ROLE,DELETED,SYS_CREATION_DATE,SYS_UPDATE_DATE)" +
            " VALUES(?,?,?,?,?,?,?,?,?,?,?)";

    public static final String UPDATE_PASSWORD = " UPDATE " + USERS + " SET PASSWORD=? , SYS_UPDATE_DATE =? " +
            "WHERE USERNAME=? AND PASSWORD=? ";

    public static final String SET_DELETED_USER_BY_USERNAME = " UPDATE " + USERS + " SET DELETED=? , SYS_UPDATE_DATE =? " +
            "WHERE USERNAME=? ";

    public static final String SELECT_USER_WHERE_USERNAME_AND_PASSWORD = "SELECT * FROM " + USERS + " " +
            "WHERE USERNAME=? AND PASSWORD=?";



    /************************************
     * Book Statements.
     **************************************/

    public static final String SELECT_ALL_BOOKS= " SELECT * FROM " + BOOKS;
    public static final String SELECT_BOOKS_BY_BOOKNAME = " SELECT * FROM " + BOOKS + " WHERE NAME=?";
    public static final String SELECT_BOOK_BY_ID= " SELECT * FROM " + BOOKS + " WHERE ID=?";
    public static final String INSERT_NEW_BOOK = "INSERT INTO " + BOOKS +
            " (NAME,PHOTO,PRICE,DESCRIPTION,LIKES_NUM,REVIEWS_NUM,DELETED,SYS_CREATION_DATE,SYS_UPDATE_DATE,FILE_PATH)" +
            " VALUES(?,?,?,?,?,?,?,?,?,?)";

    public static final String SET_DELETED_BOOK_BY_ID = " UPDATE " + BOOKS + " SET DELETED=? , SYS_UPDATE_DATE =? " +
            "WHERE ID=? ";
    public static final String ADD_BOOK_LIKE_COUNT_BY_ID = " UPDATE " + BOOKS + " SET LIKES_NUM= LIKES_NUM + 1 , SYS_UPDATE_DATE =? " +
            "WHERE ID=? ";
    public static final String ADD_BOOK_REVIEW_COUNT_BY_ID = " UPDATE " + BOOKS + " SET REVIEWS_NUM = REVIEWS_NUM + 1 , SYS_UPDATE_DATE =? " +
            "WHERE ID=? ";
    public static final String REMOVE_BOOK_LIKE_COUNT_BY_ID = " UPDATE " + BOOKS + " SET LIKES_NUM= LIKES_NUM - 1 , SYS_UPDATE_DATE =? " +
            "WHERE ID=? ";
    public static final String REMOVE_BOOK_REVIEW_COUNT_BY_ID = " UPDATE " + BOOKS + " SET REVIEWS_NUM = REVIEWS_NUM - 1 , SYS_UPDATE_DATE =? " +
            "WHERE ID=? ";
}
