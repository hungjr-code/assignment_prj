/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDateTime;

/**
 *
 * @author kAwa
 */
public class User {
    private String userID;
    private String email;
    private String passwordHash;
    private String role;
    private LocalDateTime createdAt;
    private boolean active;

    public User(String userid, String email, String password_hash, String role, LocalDateTime createdAt, boolean active) {
        this.userID = userid;
        this.email = email;
        this.passwordHash = password_hash;
        this.role = role;
        this.createdAt = createdAt;
        this.active = active;
    }

    public String getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getRole() {
        return role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
}
