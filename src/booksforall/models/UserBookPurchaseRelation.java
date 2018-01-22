package booksforall.models;

import java.util.Date;

public class UserBookPurchaseRelation {

    private String username;
    private int bookId;
    private Double price;
    private Date creationDate;

    public UserBookPurchaseRelation() {
    }

    public UserBookPurchaseRelation(String username, int bookId, Double price, Date creationDate) {
        this.username = username;
        this.bookId = bookId;
        this.price = price;
        this.creationDate = creationDate;
    }

    public String getUsername() {
        return username;
    }
    
    public int getBookId() {
        return bookId;
    }

    public Double getPrice() {
        return price;
    }

    public Date getCreationDate() {
        return creationDate;
    }

}
