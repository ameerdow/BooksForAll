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


@WebServlet(
        name = "UserServlet",
        description = "User servlet manage all user operations",
        urlPatterns = {
                "/users",
                "/user/*",
                "/user",
                "/user/forget_password",
                "/user/change_password",
                "/login",
                "/logout",
                "/search/user/*"
        }
)

public class UserServlet extends HttpServlet {


    private static final long serialVersionUID = -5666712938965769234L;
    private static final String classFunc = UserServlet.class.getSimpleName();
    private static final String GET_ALL_USERS = "/users";
    private static final String GET_USER_BY_USERNAME = "/user/";
    private static final String GET_LOGGED_IN_USER = "/user";
    private static final String APPROVE_REVIEW = "/review";
    private static final String SIGN_UP = "/user";
    private static final String LOGIN = "/login";
    private static final String LOGOUT = "/logout";
    private static final String SEARCH_USER = "/search/user/";

    private final String JSON = "application/json";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Log.l(classFunc, "doGet", "Starting");

        PrintWriter printWriter = response.getWriter();

        UserService userService = new UserService();
        
        Gson gson = new Gson();

        try {
            String uri = request.getRequestURI();
            String pathInfo = request.getPathInfo();

            Log.l(classFunc, "goGet", String.format("GET Request URI: %s", uri));
            Log.l(classFunc, "goGet", "path info = " + pathInfo + ", uri = " + uri);

            if (uri.contains(GET_ALL_USERS)) {
                Helper.checkSession(request);
                response.setContentType(JSON);
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
                response.setContentType(JSON);
                printWriter.println(gson.toJson(userService.searchUser(userToSearch)));
                return;
            } else if (uri.contains(GET_LOGGED_IN_USER)) {
                String username = Helper.checkSession(request);
                response.setContentType(JSON);
                printWriter.println(gson.toJson(userService.getUserByUsername(username)));
                return;
            } else if (uri.contains(GET_USER_BY_USERNAME)) {
                Helper.checkSession(request);
                String[] params = pathInfo.split("/");
                if (params.length != 1) {
                    Log.l(classFunc, "doGet", "No user found to get");
                    throw new RuntimeException("No user found to get");
                }

                String username = params[0];
                response.setContentType(JSON);
                printWriter.println(gson.toJson(userService.getUserByUsername(username)));
                return;
            } else if (uri.contains(LOGIN)) {
                response.setContentType(JSON);
                printWriter.println("<html><body><script>window.location.href=\"login.html\"</script></body></html>");
                return;
            }
            
            throw new RuntimeException("no function found");
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter printWriter = response.getWriter();
        Gson gson = new Gson();
        
        try {
        	String uri = request.getRequestURI();
            UserService userService = new UserService();
            Log.l(classFunc, "doPost", String.format("POST Request URI: %s", uri));
            response.setContentType(JSON);
            String postData = Helper.getPostData(request);
        	
            if(uri.endsWith(LOGIN)) {
            	ClientRequest.LoginRequest loginRequest = gson.fromJson(postData, ClientRequest.LoginRequest.class);
            	if(loginRequest == null) {
            		throw new RuntimeException("Error getting login info");
            	}else {
            		User loginUser = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
            		Helper.setSession(request, loginUser.getUsername());
            		printWriter.println(gson.toJson(loginUser));
            		return;
            	}
            }else if (uri.endsWith(LOGOUT)) {
                Helper.invalidateSession(request);
                response.sendRedirect("../login.html");
            }else if(uri.endsWith(SIGN_UP)) {
            	ClientRequest.SignUpRequest signUpRequest = gson.fromJson(postData, ClientRequest.SignUpRequest.class);
            	if(signUpRequest == null) {
            		throw new RuntimeException("Error getting singup info");
            	}else {
            		Address address = new Address(signUpRequest.getStreet(), signUpRequest.getNumber(), signUpRequest.getCity(), signUpRequest.getZip(), signUpRequest.getCountry());
            		User signUser = userService.addUser(signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword(),address ,
            				signUpRequest.getPhoneNumber(), signUpRequest.getNickname(), signUpRequest.getDescription(), signUpRequest.getPhotoUrl());
            		
            		Helper.setSession(request, signUser.getUsername());
            		printWriter.println(gson.toJson(signUser));
            		return;
            	}
            }else if(uri.endsWith(APPROVE_REVIEW)) {
            	Helper.checkSession(request);
            	ClientRequest.ApproveReviewRequest approveReviewRequest = gson.fromJson(postData, ClientRequest.ApproveReviewRequest.class);
            	if(approveReviewRequest == null) {
            		throw new RuntimeException("Error getting approve review info");
            	}else {
            		userService.approveReview(approveReviewRequest.getUsername(), approveReviewRequest.getReviewId());
            		return;
            	}
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
