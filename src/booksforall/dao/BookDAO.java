package booksforall.dao;

import booksforall.db.DBConnection;
import booksforall.models.Book;
import booksforall.utils.Log;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                        rs.getDate("SYS_CREATION_DATE"),
                        rs.getDate("SYS_UPDATE_DATE")
                ));
            }
        } catch (Exception e) {
            Log.e(classFunc, "getAllBooks", "Error getting books", e);
        }
        return booksList;
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

            PreparedStatement statement = connection.prepareStatement(SELECT_BOOKS_BY_BOOKNAME);
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
        Log.l(classFunc, "getBooksByName", "Starting");

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
     * Updates the like count to the book
     *
     * @param id    - book id to add the count
     * @param added - flag to check if count is added or decreased
     */
    public void addLikeCountToBookID(int id, Boolean added) {
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
        } catch (Exception e) {
            Log.e(classFunc, "deleteBook", "Error while activating or deactivating book", e);
        }
    }

}
