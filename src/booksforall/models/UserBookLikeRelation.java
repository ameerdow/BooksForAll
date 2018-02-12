package booksforall.models;

public class UserBookLikeRelation {

    private String username;
    private int bookId;

    public UserBookLikeRelation(String username, int bookId) {
        this.username = username;
        this.bookId = bookId;
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
}
