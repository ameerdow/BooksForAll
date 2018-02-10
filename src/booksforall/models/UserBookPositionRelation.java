package booksforall.models;

import java.util.Date;

public class UserBookPositionRelation {

    private String username;
    private int bookId;
    private Float positionPercent;
    private Date creationDate;

    public UserBookPositionRelation() {
    }

    public UserBookPositionRelation(String username, int bookId, Float positionPercent, Date creationDate) {
        this.username = username;
        this.bookId = bookId;
        this.positionPercent = positionPercent;
        this.creationDate = creationDate;
    }

    public UserBookPositionRelation(UserBookPositionRelation userBookPositionRelation) {
        this.username = userBookPositionRelation.getUsername();
        this.bookId = userBookPositionRelation.getBookId();
        this.positionPercent = userBookPositionRelation.getPositionPercent();
        this.creationDate = userBookPositionRelation.getCreationDate();
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

    public Float getPositionPercent() {
        return positionPercent;
    }

    public void setPositionPercent(Float positionPercent) {
        this.positionPercent = positionPercent;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
