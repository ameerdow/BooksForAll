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

        public SignUpRequest() {
        }

        public SignUpRequest(String username, String email, String password, String street, int number, String city, String zip, String country, String phoneNumber, String nickname, String description, String photoUrl) {
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



        public String print() {
            return username + "," + email + "," + password + "," + street + "," + number + "," + city + "," + zip + "," + country + "," + phoneNumber
                    + "," + nickname + "," + description + "," + photoUrl + "," + role + "," + deleted ;
        }
    }

    public static class ApproveReviewRequest {
        private int reviewId;

        public ApproveReviewRequest() {
        }

        public ApproveReviewRequest(int reviewId) {
            this.reviewId = reviewId;
        }

        public int getReviewId() {
            return reviewId;
        }
    }


    // book requests

    public static class DeleteBookRequest {
        private int bookId;
        private String deleteStatus;

        public DeleteBookRequest() {
        }

        public DeleteBookRequest(int bookId, String deleteStatus) {
            this.bookId = bookId;
            this.deleteStatus = deleteStatus;
        }

        public int getBookId() {
            return bookId;
        }

        public String getDeleteStatus() {
            return deleteStatus;
        }
    }

    public static class BuyBookRequest {

        private int bookId;
        private Double price;

        public BuyBookRequest(int bookId, Double price) {
            this.bookId = bookId;
            this.price = price;
        }

        public BuyBookRequest() {
        }

        public int getBookId() {
            return bookId;
        }


        public Double getPrice() {
            return price;
        }
    }

    public static class ReviewBookRequest {

        private int bookId;
        private String review;

        public ReviewBookRequest() {
        }

        public ReviewBookRequest(int bookId, String review) {
            this.bookId = bookId;
            this.review = review;
        }
        public int getBookId() {
            return bookId;
        }

        public String getReview() {
            return review;
        }
    }

    public static class LikeBookRequest {

        private int bookId;

        public LikeBookRequest(int bookId) {
            this.bookId = bookId;
        }

        public LikeBookRequest() {
        }

        public int getBookId() {
            return bookId;
        }
    }

    public static class SetReadPositionRequest {
        private int bookId;
        private Float position;

        public SetReadPositionRequest(int bookId, Float position) {
            this.bookId = bookId;
            this.position = position;
        }

        public int getBookId() {
            return bookId;
        }


        public Float getPosition() {
            return position;
        }
    }
}
