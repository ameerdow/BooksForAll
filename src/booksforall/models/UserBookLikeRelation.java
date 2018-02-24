package booksforall.models;

public class UserBookLikeRelation {

    private String username;
    private int bookId;
    private String nickname;

    public UserBookLikeRelation(String username, int bookId, String nickname) {
        this.username = username;
        this.bookId = bookId;
        this.nickname = nickname;
    }

    public UserBookLikeRelation(UserBookLikeRelation userBookLikeRelation) {
        this.username = userBookLikeRelation.getUsername();
        this.bookId = userBookLikeRelation.getBookId();
    }
    public String getUsername() {
        return username;
    }

    public int getBookId() {
        return bookId;
    }

    public String getNickname() {
        return nickname;
    }
}
