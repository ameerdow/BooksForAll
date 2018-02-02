package booksforall.services;

import booksforall.dao.BookDAO;
import booksforall.dao.UserBookRelationDAO;
import booksforall.dao.UserDAO;
import booksforall.models.Address;
import booksforall.models.Book;
import booksforall.models.User;
import booksforall.models.UserBookReviewRelation;
import booksforall.utils.Log;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {

    private final String classFunc = UserService.class.getSimpleName();

    private User getUser(String username){
        Log.l(classFunc, "getUser", "Starting");
        UserDAO userDAO = new UserDAO();
        return new User(userDAO.getUserByUsername(username));
    }

    private Book getBook(int bookId){
        Log.l(classFunc, "getBook", "Starting");
        BookDAO bookDAO = new BookDAO();
        return new Book(bookDAO.getBookByID(bookId));
    }


    /**
     * Updates the userpassword
     *
     * @param username    - username which updating his password
     * @param oldPassword - old password to verify
     * @param newPassword - new password to update
     */
    public Boolean updateUserPassword(String username, String oldPassword, String newPassword) {
        Log.l(classFunc, "updateUserPassword", "Starting");
        if (validateUsername(username) && validatePassword(oldPassword) && validatePassword(newPassword)) {
            UserDAO userDAO = new UserDAO();
            return userDAO.updatePasswordByUsernameAndPassword(username, oldPassword, newPassword);
        } else
            Log.l(classFunc, "updateUserPassword", "username or password validation failed, username " + username + " old pass " + oldPassword
                    + " new pass " + newPassword);
        return false;
    }

    /**
     * validates if password follows the criteria
     *
     * @param password password to check
     * @return true is password constructed correctly, otherwise false
     */
    private boolean validatePassword(String password) {
        return !(password.isEmpty() || password.equals("") || password.length() > 8);
    }

    /**
     * validates if password follows the criteria
     *
     * @param username username ot check
     * @return true is username constructed correctly, otherwise false
     */
    private boolean validateUsername(String username) {
        if (username.isEmpty() || username.equals("") || username.length() > 10)
            return false;
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(username);
        boolean b = m.find();
        return !b;
    }

    /**
     * Create new user
     *
     * @param username
     * @param email
     * @param password
     * @param address     Address object
     * @param phoneNumber
     * @param nickname
     * @param description optional
     * @param photoUrl    optional
     * @return User object
     */
    public User addUser(String username, String email, String password, Address address, String phoneNumber, String nickname,
                        String description, String photoUrl) {
        User user;
        //check if data is correct
        if (validateUsername(username) && validatePassword(password) && validateAddress(address) && validateNickname(nickname)
                && validateDescription(description) && validatePhoneNumber(phoneNumber)) {
            user = new User(username, email, password, address, phoneNumber, nickname, description, photoUrl, "User", "N",
                    new Date(Calendar.getInstance().getTimeInMillis()), null);
            UserDAO userDAO = new UserDAO();
            userDAO.addNewUser(user, password);
            return user;
        }
        return new User();
    }

    private boolean validateNickname(String nickname) {
        if (nickname.isEmpty() || nickname.equals("") || nickname.length() > 20)
            return false;
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(nickname);
        boolean b = m.find();
        return !b;
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.isEmpty() || phoneNumber.equals("") || phoneNumber.length() > 10 || phoneNumber.length() < 9)
            return false;
        Pattern p = Pattern.compile("^[0-9]");
        Matcher m = p.matcher(phoneNumber);
        boolean b = m.find();
        return !b;

    }

    private boolean validateDescription(String description) {
        return !(description.length() > 50);
    }

    private boolean validateAddress(Address address) {
        if (address.getZip().length() == 7 && address.getCity().length() > 3 && address.getCountry().length() > 3
                && address.getNumber() > 0) {
            Pattern p = Pattern.compile("^[a-z]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(address.getStreet());
            boolean b = m.find();
            return !b;
        }
        return false;
    }

    /**
     * Marks user as delete in DB
     *
     * @param username - username to be deleted
     */
    public void deleteUser(String username) {
        //update database user is deleted
        Log.l(classFunc, "deleteUser", "Starting");
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByUsername(username);
        if (user.getUsername().isEmpty() || user.getUsername() == "") {
            Log.l(classFunc, "deleteUser", "username " + username + " not found");
        }
        userDAO.deleteUser(username, "Y");
    }

    /**
     * Login to the system
     *
     * @param username - username for login
     * @param password - password for login
     * @return User Object of the user details
     */
    public User login(String username, String password) {
        Log.l(classFunc, "login", "Starting");
        UserDAO userDAO = new UserDAO();
        return userDAO.getUserByUsernameAndPassword(username, password);
    }

    /**
     * Get all users of the system
     *
     * @return List of user objects
     */
    public List<User> getAllUsers() {
        Log.l(classFunc, "getAllUsers", "Starting");
        UserDAO userDAO = new UserDAO();
        return userDAO.getAllUsers();
    }

    /**
     * Get user by username
     *
     * @param username username to retrieve
     * @return User object
     */
    public User getUserByUsername(String username) {
        Log.l(classFunc, "getUserByUsername", "Starting");
        return getUser(username);
    }

    /**
     * Get all books purchased by user
     *
     * @param username - username to retried the books
     * @return List of books relations with specific user.
     */
    public List<Book> getPurchasedBooksByUsername(String username) {
        Log.l(classFunc, "getPurchasedBooksByUsername", "Starting");
        UserBookRelationDAO userBookRelationDAO = new UserBookRelationDAO();
        User user = getUser(username);
        if (!(user.getUsername().isEmpty() || user.getUsername() == "")) {
            return userBookRelationDAO.getAllBooksPurchasedByUserID(user);
        }
        return new ArrayList<>();
    }

    /**
     * Get all books purchased by user
     *
     * @param username - username to retried the books
     * @return List of books relations with specific user.
     */
    public List<Book> getNotPurchasedBooksByUsername(String username) {
        Log.l(classFunc, "getNotPurchasedBooksByUsername", "Starting");
        UserBookRelationDAO userBookRelationDAO = new UserBookRelationDAO();
        User user = getUser(username);
        if (!(user.getUsername().isEmpty() || user.getUsername() == "")) {
            return userBookRelationDAO.getNotPurchasedBooksByUsername(user);
        }
        return new ArrayList<>();
    }

    /**
     * /buy book
     * @param username username who bought
     * @param bookId bookId to buy
     */
    public void buyBook(String username, int bookId) {
        Log.l(classFunc,"buyBook","Starting");

        User user = new User(getUser(username));
        Book book = new Book(getBook(bookId));

        UserBookRelationDAO userBookRelationDAO = new UserBookRelationDAO();
        userBookRelationDAO.buyBook(user, book);
    }

    /**
     * like book by user
     * @param username user liked book
     * @param bookId book id was liked
     */
    public void likeBook(String username, int bookId){
        Log.l(classFunc,"likeBook","Starting");

        UserBookRelationDAO userBookRelationDAO = new UserBookRelationDAO();
        userBookRelationDAO.addUserBookLike(
                getUser(username),
                getBook(bookId)
        );
    }

    /**
     * unlike book by user
     * @param username user unlike book
     * @param bookId book id was unlike
     */
    public void unlikeBook(String username, int bookId){
        Log.l(classFunc,"unlikeBook","Starting");

        UserBookRelationDAO userBookRelationDAO = new UserBookRelationDAO();
        userBookRelationDAO.deleteUserBookLike(
                getUser(username),
                getBook(bookId)
        );
    }

    /**
     * add review to book by user
     * @param username user added review
     * @param bookId book was reviewed
     * @param review review itself
     */
    public void reviewBook(String username, int bookId, String review){
        Log.l(classFunc,"reviewBook","Starting");

        UserBookRelationDAO userBookRelationDAO = new UserBookRelationDAO();
        userBookRelationDAO.addUserBookReview(getUser(username), getBook(bookId),review);
    }

    /**
     * approve review
     * @param username username to approve
     * @param reviewId review id to approve
     */
    public void approveReview(String username, int reviewId){
        Log.l(classFunc, "approveReview","Starting");

        User user = new User(getUser(username));
        if(user.getRole().equals("Admin")){
            UserBookRelationDAO userBookRelationDAO = new UserBookRelationDAO();
            userBookRelationDAO.approveUserBookReview(new UserBookReviewRelation(userBookRelationDAO.getReviewByID(reviewId)));
        }
    }
}