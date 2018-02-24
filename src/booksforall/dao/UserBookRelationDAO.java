package booksforall.dao;

import booksforall.db.DBConnection;
import booksforall.models.*;
import booksforall.utils.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static booksforall.utils.Constants.*;

public class UserBookRelationDAO {

    private final static String classFunc = BookDAO.class.getSimpleName();

    /**
     * Add new User Book Like relation
     *
     * @param user - user who liked
     * @param book - book has been liked
     */
    public void addUserBookLike(User user, Book book) {
        Log.l(classFunc, "AddUserBookLike", "Starting");

        try (Connection connection = new DBConnection().getConnection()) {

            PreparedStatement statement = connection.prepareStatement(ADD_USER_BOOK_LIKE);
            statement.setString(1, user.getUsername());
            statement.setInt(2, book.getID());
            statement.setString(3, user.getNickname());

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

    /**
     * delete user book like relation
     *
     * @param user user who liked
     * @param book book has been liked
     */
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

    /**
     * get user book like relation
     *
     * @param user user who liked
     * @param book book has been liked
     * @return user book relation object
     */
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
                        rs.getInt("BOOK_ID"),
                        rs.getString("NICKNAME")
                );
            } else
                throw new RuntimeException("There was not relations for user " + user.getUsername() + " and book " + book.getID());

        } catch (Exception e) {
            Log.e(classFunc, "getUserBookLikeRelation", e.getMessage(), e);
        }
        return null;
    }

    /**
     * Gets all user likes
     *
     * @param user user object
     * @return list with all user book like relation
     */
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
                        rs.getInt("BOOK_ID"),
                        rs.getString("NICKNAME")
                ));
            }
        } catch (Exception e) {
            Log.e(classFunc, "getUserBooksLikeByUser", e.getMessage(), e);
        }
        return userBookLikeRelationList;
    }

    /**
     * get all likes of a book
     *
     * @param book book object
     * @return list of all user book relation
     */
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
                        rs.getInt("BOOK_ID"),
                        rs.getString("NICKNAME")
                ));
            }
        } catch (Exception e) {
            Log.e(classFunc, "getUserBooksLikeByBook", e.getMessage(), e);
        }
        return userBookLikeRelationList;
    }

    /**
     * add new review to book
     *
     * @param user   - user object
     * @param book   - book object
     * @param review - review string
     * @return - user book relation object
     */
    public UserBookReviewRelation addUserBookReview(User user, Book book, String review) {
        Log.l(classFunc, "addUserBookReview", "Starting");

        try (Connection connection = new DBConnection().getConnection()) {

            Date date = new Date(Calendar.getInstance().getTimeInMillis());
            PreparedStatement statement = connection.prepareStatement(ADD_USER_BOOK_REVIEW, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUsername());
            statement.setInt(2, book.getID());
            statement.setString(3, review);
            statement.setString(4, "N");
            statement.setDate(5, date);

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Could not add new user ( " + user.getUsername() + " book " + book.getID() + "  review " + review);
            } else {
                connection.commit();
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next())
                    return new UserBookReviewRelation(generatedKeys.getInt(1), user.getUsername(), book.getID(), review, "N", date);
                else
                    throw new SQLException("Creating review failed, no ID obtained.");
            }
        } catch (Exception e) {
            Log.e(classFunc, "addUserBookReview", e.getMessage(), e);
        }
        return null;
    }

    /**
     * Approve user review
     *
     * @param bookReviewRelation user book relation object
     */
    public void approveUserBookReview(UserBookReviewRelation bookReviewRelation) {
        Log.l(classFunc, "approveUserBookReview", "Starting");

        try (Connection connection = new DBConnection().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(APPROVE_USER_BOOK_REVIEW);
            statement.setInt(1, bookReviewRelation.getId());

            if (statement.executeUpdate() == 0) {
                throw new SQLException("Error Approving review");
            }
            connection.commit();
        } catch (Exception e) {
            Log.e(classFunc, "approveUserBookReview", e.getMessage(), e);
        }
    }

    /**
     * get review by id
     *
     * @param reviewID review id to retrieve
     * @return UserBookReviewRelation object
     */
    public UserBookReviewRelation getReviewByID(int reviewID) {
        Log.l(classFunc, "getReviewByID", "Starting");

        try (Connection connection = new DBConnection().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_REVIEW_BY_ID);
            statement.setInt(1, reviewID);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new UserBookReviewRelation(
                        rs.getInt("ID"),
                        rs.getString("USERNAME"),
                        rs.getInt("BOOK_ID"),
                        rs.getString("REVIEW"),
                        rs.getString("APPROVED"),
                        rs.getDate("SYS_CREATION_DATE")
                );
            }
        } catch (Exception e) {
            Log.e(classFunc, "approveUserBookReview", e.getMessage(), e);
        }
        Log.l(classFunc, "getReviewByID", "no review found, review id " + reviewID);
        return new UserBookReviewRelation();
    }

    /**
     * get all book reviews
     *
     * @param book - book object
     * @return list of book review relation
     */
    public List<UserBookReviewRelation> getAllBookReviews(Book book) {
        Log.l(classFunc, "getAllBookReviews", "Starting");
        List<UserBookReviewRelation> userBookReviewRelationList = new ArrayList<>();

        try (Connection connection = new DBConnection().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BOOK_REVIEWS);
            statement.setInt(1, book.getID());

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                userBookReviewRelationList.add(new UserBookReviewRelation(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6)
                ));
            }
        } catch (Exception e) {
            Log.e(classFunc, "getAllBookReviews", e.getMessage(), e);
        }
        return userBookReviewRelationList;
    }


    /**
     * buy e-book
     *
     * @param user - user object
     * @param book - book object
     */
    public void buyBook(User user, Book book) {
        Log.l(classFunc, "buyBook", "Starting");

        UserBookPurchaseRelation userBookPurchaseRelation = getUserBookPurchaseRelation(user, book);

        if (userBookPurchaseRelation == null) {
            try (Connection connection = new DBConnection().getConnection()) {

                PreparedStatement statement = connection.prepareStatement(ADD_USER_BOOK_PURCHASE);
                statement.setString(1, user.getUsername());
                statement.setInt(2, book.getID());
                statement.setDouble(3, book.getPrice());
                
                if (statement.executeUpdate() == 0) {
                    throw new RuntimeException("Error purchasing book " + book.getID() + " for username " + user.getUsername());
                }
                connection.commit();
            } catch (Exception e) {
                Log.e(classFunc, "buyBook", e.getMessage(), e);
            }
        } else {
            Log.l(classFunc, "buyBook", "username " + user.getUsername() + " already purchased book " + book.getID());
        }
    }

    /**
     * Get user book purchase relation
     *
     * @param user - user object
     * @param book - book object
     * @return - UserBookPurchaseRelation object
     */
    public UserBookPurchaseRelation getUserBookPurchaseRelation(User user, Book book) {
        Log.l(classFunc, "getUserBookPurchaseRelation", "Starting");

        try (Connection connection = new DBConnection().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_USER_BOOK_PURCHASE);
            statement.setString(1, user.getUsername());
            statement.setInt(2, book.getID());

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new UserBookPurchaseRelation(
                        rs.getString("USERNAME"),
                        rs.getInt("BOOK_ID"),
                        rs.getDouble("PRICE"),
                        rs.getDate("SYS_CREATION_DATE")
                );
            } else {
                throw new RuntimeException("no purchase found for username " + user.getUsername() + " and book " + book.getID());
            }
        } catch (Exception e) {
            Log.e(classFunc, "getUserBookPurchaseRelation", e.getMessage(), e);
        }
        return null;
    }

    /**
     * Gets all the purchases
     *
     * @return List<UserBookPurchaseRelation>
     */
    public List<UserBookPurchaseRelation> getAllPurchases() {
        Log.l(classFunc, "getAllPurchases", "Starting");

        List<UserBookPurchaseRelation> userBookPurchaseRelationList = new ArrayList<>();
        try (Connection connection = new DBConnection().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_PURCHASES);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                userBookPurchaseRelationList.add(new UserBookPurchaseRelation(
                        rs.getString("USERNAME"),
                        rs.getInt("BOOK_ID"),
                        rs.getDouble("PRICE"),
                        rs.getDate("SYS_CREATION_DATE")
                ));
            }
        } catch (Exception e) {
            Log.e(classFunc, "userBookPurchaseRelationList", e.getMessage(), e);
        }
        return userBookPurchaseRelationList;
    }

    /**
     * Get all books not purchased bu user
     *
     * @param user user object
     * @return list of books
     */
    public List<Book> getNotPurchasedBooksByUsername(User user) {
        Log.l(classFunc, "getAllBooksNotPurchasedByUserID", "Starting");

        List<Book> booksList = new ArrayList<>();

        try (Connection connection = new DBConnection().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_BOOKS_NOT_PURCHASED_BY_USER);
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
                        rs.getString("ICON_PATH"),
                        rs.getDate("SYS_CREATION_DATE"),
                        rs.getDate("SYS_UPDATE_DATE")
                ));
            }
        } catch (Exception e) {
            Log.e(classFunc, "getAllBooksNotPurchasedByUserID", e.getMessage(), e);
        }
        return booksList;
    }

    /**
     * get all books purchased by user
     *
     * @param user user object
     * @return list of books
     */
    public List<Book> getAllBooksPurchasedByUserID(User user) {
        Log.l(classFunc, "getAllBooksPurchasedByUserID", "Starting");

        List<Book> booksList = new ArrayList<>();

        try (Connection connection = new DBConnection().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_BOOKS_PURCHASED_BY_USER);
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
                        rs.getString("ICON_PATH"),
                        rs.getDate("SYS_CREATION_DATE"),
                        rs.getDate("SYS_UPDATE_DATE")
                ));
            }
        } catch (Exception e) {
            Log.e(classFunc, "getAllBooksPurchasedByUserID", e.getMessage(), e);
        }
        return booksList;
    }


    /**
     * get the position of the reading position for username and book id
     *
     * @param username username
     * @param bookId   book id
     * @return UserBookPositionRelation object
     */
    public UserBookPositionRelation getUserBookPosition(String username, int bookId) {
        Log.l(classFunc, "getUserBookPosition", "Starting");

        try (Connection connection = new DBConnection().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_USER_BOOK_POSITION);
            statement.setString(1, username);
            statement.setInt(2, bookId);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new UserBookPositionRelation(
                        username,
                        bookId,
                        rs.getFloat("POSITION"),
                        rs.getDate("SYS_CREATION_DATE"));
            }
        } catch (Exception e) {
            Log.e(classFunc, "getUserBookPosition", e.getMessage(), e);
        }
        return new UserBookPositionRelation();
    }


    /**
     * Adds new relation for user book position
     *
     * @param username username
     * @param bookId   book id
     * @param position position percent of html
     */
    public void addUserBookPosition(String username, int bookId, Float position) {
        Log.l(classFunc, "addUserBookPosition", "Starting");

        try (Connection connection = new DBConnection().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_USER_BOOK_POSITION);
            statement.setString(1, username);
            statement.setInt(2, bookId);
            statement.setFloat(3, position);

            if (statement.executeUpdate() == 0) {
                Log.l(classFunc, "addUserBookPosition", "error adding position, username " + username +
                        " , bookId " + bookId + " position " + position);
                throw new RuntimeException("Error adding user book position relation");
            }
            connection.commit();
            connection.close();
        } catch (Exception e) {
            Log.e(classFunc, "addUserBookPosition", e.getMessage(), e);
        }
    }

    /**
     * Saves position of reading
     *
     * @param username username
     * @param bookId   book id
     * @param position position percent of html
     */
    public void saveUserBookPosition(String username, int bookId, Float position) {
        Log.l(classFunc, "saveUserBookPosition", "Starting");

        try (Connection connection = new DBConnection().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SET_USER_BOOK_POSITION);
            statement.setFloat(1, position);
            statement.setString(2, username);
            statement.setInt(3, bookId);

            if (statement.executeUpdate() == 0) {
                Log.l(classFunc, "saveUserBookPosition", "error saving position, username " + username +
                        " , bookId " + bookId + " position " + position);
                throw new RuntimeException("Error saving user book position relation");
            }
            connection.commit();
            connection.close();
        } catch (Exception e) {
            Log.e(classFunc, "saveUserBookPosition", e.getMessage(), e);
        }
    }
}

