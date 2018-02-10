package booksforall.utils;


import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;

public class Helper {

    public static String checkSession(HttpServletRequest request)
            throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            try {
                return session.getAttribute(Constants.SESSION_USERNAME).toString();
            } catch (Exception e) {
                throw new LoginException("Not Authorized Request, " + e.getMessage());
            }
        }
        throw new LoginException("Not Authorized Request");
    }

    public static void setSession(HttpServletRequest request, String username) {
        HttpSession session = request.getSession(true);
        session.setAttribute(Constants.SESSION_USERNAME, username);
    }

    public static void invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    public static String getPostData(HttpServletRequest req) throws Exception {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
            return sb.toString();
        }
    }
}
