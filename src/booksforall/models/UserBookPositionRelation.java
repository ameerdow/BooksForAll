package booksforall.models;

import java.util.Date;

public class UserBookPositionRelation {

    private String username;
    private int bookId;
    private Float position;
    private Date creationDate;

    public UserBookPositionRelation() {
    }

    public UserBookPositionRelation(String username, int bookId, Float position, Date creationDate) {
        this.username = username;
        this.bookId = bookId;
        this.position = position;
        this.creationDate = creationDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Float getPosition() {
        return position;
    }

    public void setPosition(Float position) {
        this.position = position;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
