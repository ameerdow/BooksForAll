package booksforall.models;

public class UserBookLikeRelation {

    private String username;
    private int bookId;

    public UserBookLikeRelation(String username, int bookId) {
        this.username = username;
        this.bookId = bookId;
    }

    public String getUsername() {
        return username;
    }

    public int getBookId() {
        return bookId;
    }
}
