package booksforall.dao;

import booksforall.db.DBConnection;
import booksforall.models.User;
import booksforall.utils.Log;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static booksforall.utils.Constants.INSERT_NEW_USER;
import static booksforall.utils.Constants.SELECT_ALL_USERS;
import static booksforall.utils.Constants.SELECT_USER_BY_USERNAME;

public class UserDAO {


    private static final String classFunc = UserDAO.class.getSimpleName();

    /**
     * Get a user information by username
     *
     * @param username the username which we need to retrieve data from
     * @return User object
     */
    public User getUserByUsername(String username) {
        Log.l(classFunc, "getUserByUsername", "Starting");

        try (Connection connection = new DBConnection().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_USERNAME);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getString("USERNAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PASSWORD"),
                        rs.getInt("PHONE_NUMBER"),
                        rs.getString("NICKNAME"),
                        rs.getString("DESCRIPTION"),
                        rs.getString("PHOTO"),
                        rs.getString("ROLE"),
                        rs.getString("DELETED"),
                        rs.getDate("SYS_CREATION_DATE"),
                        rs.getDate("SYS_UPDATE_DATE")
                );
            } else {
                Log.l(classFunc, "getUserByUsername", "username : " + username + " was not found in DB");
            }
        } catch (Exception e) {
            Log.e(classFunc, "getUserByUsername", "could not retrieve username : " + username + ", " + e.getMessage(), e);
        }
        return new User();
    }


    /**
     * Gets all the users in the DB
     *
     * @return List of users
     */
    public List<User> getAllUsers() {

        Log.l(classFunc, "getAllUsers", "Starting");

        try (Connection connection = new DBConnection().getConnection()) {
            List<User> users = new ArrayList<>();
            try (PreparedStatement stm = connection.prepareStatement(SELECT_ALL_USERS)) {
                try (ResultSet rs = stm.executeQuery()) {
                    Log.l(classFunc, "getAllUsers", "Users found, creating list");
                    while (rs.next()) {
                        users.add(new User(
                                rs.getString("USERNAME"),
                                rs.getString("EMAIL"),
                                rs.getString("PASSWORD"),
                                rs.getInt("PHONE_NUMBER"),
                                rs.getString("NICKNAME"),
                                rs.getString("DESCRIPTION"),
                                rs.getString("PHOTO"),
                                rs.getString("ROLE"),
                                rs.getString("DELETED"),
                                rs.getDate("SYS_CREATION_DATE"),
                                rs.getDate("SYS_UPDATE_DATE")
                        ));
                    }
                    return users;
                }
            }
        } catch (Exception e) {
            Log.e(classFunc, "getAllUsers", "Error getting all users", e);
            return new ArrayList<>();
        } finally {
            Log.l(classFunc, "getAllUsers", "Done");
        }
    }

    /**
     * Add new use to the system
     *
     * @param user register request with all the information to add
     */
    public void addNewUser(User user, String password) {
        Log.l(classFunc, "addNewUser", "Starting");

        try (Connection connection = new DBConnection().getConnection()) {
            String username = getUserByUsername(user.getUsername()).getUsername();
            if (username == null || username.isEmpty()) {
                PreparedStatement statement = connection.prepareStatement(INSERT_NEW_USER);
                statement.setString(1,user.getUsername());
                statement.setString(2,user.getEmail());
                statement.setString(3,password);
                statement.setInt(4,user.getPhoneNumber());
                statement.setString(5,user.getNickname());
                statement.setString(6,user.getDescription());
                statement.setString(7,user.getPhotoUrl());
                statement.setString(8,user.getRole());
                statement.setString(9,user.getDeleted());
                statement.setDate(10, new Date(Calendar.getInstance().getTimeInMillis()));
                statement.setDate(11, null);

                if (statement.executeUpdate() == 0) {
                    Log.l(classFunc, "addNewUser", "User has been not been added: " + user.getUsername());
                    throw new RuntimeException("User has been not been added: " + user.getUsername());
                }
                connection.commit();
                Log.l(classFunc, "addNewUser", "User has been added successfully, username : " + user.getUsername());
                return;
            }
            throw new RuntimeException("Username already exists");
        } catch (Exception e) {
            Log.e(classFunc, "addNewUser", "Exception, Error adding user :" + user.getUsername(), e);
        }
    }
}
