package booksforall.services;

import booksforall.dao.BookDAO;
import booksforall.models.Book;
import booksforall.utils.Log;

import java.util.List;

public class BookService {

    private final String classFunc = UserService.class.getSimpleName();


    public List<Book> getBookByName(String bookName) {
        Log.l(classFunc, "getBookByName", "Starting");
        BookDAO bookDAO = new BookDAO();
        return bookDAO.getBooksByName(bookName);
    }

    public Book getBookById(int id) {
        Log.l(classFunc, "getBookById", "Starting");
        BookDAO bookDAO = new BookDAO();
        return bookDAO.getBookByID(id);
    }

    public void deleteBook(int id, String deleteStatus) {
        Log.l(classFunc, "deleteBook", "Starting");
        BookDAO bookDAO = new BookDAO();
        bookDAO.deleteBook(id, deleteStatus);
    }

    public List<Book> getAllBooks(){
        Log.l(classFunc, "getAllBooks", "Starting");
        BookDAO bookDAO = new BookDAO();
        return bookDAO.getAllBooks();
    }

    public void updateLikeCountToBookID(int id, Boolean add){
        Log.l(classFunc, "updateLikeCountToBookID", "Starting");
        BookDAO bookDAO = new BookDAO();
        bookDAO.updateLikeCountToBookID(id, add);
    }

    public void updateReviewCountToBookID(int id, Boolean add){
        Log.l(classFunc, "updateLikeCountToBookID", "Starting");
        BookDAO bookDAO = new BookDAO();
        bookDAO.updateReviewCountToBookID(id, add);
    }
}
