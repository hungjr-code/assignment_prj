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
public class Lecturer extends User {
    private String fullName;
    private String specialization;
    private String qualification;
    private String phone;
    private String bio;

    public Lecturer(User user, String fullName, String specialization, String qualification, String phone, String bio) {
        super(user.getUserID(), user.getEmail(), user.getPasswordHash(), user.getRole(), user.getCreatedAt(), user.isActive());
        this.fullName = fullName;
        this.specialization = specialization;
        this.qualification = qualification;
        this.phone = phone;
        this.bio = bio;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getQualification() {
        return qualification;
    }

    public String getPhone() {
        return phone;
    }

    public String getBio() {
        return bio;
    }

}
