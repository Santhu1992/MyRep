package com.snatch.entity;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class User {

    @Id
    @Column(name = "user_id")
    @GenericGenerator(name = "UserIdGenerator",strategy = "com.snatch.util.UserIdGenerator")
    @GeneratedValue(generator = "UserIdGenerator")
    private String userId;
    @Column(name = "user_name")
    private String userName;
    private String email;
    @Column(name = "phone_no")
    private Long phoneNo;
    private String password;

    public User() {
    }

    public User(String userId, String userName, String email, Long phoneNo, String password) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
