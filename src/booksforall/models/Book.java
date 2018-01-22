package booksforall.models;

import java.util.Date;

public class Book {

    private String name;
    private Double price;
    private String description;
    private int likesCount;
    private int reviewCount;
    private String deleted;
    private String filePath;
    private Date creationDate;
    private Date updateDate;

    public Book() {
    }

    public Book(String name, Double price, String description, int likesCount, int reviewCount, String deleted, String filePath, Date creationDate, Date updateDate) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.likesCount = likesCount;
        this.reviewCount = reviewCount;
        this.deleted = deleted;
        this.filePath = filePath;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
