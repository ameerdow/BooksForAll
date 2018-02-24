package booksforall.models;

public class ReviewResponse {
    public String username = "";
    public String nickname = "";
    public String review = "";

    public ReviewResponse() {
    }

    public ReviewResponse(String username, String nickname, String review) {
        this.username = username;
        this.nickname = nickname;
        this.review = review;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
