package com.example.ToDoApp.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_model")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("email")
    @Column(unique = true)
    private String email;

    @JsonProperty("dp")
    private String dp;  // Profile picture URL

    @JsonProperty("password")
    private String password;

    @JsonProperty("countryCode")
    private String countryCode;

    @JsonProperty("address")
    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    private Date joinedDate = new Date();

    public UserModel() { }

    public UserModel(int id, String name, String phoneNumber, String email, String dp,
                     String password, String countryCode, String address, Date joinedDate) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dp = dp;
        this.password = password;
        this.countryCode = countryCode;
        this.address = address;
        this.joinedDate = joinedDate;
    }

    // Getters and setters

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getDp() {
        return dp;
    }
    public void setDp(String dp) {
        this.dp = dp;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }
    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }
}
