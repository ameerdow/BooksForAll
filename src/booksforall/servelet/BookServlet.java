package booksforall.servelet;

import booksforall.Server.ClientRequest;
import booksforall.services.BookService;
import booksforall.services.UserService;
import booksforall.utils.Helper;
import booksforall.utils.Log;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet(
        name = "BookServlet",
        description = "book servlet manage all book operations",
        urlPatterns = {
                "/books",
                "/book/*",
                "/search/book/"
        }
)

public class BookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final String JSON = "application/json";
    private final String classFunc = UserServlet.class.getSimpleName();
    private static final String GET_ALL_BOOKS = "/books";
    private static final String GET_ALL_BOOK_LIKES = "/book/likes/";
    private static final String GET_ALL_BOOK_REVIEWS = "/book/review/";
    private static final String GET_ALL_BOOK_PURCHASES = "/book/purchases/";
    private static final String GET_ALL_BOOK_USER_PURCHASES = "/book/user/purchases/";
    private static final String GET_ALL_BOOK_USER_NOT_PURCHASES = "/book/user/not/purchases/";
    private static final String SEARCH_BOOKS = "/search/book/";
    private static final String GET_BOOK_BY_ID = "/book/id/";
    private static final String GET_BOOK_BY_CATEGORY = "/book/category";
    private static final String BUY_BOOK_BY_ID = "/book/buy/";
    private static final String LIKE_BOOK_BY_ID = "/book/like/";
    private static final String REVIEW_BOOK_BY_ID = "/book/review/";
    private static final String DELETE_BOOK_BY_ID = "/book/delete";
    private static final String SAVE_READ_BOOK_POSITION = "/book/position";
    private static final String GET_READ_BOOK_POSITION = "/book/position/";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Log.l(classFunc, "doGet", "Starting");


        final String DELETE = "Y";
        PrintWriter printWriter = response.getWriter();
        UserService userService = new UserService();
        response.setHeader("Access-Control-Allow-Origin", "*");
        BookService bookService = new BookService();
        Gson gson = new Gson();

        try {
            String uri = request.getRequestURI();
            String pathInfo = request.getPathInfo();

            Log.l(classFunc, "goGet", String.format("GET Request URI: %s", uri));
            Log.l(classFunc, "goGet", "path info = " + pathInfo + ", uri = " + uri);

            response.setContentType(JSON);
            Helper.checkSession(request);
            if (uri.endsWith(GET_ALL_BOOKS)) {
                printWriter.println(bookService.getAllBooks());
                return;
            } else if (uri.contains(SEARCH_BOOKS)) {
                String[] params = pathInfo.split("/");
                if (params.length != 2) {
                    Log.l(classFunc, "doGet", "No book found for search");
                    throw new RuntimeException("No book found for search");
                }
                printWriter.println(bookService.getBookByName(params[1]));
                return;
            } else if (uri.contains(GET_BOOK_BY_ID)) {
                String[] params = pathInfo.split("/");
                if (params.length != 2) {
                    Log.l(classFunc, "doGet", "No book id found to get");
                    throw new RuntimeException("No book id found to get");
                }
                int bookId = Integer.parseInt(params[1]);
                printWriter.println(bookService.getBookById(bookId));
                return;
            } else if (uri.contains(GET_BOOK_BY_CATEGORY)) {
                String[] params = pathInfo.split("/");
                if (params.length != 2) {
                    Log.l(classFunc, "doGet", "No book category found to get");
                    throw new RuntimeException("No book category found to get");
                }
                String category = params[1];
                printWriter.println(bookService.getBookByCategory(category));
                return;
            } else if (uri.contains(GET_ALL_BOOK_LIKES)) {
                String[] params = pathInfo.split("/");
                if (params.length != 2) {
                    Log.l(classFunc, "doGet", "No book id found to get likes");
                    throw new RuntimeException("No book id found to get likes");
                }
                int bookId = Integer.parseInt(params[1]);
                printWriter.println(bookService.getAllBookLikes(bookId));
                return;
            } else if (uri.contains(GET_ALL_BOOK_REVIEWS)) {
                String[] params = pathInfo.split("/");
                if (params.length != 2) {
                    Log.l(classFunc, "doGet", "No book id found to get reviews");
                    throw new RuntimeException("No book id found to get reviews");
                }
                int bookId = Integer.parseInt(params[1]);
                printWriter.println(bookService.getAllReviewsForBook(bookId));
                return;
            } else if (uri.contains(GET_ALL_BOOK_PURCHASES)) {
                String[] params = pathInfo.split("/");
                if (params.length != 2) {
                    Log.l(classFunc, "doGet", "No book id found to get purchases");
                    throw new RuntimeException("No book id found to get purchases");
                }
                int bookId = Integer.parseInt(params[1]);
                printWriter.println(bookService.getAllPurchasesForBook(bookId));
                return;
            } else if (uri.contains(GET_ALL_BOOK_USER_PURCHASES)) {
                String[] params = pathInfo.split("/");
                if (params.length != 3) {
                    Log.l(classFunc, "doGet", "No username found to get purchases");
                    throw new RuntimeException("No username found to get purchases");
                }
                String username = params[2];
                printWriter.println(userService.getPurchasedBooksByUsername(username));
                return;
            } else if (uri.contains(GET_ALL_BOOK_USER_NOT_PURCHASES)) {
                String[] params = pathInfo.split("/");
                if (params.length != 4) {
                    Log.l(classFunc, "doGet", "No username found to get not purchased books");
                    throw new RuntimeException("No username found to get not purchased books");
                }
                String username = params[3];
                printWriter.println(userService.getNotPurchasedBooksByUsername(username));
                return;
            } else if(uri.contains(GET_READ_BOOK_POSITION)){
                String[] params = pathInfo.split("/");
                if(params.length != 3){
                    Log.l(classFunc, "doGet", "No book id pr username found to get read position");
                    throw new RuntimeException("No book id or username found to get position");
                }
                String username = Helper.checkSession(request);
                int bookId = Integer.parseInt(params[2]);
                printWriter.println(userService.getReadPosition(username,bookId));
                return;
            }
            throw new RuntimeException("no function found,");
        } catch (RuntimeException e) {
            Log.e(classFunc, "doGet", e.getMessage(), e);
            response.setStatus(300);
            response.setContentType(JSON);
            printWriter.println(gson.toJson("{code:" + "300" + ",message:\"" + e.getMessage() + "\"}"));
        } catch (Exception e) {
            Log.e(classFunc, "doGet", e.getMessage(), e);
            response.setStatus(400);
            response.setContentType(JSON);
            printWriter.println(gson.toJson("{code:" + "400" + ",message:\"" + e.getMessage() + "\"}"));
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        Gson gson = new Gson();

        try {

            String uri = request.getRequestURI();
            UserService userService = new UserService();
            response.setHeader("Access-Control-Allow-Origin", "*");
            BookService bookService = new BookService();
            Log.l(classFunc, "doPost", String.format("POST Request URI: %s", uri));
            response.setContentType(JSON);
            String postData = Helper.getPostData(request);

            if (uri.contains(DELETE_BOOK_BY_ID)) {
                ClientRequest.DeleteBookRequest deleteBookRequest = gson.fromJson(postData, ClientRequest.DeleteBookRequest.class);
                if (deleteBookRequest.getDeleteStatus().isEmpty() || deleteBookRequest.getBookId() == 0)
                    throw new RuntimeException("delete status is empty or bookId is 0");
                bookService.deleteBook(deleteBookRequest.getBookId(), deleteBookRequest.getDeleteStatus());
                return;
            } else if (uri.contains(BUY_BOOK_BY_ID)) {
                ClientRequest.BuyBookRequest buyBookRequest = gson.fromJson(postData, ClientRequest.BuyBookRequest.class);
                if (Helper.checkSession(request).isEmpty() || buyBookRequest.getBookId() == 0)
                    throw new RuntimeException("username is empty or bookId is 0");
                userService.buyBook(Helper.checkSession(request), buyBookRequest.getBookId());
            } else if (uri.contains(LIKE_BOOK_BY_ID)) {
                ClientRequest.LikeBookRequest likeBookRequest = gson.fromJson(postData, ClientRequest.LikeBookRequest.class);
                if (Helper.checkSession(request).isEmpty() || likeBookRequest.getBookId() == 0)
                    throw new RuntimeException("username is empty or bookId is 0");
                userService.likeBook(Helper.checkSession(request), likeBookRequest.getBookId());
            } else if (uri.contains(REVIEW_BOOK_BY_ID)) {
                ClientRequest.ReviewBookRequest reviewBookRequest = gson.fromJson(postData, ClientRequest.ReviewBookRequest.class);
                if (Helper.checkSession(request).isEmpty() || reviewBookRequest.getBookId() == 0 || reviewBookRequest.getReview().isEmpty())
                    throw new RuntimeException("username is empty or bookId is 0 or review is empty");
                userService.reviewBook(Helper.checkSession(request), reviewBookRequest.getBookId(), reviewBookRequest.getReview());
            } else if (uri.contains(SAVE_READ_BOOK_POSITION)) {
                ClientRequest.SetReadPositionRequest readPositionRequest = gson.fromJson(postData, ClientRequest.SetReadPositionRequest.class);
                if (Helper.checkSession(request).isEmpty() || readPositionRequest.getBookId() == 0 || readPositionRequest.getPosition().isNaN())
                    throw new RuntimeException("username is empty or bookId is 0 or position is null");
                userService.saveReadPosition(Helper.checkSession(request), readPositionRequest.getBookId(), readPositionRequest.getPosition());
            }
            throw new RuntimeException("no function found");
        } catch (RuntimeException e) {
            Log.e(classFunc, "doPost", e.getMessage(), e);
            response.setStatus(300);
            response.setContentType(JSON);
            printWriter.println(gson.toJson("{code:" + "300" + ",message:\"" + e.getMessage() + "\"}"));
        } catch (Exception e) {
            Log.e(classFunc, "doPost", e.getMessage(), e);
            response.setStatus(400);
            response.setContentType(JSON);
            printWriter.println(gson.toJson("{code:" + "400" + ",message:\"" + e.getMessage() + "\"}"));
        }
    }

}
