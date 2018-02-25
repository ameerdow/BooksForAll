package booksforall.models;

import java.util.Date;
import java.util.List;

public class Book {

    private Integer ID;
    private String name;
    private Double price;
    private String description;
    private int likesCount;
    private int reviewCount;
    private String deleted;
    private String filePath;
    private String filePrePath;
    private Date creationDate;
    private Date updateDate;
    private String iconPath;

    // transit
    private List<UserDetails> likers;
    private List<ReviewResponse> reviews;
    private Boolean isPurchased;
    private Float position;

    public Book() {
    }

    public Book(Integer ID, String name, Double price, String description, int likesCount, int reviewCount, String deleted, String filePath,String filePrePath, String iconPath, Date creationDate, Date updateDate) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.description = description;
        this.likesCount = likesCount;
        this.reviewCount = reviewCount;
        this.deleted = deleted;
        this.filePath = filePath;
        this.filePrePath = filePrePath;
        this.iconPath = iconPath;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public Book(Book book) {
        this.ID = book.getID();
        this.name = book.getName();
        this.price = book.getPrice();
        this.description = book.getDescription();
        this.likesCount = book.getLikesCount();
        this.reviewCount = book.getReviewCount();
        this.deleted = book.getDeleted();
        this.filePath = book.getFilePath();
        this.iconPath = book.getIconPath();
        this.creationDate = book.getCreationDate();
        this.updateDate = book.getUpdateDate();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public List<UserDetails> getUserDetailsList() {
        return likers;
    }

    public void setUserDetailsList(List<UserDetails> likers) {
        this.likers = likers;
    }

    public List<ReviewResponse> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewResponse> reviews) {
        this.reviews = reviews;
    }

    public Boolean getPurchased() {
        return isPurchased;
    }

    public void setPurchased(Boolean purchased) {
        isPurchased = purchased;
    }

    public String getFilePrePath() {
        return filePrePath;
    }

    public void setFilePrePath(String filePrePath) {
        this.filePrePath = filePrePath;
    }

	public Float getPosition() {
		return position;
	}

	public void setPosition(Float position) {
		this.position = position;
	}
}
