package booksforall.models;

//import java.util.Date;

public class UserBookReviewRelation {

    private Integer id;
    private String username;
    private int bookId;
    private String review;
    private String approved;
    private String creationDate;

    public UserBookReviewRelation() {
    }

    public UserBookReviewRelation(Integer id, String username, int bookId, String review, String approved, String creationDate) {
        this.id = id;
        this.username = username;
        this.bookId = bookId;
        this.review = review;
        this.approved = approved;
        this.creationDate = creationDate;
    }

    public UserBookReviewRelation(UserBookReviewRelation userBookReviewRelation) {
        this.id = userBookReviewRelation.getId();
        this.username = userBookReviewRelation.getUsername();
        this.bookId = userBookReviewRelation.getBookId();
        this.review = userBookReviewRelation.getReview();
        this.approved = userBookReviewRelation.getApproved();
        this.creationDate = userBookReviewRelation.getCreationDate();
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

}
