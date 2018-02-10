package booksforall.services;

import booksforall.dao.BookDAO;
import booksforall.dao.UserBookRelationDAO;
import booksforall.models.Book;
import booksforall.models.UserBookLikeRelation;
import booksforall.models.UserBookPurchaseRelation;
import booksforall.models.UserBookReviewRelation;
import booksforall.utils.Log;

import java.util.List;

public class BookService {

    private final String classFunc = UserService.class.getSimpleName();

    /**
     * Gets list of books which contains name
     * @param bookName book name
     * @return list of Book object
     */
    public List<Book> getBookByName(String bookName) {
        Log.l(classFunc, "getBookByName", "Starting");
        BookDAO bookDAO = new BookDAO();
        return bookDAO.getBooksByName(bookName);
    }

    /**
     * Get book by id
     * @param id book id
     * @return Book object
     */
    public Book getBookById(int id) {
        Log.l(classFunc, "getBookById", "Starting");
        BookDAO bookDAO = new BookDAO();
        return bookDAO.getBookByID(id);
    }

    /**
     * Mark book as deleted/ not deleted
     * @param id book id
     * @param deleteStatus delete status
     */
    public void deleteBook(int id, String deleteStatus) {
        Log.l(classFunc, "deleteBook", "Starting");
        BookDAO bookDAO = new BookDAO();
        bookDAO.deleteBook(id, deleteStatus);
    }

    /**
     * get all books
     * @return list of Book object
     */
    public List<Book> getAllBooks(){
        Log.l(classFunc, "getAllBooks", "Starting");
        BookDAO bookDAO = new BookDAO();
        return bookDAO.getAllBooks();
    }

    /**
     * update book likes count
     * @param id book id
     * @param add true -> add like
     *            false - reduce like
     */
    public void updateLikeCountToBookID(int id, Boolean add){
        Log.l(classFunc, "updateLikeCountToBookID", "Starting");
        BookDAO bookDAO = new BookDAO();
        bookDAO.updateLikeCountToBookID(id, add);
    }

    /**
     * update book review count
     * @param id book id
     * @param add true -> add review count
     *            false - reduce review count
     */
    public void updateReviewCountToBookID(int id, Boolean add){
        Log.l(classFunc, "updateLikeCountToBookID", "Starting");
        BookDAO bookDAO = new BookDAO();
        bookDAO.updateReviewCountToBookID(id, add);
    }

    /**
     * Get all the purchases of specific book
     * @param bookId book id
     * @return list of UserBookPurchaseRelation object
     */
    public List<UserBookPurchaseRelation> getAllPurchasesForBook(int bookId){
        Log.l(classFunc,"getAllReviewsForBook","Starting");

        UserBookRelationDAO userBookRelationDAO = new UserBookRelationDAO();
        return userBookRelationDAO.getAllPurchases();
    }

    /**
     * Get all the reviews of specific book
     * @param bookId book id
     * @return list of UserBookReviewRelation object
     */
    public List<UserBookReviewRelation> getAllReviewsForBook(int bookId){
        Log.l(classFunc,"getAllReviewsForBook","Starting");

        BookDAO bookDAO = new BookDAO();
        UserBookRelationDAO userBookRelationDAO = new UserBookRelationDAO();
        return userBookRelationDAO.getAllBookReviews(bookDAO.getBookByID(bookId));
    }

    /**
     * Get all the likes of specific book
     * @param bookId book id
     * @return list of UserBookLikeRelation object
     */
    public List<UserBookLikeRelation> getAllBookLikes(int bookId){
        Log.l(classFunc,"getAllBookLikes","Starting");

        BookDAO bookDAO = new BookDAO();
        UserBookRelationDAO userBookRelationDAO = new UserBookRelationDAO();
        return userBookRelationDAO.getUserBooksLikeByBook(bookDAO.getBookByID(bookId));
    }

}