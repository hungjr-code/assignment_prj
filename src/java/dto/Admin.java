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
public class Admin extends User {
    private String fullName;
    private String position;

    public Admin(String userid, String email, String passwordHash, String role, LocalDateTime createdAt, boolean active) {
        super(userid, email, passwordHash, role, createdAt, active);
    }

    public Admin(User user, String fullname, String position) {
        super(user.getUserID(), user.getEmail(), user.getPasswordHash(), user.getRole(), user.getCreatedAt(), user.isActive());
        this.fullName = fullname;
        this.position = position;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
