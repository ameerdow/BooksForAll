package booksforall.servelet;

import booksforall.Server.ClientRequest;
import booksforall.models.Address;
import booksforall.models.User;
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

@WebServlet(name = "UserServlet", description = "User servlet manage all user operations", urlPatterns = {"/users",
        "/user/*", "/user", "/login", "/review/*", "/logout", "/search/user/*"})

public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = -5666712938965769234L;
    private static final String classFunc = UserServlet.class.getSimpleName();
    private static final String GET_ALL_USERS = "/users";
    private static final String GET_USER_BY_USERNAME = "/user/";
    private static final String GET_LOGGED_IN_USER = "/user";
    private static final String APPROVE_REVIEW = "/review/approve";
    private static final String REJECT_REVIEW = "/review/reject";
    private static final String GET_PENDING_REVIEW = "/review/pending";
    private static final String SIGN_UP = "/user";
    private static final String LOGIN = "/login";
    private static final String LOGOUT = "/logout";
    private static final String SEARCH_USER = "/search/user/";
    private static final String DELETE_USER_BY_USERNAME = "/user/delete";
    private static final String GET_USERNAME_BOOK_PURCHASE = "/user/purchase/";

    private final String JSON = "application/json";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Log.l(classFunc, "doGet", "Starting");

        PrintWriter printWriter = response.getWriter();
        response.setHeader("Access-Control-Allow-Origin", "*");
        UserService userService = new UserService();
        Gson gson = new Gson();
        response.setContentType(JSON);

        try {
            String uri = request.getRequestURI();
            String pathInfo = request.getPathInfo();

            Log.l(classFunc, "goGet", String.format("GET Request URI: %s", uri));
            Log.l(classFunc, "goGet", "path info = " + pathInfo + ", uri = " + uri);

            if (uri.contains(GET_ALL_USERS)) {
                Helper.checkSession(request);
                printWriter.println(gson.toJson(userService.getAllUsers()));
                return;
            } else if (uri.contains(SEARCH_USER)) {
                Helper.checkSession(request);
                String[] params = pathInfo.split("/");
                if (params.length != 2) {
                    Log.l(classFunc, "doGet", "No user found for search");
                    throw new RuntimeException("No user found for search");
                }
                String userToSearch = params[1];
                printWriter.println(gson.toJson(userService.searchUser(userToSearch)));
                return;
            } else if (uri.endsWith(GET_LOGGED_IN_USER)) {
                String username = Helper.checkSession(request);
                printWriter.println(gson.toJson(userService.getUserByUsername(username)));
                return;
            } else if (uri.contains(GET_USER_BY_USERNAME)) {
                Helper.checkSession(request);
                String[] params = pathInfo.split("/");
                if (params.length != 2) {
                    Log.l(classFunc, "doGet", "No user found to get");
                    throw new RuntimeException("No user found to get");
                }
                String username = params[1];
                printWriter.println(gson.toJson(userService.getUserByUsername(username)));
                return;
            } else if (uri.contains(GET_USERNAME_BOOK_PURCHASE)) {
                String username = Helper.checkSession(request);
                String[] params = pathInfo.split("/");
                if (params.length != 1) {
                    Log.l(classFunc, "doGet", "No user book purchase relation found to get");
                    throw new RuntimeException("No user book purchase relation found to get");
                }
                int bookId = Integer.parseInt(params[0]);

                printWriter.println(gson.toJson(userService.getUserBookPurchase(username, bookId)));
                return;
            } else if(uri.contains(GET_PENDING_REVIEW)){
                String username = Helper.checkSession(request);
                printWriter.println(gson.toJson(userService.getPendingReviews(username)));
                return;
            }

            throw new RuntimeException("no function found");
        } catch (RuntimeException e) {
            Log.e(classFunc, "doGet", e.getMessage(), e);
            response.setStatus(300);
            response.setContentType(JSON);
            printWriter.println(gson.toJson("{code:" + "300" + ",message:\"" + e.getMessage() + "\"}"));
            printWriter.flush();
        } catch (Exception e) {
            Log.e(classFunc, "doGet", e.getMessage(), e);
            response.setStatus(400);
            response.setContentType(JSON);
            printWriter.println(gson.toJson("{code:" + "400" + ",message:\"" + e.getMessage() + "\"}"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        response.setHeader("Access-Control-Allow-Origin", "*");
        Gson gson = new Gson();

        try {
            String uri = request.getRequestURI();
            UserService userService = new UserService();
            Log.l(classFunc, "doPost", String.format("POST Request URI: %s", uri));
            response.setContentType(JSON);
            String postData = Helper.getPostData(request);

            if (uri.endsWith(LOGIN)) {
                ClientRequest.LoginRequest loginRequest = gson.fromJson(postData, ClientRequest.LoginRequest.class);
                if (loginRequest == null) {
                    throw new RuntimeException("Error getting login request info");
                } else {
                    User loginUser = (userService.login(loginRequest.getUsername(), loginRequest.getPassword()));
                    if (loginUser == null || loginUser.getUsername() == null || loginUser.getUsername().isEmpty())
                        throw new RuntimeException("Error getting login info");
                    Helper.setSession(request, loginUser.getUsername());
                    printWriter.println(gson.toJson(loginUser));
                    return;
                }
            } else if (uri.endsWith(LOGOUT)) {
                Helper.invalidateSession(request);
                response.sendRedirect("../login.html");
            } else if (uri.endsWith(SIGN_UP)) {
                ClientRequest.SignUpRequest signUpRequest = gson.fromJson(postData, ClientRequest.SignUpRequest.class);
                if (signUpRequest == null) {
                    throw new RuntimeException("Error getting sign up info");
                } else {
                    Address address = new Address(signUpRequest.getStreet(), signUpRequest.getNumber(),
                            signUpRequest.getCity(), signUpRequest.getZip(), signUpRequest.getCountry());
                    User signUser = new User(userService.addUser(signUpRequest.getUsername(), signUpRequest.getEmail(),
                            signUpRequest.getPassword(), address, signUpRequest.getPhoneNumber(),
                            signUpRequest.getNickname(), signUpRequest.getDescription(), signUpRequest.getPhotoUrl()));

                    Helper.setSession(request, signUser.getUsername());
                    printWriter.println(gson.toJson(signUser));
                    return;
                }
            } else if (uri.endsWith(APPROVE_REVIEW)) {
                ClientRequest.ApproveReviewRequest approveReviewRequest = gson.fromJson(postData,
                        ClientRequest.ApproveReviewRequest.class);
                if (approveReviewRequest == null) {
                    throw new RuntimeException("Error getting approve review info");
                } else {
                    userService.approveReview(Helper.checkSession(request), approveReviewRequest.getReviewId());
                    return;
                }
            }else if (uri.endsWith(REJECT_REVIEW)) {
                ClientRequest.ApproveReviewRequest approveReviewRequest = gson.fromJson(postData,
                        ClientRequest.ApproveReviewRequest.class);
                if (approveReviewRequest == null) {
                    throw new RuntimeException("Error getting approve review info");
                } else {
                    userService.rejectReview(Helper.checkSession(request), approveReviewRequest.getReviewId());
                    return;
                }
            } else if (uri.contains(DELETE_USER_BY_USERNAME)) {
                String username = Helper.checkSession(request);
                if (userService.getUserByUsername(username).getRole().equals("Admin")) {
                    ClientRequest.DeleteUserRequest deleteUserRequest = gson.fromJson(postData, ClientRequest.DeleteUserRequest.class);
                    if (deleteUserRequest == null) {
                        throw new RuntimeException("Error getting delete user review info");
                    } else {
                        userService.deleteUser(deleteUserRequest.getUsername());
                        return;
                    }
                } else {
                    throw new RuntimeException("User is not admin");
                }
            }
            throw new RuntimeException("no function found");
        } catch (RuntimeException e) {
            Log.e(classFunc, "doPost", e.getMessage(), e);
            response.setStatus(400);
            response.setContentType(JSON);
            printWriter.println(gson.toJson("{code:" + "400" + ",message:\"" + e.getMessage() + "\"}"));
        } catch (Exception e) {
            Log.e(classFunc, "doPost", e.getMessage(), e);
            response.setStatus(500);
            response.setContentType(JSON);
            printWriter.println(gson.toJson("{code:" + "500" + ",message:\"Internal server error\"}"));
        }
    }

}
