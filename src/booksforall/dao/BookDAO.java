package booksforall.dao;

import booksforall.db.DBConnection;
import booksforall.models.Book;
import booksforall.utils.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static booksforall.utils.Constants.*;

public class BookDAO {

    private final static String classFunc = BookDAO.class.getSimpleName();

    /**
     * Get all the books in the system
     *
     * @return list of object book
     */
    public List<Book> getAllBooks() {

        Log.l(classFunc, "getAllBooks", "Starting");

        List<Book> booksList = new ArrayList<>();

        try (Connection connection = new DBConnection().getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BOOKS);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                booksList.add(new Book(
                        rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getDouble("PRICE"),
                        rs.getString("DESCRIPTION"),
                        rs.getInt("LIKES_NUM"),
                        rs.getInt("REVIEWS_NUM"),
                        rs.getString("DELETED"),
                        rs.getString("FILE_PATH"),
                        rs.getString("FILE_PRE_PATH"),
                        rs.getString("ICON_PATH"),
                        rs.getDate("SYS_CREATION_DATE"),
                        rs.getDate("SYS_UPDATE_DATE")
                ));
            }
        } catch (Exception e) {
            Log.e(classFunc, "getAllBooks", "Error getting books", e);
        }
        return booksList;
    }


    public void addBook() {
        Log.l(classFunc, "addBook", "Starting");

        try (Connection connection = new DBConnection().getConnection()) {

            String query1 = "INSERT INTO " + BOOKS + " (NAME,PRICE,DESCRIPTION,CATEGORY,LIKES_NUM,REVIEWS_NUM,DELETED,SYS_CREATION_DATE,SYS_UPDATE_DATE,FILE_PATH,FILE_PRE_PATH,ICON_PATH) VALUES('name1',12.1,'book 1','horror',1,2,'N',null,NULL ,'books/1.html','books/1-pre.html','books/1.jpg')";
            String query2 = "INSERT INTO " + BOOKS + " (NAME,PRICE,DESCRIPTION,CATEGORY,LIKES_NUM,REVIEWS_NUM,DELETED,SYS_CREATION_DATE,SYS_UPDATE_DATE,FILE_PATH,FILE_PRE_PATH,ICON_PATH) VALUES('name2',12.1,'book 1','horror',1,2,'N',NULL,NULL ,'books/2.html','books/2-pre.html','books/2.jpg')";
            String query3 = "INSERT INTO " + BOOKS + " (NAME,PRICE,DESCRIPTION,CATEGORY,LIKES_NUM,REVIEWS_NUM,DELETED,SYS_CREATION_DATE,SYS_UPDATE_DATE,FILE_PATH,FILE_PRE_PATH,ICON_PATH) VALUES('name3',12.1,'book 1','horror',1,2,'N',NULL,NULL ,'books/3.html','books/3-pre.html','books/3.jpg')";
            String query4 = "INSERT INTO " + BOOKS + " (NAME,PRICE,DESCRIPTION,CATEGORY,LIKES_NUM,REVIEWS_NUM,DELETED,SYS_CREATION_DATE,SYS_UPDATE_DATE,FILE_PATH,FILE_PRE_PATH,ICON_PATH) VALUES('name4',12.1,'book 1','horror',1,2,'N',NULL,NULL ,'books/4.html','books/4-pre.html','books/4.jpg')";
            String query5 = "INSERT INTO " + BOOKS + " (NAME,PRICE,DESCRIPTION,CATEGORY,LIKES_NUM,REVIEWS_NUM,DELETED,SYS_CREATION_DATE,SYS_UPDATE_DATE,FILE_PATH,FILE_PRE_PATH,ICON_PATH) VALUES('name5',12.1,'book 1','horror',1,2,'N',NULL,NULL ,'books/5.html','books/5-pre.html','books/5.jpg')";
            String query6 = "INSERT INTO " + BOOKS + " (NAME,PRICE,DESCRIPTION,CATEGORY,LIKES_NUM,REVIEWS_NUM,DELETED,SYS_CREATION_DATE,SYS_UPDATE_DATE,FILE_PATH,FILE_PRE_PATH,ICON_PATH) VALUES('name6',12.1,'book 1','horror',1,2,'N',NULL,NULL ,'books/6.html','books/6-pre.html','books/6.jpg')";
            String query7 = "INSERT INTO " + BOOKS + " (NAME,PRICE,DESCRIPTION,CATEGORY,LIKES_NUM,REVIEWS_NUM,DELETED,SYS_CREATION_DATE,SYS_UPDATE_DATE,FILE_PATH,FILE_PRE_PATH,ICON_PATH) VALUES('name7',12.1,'book 1','horror',1,2,'N',NULL,NULL ,'books/7.html','books/7-pre.html','books/7.jpg')";
            String query8 = "INSERT INTO " + BOOKS + " (NAME,PRICE,DESCRIPTION,CATEGORY,LIKES_NUM,REVIEWS_NUM,DELETED,SYS_CREATION_DATE,SYS_UPDATE_DATE,FILE_PATH,FILE_PRE_PATH,ICON_PATH) VALUES('name8',12.1,'book 1','horror',1,2,'N',NULL,NULL ,'books/8.html','books/8-pre.html','books/8.jpg')";
            String query9 = "INSERT INTO " + BOOKS + " (NAME,PRICE,DESCRIPTION,CATEGORY,LIKES_NUM,REVIEWS_NUM,DELETED,SYS_CREATION_DATE,SYS_UPDATE_DATE,FILE_PATH,FILE_PRE_PATH,ICON_PATH) VALUES('name9',12.1,'book 1','horror',1,2,'N',NULL,NULL ,'books/9.html','books/9-pre.html','books/9.jpg')";
            String query10 = "INSERT INTO " + BOOKS + "(NAME,PRICE,DESCRIPTION,CATEGORY,LIKES_NUM,REVIEWS_NUM,DELETED,SYS_CREATION_DATE,SYS_UPDATE_DATE,FILE_PATH,FILE_PRE_PATH,ICON_PATH) VALUES('name10',12.1,'book 1','horror',1,2,'N',NULL ,NULL ,'books/10.html','books/10-pre.html','books/10.jpg')";
            PreparedStatement statement = connection.prepareStatement(query1);
            statement.executeUpdate();
            statement = connection.prepareStatement(query2);
            statement.executeUpdate();
            statement = connection.prepareStatement(query3);
            statement.executeUpdate();
            statement = connection.prepareStatement(query4);
            statement.executeUpdate();
            statement = connection.prepareStatement(query5);
            statement.executeUpdate();
            statement = connection.prepareStatement(query6);
            statement.executeUpdate();
            statement = connection.prepareStatement(query7);
            statement.executeUpdate();
            statement = connection.prepareStatement(query8);
            statement.executeUpdate();
            statement = connection.prepareStatement(query9);
            statement.executeUpdate();
            statement = connection.prepareStatement(query10);
            statement.executeUpdate();

            connection.commit();
            connection.close();
        } catch (Exception e) {
            Log.e(classFunc, "getAllBooks", "Error getting books", e);
        }
    }

    /**
     * Get all the books which has the same name
     *
     * @param bookName - the list of the books to retrieve
     * @return list of books which specific name
     */
    public List<Book> getBooksByName(String bookName) {
        Log.l(classFunc, "getBooksByName", "Starting");

        List<Book> booksList = new ArrayList<>();

        try (Connection connection = new DBConnection().getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SELECT_BOOKS_BY_BOOK_NAME);
            statement.setString(1, bookName);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                booksList.add(new Book(
                        rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getDouble("PRICE"),
                        rs.getString("DESCRIPTION"),
                        rs.getInt("LIKES_NUM"),
                        rs.getInt("REVIEWS_NUM"),
                        rs.getString("DELETED"),
                        rs.getString("FILE_PATH"),
                        rs.getString("FILE_PRE_PATH"),
                        rs.getString("ICON_PATH"),
                        rs.getDate("SYS_CREATION_DATE"),
                        rs.getDate("SYS_UPDATE_DATE")
                ));
            }
        } catch (Exception e) {
            Log.e(classFunc, "getBooksByName", "Error getting books", e);
        }
        return booksList;
    }

    /**
     * Gets a book by its ID
     *
     * @param id - id of the book
     * @return Book Object of the given id
     */
    public Book getBookByID(int id) {
        Log.l(classFunc, "getBookByID", "Starting");

        try (Connection connection = new DBConnection().getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SELECT_BOOK_BY_ID);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Book(
                        rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getDouble("PRICE"),
                        rs.getString("DESCRIPTION"),
                        rs.getInt("LIKES_NUM"),
                        rs.getInt("REVIEWS_NUM"),
                        rs.getString("DELETED"),
                        rs.getString("FILE_PATH"),
                        rs.getString("FILE_PRE_PATH"),
                        rs.getString("ICON_PATH"),
                        rs.getDate("SYS_CREATION_DATE"),
                        rs.getDate("SYS_UPDATE_DATE")
                );
            }
            Log.l(classFunc, "getBookByID", "Did not find book by id = " + id);
        } catch (Exception e) {
            Log.e(classFunc, "getBookByID", "Error getting book by id " + id, e);
        }
        return new Book();
    }

    /**
     * Gets a book by its ID
     *
     * @param category - category of the book
     * @return Book Object of the given id
     */
    public Book getBookByCategory(String category) {
        Log.l(classFunc, "getBookByCategory", "Starting");

        try (Connection connection = new DBConnection().getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SELECT_BOOK_BY_CATEGORY);
            statement.setString(1, category);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Book(
                        rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getDouble("PRICE"),
                        rs.getString("DESCRIPTION"),
                        rs.getInt("LIKES_NUM"),
                        rs.getInt("REVIEWS_NUM"),
                        rs.getString("DELETED"),
                        rs.getString("FILE_PATH"),
                        rs.getString("FILE_PRE_PATH"),
                        rs.getString("ICON_PATH"),
                        rs.getDate("SYS_CREATION_DATE"),
                        rs.getDate("SYS_UPDATE_DATE")
                );
            }
            Log.l(classFunc, "getBookByCategory", "Did not find book by  category= " + category);
        } catch (Exception e) {
            Log.e(classFunc, "getBookByCategory", "Error getting book by category " + category, e);
        }
        return new Book();
    }

    /**
     * Updates the like count to the book
     *
     * @param id    - book id to add the count
     * @param added - flag to check if count is added or decreased
     */
    public void updateLikeCountToBookID(int id, Boolean added) {
        Log.l(classFunc, "addLikeCountToBookID", "Starting");

        try (Connection connection = new DBConnection().getConnection()) {

            PreparedStatement statement;
            if (added)
                statement = connection.prepareStatement(ADD_BOOK_LIKE_COUNT_BY_ID);
            else
                statement = connection.prepareStatement(REMOVE_BOOK_LIKE_COUNT_BY_ID);
            statement.setDate(1, new Date(Calendar.getInstance().getTimeInMillis()));
            statement.setInt(2, id);

            if (statement.executeUpdate() == 0)
                throw new RuntimeException("Error updating count like, please try again");
            connection.commit();
        } catch (Exception e) {
            Log.e(classFunc, "addLikeCountToBookID", e.getMessage(), e);
        }
    }


    /**
     * Add the review count to the book
     *
     * @param id    - book id to add the count
     * @param added - flag to check if count is added or decreased
     */
    public void updateReviewCountToBookID(int id, Boolean added) {
        Log.l(classFunc, "updateReviewCountToBookID", "Starting");

        try (Connection connection = new DBConnection().getConnection()) {
            PreparedStatement statement;
            if (added)
                statement = connection.prepareStatement(ADD_BOOK_REVIEW_COUNT_BY_ID);
            else
                statement = connection.prepareStatement(REMOVE_BOOK_REVIEW_COUNT_BY_ID);

            statement.setDate(1, new Date(Calendar.getInstance().getTimeInMillis()));
            statement.setInt(2, id);

            if (statement.executeUpdate() == 0)
                throw new RuntimeException("Error updating review like count, please try again");
            connection.commit();
        } catch (Exception e) {
            Log.e(classFunc, "updateReviewCountToBookID", e.getMessage(), e);
        }
    }

    /**
     * Marks book as deleted or active in the system
     *
     * @param id           - book id to update
     * @param deleteStatus - delete status, Y or N
     */
    public void deleteBook(int id, String deleteStatus) {
        Log.l(classFunc, "deleteBook", "Starting");
        try {
            Book book = getBookByID(id);
            if (book.getID() == null) {
                throw new RuntimeException("book id - " + id + " - not found");
            }
            Connection connection = new DBConnection().getConnection();
            PreparedStatement statement = connection.prepareStatement(SET_DELETED_BOOK_BY_ID);
            statement.setString(1, deleteStatus);
            statement.setDate(2, new Date(Calendar.getInstance().getTimeInMillis()));
            statement.setInt(3, id);

            if (statement.executeUpdate() == 0) {
                throw new RuntimeException("Error while activating or deactivating book id " + id);
            }
            Log.l(classFunc, "deleteBook", "book id " + id + " has been marked deleted - " + deleteStatus);
            connection.commit();
        } catch (Exception e) {
            Log.e(classFunc, "deleteBook", "Error while activating or deactivating book", e);
        }
    }


}
