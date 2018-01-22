package booksforall.models;

import java.util.Date;

public class User {

    private String username;
    private String email;
    private String address;
    private int phoneNumber;
    private String nickname;
    private String description;
    private String photoUrl;
    private String role;
    private String deleted;
    private Date creationDate;
    private Date updateDate;


    public User() {
    }

    public User(String username, String email, String address, int phoneNumber, String nickname, String description, String photoUrl, String role, String deleted, Date creationDate, Date updateDate) {
        this.username = username;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
        this.description = description;
        this.photoUrl = photoUrl;
        this.role = role;
        this.deleted = deleted;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
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
