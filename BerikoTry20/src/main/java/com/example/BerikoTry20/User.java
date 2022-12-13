package com.example.BerikoTry20;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="berikousers")
public class User {


    private String userName;

    @Id
    @Column(unique = true)
    private String emailId;
    private String password;
    private String role;
    private boolean isBlocked;

    public User(String userName, String emailId, String password) {
        this.userName = userName;
        this.emailId = emailId;
        this.password = password;
        this.role = "USER";
        this.isBlocked = false;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public User() {
        this.userName = "";
        this.emailId = "";
        this.password = "";
        this.role = "";
    }

    public User(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
