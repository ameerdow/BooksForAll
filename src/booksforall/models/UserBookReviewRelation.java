package booksforall.models;

import java.util.Date;

public class UserBookReviewRelation {

    private Integer id;
    private String username;
    private int bookId;
    private String review;
    private String approved;
    private Date creationDate;

    public UserBookReviewRelation(Integer id, String username, int bookId, String review, String approved, Date creationDate) {
        this.id = id;
        this.username = username;
        this.bookId = bookId;
        this.review = review;
        this.approved = approved;
        this.creationDate = creationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getApproved() {
        return approved;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

}
