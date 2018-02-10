package booksforall.dao;

import booksforall.db.DBConnection;
import booksforall.models.Address;
import booksforall.models.User;
import booksforall.utils.Log;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static booksforall.utils.Constants.*;

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
                Address address = new Address(
                        rs.getString("STREET"),
                        rs.getInt("HOUSE_NUMBER"),
                        rs.getString("CITY"),
                        rs.getString("ZIP"),
                        rs.getString("COUNTRY")
                );
                return new User(
                        rs.getString("USERNAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PASSWORD"),
                        address,
                        rs.getString("PHONE_NUMBER"),
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
                        Address address = new Address(
                                rs.getString("STREET"),
                                rs.getInt("HOUSE_NUMBER"),
                                rs.getString("CITY"),
                                rs.getString("ZIP"),
                                rs.getString("COUNTRY")
                        );

                        users.add(new User(
                                rs.getString("USERNAME"),
                                rs.getString("EMAIL"),
                                rs.getString("PASSWORD"),
                                address,
                                rs.getString("PHONE_NUMBER"),
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
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getEmail());
                statement.setString(3, password);
                statement.setString(4, user.getAddress().getStreet());
                statement.setInt(5, user.getAddress().getNumber());
                statement.setString(6, user.getAddress().getCity());
                statement.setString(7, user.getAddress().getZip());
                statement.setString(8, user.getAddress().getCountry());
                statement.setString(9, user.getPhoneNumber());
                statement.setString(10, user.getNickname());
                statement.setString(11, user.getDescription());
                statement.setString(12, user.getPhotoUrl());
                statement.setString(13, user.getRole());
                statement.setString(14, user.getDeleted());
                statement.setDate(15, new Date(Calendar.getInstance().getTimeInMillis()));
                statement.setDate(16, null);

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


    /**
     * Marks user as deleted in DB
     *
     * @param username - username to delete
     */
    public void deleteUser(String username, String deleteStatus) {
        Log.l(classFunc, "deleteUser", "Starting");
        try {
            User user = getUserByUsername(username);
            if (user.getUsername() == null || user.getUsername().isEmpty()) {
                throw new RuntimeException("Username - " + username + " - not found");
            }

            Connection connection = new DBConnection().getConnection();
            PreparedStatement statement = connection.prepareStatement(SET_DELETED_USER_BY_USERNAME);
            statement.setString(1, deleteStatus);
            statement.setDate(2, new Date(Calendar.getInstance().getTimeInMillis()));
            statement.setString(3, username);

            if (statement.executeUpdate() == 0) {
                throw new RuntimeException("Error while activating or deactivating user " + username);
            }

            Log.l(classFunc, "deleteUser", "user " + username + " has been marked deleted - " + deleteStatus);
        } catch (Exception e) {
            Log.e(classFunc, "deleteUser", "Error while activating or deactivating user", e);
        }
    }


    /**
     * Gets user buy username and password combination
     *
     * @param username username to check
     * @param password password to check
     * @return user object
     */
    public User getUserByUsernameAndPassword(String username, String password) {
        Log.l(classFunc, "getUserByUsernameAndPassword", "Starting");

        try {
            Connection connection = new DBConnection().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_WHERE_USERNAME_AND_PASSWORD);
            statement.setString(1, username);
            statement.setString(1, password);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Address address = new Address(
                        rs.getString("STREET"),
                        rs.getInt("HOUSE_NUMBER"),
                        rs.getString("CITY"),
                        rs.getString("ZIP"),
                        rs.getString("COUNTRY")
                );

                return new User(
                        rs.getString("USERNAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PASSWORD"),
                        address,
                        rs.getString("PHONE_NUMBER"),
                        rs.getString("NICKNAME"),
                        rs.getString("DESCRIPTION"),
                        rs.getString("PHOTO"),
                        rs.getString("ROLE"),
                        rs.getString("DELETED"),
                        rs.getDate("SYS_CREATION_DATE"),
                        rs.getDate("SYS_UPDATE_DATE"));
            } else
                throw new RuntimeException("Username - " + username + " - not found");
        } catch (Exception e) {
            Log.e(classFunc, "getUserByUsernameAndPassword", "Error getting user by username and password", e);
        }
        return new User();
    }

    /**
     * update username password
     *
     * @param username    username to update
     * @param oldPassword old password
     * @param newPassword new password
     * @return true if password changed successfully
     */
    public Boolean updatePasswordByUsernameAndPassword(String username, String oldPassword, String newPassword) {
        Log.l(classFunc, "updatePasswordByUsernameAndPassword", "Starting");

        try {
            User user = getUserByUsernameAndPassword(username, oldPassword);
            if (user.getUsername() == null || user.getUsername().isEmpty()) {
                throw new RuntimeException("Username - " + username + " - not found");
            }

            Connection connection = new DBConnection().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_PASSWORD);
            statement.setString(1, newPassword);
            statement.setString(2, username);
            statement.setString(3, oldPassword);

            if (statement.executeUpdate() == 0) {
                Log.l(classFunc, "updatePasswordByUsernameAndPassword", "error updating password" + user.getUsername());
                throw new RuntimeException("Password has not been changed: " + user.getUsername());
            }

            connection.commit();
            Log.l(classFunc, "updatePasswordByUsernameAndPassword", "Password changed successfully");
            return true;

        } catch (Exception e) {
            Log.e(classFunc, "updatePasswordByUsernameAndPassword", "Exception, error updating password :" + username, e);
        }
        return false;
    }

    /**
     * search user with username like parameter given
     * @param username username to search
     * @return List of Users
     */
    public List<User> searchUser(String username) {
        Log.l(classFunc, "searchUser", "Starting");
        List<User> users = new ArrayList<>();
        try (Connection connection = new DBConnection().getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SEARCH_USER);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Address address = new Address(
                        rs.getString("STREET"),
                        rs.getInt("HOUSE_NUMBER"),
                        rs.getString("CITY"),
                        rs.getString("ZIP"),
                        rs.getString("COUNTRY")
                );
                users.add(new User(
                        rs.getString("USERNAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PASSWORD"),
                        address,
                        rs.getString("PHONE_NUMBER"),
                        rs.getString("NICKNAME"),
                        rs.getString("DESCRIPTION"),
                        rs.getString("PHOTO"),
                        rs.getString("ROLE"),
                        rs.getString("DELETED"),
                        rs.getDate("SYS_CREATION_DATE"),
                        rs.getDate("SYS_UPDATE_DATE")
                ));
            }
        } catch (Exception e) {
            Log.e(classFunc, "searchUser", "could not retrieve username : " + username + ", " + e.getMessage(), e);
        }
        return users;
    }
}

