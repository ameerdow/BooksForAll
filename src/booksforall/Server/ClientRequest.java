package booksforall.Server;

import java.util.Date;

public class ClientRequest {

    // user requests

    public static class LoginRequest {
        private String username;
        private String password;

        public LoginRequest() {
        }

        public LoginRequest(String password, String username) {
            this.password = password;
            this.username = username;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

    public static class SignUpRequest {

        private String username;
        private String email;
        private String password;
        private String street;
        private int number;
        private String city;
        private String zip;
        private String country;
        private String phoneNumber;
        private String nickname;
        private String description;
        private String photoUrl;
        private String role;
        private String deleted;
        private Date creationDate;
        private Date updateDate;

        public SignUpRequest() {
        }

        public SignUpRequest(String username, String email, String password, String street, int number, String city, String zip, String country, String phoneNumber, String nickname, String description, String photoUrl, String role, String deleted, Date creationDate, Date updateDate) {
            this.username = username;
            this.email = email;
            this.password = password;
            this.street = street;
            this.number = number;
            this.city = city;
            this.zip = zip;
            this.country = country;
            this.phoneNumber = phoneNumber;
            this.nickname = nickname;
            this.description = description;
            this.photoUrl = photoUrl;
            this.role = role;
            this.deleted = deleted;
            this.creationDate = creationDate;
            this.updateDate = updateDate;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public String getStreet() {
            return street;
        }

        public int getNumber() {
            return number;
        }

        public String getCity() {
            return city;
        }

        public String getZip() {
            return zip;
        }

        public String getCountry() {
            return country;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getNickname() {
            return nickname;
        }

        public String getDescription() {
            return description;
        }

        public String getPhotoUrl() {
            return photoUrl;
        }

        public String getRole() {
            return role;
        }

        public String getDeleted() {
            return deleted;
        }

        public Date getCreationDate() {
            return creationDate;
        }

        public Date getUpdateDate() {
            return updateDate;
        }

        public String print() {
            return username + "," + email + "," + password + "," + street + "," + number + "," + city + "," + zip + "," + country + "," + phoneNumber
                    + "," + nickname + "," + description + "," + photoUrl + "," + role + "," + deleted + "," + creationDate + "," + updateDate;
        }
    }

    public static class ApproveReviewRequest{
        private String username;
        private int bookId;
        private int reviewId;

        public ApproveReviewRequest() {
        }

        public ApproveReviewRequest(String username, int bookId, int reviewId) {
            this.username = username;
            this.bookId = bookId;
            this.reviewId = reviewId;
        }

        public String getUsername() {
            return username;
        }

        public int getBookId() {
            return bookId;
        }

        public int getReviewId() {
            return reviewId;
        }
    }


    // book requests

    public static class BuyBookRequest{

        private int bookId;
        private String username;
        private Double price;

        public BuyBookRequest(int bookId, String username, Double price) {
            this.bookId = bookId;
            this.username = username;
            this.price = price;
        }

        public BuyBookRequest() {
        }

        public int getBookId() {
            return bookId;
        }

        public String getUsername() {
            return username;
        }

        public Double getPrice() {
            return price;
        }
    }

    public static class ReviewBookRequest{

        private String username;
        private int bookId;
        private String review;

        public ReviewBookRequest() {
        }

        public ReviewBookRequest(String username, int bookId, String review) {
            this.username = username;
            this.bookId = bookId;
            this.review = review;
        }

        public String getUsername() {
            return username;
        }

        public int getBookId() {
            return bookId;
        }

        public String getReview() {
            return review;
        }
    }

    public static class LikeBookRequest{

        private String username;
        private int bookId;

        public LikeBookRequest(String username, int bookId) {
            this.username = username;
            this.bookId = bookId;
        }

        public LikeBookRequest() {
        }

        public String getUsername() {
            return username;
        }

        public int getBookId() {
            return bookId;
        }
    }

    public static class SetReadPositionRequest{
    }
}
