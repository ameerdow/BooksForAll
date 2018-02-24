package booksforall.utils;

public class Constants {

	/************************************
	 * Database params.
	 **************************************/

	public static final String DBName = "BooksForAllDB";
	public static final String PROTOCOL = "jdbc:derby:";
	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String URL = PROTOCOL + DBName + ";create=true";
	public static final String SESSION_USERNAME = "username";

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

	public static final String CREATE_USERS_STATEMENT = "CREATE TABLE " + USERS + "( "
			+ "USERNAME VARCHAR(10) NOT NULL UNIQUE, " + "EMAIL VARCHAR(30) NOT NULL, "
			+ "PASSWORD VARCHAR(8) NOT NULL, " + "STREET VARCHAR(50) NOT NULL, " + "HOUSE_NUMBER INTEGER NOT NULL, "
			+ "CITY VARCHAR(50) NOT NULL, " + "ZIP VARCHAR(50) NOT NULL, " + "COUNTRY VARCHAR(50) NOT NULL, "
			+ "PHONE_NUMBER VARCHAR(50) NOT NULL, " + "NICKNAME VARCHAR(20) NOT NULL, "
			+ "DESCRIPTION VARCHAR(50) NOT NULL, " + "PHOTO VARCHAR(255), " + "ROLE VARCHAR(10), "
			+ "DELETED VARCHAR(1), " + "SYS_CREATION_DATE DATE NOT NULL, " + "SYS_UPDATE_DATE DATE" + " ) ";

	private static final String INC_ID = "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)";

	public static final String CREATE_BOOKS_STATEMENT = "CREATE TABLE " + BOOKS + "( " + INC_ID + ", "
			+ " NAME VARCHAR(10) NOT NULL UNIQUE, " + "PHOTO VARCHAR(255) NOT NULL, " + "PRICE DOUBLE NOT NULL, "
			+ "DESCRIPTION VARCHAR(50) NOT NULL, " + "CATEGORY VARCHAR(50) NOT NULL, " + "LIKES_NUM INTEGER, " + "REVIEWS_NUM INTEGER, "
			+ "DELETED VARCHAR(1), " + "SYS_CREATION_DATE DATE NOT NULL, " + "SYS_UPDATE_DATE DATE,"
			+ "FILE_PATH VARCHAR(500) NOT NULL" + ", ICON_PATH VARCHAR(500) NOT NULL )";

	public static final String CREATE_USERS_BOOKS_PURCHASE_STATEMENT = "CREATE TABLE " + USERS_BOOKS_PURCHASE + "( "
			+ "USERNAME VARCHAR(10) NOT NULL," + "BOOK_ID INTEGER NOT NULL," + "PRICE DOUBLE NOT NULL,"
			+ "SYS_CREATION_DATE DATE NOT NULL" + " )";

	public static final String CREATE_USERS_BOOKS_POSITION_STATEMENT = "CREATE TABLE " + USERS_BOOKS_POSITION + "( "
			+ "USERNAME VARCHAR(10) NOT NULL," + "BOOK_ID INTEGER NOT NULL," + "POSITION FLOAT NOT NULL,"
			+ "SYS_CREATION_DATE DATE NOT NULL" + " )";

	public static final String CREATE_USERS_BOOKS_REVIEW_STATEMENT = "CREATE TABLE " + USERS_BOOKS_REVIEW + "( "
			+ INC_ID + ", " + "USERNAME VARCHAR(10) NOT NULL," + "BOOK_ID INTEGER NOT NULL,"
			+ "REVIEW VARCHAR(500) NOT NULL," + "APPROVED VARCHAR(1) NOT NULL," + "SYS_CREATION_DATE DATE NOT NULL"
			+ " )";

	public static final String CREATE_USERS_BOOKS_LIKE_STATEMENT = "CREATE TABLE " + USERS_BOOKS_LIKE + "( "
			+ "USERNAME VARCHAR(10) NOT NULL," + "BOOK_ID INTEGER NOT NULL, " + " NICKNAME VARCHAR(20) NOT NULL )";

	/************************************
	 * User Statements.
	 **************************************/

	public static final String SELECT_ALL_USERS = " SELECT * FROM " + USERS;
	public static final String SELECT_USER_BY_USERNAME = " SELECT * FROM " + USERS + " WHERE USERNAME=?";
	public static final String SEARCH_USER = " SELECT * FROM " + USERS + " WHERE USERNAME LIKE '%?%' ";
	public static final String INSERT_NEW_USER = "INSERT INTO " + USERS
			+ " (USERNAME,EMAIL,PASSWORD,STREET,HOUSE_NUMBER,CITY,ZIP,COUNTRY,PHONE_NUMBER,NICKNAME,DESCRIPTION,PHOTO,ROLE,DELETED,SYS_CREATION_DATE,SYS_UPDATE_DATE)"
			+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static final String UPDATE_PASSWORD = " UPDATE " + USERS + " SET PASSWORD=? , SYS_UPDATE_DATE =? "
			+ "WHERE USERNAME=? AND PASSWORD=? ";

	public static final String SET_DELETED_USER_BY_USERNAME = " UPDATE " + USERS
			+ " SET DELETED=? , SYS_UPDATE_DATE =? " + "WHERE USERNAME=? ";

	public static final String SELECT_USER_WHERE_USERNAME_AND_PASSWORD = "SELECT * FROM " + USERS + " "
			+ "WHERE USERNAME=? AND PASSWORD=?";

