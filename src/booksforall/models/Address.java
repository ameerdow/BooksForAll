package booksforall.models;

public class Address {

    private String street;
    private int number;
    private String city;
    private String zip;
    private String country;


    public Address(String street, int number, String city, String zip, String country) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.zip = zip;
        this.country = country;
    }

    public Address(Address address) {
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.city = address.getCity();
        this.zip = address.getZip();
        this.country = address.getCountry();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
