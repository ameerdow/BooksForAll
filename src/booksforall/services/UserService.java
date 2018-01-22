package booksforall.services;

import booksforall.models.User;
import booksforall.models.UserBookPurchaseRelation;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Date;
import java.util.List;

public class UserService {

    /**
     * Updates the userpassword
     * @param username - username which updating his password
     * @param oldPassword - old password to verify
     * @param newPassword - new password to update
     */
    public void updateUserPassword(String username, String oldPassword, String newPassword){

    }

    /**
     * updates the user data, like photo, desc, nickname
     * @param oldData - old data of the user - to retrieve information
     * @param newData - new data to update.
     */
    public void updateUserData(User oldData, User newData){

    }


    /**
     * Create new user
     * @param username - unique
     * @param password
     * @param address
     * @param phoneNumber
     * @param nickname
     * @param description
     * @param photoUrl
     * @return User object with the new user after adding it to DB.
     */
    public User addUser(String username, String password, String address, int phoneNumber, String nickname,
                        String description, String photoUrl){
        //check if data is correct

        //create new user object

        //add user obj to DB

        //return new user object

        throw new NotImplementedException();
    }

    /**
     * Marks user as delete in DB
     * @param username - username to be deleted
     */
    public void deleteUser(String username){
        //update database user is deleted
    }

    /**
     * Login to the system
     * @param username - username for login
     * @param password - password for login
     * @return User Object of the user details
     */
    public User login(String username, String password){
        throw new NotImplementedException();
    }


    /**
     * Get all books purchased by user
     * @param username - username to retried the books
     * @return List of books relations with specific user.
     */
    public List<UserBookPurchaseRelation> getPurchasedBooksByUsername(String username){
        throw new NotImplementedException();
    }


    /**
     * Get all users of the system
     * @return List of user objects
     */
    public List<User> getAllUsers(){
        throw new NotImplementedException();
    }

    public User getUserByUsername(String username){
        throw new NotImplementedException();
    }
}