	/************************************
	 * Book Statements.
	 **************************************/

	public static final String SELECT_ALL_BOOKS = " SELECT * FROM " + BOOKS;
	public static final String SELECT_BOOKS_BY_BOOK_NAME = " SELECT * FROM " + BOOKS + " WHERE NAME LIKE '%?%'";
	public static final String SELECT_BOOK_BY_ID = " SELECT * FROM " + BOOKS + " WHERE ID=?";
	public static final String SELECT_BOOK_BY_CATEGORY = " SELECT * FROM " + BOOKS + " WHERE CATEGORY=?";

	public static final String SET_DELETED_BOOK_BY_ID = " UPDATE " + BOOKS + " SET DELETED=? , SYS_UPDATE_DATE =? "
			+ "WHERE ID=? ";
	public static final String ADD_BOOK_LIKE_COUNT_BY_ID = " UPDATE " + BOOKS
			+ " SET LIKES_NUM= LIKES_NUM + 1 , SYS_UPDATE_DATE =? " + "WHERE ID=? ";
	public static final String ADD_BOOK_REVIEW_COUNT_BY_ID = " UPDATE " + BOOKS
			+ " SET REVIEWS_NUM = REVIEWS_NUM + 1 , SYS_UPDATE_DATE =? " + "WHERE ID=? ";
	public static final String REMOVE_BOOK_LIKE_COUNT_BY_ID = " UPDATE " + BOOKS
			+ " SET LIKES_NUM = LIKES_NUM - 1 , SYS_UPDATE_DATE =? " + "WHERE ID=? ";
	public static final String REMOVE_BOOK_REVIEW_COUNT_BY_ID = " UPDATE " + BOOKS
			+ " SET REVIEWS_NUM = REVIEWS_NUM - 1 , SYS_UPDATE_DATE =? " + "WHERE ID=? ";

	/************************************
	 * User Book Relation Statements.
	 **************************************/

	public static final String ADD_USER_BOOK_LIKE = " INSERT INTO " + USERS_BOOKS_LIKE
			+ " (USERNAME,BOOK_ID,NICKNAME) VALUES (?,?,?)";
	public static final String DELETE_USER_BOOK_LIKE = " DELETE FROM " + USERS_BOOKS_LIKE
			+ " WHERE USERNAME=? AND BOOK_ID=?";
	public static final String SELECT_USER_BOOK_LIKE = " SELECT FROM " + USERS_BOOKS_LIKE
			+ " WHERE USERNAME=? AND BOOK_ID=?";
	public static final String SELECT_USER_BOOK_LIKE_BY_USER = " SELECT FROM " + USERS_BOOKS_LIKE + " WHERE USERNAME=?";
	public static final String SELECT_USER_BOOK_LIKE_BY_BOOK = " SELECT FROM " + USERS_BOOKS_LIKE + " WHERE BOOK_ID=?";

	public static final String ADD_USER_BOOK_REVIEW = "INSERT INTO " + USERS_BOOKS_REVIEW
			+ " (USERNAME,BOOK_ID,REVIEW,APPROVED,SYS_CREATION_DATE VALUES (?,?,?,?,?)";
	public static final String APPROVE_USER_BOOK_REVIEW = "UPDATE " + USERS_BOOKS_REVIEW
			+ " SET APPROVED = 'Y' WHERE ID = ?";
	public static final String SELECT_BOOK_REVIEWS = "SELECT * FROM " + USERS_BOOKS_REVIEW + " WHERE BOOK_ID = ?";
	public static final String GET_REVIEW_BY_ID = "SELECT * FROM " + USERS_BOOKS_REVIEW + " WHERE ID = ?";

	public static final String ADD_USER_BOOK_PURCHASE = "INSERT INTO " + USERS_BOOKS_PURCHASE
			+ " (USERNAME,BOOK_ID,PRICE,SYS_CREATION_DATE) " + "VALUES (?,?,?,SYSDATE)";
	public static final String GET_USER_BOOK_PURCHASE = "SELECT * FROM " + USERS_BOOKS_PURCHASE
			+ " WHERE USERNAME=? AND BOOK_ID=?";
	public static final String GET_ALL_PURCHASES = "SELECT * FROM " + USERS_BOOKS_PURCHASE;

	public static final String GET_ALL_BOOKS_NOT_PURCHASED_BY_USER = "SELECT * FROM " + BOOKS + " WHERE BOOK_ID NOT IN "
			+ " ( SELECT BOOK_ID FROM " + USERS_BOOKS_PURCHASE + " WHERE USERNAME = ? )";
	public static final String GET_ALL_BOOKS_PURCHASED_BY_USER = "SELECT * FROM " + BOOKS + " WHERE BOOK_ID IN "
			+ " ( SELECT BOOK_ID FROM " + USERS_BOOKS_PURCHASE + " WHERE USERNAME = ? )";

	public static final String ADD_USER_BOOK_POSITION = "INSERT INTO " + USERS_BOOKS_POSITION
			+ " (USERNAME,BOOK_ID,POSITION,SYS_CREATION_DATE) " + "VALUES (?,?,?,SYSDATE)";
	public static final String GET_USER_BOOK_POSITION = "SELECT * FROM " + USERS_BOOKS_POSITION
			+ " WHERE USERNAME=? AND BOOK_ID=?";
	public static final String SET_USER_BOOK_POSITION = "UPDATE " + USERS_BOOKS_POSITION
			+ " SET POSITION=? WHERE USERNAME=? AND BOOK_ID=?";

}
