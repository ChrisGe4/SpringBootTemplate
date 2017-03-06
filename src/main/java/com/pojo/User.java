package com.pojo;

public class User {

    private String fullName;
    private String userName;
    private String street;
    private String zipcode;
    private String city;
    private Integer id;

    public User() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String newFullName) {
        fullName = newFullName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String newStreet) {
        street = newStreet;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String newZipcode) {
        zipcode = newZipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String newCity) {
        city = newCity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer newId) {
        this.id = newId;
    }
}
