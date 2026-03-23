/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 *
 * @author kAwa
 */
public class Student extends User {
    private String fullName;
    private Date dateOfBirth;
    private String phone;
    private String address;

    public Student(User user, String fullName, Date dateOfBirth, String phone, String address) {
        super(user.getUserID(), user.getEmail(), user.getPasswordHash(), user.getRole(), user.getCreatedAt(), user.isActive());
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
