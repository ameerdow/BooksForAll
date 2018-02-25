package booksforall.main;

import booksforall.db.InitDatabase;
import booksforall.models.Address;
import booksforall.models.User;
import booksforall.models.UserBookReviewRelation;
import booksforall.services.UserService;
import booksforall.utils.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

public class BooksForAll {

    private static final String classFunc = BooksForAll.class.getSimpleName();
    private static BooksForAll instance = null;

    /**
     * Singleton WebProjectApp, init the DB connections
     */
    public static BooksForAll getInstance() throws SQLException {
        if (instance == null)
            instance = new BooksForAll();
        return instance;
    }

    private BooksForAll() {
        InitDatabase.initTables();
        initMockData();
    }


    private void initMockData() {
        Log.l(classFunc, "initUsers", "Starting");

        UserService service = new UserService();

        //adding admin user
        Address address = new Address("street ", 1, "admin", "1234567", "admin");
        service.addUser("admin", "admin@email.com", "passw0rd", address, "0546597762", "nickname", "description", "photoUrl");

        List<User> users = getMockUsers();
        List<UserBookReviewRelation> userBookReviewRelations = getMockReviews();
        if (users != null) {
            // adding 20 user to the system
            for (User user : users) {
                service.addUser(user.getUsername(), user.getEmail(), user.getPassword(), user.getAddress(), user.getPhoneNumber(), user.getNickname(), user.getDescription(), user.getPhotoUrl());
            }
        }

        if(userBookReviewRelations != null){
            for(UserBookReviewRelation reviewRelation: userBookReviewRelations){
                service.reviewBook(reviewRelation.getUsername(),reviewRelation.getBookId(),reviewRelation.getReview());
            }
        }
    }


    private List<User> getMockUsers() {
        Gson gson = new Gson();
        try {
            String filepath = getClass().getClassLoader().getResource("booksforall/main/data/mock-data.json").getPath();
            JsonReader reader = new JsonReader(new FileReader(filepath));
            Type type = new TypeToken<List<User>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private List<UserBookReviewRelation> getMockReviews() {
        Gson gson = new Gson();
        try {
            String filepath = getClass().getClassLoader().getResource("booksforall/main/data/mock-review.json").getPath();
            JsonReader reader = new JsonReader(new FileReader(filepath));
            Type type = new TypeToken<List<UserBookReviewRelation>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    public static void main(String[] args ){
        try {
            BooksForAll booksForAll = BooksForAll.getInstance();

        }catch (Exception ignore){

        }
    }
}
