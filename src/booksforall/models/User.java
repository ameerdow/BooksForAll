package booksforall.models;

import java.util.Date;

public class User {

    private String username;
    private String email;
    private String password;
    private Address address;
    private String phoneNumber;
    private String nickname;
    private String description;
    private String photoUrl;
    private String role;
    private String deleted;
    private String creationDate;
    private String updateDate;


    public User() {
    }

    public User(String username, String email,String password, Address address, String phoneNumber, String nickname, String description, String photoUrl, String role, String deleted, String creationDate, String updateDate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = new Address(address);
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
        this.description = description;
        this.photoUrl = photoUrl;
        this.role = role;
        this.deleted = deleted;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public User(User user){
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.address = new Address(user.getAddress());
        this.phoneNumber = user.getPhoneNumber();
        this.nickname = user.getNickname();
        this.description = user.getDescription();
        this.photoUrl = user.getPhotoUrl();
        this.role = user.getRole();
        this.deleted = user.getDeleted();
        this.creationDate = user.getCreationDate();
        this.updateDate = user.getUpdateDate();
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = new Address(address);
    }

    public String  getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String  phoneNumber) {
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getPassword() {
        return password;
    }
}
