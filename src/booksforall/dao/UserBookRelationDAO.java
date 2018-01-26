package booksforall.dao;

import booksforall.db.DBConnection;
import booksforall.models.Book;
import booksforall.models.User;
import booksforall.models.UserBookLikeRelation;
import booksforall.utils.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static booksforall.utils.Constants.*;

public class UserBookRelationDAO {

    private final static String classFunc = BookDAO.class.getSimpleName();

    public void addUserBookLike(User user, Book book) {
        Log.l(classFunc, "AddUserBookLike", "Starting");

        try (Connection connection = new DBConnection().getConnection()) {

            PreparedStatement statement = connection.prepareStatement(ADD_USER_BOOK_LIKE);
            statement.setString(1, user.getUsername());
            statement.setInt(2, book.getID());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Could not add new user ( " + user.getUsername() + " book " + book.getID() + " relation like");
            } else {
                Log.l(classFunc, "AddUserBookLike", "New user ( " + user.getUsername() + " book " + book.getID() + " relation like added");
            }
            connection.commit();
        } catch (Exception e) {
            Log.e(classFunc, "AddUserBookLike", e.getMessage(), e);
        }
    }

    public void deleteUserBookLike(User user, Book book) {
        Log.l(classFunc, "deleteUserBookLike", "Starting");

        try (Connection connection = new DBConnection().getConnection()) {

            PreparedStatement statement = connection.prepareStatement(DELETE_USER_BOOK_LIKE);
            statement.setString(1, user.getUsername());
            statement.setInt(2, book.getID());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Could not delete user ( " + user.getUsername() + " book " + book.getID() + " relation like");
            } else {
                Log.l(classFunc, "deleteUserBookLike", "user ( " + user.getUsername() + " book " + book.getID() + " relation like deleted");
            }
            connection.commit();
        } catch (Exception e) {
            Log.e(classFunc, "deleteUserBookLike", e.getMessage(), e);
        }
    }

    public UserBookLikeRelation getUserBookLikeRelation(User user, Book book) {
        Log.l(classFunc, "getUserBookLikeRelation", "Starting");

        try (Connection connection = new DBConnection().getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BOOK_LIKE);
            statement.setString(1, user.getUsername());
            statement.setInt(2, book.getID());

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new UserBookLikeRelation(
                        rs.getString("USERNAME"),
                        rs.getInt("BOOK_ID")
                );
            } else
                throw new RuntimeException("There was not relations for user " + user.getUsername() + " and book " + book.getID());

        } catch (Exception e) {
            Log.e(classFunc, "getUserBookLikeRelation", e.getMessage(), e);
        }
        return null;
    }

    public List<UserBookLikeRelation> getUserBooksLikeByUser(User user) {
        Log.l(classFunc, "getUserBooksLikeByUser", "Starting");
        List<UserBookLikeRelation> userBookLikeRelationList = new ArrayList<>();
        try (Connection connection = new DBConnection().getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BOOK_LIKE_BY_USER);
            statement.setString(1, user.getUsername());

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                userBookLikeRelationList.add(new UserBookLikeRelation(
                        rs.getString("USERNAME"),
                        rs.getInt("BOOK_ID")
                ));
            }
        } catch (Exception e) {
            Log.e(classFunc, "getUserBooksLikeByUser", e.getMessage(), e);
        }
        return userBookLikeRelationList;
    }

    public List<UserBookLikeRelation> getUserBooksLikeByBook(Book book) {
        Log.l(classFunc, "getUserBooksLikeByBook", "Starting");
        List<UserBookLikeRelation> userBookLikeRelationList = new ArrayList<>();
        try (Connection connection = new DBConnection().getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BOOK_LIKE_BY_BOOK);
            statement.setInt(1, book.getID());

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                userBookLikeRelationList.add(new UserBookLikeRelation(
                        rs.getString("USERNAME"),
                        rs.getInt("BOOK_ID")
                ));
            }
        } catch (Exception e) {
            Log.e(classFunc, "getUserBooksLikeByBook", e.getMessage(), e);
        }
        return userBookLikeRelationList;
    }
}

